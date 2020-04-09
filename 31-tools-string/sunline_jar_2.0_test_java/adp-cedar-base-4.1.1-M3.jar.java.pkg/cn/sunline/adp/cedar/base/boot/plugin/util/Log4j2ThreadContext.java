// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log4j2ThreadContext.java

package cn.sunline.adp.cedar.base.boot.plugin.util;

import org.apache.logging.log4j.ThreadContext;

public class Log4j2ThreadContext
{

    public Log4j2ThreadContext()
    {
    }

    public static void set_token()
    {
        ThreadContext.put("token2", genToken());
    }

    public static void set_sys_boot()
    {
        set_type("boot");
        set_token();
    }

    public static void set_type(String type)
    {
        ThreadContext.put("ltts_log_type", type);
    }

    public static String genToken()
    {
        return random(8, true);
    }

    private static String random(int expectLen, boolean number)
    {
        char ch[] = new char[expectLen];
        for(int i = 0; i < expectLen; i++)
            ch[i] = CHAR[(int)(Math.random() * (double)(number ? 10 : CHAR.length))];

        return new String(ch);
    }

    public static final String key_root = "ltts_log_type";
    public static final String key_token = "token2";
    private static final char CHAR[] = new char[62];

}
