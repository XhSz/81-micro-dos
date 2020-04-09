// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceManagerPlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.engine.sequence.SequenceManager;
import cn.sunline.adp.cedar.base.engine.sequence.SequenceManagerServiceConfig;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.metadata.base.config.ConfigManager;
import cn.sunline.adp.metadata.base.config.ConfigManagerFactory;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;

public class SequenceManagerPlugin extends PluginSupport
{

    public SequenceManagerPlugin()
    {
    }

    public boolean initPlugin()
    {
        config = (SequenceManagerServiceConfig)EdspCoreBeanUtil.getConfigManagerFactory().getDefaultConfigManager().getConfig(cn/sunline/adp/cedar/base/engine/sequence/SequenceManagerServiceConfig);
        if(config == null)
        {
            log.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C037());
            return false;
        } else
        {
            return true;
        }
    }

    public void startupPlugin()
    {
        SequenceManager.get().init(config);
    }

    public void shutdownPlugin()
    {
        SequenceManager.get().collection();
    }

    private static final SysLog log = SysLogUtil.getBootLogger();
    private SequenceManagerServiceConfig config;

}
