// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedServiceInstanceSerializer.java

package cn.sunline.adp.cedar.base.discovery;


// Referenced classes of package cn.sunline.adp.cedar.base.discovery:
//            NamedServicePayload

public interface NamedServiceInstanceSerializer
{

    public abstract byte[] serialize(NamedServicePayload namedservicepayload);

    public abstract NamedServicePayload deserialize(byte abyte0[]);
}
