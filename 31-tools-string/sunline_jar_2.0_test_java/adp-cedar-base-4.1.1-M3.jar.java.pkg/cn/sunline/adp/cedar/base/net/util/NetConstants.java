// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetConstants.java

package cn.sunline.adp.cedar.base.net.util;


// Referenced classes of package cn.sunline.adp.cedar.base.net.util:
//            BaseUtil

public class NetConstants
{

    public NetConstants()
    {
    }

    public static String version = "0.1";
    public static String vmId = (new StringBuilder()).append(BaseUtil.getHostIp()).append("/").append(BaseUtil.getPID()).toString();
    public static String encoding = "UTF-8";

}
