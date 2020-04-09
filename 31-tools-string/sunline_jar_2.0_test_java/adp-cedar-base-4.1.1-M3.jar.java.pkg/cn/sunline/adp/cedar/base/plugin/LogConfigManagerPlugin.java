// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogConfigManagerPlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.logging.*;
import cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig;
import cn.sunline.adp.metadata.base.config.ConfigManager;
import cn.sunline.adp.metadata.base.config.ConfigManagerFactory;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;

public class LogConfigManagerPlugin extends PluginSupport
{

    public LogConfigManagerPlugin()
    {
    }

    public boolean initPlugin()
    {
        try
        {
            LogConfigManagerConfig config = (LogConfigManagerConfig)EdspCoreBeanUtil.getConfigManagerFactory().getDefaultConfigManager().getConfig(cn/sunline/adp/cedar/base/logging/config/LogConfigManagerConfig);
            if(config != null)
            {
                log.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C020());
                LogConfigManager.get().init(config);
            }
        }
        catch(Exception e)
        {
            throw ExceptionUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C054(), e);
        }
        return true;
    }

    private static final SysLog log = SysLogUtil.getBootLogger();

}
