// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccessThreadPoolEvent.java

package cn.sunline.adp.cedar.base.net.rpc.server;

import java.util.ArrayList;
import java.util.List;

public class AccessThreadPoolEvent
{

    public AccessThreadPoolEvent()
    {
    }

    public String getPoolName()
    {
        return poolName;
    }

    public double getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(double totalCount)
    {
        this.totalCount = totalCount;
    }

    public double getCoreCount()
    {
        return coreCount;
    }

    public void setCoreCount(double coreCount)
    {
        this.coreCount = coreCount;
    }

    public double getMaxCount()
    {
        return maxCount;
    }

    public void setMaxCount(double maxCount)
    {
        this.maxCount = maxCount;
    }

    public double getQueueCount()
    {
        return queueCount;
    }

    public void setQueueCount(double queueCount)
    {
        this.queueCount = queueCount;
    }

    public double getQueueSize()
    {
        return queueSize;
    }

    public void setQueueSize(double queueSize)
    {
        this.queueSize = queueSize;
    }

    public void setPoolName(String poolName)
    {
        this.poolName = poolName;
    }

    public List labels()
    {
        List list = new ArrayList();
        list.add(getPoolName());
        return list;
    }

    public static List labelNames()
    {
        List list = new ArrayList();
        list.add("poolName");
        return list;
    }

    private String poolName;
    private double totalCount;
    private double coreCount;
    private double maxCount;
    private double queueCount;
    private double queueSize;
}
