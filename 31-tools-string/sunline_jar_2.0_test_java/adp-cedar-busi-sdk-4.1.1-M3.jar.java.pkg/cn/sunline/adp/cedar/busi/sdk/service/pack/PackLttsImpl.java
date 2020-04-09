// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PackLttsImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.pack;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.util.LttsPkgUtil;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.component.PackCompImpl;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class PackLttsImpl extends cn.sunline.adp.cedar.busi.sdk.component.PackCompImpl.PackLtts
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Pack
{

    public PackLttsImpl()
    {
    }

    public String format(Map buffer)
        throws EdspServiceException
    {
        byte ret[] = LttsPkgUtil.format(buffer, getEncoding());
        try
        {
            return new String(ret, getEncoding());
        }
        catch(UnsupportedEncodingException e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E029(e);
        }
    }

    public Map parse(String buffer)
        throws EdspServiceException
    {
        return LttsPkgUtil.parse(buffer, getEncoding());
    }
}
