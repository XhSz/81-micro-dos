// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AdpLogProperties.java

package cn.sunline.adp.boot.cedar.launch;


public class AdpLogProperties
{

    public AdpLogProperties()
    {
    }

    public String getVmid()
    {
        return vmid;
    }

    public void setVmid(String vmid)
    {
        this.vmid = vmid;
    }

    public String getHome()
    {
        return home;
    }

    public void setHome(String home)
    {
        this.home = home;
    }

    public String getLogHome()
    {
        return logHome;
    }

    public void setLogHome(String logHome)
    {
        this.logHome = logHome;
    }

    public String getLogPath()
    {
        return logPath;
    }

    public void setLogPath(String logPath)
    {
        this.logPath = logPath;
    }

    private String vmid;
    private String home;
    private String logHome;
    private String logPath;
}
