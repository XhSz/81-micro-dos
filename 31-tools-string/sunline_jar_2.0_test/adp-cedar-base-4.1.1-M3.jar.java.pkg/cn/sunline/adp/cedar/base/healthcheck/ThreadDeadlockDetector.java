// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadDeadlockDetector.java

package cn.sunline.adp.cedar.base.healthcheck;

import java.lang.management.*;
import java.util.*;

public class ThreadDeadlockDetector
{

    public ThreadDeadlockDetector()
    {
        this(ManagementFactory.getThreadMXBean());
    }

    public ThreadDeadlockDetector(ThreadMXBean threads)
    {
        this.threads = threads;
    }

    public Set getDeadlockedThreads()
    {
        long ids[] = threads.findDeadlockedThreads();
        if(ids != null)
        {
            Set deadlocks = new HashSet();
            ThreadInfo athreadinfo[] = threads.getThreadInfo(ids, 100);
            int i = athreadinfo.length;
            for(int j = 0; j < i; j++)
            {
                ThreadInfo info = athreadinfo[j];
                StringBuilder stackTrace = new StringBuilder();
                StackTraceElement astacktraceelement[] = info.getStackTrace();
                int k = astacktraceelement.length;
                for(int l = 0; l < k; l++)
                {
                    StackTraceElement element = astacktraceelement[l];
                    stackTrace.append("\t at ").append(element.toString()).append(String.format("%n", new Object[0]));
                }

                deadlocks.add(String.format("%s locked on %s (owned by %s):%n%s", new Object[] {
                    info.getThreadName(), info.getLockName(), info.getLockOwnerName(), stackTrace.toString()
                }));
            }

            return Collections.unmodifiableSet(deadlocks);
        } else
        {
            return Collections.emptySet();
        }
    }

    private static final int MAX_STACK_TRACE_DEPTH = 100;
    private final ThreadMXBean threads;
}
