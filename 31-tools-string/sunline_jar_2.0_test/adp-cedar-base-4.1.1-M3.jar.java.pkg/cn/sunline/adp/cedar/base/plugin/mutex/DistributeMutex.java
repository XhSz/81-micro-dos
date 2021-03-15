// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributeMutex.java

package cn.sunline.adp.cedar.base.plugin.mutex;


public interface DistributeMutex
{

    public abstract void init();

    public abstract void acquire()
        throws InterruptedException;

    public abstract void release();

    public abstract boolean attempt(long l);
}
