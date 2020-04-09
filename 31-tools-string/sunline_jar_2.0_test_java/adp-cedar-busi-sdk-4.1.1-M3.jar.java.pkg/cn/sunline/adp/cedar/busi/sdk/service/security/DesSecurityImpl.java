// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DesSecurityImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.security;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.component.SecurityCompImpl;

public class DesSecurityImpl extends cn.sunline.adp.cedar.busi.sdk.component.SecurityCompImpl.DesSecurity
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Security
{

    public DesSecurityImpl()
    {
    }

    public String encryptPin(String acctno, String pin)
        throws EdspServiceException
    {
        return pin;
    }

    public String decryptPin(String acctno, String pinBlock)
        throws EdspServiceException
    {
        return pinBlock;
    }

    public String cvvBuild(String cardNo, String expireDate, String svcCode)
        throws EdspServiceException
    {
        return null;
    }

    public Boolean macCheck(String pin, String mac, String lastsysid)
        throws EdspServiceException
    {
        return Boolean.valueOf(true);
    }

    public String translatePin(String fromAcctno, String toAcctno, String fromPinBlock)
        throws EdspServiceException
    {
        return fromPinBlock;
    }

    public String translatePin(String fromKeyName, String toKeyName, String fromAcctno, String toAcctno, String fromPinBlock)
        throws EdspServiceException
    {
        return fromPinBlock;
    }

    public Boolean cvvCheck(String cvv, String cardno, String serviceCode, String matdata)
        throws EdspServiceException
    {
        return Boolean.valueOf(true);
    }
}
