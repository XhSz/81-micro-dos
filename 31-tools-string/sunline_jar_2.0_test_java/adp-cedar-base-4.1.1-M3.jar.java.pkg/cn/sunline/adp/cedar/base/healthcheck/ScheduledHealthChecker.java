// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScheduledHealthChecker.java

package cn.sunline.adp.cedar.base.healthcheck;

import cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef;
import cn.sunline.adp.cedar.base.logging.*;
import cn.sunline.adp.cedar.base.srv.ServerContants;
import cn.sunline.adp.cedar.base.srv.socket.common.SimpleThreadFactory;
import java.util.concurrent.*;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            HealthCheckRegistry, SharedHealthCheckRegistries

public class ScheduledHealthChecker
{

    public ScheduledHealthChecker(String name, long checkInterval)
    {
        this.name = name;
        this.checkInterval = checkInterval;
        executor = Executors.newSingleThreadScheduledExecutor(new SimpleThreadFactory((new StringBuilder()).append("Sunline-HealthCheck-").append(name).toString()));
    }

    public void start()
    {
        executor.scheduleAtFixedRate(new Runnable() {

            public void run()
            {
                HealthCheckRegistry registry;
                Log4j2Util.setPollThreadLogContext("healCheck");
                registry = SharedHealthCheckRegistries.getOrCreate(name);
                if(registry == null)
                    return;
                java.util.SortedMap sortedmap;
                try
                {
                    sortedmap = registry.runHealthChecks();
                }
                catch(RuntimeException e)
                {
                    ScheduledHealthChecker.syslog.error(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C011(), e, new Object[0]);
                }
                return;
            }

            final ScheduledHealthChecker this$0;

            
            {
                this.this$0 = ScheduledHealthChecker.this;
                super();
            }
        }
, checkInterval, checkInterval, TimeUnit.SECONDS);
    }

    public void stop()
    {
        executor.shutdown();
        try
        {
            if(!executor.awaitTermination(1L, TimeUnit.SECONDS))
            {
                executor.shutdownNow();
                if(!executor.awaitTermination(1L, TimeUnit.SECONDS))
                    syslog.error((new StringBuilder()).append(getClass().getSimpleName()).append(": ScheduledHealthChecker did not terminate").toString());
            }
        }
        catch(InterruptedException ie)
        {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static final SysLog syslog = SysLogUtil.getBootLogger();
    private final ScheduledExecutorService executor;
    private String name;
    private long checkInterval;



}
