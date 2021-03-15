// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CedarMain.java

package cn.sunline.adp.boot.cedar;

import cn.sunline.adp.boot.cedar.launch.AdpLogProperties;
import cn.sunline.adp.boot.cedar.launch.IDERunUtil;
import cn.sunline.adp.boot.cedar.launch.IDERunner;
import cn.sunline.adp.core.GlobalContext;
import cn.sunline.edsp.base.factories.FactoriesLoader;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.io.PrintStream;
import org.springframework.boot.SpringApplication;
import org.springframework.util.Assert;

public class CedarMain
{

    public CedarMain()
    {
    }

    public static void setSystemLogConfig(AdpLogProperties adpLogProperties)
    {
        if(System.getProperty("ltts.vmid") == null)
            System.setProperty("ltts.vmid", adpLogProperties.getVmid());
        if(System.getProperty("ltts.log.home") == null)
            System.setProperty("ltts.log.home", adpLogProperties.getLogHome());
        if(System.getProperty("log4j.configurationFile") == null)
            System.setProperty("log4j.configurationFile", adpLogProperties.getLogPath());
    }

    public static void main(AdpLogProperties adpLogProperties, String args[])
    {
        setSystemLogConfig(adpLogProperties);
        main(args);
    }

    public static void main(String args[])
    {
        try
        {
            checkLogParam();
            SpringApplication application = new SpringApplication(new Class[] {
                cn/sunline/adp/boot/cedar/CedarMain
            });
            application.run(args);
        }
        catch(Exception e)
        {
            System.err.println("Service Started Fail!");
            GlobalContext.get().setStatus(cn.sunline.adp.core.GlobalContext.SystemStatus.FAILED);
            throw new RuntimeException("Service Startup Failure", e);
        }
        String ideRunType = System.getProperty("ideRunTypeKey");
        if(!StringUtil.isBlank(ideRunType))
        {
            IDERunner runner = (IDERunner)FactoriesLoader.getFactoryById(cn/sunline/adp/boot/cedar/launch/IDERunner, ideRunType);
            runner.process(args);
        }
        System.out.println("Service Started Success!");
    }

    private static void checkLogParam()
    {
        Assert.notNull(System.getProperty("ltts.vmid"), "ltts.vmid must not be null");
        Assert.notNull(System.getProperty("ltts.log.home"), "ltts.log.home must not be null");
    }

    private static final String LTTS_VMID = "ltts.vmid";
    private static final String LTTS_LOG_HOME = "ltts.log.home";
    private static final String LTTS_LOG_PATH = "log4j.configurationFile";
}
