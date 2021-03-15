// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginWrapper.java

package cn.sunline.adp.cedar.base.boot.plugin.model;

import cn.sunline.adp.cedar.base.boot.plugin.IPlugin;
import cn.sunline.adp.cedar.base.boot.plugin.PluginContext;
import cn.sunline.adp.cedar.base.boot.plugin.util.PluginUtil;
import cn.sunline.adp.cedar.base.errors.CorePluginErrorDef;
import java.lang.reflect.Field;
import java.net.URL;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.model:
//            IOrder, PluginConf

public class PluginWrapper
    implements IOrder
{

    public PluginWrapper(PluginConf pluginConf, URL url)
        throws Exception
    {
        this.pluginConf = pluginConf;
        Class type = null;
        if(PluginUtil.isEmpty(pluginConf.getActivator()))
            throw PluginUtil.wrapThrow("plugin [{}] Activator is null", new Object[] {
                url.getFile()
            });
        type = PluginUtil.classForName(pluginConf.getActivator());
        if(!cn/sunline/adp/cedar/base/boot/plugin/IPlugin.isAssignableFrom(type))
        {
            throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E057_ERROR(), new Object[] {
                pluginConf.getId(), cn/sunline/adp/cedar/base/boot/plugin/IPlugin.getCanonicalName()
            });
        } else
        {
            PluginContext ctx = new PluginContext(pluginConf);
            pluginActivatorObj = (IPlugin)type.newInstance();
            Field field = cn/sunline/adp/cedar/base/boot/plugin/IPlugin.getDeclaredField("context");
            field.setAccessible(true);
            field.set(pluginActivatorObj, ctx);
            this.url = url;
            return;
        }
    }

    public IPlugin getPluginActivatorObj()
    {
        return pluginActivatorObj;
    }

    public PluginConf getPluginConf()
    {
        return pluginConf;
    }

    public int getOrder()
    {
        return pluginConf.getOrder();
    }

    public String getPluginConfFileName()
    {
        return url.getFile();
    }

    private PluginConf pluginConf;
    private URL url;
    private IPlugin pluginActivatorObj;
}
