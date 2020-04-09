// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginConfManager.java

package cn.sunline.adp.cedar.base.boot.plugin.impl;

import cn.sunline.adp.cedar.base.boot.IBootConstant;
import cn.sunline.adp.cedar.base.boot.plugin.model.*;
import cn.sunline.adp.cedar.base.boot.plugin.util.PluginUtil;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.impl:
//            XmlConfigManager

public class PluginConfManager
{

    private PluginConfManager()
    {
    }

    static void loadPluginConfs()
    {
        Map repeatCheck;
        InputStream is;
        PluginConf pluginConf;
        URL aurl[];
        int i;
        int j;
        loadGlobalConf();
        String pluginMode = System.getProperty("pluginMode");
        if(null == pluginMode || "".equals(pluginMode))
            pluginMode = "*.plugin.xml";
        URL resources[] = PluginUtil.findResources((new StringBuilder()).append("classpath*:/plugin/").append(pluginMode).toString());
        repeatCheck = new HashMap();
        is = null;
        pluginConf = null;
        aurl = resources;
        i = aurl.length;
        j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        URL resource = aurl[j];
        is = resource.openStream();
        pluginConf = (PluginConf)xcm.load(is);
        if(null != globalPluginConfigs)
            setGlobalConf(pluginConf);
        if(!pluginConf.isEnable())
        {
            PLUGIN_LOGGER.info("plugin [{}] is disabled", pluginConf.getId());
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            if(repeatCheck.containsKey(pluginConf.getId()))
            {
                URL r = (URL)repeatCheck.get(pluginConf.getId());
                throw PluginUtil.wrapThrow("plugin ID [{}] repeat: [{}] & [{}]", new Object[] {
                    pluginConf.getId(), r.getFile(), resource.getFile()
                });
            }
            repeatCheck.put(pluginConf.getId(), resource);
            PluginWrapper pluginWrapper = new PluginWrapper(pluginConf, resource);
            pluginsOrdered.add(pluginWrapper);
            is.close();
            continue; /* Loop/switch isn't completed */
        }
        catch(Exception ex)
        {
            PLUGIN_LOGGER.error("plugin [{}] load failed", resource.getFile(), ex);
            throw PluginUtil.wrapThrow("plugin [{}] load failed", ex, new Object[] {
                resource.getFile()
            });
        }
        j++;
          goto _L3
_L2:
        repeatCheck.clear();
        Collections.sort(pluginsOrdered, new Comparator() {

            public int compare(IOrder o1, IOrder o2)
            {
                return o1.getOrder() >= o2.getOrder() ? 1 : -1;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((IOrder)obj, (IOrder)obj1);
            }

        }
);
        dumpLog();
        break MISSING_BLOCK_LABEL_345;
        Throwable e;
        e;
        PLUGIN_LOGGER.error("plugin configure model load failed", e);
        throw PluginUtil.wrapThrow("plugin configure model load failed", e, new Object[0]);
    }

    public static ExtensionPointConf getExtensionPointConf(String pointId)
    {
        ExtensionPointConfWrapper extensionConfWrapper = (ExtensionPointConfWrapper)index_point.get(pointId);
        if(extensionConfWrapper == null)
            return null;
        else
            return extensionConfWrapper.getPoint();
    }

    private static String getPointId(PluginConf plugin, ExtensionPointConf point)
    {
        return (new StringBuilder()).append(plugin.getId()).append(".").append(point.getId()).toString();
    }

    protected static void initExtensionConf(PluginWrapper plugin)
    {
        if(plugin.getPluginConf().getExtensionPoints() == null)
            return;
        String pointId;
        ExtensionPointConfWrapper extensionConfWrapper;
        for(Iterator iterator = plugin.getPluginConf().getExtensionPoints().iterator(); iterator.hasNext(); index_point.put(pointId, extensionConfWrapper))
        {
            ExtensionPointConf point = (ExtensionPointConf)iterator.next();
            pointId = getPointId(plugin.getPluginConf(), point);
            if(index_point.get(pointId) != null)
                throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C055(), new Object[] {
                    plugin.getPluginConfFileName(), pointId
                });
            extensionConfWrapper = new ExtensionPointConfWrapper(pointId, plugin, point);
        }

        ExtensionConf extension;
        ExtensionPointConfWrapper pointConf;
        for(Iterator iterator1 = plugin.getPluginConf().getExtensions().iterator(); iterator1.hasNext(); pointConf.addExtensionConf(plugin, extension))
        {
            extension = (ExtensionConf)iterator1.next();
            String pointId = extension.getPointId();
            pointConf = (ExtensionPointConfWrapper)index_point.get(pointId);
            if(pointConf == null)
                throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C056(), new Object[] {
                    plugin.getPluginConfFileName(), extension.getId(), pointId
                });
        }

    }

    static List getPluginsOrdered()
    {
        return pluginsOrdered;
    }

    public static List getExtensionConfs(String pointId)
    {
        ExtensionPointConfWrapper point = (ExtensionPointConfWrapper)index_point.get(pointId);
        if(point == null)
            throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C057(), new Object[] {
                pointId
            });
        else
            return point.getExtensions();
    }

    public static ExtensionConf getExtensionConf(String pointId, String extensionId)
    {
        return ((ExtensionPointConfWrapper)index_point.get(pointId)).getExtensionConf(extensionId);
    }

    private static void dumpLog()
    {
        PLUGIN_LOGGER.info("plugin configure loading>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        PluginConf plugin;
        for(Iterator iterator = getPluginsOrdered().iterator(); iterator.hasNext(); PLUGIN_LOGGER.info("plugin [{}] [{}] [{}]", new Object[] {
    plugin.getId(), plugin.getDisplayName(), Integer.valueOf(plugin.getOrder())
}))
        {
            PluginWrapper pluginWrapper = (PluginWrapper)iterator.next();
            plugin = pluginWrapper.getPluginConf();
        }

        PLUGIN_LOGGER.info("plugin configure loaded>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void loadGlobalConf()
    {
        String pluginGlobalConfFile = null;
        InputStream is;
        Properties pp;
        Iterator iterator;
        Object key;
        try
        {
            pluginGlobalConfFile = System.getProperty("edsp.plugin.global.conf.path", "plugin-global-conf.properties");
            PLUGIN_LOGGER.info("global plugin configure file [{}]", pluginGlobalConfFile);
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pluginGlobalConfFile);
            if(is == null)
            {
                PLUGIN_LOGGER.info("global plugin configure file [{}] not found", pluginGlobalConfFile);
                return;
            }
        }
        catch(Throwable e)
        {
            throw PluginUtil.wrapThrow("global plugin configure [{}] load failed", new Object[] {
                pluginGlobalConfFile
            });
        }
        pp = new Properties();
        pp.load(is);
        for(iterator = pp.keySet().iterator(); iterator.hasNext(); globalPluginConfigs.put(key.toString(), pp.get(key)))
            key = iterator.next();

        is.close();
    }

    private static void setGlobalConf(PluginConf pluginConf)
    {
        String key = (new StringBuilder()).append(pluginConf.getId()).append(".enable").toString();
        if(globalPluginConfigs.containsKey(key))
            pluginConf.setEnable(Boolean.valueOf(globalPluginConfigs.get(key).toString()).booleanValue());
        key = (new StringBuilder()).append(pluginConf.getId()).append(".order").toString();
        if(globalPluginConfigs.containsKey(key))
            pluginConf.setOrder(Integer.valueOf(globalPluginConfigs.get(key).toString()).intValue());
        key = (new StringBuilder()).append(pluginConf.getId()).append(".failOnInitError").toString();
        if(globalPluginConfigs.containsKey(key))
            pluginConf.setFailOnInitError(Boolean.valueOf(globalPluginConfigs.get(key).toString()).booleanValue());
    }

    private static final Logger PLUGIN_LOGGER = LoggerFactory.getLogger(cn/sunline/adp/cedar/base/boot/plugin/impl/PluginConfManager);
    private static List pluginsOrdered = new ArrayList();
    private static Map globalPluginConfigs = new HashMap();
    private static Map index_point = new HashMap();
    private static XmlConfigManager xcm = new XmlConfigManager(new Class[] {
        cn/sunline/adp/cedar/base/boot/plugin/model/PluginConf
    });

}
