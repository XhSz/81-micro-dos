// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorkerChannelBoard.java

package cn.sunline.adp.cedar.base.healthcheck;

import cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            WorkerChannelPool, DefaultWorkerChannel, WorkerChannel

public class WorkerChannelBoard
    implements ThreadCriticalResourceAccessor
{

    public WorkerChannelBoard(String name)
    {
        index = 0;
        channelPool = new WorkerChannelPool(name);
    }

    public WorkerChannelPool getChannelPool()
    {
        return channelPool;
    }

    public DefaultWorkerChannel getCurrentWorkerChannel()
    {
        return getThreadLocalData();
    }

    public void removeWorkerChannel(WorkerChannel workerchannel)
    {
    }

    public List getActiveWorkerChannels()
    {
        return channelPool.getData();
    }

    public void onChannelStart(Socket socket)
    {
        DefaultWorkerChannel data = getThreadLocalData();
        if(data == null)
        {
            data = createServerChannel(socket);
            threadLocalData.set(data);
            channelPool.addServerChannel(data);
        }
        cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor.Factory.set(this);
    }

    public void onChannelBeginProcessing(String request)
    {
        DefaultWorkerChannel.TransData trans = new DefaultWorkerChannel.TransData(++index, request);
        getThreadLocalData().begin(trans);
        getThreadLocalData().setTransactionTimeout(false);
        resetThreadName();
    }

    public void onChannelSuccessProcessing(String response)
    {
        onEndProcessing(response, true);
    }

    public void onChannelExceptionProcessing(Throwable e)
    {
        onEndProcessing((new StringBuilder()).append("Error:").append(e.getMessage()).toString(), false);
    }

    public void onChannelTerminate()
    {
        if(threadLocalData.get() != null)
        {
            ((DefaultWorkerChannel)threadLocalData.get()).setEndTime(new Date());
            ((DefaultWorkerChannel)threadLocalData.get()).clearCriticalResource();
            channelPool.removeServerChannel((DefaultWorkerChannel)threadLocalData.get());
            threadLocalData.set(null);
        }
    }

    private void onEndProcessing(String res, boolean success)
    {
        DefaultWorkerChannel.TransData trans = getThreadLocalData().getProcessingTransData();
        if(trans == null)
        {
            return;
        } else
        {
            trans.end(res, success);
            getThreadLocalData().end();
            resetThreadName();
            return;
        }
    }

    public static DefaultWorkerChannel getThreadLocalData()
    {
        return (DefaultWorkerChannel)threadLocalData.get();
    }

    private DefaultWorkerChannel createServerChannel(Socket socket)
    {
        DefaultWorkerChannel ret = new DefaultWorkerChannel((new StringBuilder()).append("").append(SCID.getAndIncrement()).toString(), Thread.currentThread(), socket, new Date());
        return ret;
    }

    private void resetThreadName()
    {
        Thread t = Thread.currentThread();
        int start = t.getName().indexOf("-[Stuck");
        if(start != -1)
        {
            int end = t.getName().indexOf("]", start);
            String name = t.getName().substring(0, start);
            if(end != -1 && end < t.getName().length())
                name = (new StringBuilder()).append(name).append(t.getName().substring(end + 1)).toString();
            t.setName(name);
        }
    }

    public boolean isTransactionTimeout()
    {
        return getThreadLocalData().isTransactionTimeout();
    }

    public void attatchCriticalResource(cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor.CriticalResource resource)
    {
        getThreadLocalData().addCriticalResource(resource);
    }

    public void detatchCriticalResource(cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor.CriticalResource resource)
    {
        getThreadLocalData().removeCriticalResource(resource);
    }

    private final WorkerChannelPool channelPool;
    private final AtomicLong SCID = new AtomicLong(1L);
    private int index;
    private static ThreadLocal threadLocalData = new ThreadLocal();

}
