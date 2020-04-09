// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginManager.java

package cn.sunline.adp.cedar.base.boot.plugin.impl;

import cn.sunline.adp.cedar.base.boot.plugin.IPlugin;
import cn.sunline.adp.cedar.base.boot.plugin.model.PluginConf;
import cn.sunline.adp.cedar.base.boot.plugin.model.PluginWrapper;
import cn.sunline.adp.cedar.base.boot.plugin.util.PluginUtil;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.impl:
//            PluginConfManager

public class PluginManager
{

    private PluginManager()
    {
    }

    public static void initPluginServices()
    {
        PluginConfManager.loadPluginConfs();
        _initPluginServices();
    }

    private static void _initPluginServices()
    {
        BOOT_LOGGER.info("plugins initializing");
        plugins_enable.clear();
        List plugins = PluginConfManager.getPluginsOrdered();
        for(int i = 0; i < plugins.size();)
        {
            PluginWrapper pluginConfWrapper = (PluginWrapper)plugins.get(i);
            PluginConf pluginConf = pluginConfWrapper.getPluginConf();
            long start = System.currentTimeMillis();
            try
            {
                IPlugin plugin = pluginConfWrapper.getPluginActivatorObj();
                boolean isInitSuccess = plugin.initPlugin();
                if(isInitSuccess)
                {
                    PluginConfManager.initExtensionConf(pluginConfWrapper);
                    BOOT_LOGGER.info("plugin [{}] initialized: {} s", pluginConf.getDisplayName(), Double.valueOf((double)(System.currentTimeMillis() - start) / 1000D));
                    plugins_enable.add(pluginConfWrapper);
                }
                continue;
            }
            catch(Throwable e)
            {
                if(pluginConf.isFailOnInitError())
                {
                    BOOT_LOGGER.error("plugin [{}] initialization failed", pluginConf.getId(), e);
                    if(e instanceof RuntimeException)
                        throw (RuntimeException)e;
                    else
                        throw new IllegalStateException((new StringBuilder()).append("plugin initialization failed: id=[").append(pluginConf.getId()).append("]").append(e.getMessage()).toString(), e);
                }
                BOOT_LOGGER.info("plugin [{}] initialization failed: {}, and next...", pluginConf.getDisplayName(), e.getMessage());
                i++;
            }
        }

        BOOT_LOGGER.info("all plugins initialized");
    }

    public static void startAllPluginServices()
    {
        Iterator iterator = plugins_enable.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            PluginWrapper plugin = (PluginWrapper)iterator.next();
            BOOT_LOGGER.info("plugin [{}] starting...", plugin.getPluginConf().getDisplayName());
            try
            {
                plugin.getPluginActivatorObj().startupPlugin();
                BOOT_LOGGER.info("plugin [{}] started", plugin.getPluginConf().getDisplayName());
            }
            catch(Throwable e)
            {
                BOOT_LOGGER.error("plugin [{}] start failed", plugin.getPluginConf().getDisplayName(), e);
                throw new RuntimeException(PluginUtil.formatForLog("plugin [{}] start failed", new Object[] {
                    plugin.getPluginConf().getDisplayName()
                }), e);
            }
        } while(true);
        BOOT_LOGGER.info("all plugins started");
    }

    public static void shutdownAllPluginServices()
    {
        for(int i = plugins_enable.size() - 1; i >= 0; i--)
        {
            PluginWrapper plugin = (PluginWrapper)plugins_enable.get(i);
            BOOT_LOGGER.info("plugin [{}] stopping", plugin.getPluginConf().getDisplayName());
            try
            {
                plugin.getPluginActivatorObj().shutdownPlugin();
                BOOT_LOGGER.info("plugin [{}] stopped", plugin.getPluginConf().getDisplayName());
            }
            catch(Throwable e)
            {
                BOOT_LOGGER.error("plugin [{}] stop failed", plugin.getPluginConf().getDisplayName(), e);
            }
        }

        BOOT_LOGGER.info("all plugins stopped");
    }

    private static Logger BOOT_LOGGER = LoggerFactory.getLogger(cn/sunline/adp/cedar/base/boot/plugin/impl/PluginManager);
    private static final List plugins_enable = new ArrayList();

}
