// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorkerChannel.java

package cn.sunline.adp.cedar.base.healthcheck;

import cn.sunline.adp.core.profile.PerformanceData;
import cn.sunline.adp.core.profile.ProfileData;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public interface WorkerChannel
    extends Serializable
{
    public static interface TransData
        extends Serializable
    {

        public abstract long getBeginProcessTime();

        public abstract long getTimeout();

        public abstract void setTimeout(long l);

        public abstract String getTransName();

        public abstract void setTransName(String s);

        public abstract String getUserId();

        public abstract void setUserId(String s);

        public abstract String getPckgdt();

        public abstract void setPckgdt(String s);

        public abstract String getPckgsq();

        public abstract void setPckgsq(String s);

        public abstract String getReq();

        public abstract String getRes();

        public abstract long getCost();

        public abstract int getIndex();

        public abstract boolean isSuccessful();

        public abstract ProfileData getProfileData(String s);

        public abstract ProfileData[] getProfileData();

        public abstract PerformanceData getPerformanceData();
    }


    public abstract String getId();

    public abstract String getClientName();

    public abstract Map getProperties();

    public abstract boolean isProcessing();

    public abstract boolean isTerminated();

    public abstract String getRemoteIp();

    public abstract int getRemotePort();

    public abstract int getLocalPort();

    public abstract String getPropertiesStr();

    public abstract Date getStartTime();

    public abstract Date getEndTime();

    public abstract long getReceivedCount();

    public abstract long getSuccessCount();

    public abstract long getFailCount();

    public abstract double getUseRatio();

    public abstract long getTotalCost();

    public abstract double getTps();

    public abstract PerformanceData getPerformanceData();
}
