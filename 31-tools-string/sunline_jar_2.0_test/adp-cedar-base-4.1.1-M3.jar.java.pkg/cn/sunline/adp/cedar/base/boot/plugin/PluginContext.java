// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginContext.java

package cn.sunline.adp.cedar.base.boot.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.model.PluginConf;

public class PluginContext
{

    public PluginContext(PluginConf confModel)
    {
        this.confModel = confModel;
    }

    public PluginConf getConfModel()
    {
        return confModel;
    }

    private PluginConf confModel;
}
