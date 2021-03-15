// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcConnectionException.java

package cn.sunline.adp.cedar.base.net.rpc.exception;

import cn.sunline.adp.cedar.base.net.util.NetException;

public class RpcConnectionException extends NetException
{

    public RpcConnectionException(String message)
    {
        super(message);
    }

    public RpcConnectionException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RpcConnectionException(Throwable cause)
    {
        super(cause);
    }

    private static final long serialVersionUID = 0x7a9f77cb74c6cb1dL;
}
