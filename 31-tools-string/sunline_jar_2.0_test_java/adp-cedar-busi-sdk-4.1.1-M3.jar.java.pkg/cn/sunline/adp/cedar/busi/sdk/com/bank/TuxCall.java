// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TuxCall.java

package cn.sunline.adp.cedar.busi.sdk.com.bank;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import java.io.UnsupportedEncodingException;

public class TuxCall
{

    public TuxCall()
    {
    }

    public static native String SendRecv(String s, String s1);

    public static native byte[] SendRecvEx(byte abyte0[], byte abyte1[]);

    public static native int filePut(String s, String s1, String s2);

    public static native int fileGet(String s, String s1, String s2);

    public static String Tpcall(String addr, String sendPackage, String encoding)
    {
        try
        {
            return new String(SendRecvEx(addr.getBytes(encoding), sendPackage.getBytes(encoding)), encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            return null;
        }
    }

    public static String Tpcall(String addr, String sendPackage)
    {
        return Tpcall(addr, sendPackage, "GB2312");
    }

    private static final SysLog log;

    static 
    {
        log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/com/bank/TuxCall);
        try
        {
            System.loadLibrary("TuxCall");
        }
        catch(Exception e)
        {
            log.error("LoadLibrary TuxCall Fail", e, new Object[0]);
        }
    }
}
