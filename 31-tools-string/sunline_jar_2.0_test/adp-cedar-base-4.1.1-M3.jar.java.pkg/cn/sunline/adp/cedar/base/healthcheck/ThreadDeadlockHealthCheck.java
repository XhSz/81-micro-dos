// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadDeadlockHealthCheck.java

package cn.sunline.adp.cedar.base.healthcheck;

import java.util.Set;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            HealthCheck, ThreadDeadlockDetector

public class ThreadDeadlockHealthCheck extends HealthCheck
{

    public ThreadDeadlockHealthCheck()
    {
        this(new ThreadDeadlockDetector());
    }

    public ThreadDeadlockHealthCheck(ThreadDeadlockDetector detector)
    {
        this.detector = detector;
    }

    protected HealthCheck.Result check()
        throws Exception
    {
        Set threads = detector.getDeadlockedThreads();
        if(threads.isEmpty())
            return HealthCheck.Result.healthy();
        else
            return HealthCheck.Result.unhealthy(threads.toString());
    }

    private final ThreadDeadlockDetector detector;
}
