// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DiscoveryServiceConfig.java

package cn.sunline.adp.cedar.base.plugin.discovery;

import java.util.List;

public class DiscoveryServiceConfig
{
    public static class DiscoveryConfig
    {

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public Boolean getObserver()
        {
            return observer;
        }

        public void setObserver(Boolean observer)
        {
            this.observer = observer;
        }

        public String getCallback()
        {
            return callback;
        }

        public void setCallback(String callback)
        {
            this.callback = callback;
        }

        public String name;
        public Boolean observer;
        public String callback;

        public DiscoveryConfig()
        {
        }
    }


    public DiscoveryServiceConfig()
    {
    }

    public Integer getHeartBeatInterval()
    {
        return heartBeatInterval;
    }

    public void setHeartBeatInterval(Integer heartBeatInterval)
    {
        this.heartBeatInterval = heartBeatInterval;
    }

    public List getDiscoveries()
    {
        return discoveries;
    }

    public void setDiscoveries(List discoveries)
    {
        this.discoveries = discoveries;
    }

    private Integer heartBeatInterval;
    private List discoveries;
}
