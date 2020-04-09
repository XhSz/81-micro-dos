// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultIcSecurityImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.ic;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.busi.sdk.com.suncard.security.Secretkey;
import cn.sunline.adp.cedar.busi.sdk.component.IcCompImpl;

public class DefaultIcSecurityImpl extends cn.sunline.adp.cedar.busi.sdk.component.IcCompImpl.DefaultIcSecurity
{

    public DefaultIcSecurityImpl()
    {
    }

    public String arqc(String cardno, String tag_5f34, String tag_9f26, String tag_9f02, String tag_9f03, String tag_9f1a, String tag_95, 
            String tag_5f2a, String tag_9a, String tag_9c, String tag_9f37, String tag_82, String tag_9f36, String tag_9f10)
        throws EdspServiceException
    {
        return Secretkey.arqc(getIp(), getPort().intValue(), cardno, tag_5f34, tag_9f26, tag_9f02, tag_9f03, tag_9f1a, tag_95, tag_5f2a, tag_9a, tag_9c, tag_9f37, tag_82, tag_9f36, tag_9f10);
    }

    public String arpc(String cardno, String tag_5f34, String tag_9f26, String tag_9f36, String arcnum)
        throws EdspServiceException
    {
        return Secretkey.arpc(getIp(), getPort().intValue(), cardno, tag_5f34, tag_9f26, tag_9f36, arcnum);
    }

    public String isscmac(String cardno, String tag_5f34, String tag_9f26, String tag_9f36, String apdu_head, String apdu_data)
        throws EdspServiceException
    {
        return Secretkey.isscmac(getIp(), getPort().intValue(), cardno, tag_5f34, tag_9f26, tag_9f36, apdu_head, apdu_data);
    }

    public String isscenc(String cardno, String tag_5f34, String tag_9f36, String opertp, String newpin, String oldpin)
        throws EdspServiceException
    {
        return Secretkey.isscenc(getIp(), getPort().intValue(), cardno, tag_5f34, tag_9f36, opertp, newpin, oldpin);
    }

    public String cvvauth(String cvvkey, String cardno, String cardsq, String inefdt, String servcd, String cvvnum)
        throws EdspServiceException
    {
        return Secretkey.cvvauth(getIp(), getPort().intValue(), cvvkey, cardno, cardsq, inefdt, servcd, cvvnum);
    }
}
