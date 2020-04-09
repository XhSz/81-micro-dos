// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractFuture.java

package cn.sunline.adp.cedar.base.net.comm;

import cn.sunline.adp.cedar.base.constant.CommonNetConstantDef;
import cn.sunline.adp.cedar.base.net.rpc.exception.RpcConnectionException;
import cn.sunline.adp.cedar.base.net.rpc.exception.RpcTimeoutException;
import cn.sunline.adp.cedar.base.net.util.JsonUtil;
import cn.sunline.adp.cedar.base.net.util.NetExceptionUtil;
import java.util.Observable;
import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractFuture extends Observable
    implements Future
{

    public AbstractFuture(String reqId)
    {
        cdl = null;
        cancel = false;
        this.reqId = reqId;
        cdl = new CountDownLatch(1);
        startTime = System.currentTimeMillis();
    }

    public String getRequestId()
    {
        return reqId;
    }

    public boolean cancel(boolean mayInterruptIfRunning)
    {
        cancel = true;
        cdl.countDown();
        setChanged();
        notifyObservers("cancel");
        return true;
    }

    public boolean isCancelled()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isDone()
    {
        return resObj != null;
    }

    public void done(Object resObj)
    {
        if(resObj == null)
        {
            throw NetExceptionUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C001(), new String[0]);
        } else
        {
            this.resObj = resObj;
            cdl.countDown();
            setChanged();
            notifyObservers("done");
            endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;
            logger.debug(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C002(), reqId, Long.valueOf(responseTime));
            logger.info((new StringBuilder()).append("CRES[").append(JsonUtil.toJson(resObj)).append("]").toString());
            return;
        }
    }

    public Object get()
        throws InterruptedException, ExecutionException
    {
        cdl.await();
        if(cancel)
            throw new RpcConnectionException(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C003());
        if(resObj == null)
        {
            endTime = System.currentTimeMillis();
            throw new RpcTimeoutException(String.format(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C004(), new Object[] {
                Long.valueOf(endTime - startTime)
            }));
        } else
        {
            return resObj;
        }
    }

    public Object get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException
    {
        cdl.await(timeout, unit);
        if(resObj == null)
        {
            endTime = System.currentTimeMillis();
            throw new RpcTimeoutException(String.format(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C004(), new Object[] {
                Long.valueOf(endTime - startTime)
            }));
        } else
        {
            return resObj;
        }
    }

    private static final Logger logger = LogManager.getLogger(cn/sunline/adp/cedar/base/net/comm/AbstractFuture);
    String reqId;
    private volatile Object resObj;
    CountDownLatch cdl;
    private long startTime;
    private long endTime;
    private volatile boolean cancel;
    public static final String notify_done_event = "done";
    public static final String notify_cancel_event = "cancel";

}
