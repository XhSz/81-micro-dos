// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDERunUtil.java

package cn.sunline.adp.boot.cedar.launch;

import org.apache.logging.log4j.core.config.ConfigurationFactory;

public class IDERunUtil
{

    public IDERunUtil()
    {
    }

    public static void init(String args[])
    {
        if(System.getProperty("log4j.configurationFile") == null)
            System.setProperty("log4j.configurationFile", "ltts_log_dev.xml");
    }

    public static final String IDE_RUN_TYPE_KEY = "ideRunTypeKey";
    public static final String IDE_RUN_TYPE_ONL = "onl";
    public static final String IDE_RUN_TYPE_BAT = "bat";
}
