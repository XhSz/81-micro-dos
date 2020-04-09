// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultLogLevelContext.java

package cn.sunline.adp.cedar.base.logging;

import cn.sunline.adp.cedar.base.logging.config.LogLevelAdapt;

// Referenced classes of package cn.sunline.adp.cedar.base.logging:
//            LogLevelContext

public class DefaultLogLevelContext
    implements LogLevelContext
{

    public DefaultLogLevelContext()
    {
        currentLogLevel = new ThreadLocal();
        currentLogType = new ThreadLocal();
    }

    public void setCurrentLogLevel(String type, String bizLevel)
    {
        currentLogType.set(type);
        currentLogLevel.set(bizLevel);
    }

    public String getCurrentLogLevel()
    {
        return (String)currentLogLevel.get();
    }

    public void setCurrentLogType(String type)
    {
        currentLogType.set(type);
    }

    public LogLevelAdapt getCurrentLogLevelAdapt()
    {
        return null;
    }

    private ThreadLocal currentLogLevel;
    private ThreadLocal currentLogType;
}
