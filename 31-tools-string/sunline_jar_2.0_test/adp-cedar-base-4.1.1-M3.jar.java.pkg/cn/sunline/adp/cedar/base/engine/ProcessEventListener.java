// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProcessEventListener.java

package cn.sunline.adp.cedar.base.engine;


public interface ProcessEventListener
{

    public abstract void beginProcess(String s, String s1);

    public abstract void endProcess(String s, String s1);

    public abstract void beginProcess(String s);

    public abstract void endProcess();

    public abstract void beginDataMappingProcess(String s);

    public abstract void endDataMappingProcess();
}
