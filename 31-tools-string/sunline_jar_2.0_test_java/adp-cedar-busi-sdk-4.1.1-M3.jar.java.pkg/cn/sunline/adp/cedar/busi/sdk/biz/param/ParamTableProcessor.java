// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParamTableProcessor.java

package cn.sunline.adp.cedar.busi.sdk.biz.param;


public interface ParamTableProcessor
{

    public abstract void before(String s, Object obj);

    public abstract void after(String s, Object obj);
}
