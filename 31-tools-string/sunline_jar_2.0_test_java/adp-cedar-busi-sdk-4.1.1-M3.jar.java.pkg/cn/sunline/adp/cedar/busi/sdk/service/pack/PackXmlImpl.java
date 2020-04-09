// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PackXmlImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.pack;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.util.XmlUtil;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.component.PackCompImpl;
import java.util.Map;

public class PackXmlImpl extends cn.sunline.adp.cedar.busi.sdk.component.PackCompImpl.PackXml
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Pack
{

    public PackXmlImpl()
    {
    }

    public String format(Map buffer)
        throws EdspServiceException
    {
        return XmlUtil.format(buffer);
    }

    public Map parse(String buffer)
        throws EdspServiceException
    {
        return XmlUtil.parse(buffer);
    }
}
