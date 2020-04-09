// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginSupport.java

package cn.sunline.adp.cedar.base.boot.plugin;

import cn.sunline.adp.cedar.base.bean.ApplicationBeanResources;
import cn.sunline.adp.core.cache.CacheManager;
import cn.sunline.adp.core.cache.Cacher;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin:
//            IPlugin

public class PluginSupport extends IPlugin
{

    public PluginSupport()
    {
    }

    public boolean initPlugin()
    {
        return true;
    }

    public void startupPlugin()
    {
    }

    public void shutdownPlugin()
    {
    }

    public final void addCacher(String cacherName, Cacher cacher)
    {
        CacheManager.addCacher(cacherName, cacher);
    }

    public final Cacher getCacher(String cacherName)
    {
        return CacheManager.getCacher(cacherName);
    }

    public final void addBeanMapping(Class intfClass, Class implClass)
    {
        ApplicationBeanResources.getInstance().putBeanMapping(intfClass, implClass);
    }

    public final Object getBean(Class intfClass)
    {
        return ApplicationBeanResources.getInstance().getBean(intfClass);
    }
}
