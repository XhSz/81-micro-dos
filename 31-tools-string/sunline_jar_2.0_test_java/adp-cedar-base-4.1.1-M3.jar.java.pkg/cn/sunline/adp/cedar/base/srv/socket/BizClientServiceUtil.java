// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BizClientServiceUtil.java

package cn.sunline.adp.cedar.base.srv.socket;


// Referenced classes of package cn.sunline.adp.cedar.base.srv.socket:
//            BizClient

public class BizClientServiceUtil
{

    public BizClientServiceUtil()
    {
    }

    public static void init(BizClient s)
    {
        service = s;
    }

    public static BizClient getBizClient()
    {
        return service;
    }

    private static BizClient service;
}
