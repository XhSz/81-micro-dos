// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultDBPassSecurityImpl.java

package cn.sunline.adp.cedar.base.security;

import cn.sunline.adp.cedar.base.ConfigConst;
import cn.sunline.adp.cedar.base.exception.EdspServiceException;

// Referenced classes of package cn.sunline.adp.cedar.base.security:
//            DBPassSecurity, EncryptUtils

public class DefaultDBPassSecurityImpl
    implements DBPassSecurity
{

    public DefaultDBPassSecurityImpl()
    {
    }

    public String encrypt(String plainText)
        throws EdspServiceException
    {
        try
        {
            return EncryptUtils.encrypt(plainText);
        }
        catch(Exception e)
        {
            throw new EdspServiceException("0501", ConfigConst.DefaultDBPassSecurityImpl01, e);
        }
    }

    public String decrypt(String cipherText)
        throws EdspServiceException
    {
        try
        {
            return EncryptUtils.decrypt(cipherText);
        }
        catch(Exception e)
        {
            throw new EdspServiceException("0502", ConfigConst.DefaultDBPassSecurityImpl02, e);
        }
    }
}
