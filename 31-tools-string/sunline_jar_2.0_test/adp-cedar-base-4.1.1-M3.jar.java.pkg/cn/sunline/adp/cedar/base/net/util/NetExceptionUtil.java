// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetExceptionUtil.java

package cn.sunline.adp.cedar.base.net.util;


// Referenced classes of package cn.sunline.adp.cedar.base.net.util:
//            NetException

public class NetExceptionUtil
{

    public NetExceptionUtil()
    {
    }

    public static NetException wrapThrow(Throwable e)
    {
        return new NetException(e);
    }

    public static NetException wrapThrow(String message, Throwable e)
    {
        return new NetException(message, e);
    }

    public static transient NetException wrapThrow(String format, String args[])
    {
        throw new NetException(String.format(format, args));
    }

    public static boolean isException(Throwable targetException, Class exClass)
    {
        if(targetException == null)
            return false;
        if(exClass.isAssignableFrom(targetException.getClass()))
            return true;
        else
            return isException(targetException.getCause(), exClass);
    }
}
