// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;

public class LogUtil
{
    public static interface Log
    {

        public abstract void error(String s);

        public abstract void error(String s, Throwable throwable);

        public abstract void error(Throwable throwable);

        public abstract void info(String s);
    }

    public static class LogImpl
        implements Log
    {

        public void error(String msg)
        {
            LogUtil.log1.error(msg);
        }

        public void error(String message, Throwable t)
        {
            LogUtil.log1.error(message, t, new Object[0]);
        }

        public void error(Throwable t)
        {
            LogUtil.log1.error(t.getMessage(), t, new Object[0]);
        }

        public void info(String msg)
        {
            LogUtil.log1.info(msg);
        }

        public LogImpl()
        {
        }
    }


    public LogUtil()
    {
    }

    private static SysLog log1 = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/util/LogUtil);
    public static Log log = new LogImpl();


}
