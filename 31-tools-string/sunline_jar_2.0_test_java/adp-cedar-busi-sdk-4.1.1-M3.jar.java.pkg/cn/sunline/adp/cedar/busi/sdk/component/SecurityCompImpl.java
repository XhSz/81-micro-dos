// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SecurityCompImpl.java

package cn.sunline.adp.cedar.busi.sdk.component;

import cn.sunline.adp.metadata.model.annotation.ConfigType;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.component:
//            BaseComp

public class SecurityCompImpl
{
    public static abstract class EsscSecurity
        implements BaseComp.Security
    {

        public String getKeyName()
        {
            return keyName;
        }

        public void setKeyName(String keyName)
        {
            this.keyName = keyName;
        }

        private String keyName;

        public EsscSecurity()
        {
        }
    }

    public static abstract class DesSecurity
        implements BaseComp.Security
    {

        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        private String key;

        public DesSecurity()
        {
        }
    }


    public SecurityCompImpl()
    {
    }
}
