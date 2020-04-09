// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CachePlugin.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.PluginSupport;
import cn.sunline.adp.cedar.base.tmp.LocalTmpTableCaches;
import cn.sunline.adp.core.cache.*;

public class CachePlugin extends PluginSupport
{

    public CachePlugin()
    {
    }

    public boolean initPlugin()
    {
        cache = new CacheManager();
        addCacher("exprGlobal", new GlobalCache());
        addCacher(LocalTmpTableCaches.TMP_TABLE_CACHER, new LocalCache());
        return true;
    }

    public void shutdownPlugin()
    {
        if(cache != null)
            cache.clear();
    }

    public void startupPlugin()
    {
    }

    private CacheManager cache;
}
