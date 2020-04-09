// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PackJsonImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.pack;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.component.PackCompImpl;
import cn.sunline.adp.core.util.JsonUtil;
import java.util.Map;

public class PackJsonImpl extends cn.sunline.adp.cedar.busi.sdk.component.PackCompImpl.PackJson
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Pack
{

    public PackJsonImpl()
    {
    }

    public String format(Map buffer)
        throws EdspServiceException
    {
        return JsonUtil.formatPkg(buffer);
    }

    public Map parse(String buffer)
        throws EdspServiceException
    {
        return JsonUtil.parse(buffer);
    }
}
