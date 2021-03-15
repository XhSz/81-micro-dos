// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcCommunicationException.java

package cn.sunline.adp.cedar.base.net.rpc.exception;

import cn.sunline.adp.cedar.base.net.util.NetException;

public class RpcCommunicationException extends NetException
{

    public RpcCommunicationException(String message)
    {
        super(message);
    }

    public RpcCommunicationException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RpcCommunicationException(Throwable cause)
    {
        super(cause);
    }

    private static final long serialVersionUID = 0x51aaa1b7371404cfL;
}
