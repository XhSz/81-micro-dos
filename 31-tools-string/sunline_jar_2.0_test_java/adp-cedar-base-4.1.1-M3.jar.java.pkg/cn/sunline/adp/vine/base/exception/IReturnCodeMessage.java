// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IReturnCodeMessage.java

package cn.sunline.adp.vine.base.exception;


public interface IReturnCodeMessage
{

    public abstract String getCode();

    public abstract String getMessage();

    public String getCodeNumber()
    {
        return getCode().substring(4);
    }
}
