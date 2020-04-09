// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IPlugin.java

package cn.sunline.adp.cedar.base.boot.plugin;


// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin:
//            PluginContext

public abstract class IPlugin
{

    public IPlugin()
    {
    }

    public abstract boolean initPlugin();

    public abstract void startupPlugin();

    public abstract void shutdownPlugin();

    protected PluginContext context;
}
