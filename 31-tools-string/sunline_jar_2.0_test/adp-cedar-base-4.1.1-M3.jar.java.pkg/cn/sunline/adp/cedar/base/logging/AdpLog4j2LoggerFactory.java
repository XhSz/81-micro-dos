// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AdpLog4j2LoggerFactory.java

package cn.sunline.adp.cedar.base.logging;


// Referenced classes of package cn.sunline.adp.cedar.base.logging:
//            Log4jLogImpl, AdpLoggerFactory, BizLog, SysLog

public class AdpLog4j2LoggerFactory
    implements AdpLoggerFactory
{

    public AdpLog4j2LoggerFactory()
    {
    }

    public BizLog getBizLog(String name)
    {
        return new Log4jLogImpl(name);
    }

    public SysLog getSysLog(String name)
    {
        return new Log4jLogImpl(name);
    }
}
