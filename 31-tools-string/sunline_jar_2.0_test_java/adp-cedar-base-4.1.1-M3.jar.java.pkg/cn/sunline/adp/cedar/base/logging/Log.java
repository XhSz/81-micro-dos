// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log.java

package cn.sunline.adp.cedar.base.logging;


public interface Log
{

    public abstract void debug(Object obj, Throwable throwable);

    public abstract void debug(Object obj);

    public abstract void error(Object obj, Throwable throwable);

    public abstract void error(Object obj);

    public abstract void fatal(Object obj, Throwable throwable);

    public abstract void fatal(Object obj);

    public abstract void info(Object obj, Throwable throwable);

    public abstract void info(Object obj);

    public abstract boolean isDebugEnabled();

    public abstract boolean isWarnEnabled();

    public abstract boolean isErrorEnabled();

    public abstract boolean isFatalEnabled();

    public abstract boolean isInfoEnabled();

    public abstract void warn(Object obj, Throwable throwable);

    public abstract void warn(Object obj);

    public abstract void parm(Object obj);

    public abstract boolean isParmEnabled();

    public abstract void method(Object obj);

    public abstract boolean isMethodEnabled();

    public abstract void techMethod(Object obj);

    public abstract boolean isTechMethodEnabled();

    public abstract void dataArea(Object obj);

    public abstract boolean isDataAreaEnabled();

    public abstract void sql(Object obj);

    public abstract void sqlInfo(Object obj);

    public abstract void service(Object obj);

    public abstract boolean isSqlEnabled();

    public abstract boolean isSqlInfoEnabled();

    public abstract boolean isServiceEnabled();

    public abstract void profile(Object obj);

    public abstract boolean isProfileEnabled();
}
