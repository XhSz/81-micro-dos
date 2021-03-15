// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineTransactionConstant.java

package cn.sunline.adp.cedar.base.engine.transaction;


public class OnlineTransactionConstant
{

    public static String getDtxErrortx()
    {
        return dtxErrortx;
    }

    public static void setDtxErrortx(String dtxErrortx)
    {
        dtxErrortx = dtxErrortx;
    }

    public static String getDtxErrorcd()
    {
        return dtxErrorcd;
    }

    public static void setDtxErrorcd(String dtxErrorcd)
    {
        dtxErrorcd = dtxErrorcd;
    }

    public static String getTranErrorcd()
    {
        return tranErrorcd;
    }

    public static void setTranErrorcd(String tranErrorcd)
    {
        tranErrorcd = tranErrorcd;
    }

    private OnlineTransactionConstant()
    {
    }

    private static String dtxErrortx;
    private static String dtxErrorcd;
    private static String tranErrorcd;
}
