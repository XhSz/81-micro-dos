// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Loader.java

package cn.sunline.adp.cedar.base.boot;


public interface Loader
{

    public abstract void info(String s);

    public abstract void error(String s, Throwable throwable);

    public abstract void warning(String s, Throwable throwable);
}
