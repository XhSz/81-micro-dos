// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocalCacheUtil.java

package cn.sunline.adp.vine.base.util.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCacheUtil
{

    public LocalCacheUtil()
    {
    }

    public static Cache getCache(String group)
    {
        Cache cache = (Cache)cachePool.get(group);
        if(cache == null)
        {
            cache = CacheBuilder.newBuilder().maximumSize(0x7fffffffL).build();
            cachePool.put(group, cache);
        }
        return cache;
    }

    public static void removeCache(String group)
    {
        Cache cache = (Cache)cachePool.get(group);
        if(cache != null)
        {
            cachePool.remove(group);
            cache.invalidateAll();
        }
    }

    public static Map getCachePool()
    {
        return cachePool;
    }

    private static final Map cachePool = new ConcurrentHashMap();

}
