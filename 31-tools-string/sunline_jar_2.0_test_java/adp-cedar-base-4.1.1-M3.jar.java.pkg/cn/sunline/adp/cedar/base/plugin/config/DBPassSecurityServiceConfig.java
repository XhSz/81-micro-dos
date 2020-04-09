// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBPassSecurityServiceConfig.java

package cn.sunline.adp.cedar.base.plugin.config;


public class DBPassSecurityServiceConfig
{

    public DBPassSecurityServiceConfig()
    {
    }

    public String getCallback()
    {
        return callback;
    }

    public void setCallback(String callback)
    {
        this.callback = callback;
    }

    public static final String DEFAULT_CALLBACK_CLASS = "cn.sunline.ltts.config.dbpass.DefaultDBPassSecurityImpl";
    private String callback;
}
