// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HealthCheckRegistry.java

package cn.sunline.adp.cedar.base.healthcheck;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import java.util.*;
import java.util.concurrent.*;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            HealthCheck

public class HealthCheckRegistry
{

    public HealthCheckRegistry()
    {
    }

    public void register(String name, HealthCheck healthCheck)
    {
        healthChecks.putIfAbsent(name, healthCheck);
    }

    public void unregister(String name)
    {
        healthChecks.remove(name);
    }

    public SortedSet getNames()
    {
        return Collections.unmodifiableSortedSet(new TreeSet(healthChecks.keySet()));
    }

    public HealthCheck.Result runHealthCheck(String name)
        throws NoSuchElementException
    {
        HealthCheck healthCheck = (HealthCheck)healthChecks.get(name);
        if(healthCheck == null)
            throw new NoSuchElementException((new StringBuilder()).append("No health check named ").append(name).append(" exists").toString());
        else
            return healthCheck.execute();
    }

    public SortedMap runHealthChecks()
    {
        SortedMap results = new TreeMap();
        java.util.Map.Entry entry;
        HealthCheck.Result result;
        for(Iterator iterator = healthChecks.entrySet().iterator(); iterator.hasNext(); results.put(entry.getKey(), result))
        {
            entry = (java.util.Map.Entry)iterator.next();
            result = ((HealthCheck)entry.getValue()).execute();
        }

        return Collections.unmodifiableSortedMap(results);
    }

    public SortedMap runHealthChecks(ExecutorService executor)
    {
        Map futures = new HashMap();
        final java.util.Map.Entry entry;
        for(Iterator iterator = healthChecks.entrySet().iterator(); iterator.hasNext(); futures.put(entry.getKey(), executor.submit(new Callable() {

        public HealthCheck.Result call()
            throws Exception
        {
            return ((HealthCheck)entry.getValue()).execute();
        }

        public volatile Object call()
            throws Exception
        {
            return call();
        }

        final java.util.Map.Entry val$entry;
        final HealthCheckRegistry this$0;

            
            {
                this.this$0 = HealthCheckRegistry.this;
                entry = entry1;
                super();
            }
    }
)))
            entry = (java.util.Map.Entry)iterator.next();

        SortedMap results = new TreeMap();
        for(Iterator iterator1 = futures.entrySet().iterator(); iterator1.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            try
            {
                results.put(entry.getKey(), ((Future)entry.getValue()).get());
            }
            catch(Exception e)
            {
                LOGGER.warn("Error executing health check {}", new Object[] {
                    entry.getKey(), e
                });
            }
        }

        return Collections.unmodifiableSortedMap(results);
    }

    private static final SysLog LOGGER = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/healthcheck/HealthCheckRegistry);
    private final ConcurrentMap healthChecks = new ConcurrentHashMap();

}
