// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustomLog4j2Level.java

package cn.sunline.adp.cedar.base.logging;

import org.apache.logging.log4j.Level;

public class CustomLog4j2Level
{

    public CustomLog4j2Level()
    {
    }

    public static final Level SQLINFO()
    {
        return Level.getLevel("SQLIF");
    }

    public static final Level SQL()
    {
        return Level.getLevel("SQL");
    }

    public static final Level SERVICE()
    {
        return Level.getLevel("SVC");
    }

    public static final Level PROFILE()
    {
        return Level.getLevel("PROF");
    }

    public static final Level DATAAREA()
    {
        return Level.getLevel("DAREA");
    }

    public static final Level PARM()
    {
        return Level.getLevel("PARM");
    }

    public static final Level METHOD()
    {
        return Level.getLevel("METHD");
    }

    public static final Level TECH_METHOD()
    {
        return Level.getLevel("TECH");
    }
}
