// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedServiceDiscoveryManager.java

package cn.sunline.adp.cedar.base.discovery;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.discovery:
//            NamedServiceDiscovery

public class NamedServiceDiscoveryManager
{

    public NamedServiceDiscoveryManager()
    {
    }

    public static boolean isActive(long currentTimeMillis, Long lastHeartBeat)
    {
        return currentTimeMillis - lastHeartBeat.longValue() <= (long)(3 * heartBeatInterval.intValue() * 1000);
    }

    public static NamedServiceDiscovery findDiscovery(String discoveryName)
    {
        NamedServiceDiscovery ret = (NamedServiceDiscovery)caches.get(discoveryName);
        if(ret == null)
            throw new RuntimeException(StringUtil.format(BaseConst.NamedServiceDiscoveryManager01, new Object[] {
                discoveryName
            }));
        else
            return ret;
    }

    public static Collection allDiscoveries()
    {
        return caches.values();
    }

    public static void addDiscovery(String discoveryName, NamedServiceDiscovery discovery)
    {
        caches.put(discoveryName, discovery);
    }

    public static void removeDiscovery(String discoveryName)
    {
        caches.remove(discoveryName);
    }

    public static Integer heartBeatInterval = Integer.valueOf(120);
    private static final Map caches = new ConcurrentHashMap();

}
