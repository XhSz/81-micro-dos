// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfigSecurityUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.security.DBPassSecurity;
import cn.sunline.adp.cedar.base.security.DBPassSecurityManager;
import cn.sunline.edsp.base.factories.FactoriesLoader;

public class ConfigSecurityUtil
{

    public ConfigSecurityUtil()
    {
    }

    public static boolean needEncrypt(String s)
    {
        if(s == null)
        {
            return false;
        } else
        {
            s = s.trim();
            return s.startsWith("{{enc:") && s.endsWith("}}") && PROCESS_DB_PASSWORD;
        }
    }

    public static boolean needDecrypt(String s)
    {
        if(s == null)
        {
            return false;
        } else
        {
            s = s.trim();
            return s.startsWith("{{dec:") && s.endsWith("}}") && PROCESS_DB_PASSWORD;
        }
    }

    public static String encrypt(String s)
    {
        return (new StringBuilder()).append("{{dec:").append(((DBPassSecurityManager)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/security/DBPassSecurityManager)).getSecurity().encrypt(s)).append("}}").toString();
    }

    public static String decrypt(String s)
    {
        s = s.trim();
        if(s.startsWith("{{enc:"))
        {
            return s.substring("{{enc:".length(), s.length() - "}}".length());
        } else
        {
            s = s.substring("{{dec:".length(), s.length() - "}}".length());
            return ((DBPassSecurityManager)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/security/DBPassSecurityManager)).getSecurity().decrypt(s);
        }
    }

    public static String decryptWithCheck(String s)
    {
        PROCESS_DB_PASSWORD = true;
        if(needDecrypt(s))
        {
            return decrypt(s);
        } else
        {
            PROCESS_DB_PASSWORD = false;
            return s;
        }
    }

    private static final String DEFAULT_PREFIX_ENC = "{{enc:";
    private static final String DEFAULT_PREFIX_DEC = "{{dec:";
    private static final String DEFAULT_SUFFIX = "}}";
    public static boolean PROCESS_DB_PASSWORD = true;

}
