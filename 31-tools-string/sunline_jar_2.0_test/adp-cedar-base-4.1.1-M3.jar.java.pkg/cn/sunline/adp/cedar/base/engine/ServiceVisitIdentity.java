// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceVisitIdentity.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.constant.TspBaseConstantDef;
import cn.sunline.edsp.base.util.lang.StringUtil;

public class ServiceVisitIdentity
{

    public ServiceVisitIdentity(String serviceApp, String serviceCode, String serviceGroup, String serviceVersion)
    {
        this.serviceApp = serviceApp;
        this.serviceCode = serviceCode;
        this.serviceGroup = serviceGroup;
        this.serviceVersion = serviceVersion;
        visitIdentity = (new StringBuilder()).append(serviceApp).append(":").append(serviceCode).append(":").append(serviceGroup).append(":").append(serviceVersion).toString();
    }

    public ServiceVisitIdentity(String visitIdentity)
    {
        this.visitIdentity = visitIdentity;
        if(StringUtil.isEmpty(visitIdentity))
            throw new RuntimeException(String.format(cn.sunline.adp.cedar.base.constant.TspBaseConstantDef.TBConst.C022(), new Object[] {
                visitIdentity
            }));
        String accessIds[] = visitIdentity.split(":");
        if(accessIds.length != 4)
        {
            throw new RuntimeException(String.format(cn.sunline.adp.cedar.base.constant.TspBaseConstantDef.TBConst.C023(), new Object[] {
                visitIdentity
            }));
        } else
        {
            serviceApp = accessIds[0];
            serviceCode = accessIds[1];
            serviceGroup = accessIds[2];
            serviceVersion = accessIds[3];
            return;
        }
    }

    public String getServiceApp()
    {
        return serviceApp;
    }

    public String getServiceCode()
    {
        return serviceCode;
    }

    public String getServiceGroup()
    {
        return serviceGroup;
    }

    public String getServiceVersion()
    {
        return serviceVersion;
    }

    public String getVisitIdentity()
    {
        return visitIdentity;
    }

    private static final String ACCESSID_SPLIT = ":";
    private String serviceApp;
    private String serviceCode;
    private String serviceGroup;
    private String serviceVersion;
    private String visitIdentity;
}
