// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultWorkerChannel.java

package cn.sunline.adp.cedar.base.healthcheck;

import cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor;
import cn.sunline.adp.cedar.base.util.LttsPkgUtil;
import cn.sunline.adp.core.profile.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            WorkerChannel

public class DefaultWorkerChannel
    implements WorkerChannel, Serializable
{
    public static class TransData
        implements Serializable
    {

        public void end(String res, boolean success)
        {
            pd = ProfileUtil.getPerformanceData();
            this.res = res;
            cost = pd.getTotalCost();
            successful = success;
            profileData = ProfileUtil.getProfileData(1);
        }

        public long getBeginProcessTime()
        {
            return beginProcessTime;
        }

        public long getTimeout()
        {
            return timeout;
        }

        public void setTimeout(long timeout)
        {
            this.timeout = timeout;
        }

        public String getReq()
        {
            return req;
        }

        public String getRes()
        {
            return res;
        }

        public long getCost()
        {
            return cost;
        }

        public int getIndex()
        {
            return index;
        }

        public boolean isSuccessful()
        {
            return successful;
        }

        public String getTransName()
        {
            return transName;
        }

        public void setTransName(String transName)
        {
            this.transName = transName;
        }

        public ProfileData getProfileData(String operation)
        {
            if(profileData != null)
            {
                ProfileData aprofiledata[] = profileData;
                int i = aprofiledata.length;
                for(int j = 0; j < i; j++)
                {
                    ProfileData p = aprofiledata[j];
                    if(p.getOperation().equals(operation))
                        return p;
                }

            }
            return null;
        }

        public ProfileData[] getProfileData()
        {
            return profileData;
        }

        public PerformanceData getPerformanceData()
        {
            return pd;
        }

        public String getUserId()
        {
            return userId;
        }

        public void setUserId(String userId)
        {
            this.userId = userId;
        }

        public String getTranData()
        {
            return tranData;
        }

        public void setTranData(String tranData)
        {
            this.tranData = tranData;
        }

        public String getBusiSeqNo()
        {
            return busiSeqNo;
        }

        public void setBusiSeqNo(String busiSeqNo)
        {
            this.busiSeqNo = busiSeqNo;
        }

        public String getCallSeqNo()
        {
            return callSeqNo;
        }

        public void setCallSeqNo(String callSeqNo)
        {
            this.callSeqNo = callSeqNo;
        }

        private static final long serialVersionUID = 0x5e839006189ba471L;
        private long beginProcessTime;
        private long timeout;
        private final int index;
        private final String req;
        private String transName;
        private String res;
        private long cost;
        private ProfileData profileData[];
        private PerformanceData pd;
        private boolean successful;
        private String userId;
        private String tranData;
        private String busiSeqNo;
        private String callSeqNo;

        public TransData(int index, String req)
        {
            cost = 0L;
            pd = PerformanceData.ZERO;
            beginProcessTime = System.currentTimeMillis();
            this.index = index;
            this.req = req;
        }
    }


    public DefaultWorkerChannel(String id, Thread thread, Socket socket, Date startTime)
    {
        properties = new LinkedHashMap();
        totalCost = 0L;
        receivedCount = 0L;
        successCount = 0L;
        failCount = 0L;
        processingTransDataLock = new Object();
        propertiesStr = null;
        this.id = id;
        this.thread = thread;
        this.socket = socket;
        this.startTime = startTime;
        tpsBeginTime = startTime;
        if(socket != null)
        {
            remoteIp = socket.getInetAddress().getHostAddress();
            remotePort = socket.getPort();
            localPort = socket.getLocalPort();
            clientName = (new StringBuilder()).append(remoteIp).append(":").append(remotePort).toString();
        }
        pd = PerformanceData.ZERO;
        channelName = null;
    }

    public void begin(TransData trans)
    {
        synchronized(processingTransDataLock)
        {
            processingTransData = trans;
        }
        receivedCount++;
    }

    public void end()
    {
        synchronized(processingTransDataLock)
        {
            totalCost += getProcessingTransData().getCost();
            if(processingTransData.isSuccessful())
                successCount++;
            else
                failCount++;
            pd = pd.add(processingTransData.getPerformanceData());
            processingTransData = null;
        }
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public TransData getProcessingTransData()
    {
        Object obj = processingTransDataLock;
        JVM INSTR monitorenter ;
        return processingTransData;
        Exception exception;
        exception;
        throw exception;
    }

    public void addCriticalResource(cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor.CriticalResource r)
    {
        criticalResources.add(r);
    }

    public void removeCriticalResource(cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor.CriticalResource r)
    {
        criticalResources.remove(r);
    }

    public void clearCriticalResource()
    {
        thread = null;
        socket = null;
        criticalResources.clear();
    }

    public void setTransactionTimeout(boolean transactionTimeout)
    {
        this.transactionTimeout = transactionTimeout;
    }

    public Socket getSocket()
    {
        return socket;
    }

    public void reset()
    {
        tpsBeginTime = new Date();
        receivedCount = 0L;
        successCount = 0L;
        failCount = 0L;
        totalCost = 0L;
    }

    public Set getCriticalResources()
    {
        return Collections.unmodifiableSet(criticalResources);
    }

    public boolean isTerminated()
    {
        return endTime != null;
    }

    public boolean isProcessing()
    {
        return getProcessingTransData() != null;
    }

    public double getTps()
    {
        long endTime = this.endTime != null ? this.endTime.getTime() : System.currentTimeMillis();
        long time = endTime - tpsBeginTime.getTime();
        if(time == 0L)
            return (double)(failCount + successCount);
        else
            return (double)(failCount + successCount) / ((double)time / 1000D);
    }

    public String getId()
    {
        return id;
    }

    public Thread getThread()
    {
        return thread;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public long getReceivedCount()
    {
        return receivedCount;
    }

    public long getSuccessCount()
    {
        return successCount;
    }

    public long getFailCount()
    {
        return failCount;
    }

    public long getTotalCost()
    {
        return totalCost;
    }

    public double getUseRatio()
    {
        long endTime = this.endTime != null ? this.endTime.getTime() : System.currentTimeMillis();
        long time = endTime - tpsBeginTime.getTime();
        if(time == 0L)
            return 1.0D;
        else
            return ((double)totalCost * 1.0D) / (double)time;
    }

    public Map getProperties()
    {
        return Collections.unmodifiableMap(properties);
    }

    public void setProperty(String key, String value)
    {
        properties.put(key, value);
        propertiesStr = LttsPkgUtil.formatToString(properties);
    }

    public String getPropertiesStr()
    {
        return propertiesStr;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public String getRemoteIp()
    {
        return remoteIp;
    }

    public int getRemotePort()
    {
        return remotePort;
    }

    public int getLocalPort()
    {
        return localPort;
    }

    public boolean isTransactionTimeout()
    {
        return transactionTimeout;
    }

    public PerformanceData getPerformanceData()
    {
        return pd;
    }

    private static final long serialVersionUID = 0xf928702c7db269b4L;
    private transient Thread thread;
    private transient Socket socket;
    private final transient Set criticalResources = new LinkedHashSet();
    private final String id;
    private String channelName;
    private Map properties;
    private final Date startTime;
    private Date endTime;
    private Date tpsBeginTime;
    private long totalCost;
    private long receivedCount;
    private long successCount;
    private long failCount;
    private PerformanceData pd;
    private transient TransData processingTransData;
    private transient Object processingTransDataLock;
    private boolean transactionTimeout;
    private String propertiesStr;
    private String clientName;
    private String remoteIp;
    private int remotePort;
    private int localPort;
}
