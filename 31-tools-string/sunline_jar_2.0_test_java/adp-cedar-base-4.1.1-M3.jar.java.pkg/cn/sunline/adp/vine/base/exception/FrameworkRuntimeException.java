// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FrameworkRuntimeException.java

package cn.sunline.adp.vine.base.exception;


public class FrameworkRuntimeException extends RuntimeException
{

    public FrameworkRuntimeException()
    {
    }

    public FrameworkRuntimeException(String message)
    {
        super(message);
    }

    public FrameworkRuntimeException(Throwable cause)
    {
        super(cause);
    }

    public FrameworkRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    private static final long serialVersionUID = 0xcfb74bebb271daadL;
}
