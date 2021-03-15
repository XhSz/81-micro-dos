// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParamRefreshPollThreadPlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.core.util.SpringUtils;
import cn.sunline.adp.metadata.loader.config.ParamReloadThreadConfig;
import cn.sunline.adp.metadata.loader.remote.parmreload.ParamReloadPollThread;
import cn.sunline.adp.metadata.starter.config.MetaDataConfigProperties;

public class ParamRefreshPollThreadPlugin extends PluginSupport
{

    public ParamRefreshPollThreadPlugin()
    {
    }

    public void startupPlugin()
    {
        if(enableParamReload && thread != null)
            thread.startup();
    }

    public void shutdownPlugin()
    {
        if(enableParamReload && thread != null)
            thread.shutdown();
    }

    public boolean initPlugin()
    {
        MetaDataConfigProperties config = (MetaDataConfigProperties)SpringUtils.getBean(cn/sunline/adp/metadata/starter/config/MetaDataConfigProperties);
        if(config == null || config.getParamReloadThreadConfig() == null)
        {
            log.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C033());
            return false;
        } else
        {
            log.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C034());
            enableParamReload = config.isEnableParamReload();
            ParamReloadThreadConfig paramReloader = config.getParamReloadThreadConfig();
            thread = new ParamReloadPollThread(paramReloader.getThreadId(), paramReloader.getThreadName(), paramReloader.getReloadDelay(), paramReloader.getReloadInterval());
            return true;
        }
    }

    private static final SysLog log = SysLogUtil.getBootLogger();
    private ParamReloadPollThread thread;
    private boolean enableParamReload;

}
