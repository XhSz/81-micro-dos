// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SharedHealthCheckRegistries.java

package cn.sunline.adp.cedar.base.healthcheck;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            HealthCheckRegistry

public class SharedHealthCheckRegistries
{

    private SharedHealthCheckRegistries()
    {
    }

    public static void clear()
    {
        REGISTRIES.clear();
    }

    public static Set names()
    {
        return REGISTRIES.keySet();
    }

    public static void remove(String key)
    {
        REGISTRIES.remove(key);
    }

    public static HealthCheckRegistry add(String name, HealthCheckRegistry registry)
    {
        return (HealthCheckRegistry)REGISTRIES.putIfAbsent(name, registry);
    }

    public static HealthCheckRegistry getOrCreate(String name)
    {
        HealthCheckRegistry existing = (HealthCheckRegistry)REGISTRIES.get(name);
        if(existing == null)
        {
            HealthCheckRegistry created = new HealthCheckRegistry();
            HealthCheckRegistry raced = add(name, created);
            if(raced == null)
                return created;
            else
                return raced;
        } else
        {
            return existing;
        }
    }

    private static final ConcurrentMap REGISTRIES = new ConcurrentHashMap();

}
