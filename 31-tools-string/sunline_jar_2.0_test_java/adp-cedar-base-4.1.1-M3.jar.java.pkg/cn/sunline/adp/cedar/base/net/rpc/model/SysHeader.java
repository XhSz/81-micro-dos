// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SysHeader.java

package cn.sunline.adp.cedar.base.net.rpc.model;

import cn.sunline.adp.cedar.base.net.util.NetConstants;
import java.io.Serializable;

public class SysHeader
    implements Serializable
{

    public SysHeader()
    {
        version = NetConstants.version;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getReqId()
    {
        return reqId;
    }

    public void setReqId(String reqId)
    {
        this.reqId = reqId;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    private static final long serialVersionUID = 0xb08953e623b9b349L;
    private String clientId;
    private String reqId;
    private String version;
}
