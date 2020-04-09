// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceTransactionMode.java

package cn.sunline.adp.cedar.base.engine.transaction;


public final class ServiceTransactionMode extends Enum
{

    public static ServiceTransactionMode[] values()
    {
        return (ServiceTransactionMode[])$VALUES.clone();
    }

    public static ServiceTransactionMode valueOf(String name)
    {
        return (ServiceTransactionMode)Enum.valueOf(cn/sunline/adp/cedar/base/engine/transaction/ServiceTransactionMode, name);
    }

    private ServiceTransactionMode(String s, int i, String value)
    {
        super(s, i);
        value_ = value;
    }

    public boolean is2Committable()
    {
        static class _cls1
        {

            static final int $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$ServiceTransactionMode[];

            static 
            {
                $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$ServiceTransactionMode = new int[ServiceTransactionMode.values().length];
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$ServiceTransactionMode[ServiceTransactionMode.Required.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$ServiceTransactionMode[ServiceTransactionMode.NotSupported.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
            }
        }

        switch(_cls1..SwitchMap.cn.sunline.adp.cedar.base.engine.transaction.ServiceTransactionMode[ordinal()])
        {
        case 1: // '\001'
            return true;
        }
        return false;
    }

    public boolean isBeginable()
    {
        switch(_cls1..SwitchMap.cn.sunline.adp.cedar.base.engine.transaction.ServiceTransactionMode[ordinal()])
        {
        case 2: // '\002'
            return false;
        }
        return true;
    }

    public String getValue()
    {
        return value_;
    }

    public void setValue(String value)
    {
        value_ = value;
    }

    public static ServiceTransactionMode get(String value)
    {
        ServiceTransactionMode aservicetransactionmode[] = values();
        int i = aservicetransactionmode.length;
        for(int j = 0; j < i; j++)
        {
            ServiceTransactionMode mode = aservicetransactionmode[j];
            if(String.valueOf(mode.getValue()).equals(value))
                return mode;
        }

        return null;
    }

    public static final ServiceTransactionMode NotSupported;
    public static final ServiceTransactionMode Supports;
    public static final ServiceTransactionMode Required;
    private String value_;
    private static final ServiceTransactionMode $VALUES[];

    static 
    {
        NotSupported = new ServiceTransactionMode("NotSupported", 0, "NotSupported");
        Supports = new ServiceTransactionMode("Supports", 1, "Supports");
        Required = new ServiceTransactionMode("Required", 2, "Required");
        $VALUES = (new ServiceTransactionMode[] {
            NotSupported, Supports, Required
        });
    }
}
