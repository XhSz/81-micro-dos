// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IcCompImpl.java

package cn.sunline.adp.cedar.busi.sdk.component;

import cn.sunline.adp.metadata.model.annotation.ConfigType;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.component:
//            BaseComp

public class IcCompImpl
{
    public static abstract class DefaultIcSecurity
        implements BaseComp.IcSecurity
    {

        public String getIp()
        {
            return ip;
        }

        public void setIp(String ip)
        {
            this.ip = ip;
        }

        public Integer getPort()
        {
            return port;
        }

        public void setPort(Integer port)
        {
            this.port = port;
        }

        private String ip;
        private Integer port;

        public DefaultIcSecurity()
        {
        }
    }


    public IcCompImpl()
    {
    }
}
