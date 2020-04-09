// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonNamedServiceInstanceSerializer.java

package cn.sunline.adp.cedar.base.discovery.impl;

import cn.sunline.adp.cedar.base.discovery.NamedServiceInstanceSerializer;
import cn.sunline.adp.cedar.base.discovery.NamedServicePayload;
import cn.sunline.adp.core.util.JsonUtil;

public class JsonNamedServiceInstanceSerializer
    implements NamedServiceInstanceSerializer
{

    public JsonNamedServiceInstanceSerializer(Class payloadClass)
    {
        this.payloadClass = payloadClass;
    }

    public byte[] serialize(NamedServicePayload instance)
    {
        return JsonUtil.formatEntity(instance).getBytes();
    }

    public NamedServicePayload deserialize(byte bytes[])
    {
        NamedServicePayload ret = (NamedServicePayload)JsonUtil.parseEntity(new String(bytes), payloadClass);
        return ret;
    }

    private final Class payloadClass;
}
