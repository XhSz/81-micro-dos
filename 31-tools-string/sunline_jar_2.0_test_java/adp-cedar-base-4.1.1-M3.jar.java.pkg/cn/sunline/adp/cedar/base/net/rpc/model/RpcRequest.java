// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcRequest.java

package cn.sunline.adp.cedar.base.net.rpc.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.model:
//            SysHeader

public class RpcRequest
    implements Serializable
{

    public RpcRequest(String reqId)
    {
        sysHeader = new SysHeader();
        timeout = Long.valueOf(0L);
        sysHeader.setReqId(reqId);
    }

    public RpcRequest()
    {
        sysHeader = new SysHeader();
        timeout = Long.valueOf(0L);
        sysHeader.setReqId(newId());
    }

    public static String newId()
    {
        return Long.toString(INVOKE_ID.getAndIncrement());
    }

    public SysHeader getSysHeader()
    {
        return sysHeader;
    }

    public void setSysHeader(SysHeader sysHeader)
    {
        this.sysHeader = sysHeader;
    }

    public Long getTimeout()
    {
        return timeout;
    }

    public void setTimeout(Long timeout)
    {
        this.timeout = timeout;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    private static final long serialVersionUID = 1L;
    private String content;
    private SysHeader sysHeader;
    private static final AtomicLong INVOKE_ID = new AtomicLong(0L);
    private Long timeout;

}
