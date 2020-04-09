// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcResponse.java

package cn.sunline.adp.cedar.base.net.rpc.model;

import cn.sunline.adp.cedar.base.net.util.NetConstants;
import java.io.Serializable;

public class RpcResponse
    implements Serializable
{

    public RpcResponse()
    {
        retCode = "000";
        inner = (new StringBuilder()).append("serverId=").append(NetConstants.vmId).toString();
    }

    public String getInner()
    {
        return inner;
    }

    public void setInner(String inner)
    {
        this.inner = inner;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setRqId(String rqId)
    {
        this.rqId = rqId;
    }

    public String getRqId()
    {
        return rqId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getRetCode()
    {
        return retCode;
    }

    public String getRetMsg()
    {
        return retMsg;
    }

    public void setRetCode(String retCode)
    {
        this.retCode = retCode;
    }

    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }

    private static final long serialVersionUID = 1L;
    private String content;
    private String rqId;
    private String retCode;
    private String retMsg;
    private String status;
    private String inner;
}
