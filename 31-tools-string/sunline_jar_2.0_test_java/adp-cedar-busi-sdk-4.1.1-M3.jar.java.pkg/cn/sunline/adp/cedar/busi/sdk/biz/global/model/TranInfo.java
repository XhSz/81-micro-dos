// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TranInfo.java

package cn.sunline.adp.cedar.busi.sdk.biz.global.model;

import cn.sunline.adp.cedar.flowtran.model.TransactionConf;

public class TranInfo
{

    public TranInfo(TransactionConf config)
    {
        this.config = config;
    }

    public String getTranName()
    {
        return config.getId();
    }

    public String getLongname()
    {
        return config.getLongname();
    }

    public String getType()
    {
        return config.getKind();
    }

    private TransactionConf config;
}
