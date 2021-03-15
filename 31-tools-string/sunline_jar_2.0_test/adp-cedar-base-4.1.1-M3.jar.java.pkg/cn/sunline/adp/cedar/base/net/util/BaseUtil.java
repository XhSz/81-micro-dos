// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseUtil.java

package cn.sunline.adp.cedar.base.net.util;

import cn.sunline.adp.core.util.SystemContext;

// Referenced classes of package cn.sunline.adp.cedar.base.net.util:
//            RandomGUID

public class BaseUtil
{

    public BaseUtil()
    {
    }

    public static String createGUID()
    {
        return (new RandomGUID()).getValueAfterMD5();
    }

    public static String getHostIp()
    {
        return SystemContext.getIp();
    }

    public static String getPID()
    {
        return SystemContext.getPID();
    }

    public static transient String format(String messagePattern, Object arguments[])
    {
        StringBuilder seusable = new StringBuilder();
        format(seusable, messagePattern, arguments);
        return seusable.toString();
    }

    public static transient void format(StringBuilder destSB, String messagePattern, Object arguments[])
    {
        if(arguments == null || arguments.length == 0)
        {
            destSB.append(messagePattern);
            return;
        }
        int argsIndex = 0;
        for(int i = 0; i < messagePattern.length(); i++)
        {
            char c = messagePattern.charAt(i);
            if('%' == c && messagePattern.charAt(i + 1) == 's')
            {
                destSB.append(String.valueOf(arguments[argsIndex]));
                argsIndex++;
                i++;
            } else
            {
                destSB.append(c);
            }
        }

    }

    private static final char DELIM_START = 37;
    private static final char DELIM_STOP = 115;
}
