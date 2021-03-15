// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedServiceInstanceBuilder.java

package cn.sunline.adp.cedar.base.discovery;


// Referenced classes of package cn.sunline.adp.cedar.base.discovery:
//            NamedServiceInstance, NamedServicePayload

public class NamedServiceInstanceBuilder
{

    NamedServiceInstanceBuilder(String name, String id, String address)
    {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public NamedServiceInstance build()
    {
        return new NamedServiceInstance(name, id, address, port, payload);
    }

    public NamedServiceInstanceBuilder port(Integer port)
    {
        this.port = port;
        return this;
    }

    public NamedServiceInstanceBuilder payload(NamedServicePayload payload)
    {
        this.payload = payload;
        return this;
    }

    private String name;
    private String id;
    private String address;
    private Integer port;
    private NamedServicePayload payload;
}
