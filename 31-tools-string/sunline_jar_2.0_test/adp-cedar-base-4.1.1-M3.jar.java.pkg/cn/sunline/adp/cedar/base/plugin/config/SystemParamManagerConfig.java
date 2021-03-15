// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemParamManagerConfig.java

package cn.sunline.adp.cedar.base.plugin.config;

import cn.sunline.adp.metadata.model.annotation.Index;

public class SystemParamManagerConfig
{

    public SystemParamManagerConfig()
    {
    }

    public String getOptions()
    {
        return options;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getSystemCode()
    {
        return systemCode;
    }

    public void setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getSubSystemId()
    {
        return subSystemId;
    }

    public void setSubSystemId(String subSystemId)
    {
        this.subSystemId = subSystemId;
    }

    public boolean isDistributedSystem()
    {
        return distributedSystem;
    }

    public void setDistributedSystem(boolean distributedSystem)
    {
        this.distributedSystem = distributedSystem;
    }

    public String getDcnNo()
    {
        return dcnNo;
    }

    public void setDcnNo(String dcnNo)
    {
        this.dcnNo = dcnNo;
    }

    private String systemCode;
    private String subSystemId;
    private String tenantId;
    private String dcnNo;
    private boolean distributedSystem;
    private String options;
}
