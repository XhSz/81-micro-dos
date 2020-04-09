// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DbConfig.java

package cn.sunline.adp.cedar.busi.sdk.biz.global.model;

import cn.sunline.adp.dao.base.DbType;

public class DbConfig
{

    public DbConfig()
    {
    }

    public String getTns()
    {
        return tns;
    }

    public void setTns(String tns)
    {
        this.tns = tns;
    }

    public String getTnsname()
    {
        return tnsname;
    }

    public void setTnsname(String tnsname)
    {
        this.tnsname = tnsname;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(String sourceId)
    {
        this.sourceId = sourceId;
    }

    public DbType getDatabaseType()
    {
        return databaseType;
    }

    public void setDatabaseType(DbType databaseType)
    {
        this.databaseType = databaseType;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

    private String url;
    private String tnsname;
    private String userName;
    private String password;
    private String sourceId;
    private DbType databaseType;
    private String encoding;
    private String tns;
}
