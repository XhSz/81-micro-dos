// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceRequestHeader.java

package cn.sunline.adp.cedar.base.engine.service;

import cn.sunline.adp.cedar.base.StandardHeaderData;
import cn.sunline.adp.cedar.base.engine.HeaderDataConstants;
import cn.sunline.adp.cedar.base.engine.ServiceVisitIdentity;

public class ServiceRequestHeader extends cn.sunline.adp.cedar.base.StandardHeaderData.Request
{

    public ServiceRequestHeader()
    {
    }

    public ServiceVisitIdentity getServiceVisitIdentity()
    {
        return serviceVisitIdentity;
    }

    public void setServiceVisitIdentity(ServiceVisitIdentity serviceVisitIdentity)
    {
        this.serviceVisitIdentity = serviceVisitIdentity;
        setString(HeaderDataConstants.SERVICE_CODE, serviceVisitIdentity.getServiceCode());
        setString(HeaderDataConstants.SERVICE_GROUP_CODE, serviceVisitIdentity.getServiceGroup());
        setString(HeaderDataConstants.SERVICE_VERSION, serviceVisitIdentity.getServiceVersion());
    }

    private ServiceVisitIdentity serviceVisitIdentity;
}
