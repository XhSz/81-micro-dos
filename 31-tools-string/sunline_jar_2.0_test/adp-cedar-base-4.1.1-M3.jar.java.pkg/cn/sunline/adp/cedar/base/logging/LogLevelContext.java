// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogLevelContext.java

package cn.sunline.adp.cedar.base.logging;

import cn.sunline.adp.cedar.base.logging.config.LogLevelAdapt;

public interface LogLevelContext
{

    public abstract void setCurrentLogLevel(String s, String s1);

    public abstract String getCurrentLogLevel();

    public abstract void setCurrentLogType(String s);

    public abstract LogLevelAdapt getCurrentLogLevelAdapt();
}
