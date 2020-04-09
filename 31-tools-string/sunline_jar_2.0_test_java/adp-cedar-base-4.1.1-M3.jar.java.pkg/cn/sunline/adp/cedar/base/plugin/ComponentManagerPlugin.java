// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentManagerPlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.component.ComponentManager;

public class ComponentManagerPlugin extends PluginSupport
{

    public ComponentManagerPlugin()
    {
    }

    public boolean initPlugin()
    {
        return true;
    }

    public void startupPlugin()
    {
        ComponentManager.get().init();
    }

    public void shutdownPlugin()
    {
    }

    private static ComponentManagerPlugin instance = new ComponentManagerPlugin();

}
