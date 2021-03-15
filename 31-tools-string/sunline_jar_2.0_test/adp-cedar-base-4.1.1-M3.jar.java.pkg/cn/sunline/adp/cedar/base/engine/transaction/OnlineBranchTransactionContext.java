// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineBranchTransactionContext.java

package cn.sunline.adp.cedar.base.engine.transaction;

import cn.sunline.adp.cedar.base.engine.service.ServiceRequest;
import cn.sunline.adp.cedar.base.util.SystemParams;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.transaction:
//            OnlineBranchTransactionController, ServiceTransactionMode

public class OnlineBranchTransactionContext
{

    public OnlineBranchTransactionContext(ServiceRequest request, OnlineBranchTransactionController serviceController)
    {
        this.request = request;
        isLocal = true;
        targetDCN = SystemParams.get().getDcnNo();
        hasDBTransaction = true;
        this.serviceController = serviceController;
    }

    public boolean isLocal()
    {
        return isLocal;
    }

    public void setLocal(boolean isLocal)
    {
        this.isLocal = isLocal;
    }

    public String getTargetDCN()
    {
        return targetDCN;
    }

    public void setTargetDCN(String targetDCN)
    {
        this.targetDCN = targetDCN;
    }

    public ServiceRequest getRequest()
    {
        return request;
    }

    public ServiceTransactionMode getServiceTransactionMode()
    {
        return serviceTransactionMode;
    }

    public void setServiceTransactionMode(ServiceTransactionMode serviceTransactionMode)
    {
        this.serviceTransactionMode = serviceTransactionMode;
    }

    public boolean isHasDBTransaction()
    {
        return hasDBTransaction;
    }

    public void setHasDBTransaction(boolean hasDBTransaction)
    {
        this.hasDBTransaction = hasDBTransaction;
    }

    public OnlineBranchTransactionController getServiceController()
    {
        return serviceController;
    }

    private final ServiceRequest request;
    private boolean isLocal;
    private String targetDCN;
    private boolean hasDBTransaction;
    final OnlineBranchTransactionController serviceController;
    private ServiceTransactionMode serviceTransactionMode;
}
