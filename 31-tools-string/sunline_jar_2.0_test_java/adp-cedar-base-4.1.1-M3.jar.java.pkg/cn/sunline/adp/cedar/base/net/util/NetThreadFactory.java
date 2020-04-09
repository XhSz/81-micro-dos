// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetThreadFactory.java

package cn.sunline.adp.cedar.base.net.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NetThreadFactory
    implements ThreadFactory
{

    public NetThreadFactory(String name)
    {
        groupName = name;
        AtomicInteger threadNumber = (AtomicInteger)threadNumberByName.get(name);
        if(threadNumber == null)
        {
            threadNumber = new AtomicInteger(1);
            threadNumberByName.put(name, threadNumber);
        }
        group = new ThreadGroup(name);
    }

    public Thread newThread(Runnable r)
    {
        Thread t = __newThread(r);
        return t;
    }

    private Thread __newThread(Runnable r)
    {
        AtomicInteger threadNumber = (AtomicInteger)threadNumberByName.get(groupName);
        Thread t = new Thread(group, r, (new StringBuilder()).append(group.getName()).append("-").append(threadNumber.getAndIncrement()).toString(), 0L);
        if(t.isDaemon())
            t.setDaemon(false);
        if(t.getPriority() != 5)
            t.setPriority(5);
        return t;
    }

    private final ThreadGroup group;
    private final String groupName;
    private final Map threadNumberByName = new ConcurrentHashMap();
}
