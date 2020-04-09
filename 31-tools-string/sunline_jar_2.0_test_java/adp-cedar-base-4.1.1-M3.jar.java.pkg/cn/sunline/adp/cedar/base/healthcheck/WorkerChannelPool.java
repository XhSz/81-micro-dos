// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorkerChannelPool.java

package cn.sunline.adp.cedar.base.healthcheck;

import java.lang.ref.SoftReference;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            DefaultWorkerChannel, WorkerChannel

public class WorkerChannelPool
{

    public WorkerChannelPool(String name)
    {
        maxInactiveServerChannelSize = 10;
    }

    public List getData()
    {
        return new ArrayList(activeServerChannels);
    }

    public void addServerChannel(DefaultWorkerChannel data)
    {
        activeServerChannels.add(data);
    }

    public void removeServerChannel(DefaultWorkerChannel data)
    {
        activeServerChannels.remove(data);
        if(maxInactiveServerChannelSize != 0)
            synchronized(inactiveServerChannels)
            {
                if(inactiveServerChannels.size() > Math.abs(maxInactiveServerChannelSize))
                    if(maxInactiveServerChannelSize < 0)
                        inactiveServerChannels.clear();
                    else
                        inactiveServerChannels.remove(inactiveServerChannels.size() - 1);
                inactiveServerChannels.add(new SoftReference(data));
            }
    }

    public void reset()
    {
        DefaultWorkerChannel sc;
        for(Iterator iterator = activeServerChannels.iterator(); iterator.hasNext(); sc.reset())
            sc = (DefaultWorkerChannel)iterator.next();

        inactiveServerChannels.clear();
    }

    public WorkerChannel[] getAllServerChannels()
    {
        List ret = new ArrayList(activeServerChannels);
        Iterator iterator = inactiveServerChannels.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            SoftReference ss = (SoftReference)iterator.next();
            if(ss != null && ss.get() != null)
                ret.add(ss.get());
        } while(true);
        return (WorkerChannel[])ret.toArray(new WorkerChannel[ret.size()]);
    }

    public WorkerChannel[] getActiveServerChannels()
    {
        return (WorkerChannel[])activeServerChannels.toArray(new WorkerChannel[0]);
    }

    private int maxInactiveServerChannelSize;
    private final List activeServerChannels = Collections.synchronizedList(new ArrayList());
    private final List inactiveServerChannels = Collections.synchronizedList(new ArrayList());
}
