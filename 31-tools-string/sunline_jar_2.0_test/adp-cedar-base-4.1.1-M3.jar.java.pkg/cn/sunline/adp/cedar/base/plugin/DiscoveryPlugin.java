// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DiscoveryPlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.discovery.NamedServiceDiscovery;
import cn.sunline.adp.cedar.base.discovery.NamedServiceDiscoveryManager;
import cn.sunline.adp.cedar.base.errors.CorePluginErrorDef;
import cn.sunline.adp.cedar.base.logging.*;
import cn.sunline.adp.cedar.base.plugin.discovery.DiscoveryServiceConfig;
import cn.sunline.adp.cedar.base.srv.ServerContants;
import cn.sunline.adp.cedar.base.srv.socket.common.SimpleThreadFactory;
import cn.sunline.adp.cedar.base.util.SystemParams;
import cn.sunline.adp.core.GlobalContext;
import cn.sunline.adp.core.util.SystemContext;
import cn.sunline.adp.dao.base.conn.DBConnectionManager;
import cn.sunline.adp.metadata.base.config.ConfigManager;
import cn.sunline.adp.metadata.base.config.ConfigManagerFactory;
import cn.sunline.adp.metadata.base.engine.EngineResourceManager;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.*;

public class DiscoveryPlugin extends PluginSupport
{
    private static class ScheduledDiscoveryService
    {

        public void start()
        {
            NamedServiceDiscovery discovery;
            for(Iterator it = discoveries.iterator(); it.hasNext(); discovery.start())
                discovery = (NamedServiceDiscovery)it.next();

            executor.scheduleAtFixedRate(new Runnable() {

                public void run()
                {
                    Log4j2Util.setPollThreadLogContext("discoveryService");
                    if(!cn.sunline.adp.core.GlobalContext.SystemStatus.STARTED.equals(GlobalContext.get().getStatus()))
                        return;
                    DiscoveryPlugin.POLL_LOGGER.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C010());
                    EdspCoreBeanUtil.getDBConnectionManager().checkAndReconnect();
                    EdspCoreBeanUtil.getDBConnectionManager().setApplicationModule((new StringBuilder()).append(SystemParams.get().getSystemId()).append("-").append(SystemContext.getVmid().substring(0, 3)).toString(), "heartbeat");
                    for(Iterator it = discoveries.iterator(); it.hasNext();)
                    {
                        NamedServiceDiscovery discovery = (NamedServiceDiscovery)it.next();
                        try
                        {
                            Log4j2Util.setPollThreadLogContext(discovery.getName());
                            discovery.heartbeat();
                        }
                        catch(Exception e)
                        {
                            DiscoveryPlugin.syslog.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C011(), e, new Object[] {
                                discovery.getClass().getSimpleName()
                            });
                        }
                    }

                    DiscoveryPlugin.POLL_LOGGER.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C013());
                    EdspCoreBeanUtil.getEngineResourceManager().clearThreadCache(false);
                    break MISSING_BLOCK_LABEL_259;
                    Exception ignore;
                    ignore;
                    DiscoveryPlugin.POLL_LOGGER.error(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C012(), new Object[] {
                        ignore.getMessage()
                    });
                    DiscoveryPlugin.POLL_LOGGER.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C013());
                    EdspCoreBeanUtil.getEngineResourceManager().clearThreadCache(false);
                    break MISSING_BLOCK_LABEL_259;
                    Exception exception;
                    exception;
                    DiscoveryPlugin.POLL_LOGGER.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C013());
                    EdspCoreBeanUtil.getEngineResourceManager().clearThreadCache(false);
                    throw exception;
                }

                final ScheduledDiscoveryService this$0;

                
                {
                    this.this$0 = ScheduledDiscoveryService.this;
                    super();
                }
            }
, 0L, heartbeat.intValue(), TimeUnit.SECONDS);
        }

        public void stop()
        {
            for(Iterator it = discoveries.iterator(); it.hasNext();)
            {
                NamedServiceDiscovery discovery = (NamedServiceDiscovery)it.next();
                try
                {
                    discovery.close();
                }
                catch(Exception e)
                {
                    DiscoveryPlugin.syslog.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C014(), e, new Object[] {
                        discovery.getClass().getSimpleName()
                    });
                }
            }

            executor.shutdown();
            try
            {
                if(!executor.awaitTermination(1L, TimeUnit.SECONDS))
                {
                    executor.shutdownNow();
                    if(!executor.awaitTermination(1L, TimeUnit.SECONDS))
                        System.err.println((new StringBuilder()).append(getClass().getSimpleName()).append(": ScheduledDiscoveryService did not terminate").toString());
                }
            }
            catch(InterruptedException ie)
            {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        private ScheduledExecutorService executor;
        private Integer heartbeat;
        private final List discoveries;
        public static final String DISCOVERY_SERVICE = "discoveryService";


        public ScheduledDiscoveryService(Integer heartbeat, List discoveries)
        {
            this.heartbeat = heartbeat;
            executor = Executors.newSingleThreadScheduledExecutor(new SimpleThreadFactory("Sunline-discoveryService"));
            this.discoveries = discoveries;
        }
    }


    public DiscoveryPlugin()
    {
    }

    public boolean initPlugin()
    {
        List discoveries;
        Iterator iterator;
        config = (DiscoveryServiceConfig)EdspCoreBeanUtil.getConfigManagerFactory().getDefaultConfigManager().getConfig(cn/sunline/adp/cedar/base/plugin/discovery/DiscoveryServiceConfig);
        if(config == null)
        {
            syslog.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C007());
            return false;
        }
        if(config.getDiscoveries() == null || config.getDiscoveries().isEmpty())
        {
            syslog.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C008());
            return false;
        }
        syslog.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C009());
        discoveries = new ArrayList();
        iterator = config.getDiscoveries().iterator();
_L2:
        cn.sunline.adp.cedar.base.plugin.discovery.DiscoveryServiceConfig.DiscoveryConfig item;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        item = (cn.sunline.adp.cedar.base.plugin.discovery.DiscoveryServiceConfig.DiscoveryConfig)iterator.next();
        NamedServiceDiscovery discovery;
        try
        {
            discovery = (NamedServiceDiscovery)ReflectionUtil.classForName(item.callback.trim()).newInstance();
        }
        catch(ReflectiveOperationException e)
        {
            throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E054(item.callback.trim(), e);
        }
        NamedServiceDiscoveryManager.addDiscovery(item.name, discovery);
        if(item.observer == null || !item.observer.booleanValue())
            discoveries.add(discovery);
        if(true) goto _L2; else goto _L1
_L1:
        NamedServiceDiscoveryManager.heartBeatInterval = config.getHeartBeatInterval();
        if(!discoveries.isEmpty())
            service = new ScheduledDiscoveryService(config.getHeartBeatInterval(), discoveries);
        return true;
    }

    public void startupPlugin()
    {
        if(service != null)
            service.start();
    }

    public void shutdownPlugin()
    {
        if(service != null)
            service.stop();
    }

    private static final SysLog syslog = SysLogUtil.getBootLogger();
    private static final SysLog POLL_LOGGER = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/plugin/DiscoveryPlugin);
    private DiscoveryServiceConfig config;
    private ScheduledDiscoveryService service;



}
