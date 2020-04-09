// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcInterfaceMainImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl;
import cn.sunline.adp.metadata.base.dao.CommonDao;
import cn.sunline.adp.metadata.base.dao.CommonDaoFactory;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import java.util.HashMap;
import java.util.Map;

public class AppcInterfaceMainImpl extends cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl.AppcInterfaceMain
{

    public AppcInterfaceMainImpl()
    {
    }

    public String call(String sendBuffer)
        throws EdspServiceException
    {
        Map param = new HashMap();
        param.put("inData", sendBuffer);
        param.put("debug", Integer.valueOf(0));
        EdspCoreBeanUtil.getCommonDaoFactory().getInstance().querySqlList("tran.callV5Trans", param);
        return (String)param.get("outData");
    }

    public String call(String sendBuffer, Map properties)
        throws EdspServiceException
    {
        return call(sendBuffer);
    }
}
