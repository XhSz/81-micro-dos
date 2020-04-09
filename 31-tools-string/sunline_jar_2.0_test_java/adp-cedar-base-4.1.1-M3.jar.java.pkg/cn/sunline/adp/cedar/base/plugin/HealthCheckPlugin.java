// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HealthCheckPlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.healthcheck.ScheduledCheckerManager;
import cn.sunline.adp.cedar.base.healthcheck.ScheduledHealthChecker;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import java.util.Collection;
import java.util.Iterator;

public class HealthCheckPlugin extends PluginSupport
{

    public HealthCheckPlugin()
    {
    }

    public boolean initPlugin()
    {
        return true;
    }

    public void startupPlugin()
    {
        Collection checkers = ScheduledCheckerManager.get().getScheduledHealthCheckers();
        if(checkers == null || checkers.size() < 1)
        {
            syslog.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C053());
            return;
        }
        ScheduledHealthChecker scheduledChecker;
        for(Iterator iterator = checkers.iterator(); iterator.hasNext(); scheduledChecker.start())
            scheduledChecker = (ScheduledHealthChecker)iterator.next();

    }

    public void shutdownPlugin()
    {
        Collection checkers = ScheduledCheckerManager.get().getScheduledHealthCheckers();
        if(checkers == null || checkers.size() < 1)
            return;
        ScheduledHealthChecker scheduledChecker;
        for(Iterator iterator = checkers.iterator(); iterator.hasNext(); scheduledChecker.stop())
            scheduledChecker = (ScheduledHealthChecker)iterator.next();

    }

    private static final SysLog syslog = SysLogUtil.getBootLogger();

}
