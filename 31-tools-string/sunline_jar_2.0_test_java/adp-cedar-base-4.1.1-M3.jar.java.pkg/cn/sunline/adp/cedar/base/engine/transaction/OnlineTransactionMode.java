// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineTransactionMode.java

package cn.sunline.adp.cedar.base.engine.transaction;

import cn.sunline.edsp.base.util.lang.StringUtil;

public final class OnlineTransactionMode extends Enum
{

    public static OnlineTransactionMode[] values()
    {
        return (OnlineTransactionMode[])$VALUES.clone();
    }

    public static OnlineTransactionMode valueOf(String name)
    {
        return (OnlineTransactionMode)Enum.valueOf(cn/sunline/adp/cedar/base/engine/transaction/OnlineTransactionMode, name);
    }

    private OnlineTransactionMode(String s, int i, String id, String longname)
    {
        super(s, i);
        this.id = id;
        this.longname = longname;
    }

    public String getId()
    {
        return id;
    }

    public String getLongname()
    {
        return longname;
    }

    public static OnlineTransactionMode get(String id)
    {
        if(StringUtil.isEmpty(id))
            return null;
        OnlineTransactionMode aonlinetransactionmode[] = values();
        int i = aonlinetransactionmode.length;
        for(int j = 0; j < i; j++)
        {
            OnlineTransactionMode mode = aonlinetransactionmode[j];
            if(mode.getId().equals(id))
                return mode;
        }

        return null;
    }

    public static final OnlineTransactionMode READ_ONLY;
    public static final OnlineTransactionMode ATOMIC;
    public static final OnlineTransactionMode CHUNK;
    public static final OnlineTransactionMode DISTRIBUTED;
    private String id;
    private String longname;
    private static final OnlineTransactionMode $VALUES[];

    static 
    {
        READ_ONLY = new OnlineTransactionMode("READ_ONLY", 0, "R", "\u53EA\u8BFB\u6A21\u5F0F");
        ATOMIC = new OnlineTransactionMode("ATOMIC", 1, "A", "\u539F\u5B50\u4E8B\u52A1\u6A21\u5F0F");
        CHUNK = new OnlineTransactionMode("CHUNK", 2, "C", "\u4E8B\u52A1\u5757\u6A21\u5F0F");
        DISTRIBUTED = new OnlineTransactionMode("DISTRIBUTED", 3, "D", "\u5206\u5E03\u5F0F\u4E8B\u52A1\u6A21\u5F0F");
        $VALUES = (new OnlineTransactionMode[] {
            READ_ONLY, ATOMIC, CHUNK, DISTRIBUTED
        });
    }
}
