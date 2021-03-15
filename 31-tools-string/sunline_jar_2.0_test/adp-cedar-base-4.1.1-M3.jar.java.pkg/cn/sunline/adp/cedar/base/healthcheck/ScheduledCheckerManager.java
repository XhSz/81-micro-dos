// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScheduledCheckerManager.java

package cn.sunline.adp.cedar.base.healthcheck;

import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            ScheduledHealthChecker

public class ScheduledCheckerManager
{

    private ScheduledCheckerManager()
    {
        scheduledCheckers = new HashMap();
    }

    public static ScheduledCheckerManager get()
    {
        return instance;
    }

    public void addScheduledCheckers(String name, long checkInterval)
    {
        if(scheduledCheckers.containsKey(name))
        {
            return;
        } else
        {
            scheduledCheckers.put(name, new ScheduledHealthChecker(name, checkInterval));
            return;
        }
    }

    public Collection getScheduledHealthCheckers()
    {
        return Collections.unmodifiableCollection(scheduledCheckers.values());
    }

    private Map scheduledCheckers;
    private static final ScheduledCheckerManager instance = new ScheduledCheckerManager();

}
