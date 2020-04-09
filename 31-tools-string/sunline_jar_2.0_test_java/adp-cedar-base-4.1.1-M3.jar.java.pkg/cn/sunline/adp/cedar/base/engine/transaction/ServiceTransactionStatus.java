// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceTransactionStatus.java

package cn.sunline.adp.cedar.base.engine.transaction;


public final class ServiceTransactionStatus extends Enum
{

    public static ServiceTransactionStatus[] values()
    {
        return (ServiceTransactionStatus[])$VALUES.clone();
    }

    public static ServiceTransactionStatus valueOf(String name)
    {
        return (ServiceTransactionStatus)Enum.valueOf(cn/sunline/adp/cedar/base/engine/transaction/ServiceTransactionStatus, name);
    }

    private ServiceTransactionStatus(String s, int i, String value)
    {
        super(s, i);
        value_ = value;
    }

    public String getValue()
    {
        return value_;
    }

    public static final ServiceTransactionStatus SyncC2Success;
    public static final ServiceTransactionStatus SyncC2Fail;
    public static final ServiceTransactionStatus SyncC2FailToAsync;
    public static final ServiceTransactionStatus SyncC2FailToCancel;
    public static final ServiceTransactionStatus SyncRbSuccess;
    public static final ServiceTransactionStatus SyncRbFail;
    public static final ServiceTransactionStatus SyncRbFailToAsync;
    private String value_;
    private static final ServiceTransactionStatus $VALUES[];

    static 
    {
        SyncC2Success = new ServiceTransactionStatus("SyncC2Success", 0, "SyncC2Success");
        SyncC2Fail = new ServiceTransactionStatus("SyncC2Fail", 1, "SyncC2Fail");
        SyncC2FailToAsync = new ServiceTransactionStatus("SyncC2FailToAsync", 2, "SyncC2FailToAsync");
        SyncC2FailToCancel = new ServiceTransactionStatus("SyncC2FailToCancel", 3, "SyncC2FailToCancel");
        SyncRbSuccess = new ServiceTransactionStatus("SyncRbSuccess", 4, "SyncRbSuccess");
        SyncRbFail = new ServiceTransactionStatus("SyncRbFail", 5, "SyncRbFail");
        SyncRbFailToAsync = new ServiceTransactionStatus("SyncRbFailToAsync", 6, "SyncRbFailToAsync");
        $VALUES = (new ServiceTransactionStatus[] {
            SyncC2Success, SyncC2Fail, SyncC2FailToAsync, SyncC2FailToCancel, SyncRbSuccess, SyncRbFail, SyncRbFailToAsync
        });
    }
}
