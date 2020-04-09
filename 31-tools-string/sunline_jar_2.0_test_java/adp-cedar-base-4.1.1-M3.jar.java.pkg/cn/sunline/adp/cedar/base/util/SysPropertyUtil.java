// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SysPropertyUtil.java

package cn.sunline.adp.cedar.base.util;

import java.util.Properties;

public class SysPropertyUtil
{

    public SysPropertyUtil()
    {
    }

    public static void init(Properties props)
    {
        props = props;
    }

    public static boolean getBooleanProperty(String name, boolean defaultValue)
    {
        String prop = getStringProperty(name);
        return prop != null ? "true".equalsIgnoreCase(prop) : defaultValue;
    }

    public static int getIntegerProperty(String name, int defaultValue)
    {
        String prop = getStringProperty(name);
        if(prop != null)
            try
            {
                return Integer.parseInt(prop);
            }
            catch(Exception ignored)
            {
                return defaultValue;
            }
        else
            return defaultValue;
    }

    public static String getStringProperty(String name)
    {
        String prop = null;
        try
        {
            prop = System.getProperty(name);
        }
        catch(SecurityException securityexception) { }
        return prop != null ? prop : props.getProperty(name);
    }

    private static Properties props = new Properties();

}
