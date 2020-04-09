// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PerformancePlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.plugin.config.PerformanceConfig;
import cn.sunline.adp.core.profile.ProfileSwitcher;
import cn.sunline.adp.metadata.base.config.ConfigManager;
import cn.sunline.adp.metadata.base.config.ConfigManagerFactory;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;

public class PerformancePlugin extends PluginSupport
{

    public PerformancePlugin()
    {
    }

    public boolean initPlugin()
    {
        PerformanceConfig config = (PerformanceConfig)EdspCoreBeanUtil.getConfigManagerFactory().getDefaultConfigManager().getConfig(cn/sunline/adp/cedar/base/plugin/config/PerformanceConfig);
        if(config == null)
        {
            log.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C035());
            return false;
        }
        log.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C036(), new Object[] {
            Boolean.valueOf(config.getEnabled())
        });
        if(config.getEnabled())
            ProfileSwitcher.globalProfileLogEnabled = true;
        return true;
    }

    private static final SysLog log = SysLogUtil.getBootLogger();

}
