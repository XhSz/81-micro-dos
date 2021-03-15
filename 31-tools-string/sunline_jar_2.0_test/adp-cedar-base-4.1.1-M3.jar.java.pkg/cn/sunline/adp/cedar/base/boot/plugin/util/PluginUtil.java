// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginUtil.java

package cn.sunline.adp.cedar.base.boot.plugin.util;

import java.io.IOException;
import java.net.URL;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class PluginUtil
{

    public PluginUtil()
    {
    }

    public static transient RuntimeException wrapThrow(String format, Throwable e, Object args[])
    {
        return new RuntimeException(formatForLog(format, args), e);
    }

    public static transient RuntimeException wrapThrow(String format, Object args[])
    {
        throw new RuntimeException(formatForLog(format, args));
    }

    public static transient String formatForLog(String format, Object args[])
    {
        StringBuilder destSB = new StringBuilder();
        format(destSB, format, args);
        return destSB.toString();
    }

    public static boolean isEmpty(String s)
    {
        if(s == null)
            return true;
        return s.trim().length() == 0;
    }

    public static Class classForName(String className)
        throws ClassNotFoundException
    {
        return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
    }

    public static URL[] findResources(String pattern)
    {
        ResourcePatternResolver rr = new PathMatchingResourcePatternResolver();
        try
        {
            Resource resources[] = rr.getResources(pattern);
            URL ret[] = new URL[resources.length];
            for(int i = 0; i < resources.length; i++)
                ret[i] = resources[i].getURL();

            return ret;
        }
        catch(IOException e)
        {
            throw wrapThrow((new StringBuilder()).append("classpath search ").append(pattern).append(" error").toString(), e, new Object[0]);
        }
    }

    private static transient void format(StringBuilder destSB, String messagePattern, Object arguments[])
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
            if('{' == c && messagePattern.charAt(i + 1) == '}')
            {
                destSB.append(String.valueOf(arguments[argsIndex]));
                argsIndex++;
                i++;
                continue;
            }
            if('{' == c && messagePattern.charAt(i + 1) != '}')
                throw new RuntimeException("messagePattern error");
            destSB.append(c);
        }

    }

    private static final char DELIM_START = 123;
    private static final char DELIM_STOP = 125;
}
