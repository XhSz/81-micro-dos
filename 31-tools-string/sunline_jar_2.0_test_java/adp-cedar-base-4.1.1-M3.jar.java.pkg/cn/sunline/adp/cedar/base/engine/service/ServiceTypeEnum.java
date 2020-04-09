// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceTypeEnum.java

package cn.sunline.adp.cedar.base.engine.service;


public final class ServiceTypeEnum extends Enum
{

    public static ServiceTypeEnum[] values()
    {
        return (ServiceTypeEnum[])$VALUES.clone();
    }

    public static ServiceTypeEnum valueOf(String name)
    {
        return (ServiceTypeEnum)Enum.valueOf(cn/sunline/adp/cedar/base/engine/service/ServiceTypeEnum, name);
    }

    private ServiceTypeEnum(String s, int i, String value)
    {
        super(s, i);
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public static ServiceTypeEnum get(String value)
    {
        ServiceTypeEnum aservicetypeenum[] = values();
        int i = aservicetypeenum.length;
        for(int j = 0; j < i; j++)
        {
            ServiceTypeEnum st = aservicetypeenum[j];
            if(st.getValue().equals(value))
                return st;
        }

        return null;
    }

    public static final ServiceTypeEnum CHECK_SERVICE;
    public static final ServiceTypeEnum TRY_SERVICE;
    public static final ServiceTypeEnum CONFIRM_SERVICE;
    public static final ServiceTypeEnum CANCEL_SERVICE;
    public static final ServiceTypeEnum NOTIFY_SERVICE;
    private String value;
    private static final ServiceTypeEnum $VALUES[];

    static 
    {
        CHECK_SERVICE = new ServiceTypeEnum("CHECK_SERVICE", 0, "check");
        TRY_SERVICE = new ServiceTypeEnum("TRY_SERVICE", 1, "try");
        CONFIRM_SERVICE = new ServiceTypeEnum("CONFIRM_SERVICE", 2, "confirm");
        CANCEL_SERVICE = new ServiceTypeEnum("CANCEL_SERVICE", 3, "cancel");
        NOTIFY_SERVICE = new ServiceTypeEnum("NOTIFY_SERVICE", 4, "notify");
        $VALUES = (new ServiceTypeEnum[] {
            CHECK_SERVICE, TRY_SERVICE, CONFIRM_SERVICE, CANCEL_SERVICE, NOTIFY_SERVICE
        });
    }
}
