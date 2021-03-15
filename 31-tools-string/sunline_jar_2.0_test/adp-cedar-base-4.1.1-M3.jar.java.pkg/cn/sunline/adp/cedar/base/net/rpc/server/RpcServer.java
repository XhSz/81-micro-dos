// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcServer.java

package cn.sunline.adp.cedar.base.net.rpc.server;

import cn.sunline.adp.cedar.base.constant.CommonNetConstantDef;
import cn.sunline.adp.cedar.base.net.comm.AbstractServer;
import cn.sunline.adp.cedar.base.net.util.NetException;
import cn.sunline.adp.cedar.base.net.util.NetThreadFactory;
import io.netty.channel.ChannelInitializer;
import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.server:
//            RpcNettyChannelInitializer, RpcHandler

public class RpcServer extends AbstractServer
{

    private RpcServer(int port)
    {
        super(port);
        handlePool = null;
    }

    public static RpcServer create(int port)
    {
        return new RpcServer(port);
    }

    public RpcServer initHandlePool(int threads)
    {
        return initHandlePool(threads, "Net-Handle");
    }

    public RpcServer setHandler(RpcHandler handler)
    {
        this.handler = handler;
        return this;
    }

    public void start()
        throws InterruptedException
    {
        if(handler == null)
        {
            throw new NetException(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C034());
        } else
        {
            super.start();
            return;
        }
    }

    public RpcServer initHandlePool(int threads, String threadName)
    {
        this.threadName = threadName;
        handlePool = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue(), new NetThreadFactory(threadName), new java.util.concurrent.ThreadPoolExecutor.AbortPolicy()) {

            protected void beforeExecute(Thread t, Runnable r)
            {
                RpcServer.logger.debug((new StringBuilder()).append("beforeExecute").append(Thread.currentThread().getName()).append(" ").append(System.currentTimeMillis()).toString());
                super.beforeExecute(t, r);
            }

            protected void afterExecute(Runnable r, Throwable t)
            {
                RpcServer.logger.debug((new StringBuilder()).append("afterExecute").append(Thread.currentThread().getName()).append(" ").append(System.currentTimeMillis()).toString());
                super.afterExecute(r, t);
            }

            final RpcServer this$0;

            
            {
                this.this$0 = RpcServer.this;
                super(x0, x1, x2, x3, x4, x5, x6);
            }
        }
;
        return this;
    }

    protected ThreadPoolExecutor getHandlePool()
    {
        return handlePool;
    }

    protected ChannelInitializer getNettyChannelInitializer()
    {
        if(channelInitializer == null)
            channelInitializer = new RpcNettyChannelInitializer(this);
        return channelInitializer;
    }

    public RpcHandler getNetHandler()
    {
        return handler;
    }

    public Integer getCurrentExecuteJobCount()
    {
        return Integer.valueOf(handlePool.getActiveCount());
    }

    public String getThreadName()
    {
        return threadName;
    }

    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }

    private static final Logger logger = LogManager.getLogger(cn/sunline/adp/cedar/base/net/rpc/server/RpcServer);
    private RpcHandler handler;
    private RpcNettyChannelInitializer channelInitializer;
    private String threadName;
    private ThreadPoolExecutor handlePool;


}
