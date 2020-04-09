// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemParams.java

package cn.sunline.adp.cedar.base.util;


public class SystemParams
{

    private SystemParams()
    {
        distributedSystem = false;
        isInit = false;
    }

    public static SystemParams get()
    {
        if(!instance.isInit)
        {
            instance.init();
            instance.isInit = true;
        }
        return instance;
    }

    private void init()
    {
        systemId = System.getProperty("edsp_system_id");
        tenantId = System.getProperty("edsp_tenant_id");
        subSystemId = System.getProperty("edsp_subSystem_id");
        dcnNo = System.getProperty("edsp_dcnNo");
        distributedSystem = "true".equals(System.getProperty("edsp_distributedSystem"));
    }

    public String getSystemId()
    {
        return systemId;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public String getSubSystemId()
    {
        return subSystemId;
    }

    public String getDcnNo()
    {
        return dcnNo;
    }

    public boolean isDistributedSystem()
    {
        return distributedSystem;
    }

    public static final String SPI_ID = "default";
    public static final String systemIdKey = "edsp_system_id";
    public static final String tenantIdKey = "edsp_tenant_id";
    public static final String subSystemIdKey = "edsp_subSystem_id";
    public static final String dcnNoKey = "edsp_dcnNo";
    public static final String edsp_distributedSystemKey = "edsp_distributedSystem";
    private String systemId;
    private String tenantId;
    private String subSystemId;
    private String dcnNo;
    private boolean distributedSystem;
    private boolean isInit;
    private static final SystemParams instance = new SystemParams();

}
