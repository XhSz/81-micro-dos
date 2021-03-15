// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleThreadFactory.java

package cn.sunline.adp.cedar.base.srv.socket.common;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadFactory
    implements ThreadFactory
{

    public SimpleThreadFactory(String groupName)
    {
        this.groupName = groupName;
        AtomicInteger threadNumber = (AtomicInteger)threadNumberByName.get(groupName);
        if(threadNumber == null)
        {
            threadNumber = new AtomicInteger(1);
            threadNumberByName.put(groupName, threadNumber);
        }
        group = new ThreadGroup(groupName);
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

    private static final SysLog SERVER_LOGGER = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/srv/socket/common/SimpleThreadFactory);
    private final ThreadGroup group;
    private final String groupName;
    private final Map threadNumberByName = new ConcurrentHashMap();

}
