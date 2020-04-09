// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineBranchTransactionController.java

package cn.sunline.adp.cedar.base.engine.transaction;

import cn.sunline.adp.cedar.base.engine.service.ServiceTypeEnum;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.transaction:
//            ServiceTransactionMode

public class OnlineBranchTransactionController
{

    public OnlineBranchTransactionController()
    {
    }

    public String getCancelService()
    {
        return cancelService;
    }

    public String getConfirmService()
    {
        return confirmService;
    }

    public void setCancelService(String cancelService)
    {
        this.cancelService = cancelService;
    }

    public void setConfirmService(String confirmService)
    {
        this.confirmService = confirmService;
    }

    public ServiceTransactionMode getServiceTransactionMode()
    {
        return serviceTransactionMode;
    }

    public void setServiceTransactionMode(ServiceTransactionMode serviceTransactionMode)
    {
        this.serviceTransactionMode = serviceTransactionMode;
    }

    public ServiceTypeEnum getServiceType()
    {
        return serviceType;
    }

    public void setServiceType(ServiceTypeEnum serviceType)
    {
        this.serviceType = serviceType;
    }

    private String cancelService;
    private String confirmService;
    private ServiceTransactionMode serviceTransactionMode;
    private ServiceTypeEnum serviceType;
}
