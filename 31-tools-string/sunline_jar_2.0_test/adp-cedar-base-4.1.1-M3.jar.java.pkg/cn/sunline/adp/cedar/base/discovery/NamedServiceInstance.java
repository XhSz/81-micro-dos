// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedServiceInstance.java

package cn.sunline.adp.cedar.base.discovery;

import cn.sunline.adp.core.util.SystemContext;

// Referenced classes of package cn.sunline.adp.cedar.base.discovery:
//            NamedServiceInstanceBuilder, NamedServicePayload

public class NamedServiceInstance
{

    public static NamedServiceInstanceBuilder builder(String name)
    {
        return new NamedServiceInstanceBuilder(name, buildFullId(), SystemContext.getIp());
    }

    public static String buildFullId()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(SystemContext.getIp()).append("#");
        sb.append(SystemContext.getUserName()).append("#");
        sb.append(SystemContext.getVmid());
        return sb.toString();
    }

    public NamedServiceInstance()
    {
    }

    public NamedServiceInstance(String name, String id, String address, Integer port, NamedServicePayload payload)
    {
        this.name = name;
        this.id = id;
        this.address = address;
        this.port = port;
        this.payload = payload;
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public String getAddress()
    {
        return address;
    }

    public Integer getPort()
    {
        return port;
    }

    public NamedServicePayload getPayload()
    {
        return payload;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public void setPayload(NamedServicePayload payload)
    {
        this.payload = payload;
    }

    private String name;
    private String id;
    private String address;
    private Integer port;
    private NamedServicePayload payload;
}
