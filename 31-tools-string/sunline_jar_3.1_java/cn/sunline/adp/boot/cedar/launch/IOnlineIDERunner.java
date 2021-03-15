// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IOnlineIDERunner.java

package cn.sunline.adp.boot.cedar.launch;


public abstract class IOnlineIDERunner
{

    public IOnlineIDERunner()
    {
    }

    public final void _run(String args[])
    {
        ide = true;
        if("true".equals(args[0]))
        {
            ide_debug = true;
            System.setProperty("ide_debug", "true");
        }
        run(args);
    }

    public abstract void run(String as[]);

    public static boolean isIDERun()
    {
        return ide;
    }

    public static boolean isIDEDebug()
    {
        return ide_debug;
    }

    public static String POINT = "online.ide.runner";
    private static boolean ide_debug = false;
    private static boolean ide = false;

}
