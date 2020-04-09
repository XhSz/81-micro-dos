// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustomThreadFactory.java

package cn.sunline.adp.cedar.base.srv.socket.common;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.srv.ServerContants;
import cn.sunline.adp.dao.base.conn.DBConnectionManager;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory
    implements ThreadFactory
{

    public CustomThreadFactory(String name)
    {
        this(name, SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/srv/socket/common/CustomThreadFactory));
    }

    public CustomThreadFactory(String name, SysLog log)
    {
        threadNumber = new AtomicInteger(1);
        namePrefix = (new StringBuilder()).append("Sunline-Pool-").append(poolNumber.getAndIncrement()).toString();
        group = new ThreadGroup((new StringBuilder()).append(namePrefix).append("(").append(name).append(")").toString());
        SERVER_LOGGER = log;
    }

    public Thread newThread(Runnable r)
    {
        Thread t = __newThread(r);
        return t;
    }

    private Thread __newThread(Runnable r)
    {
        Thread t = new Thread(group, r, (new StringBuilder()).append(group.getName()).append("-thread-").append(threadNumber.getAndIncrement()).toString(), 0L) {

            public void run()
            {
                super.run();
                try
                {
                    EdspCoreBeanUtil.getDBConnectionManager().rollback();
                }
                catch(Throwable e)
                {
                    SERVER_LOGGER.error(BaseConst.CustomThreadFactory01, e, new Object[] {
                        e.getMessage()
                    });
                }
                try
                {
                    EdspCoreBeanUtil.getDBConnectionManager().close();
                }
                catch(Throwable e)
                {
                    SERVER_LOGGER.error(BaseConst.CustomThreadFactory02, e, new Object[] {
                        e.getMessage()
                    });
                }
                break MISSING_BLOCK_LABEL_166;
                Exception exception;
                exception;
                try
                {
                    EdspCoreBeanUtil.getDBConnectionManager().rollback();
                }
                catch(Throwable e)
                {
                    SERVER_LOGGER.error(BaseConst.CustomThreadFactory01, e, new Object[] {
                        e.getMessage()
                    });
                }
                try
                {
                    EdspCoreBeanUtil.getDBConnectionManager().close();
                }
                catch(Throwable e)
                {
                    SERVER_LOGGER.error(BaseConst.CustomThreadFactory02, e, new Object[] {
                        e.getMessage()
                    });
                }
                throw exception;
            }

            final CustomThreadFactory this$0;

            
            {
                this.this$0 = CustomThreadFactory.this;
                super(x0, x1, x2, x3);
            }
        }
;
        if(t.isDaemon())
            t.setDaemon(false);
        if(t.getPriority() != 5)
            t.setPriority(5);
        return t;
    }

    private final SysLog SERVER_LOGGER;
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber;
    private final String namePrefix;


}
