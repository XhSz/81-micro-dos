// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcResponseStatus.java

package cn.sunline.adp.cedar.base.net.rpc.model;

import java.io.Serializable;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.model:
//            ResponseStatus

public class RpcResponseStatus
    implements Serializable
{

    public RpcResponseStatus()
    {
    }

    public RpcResponseStatus(String rqId, ResponseStatus status)
    {
        this.rqId = rqId;
        this.status = status;
    }

    public String getRqId()
    {
        return rqId;
    }

    public void setRqId(String rqId)
    {
        this.rqId = rqId;
    }

    public ResponseStatus getStatus()
    {
        return status;
    }

    public void setStatus(ResponseStatus status)
    {
        this.status = status;
    }

    private static final long serialVersionUID = 1L;
    public static final String HEAD_Flag = "#";
    private String rqId;
    private ResponseStatus status;
}
