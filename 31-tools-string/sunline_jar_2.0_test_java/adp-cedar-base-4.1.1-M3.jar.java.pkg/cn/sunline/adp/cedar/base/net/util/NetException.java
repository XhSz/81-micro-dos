// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetException.java

package cn.sunline.adp.cedar.base.net.util;


public class NetException extends RuntimeException
{

    public NetException(String message)
    {
        super(message);
    }

    public NetException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NetException(Throwable cause)
    {
        super(cause);
    }

    private static final long serialVersionUID = 1L;
}
