// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseStatus.java

package cn.sunline.adp.cedar.base.net.rpc.model;


public final class ResponseStatus extends Enum
{

    public static ResponseStatus[] values()
    {
        return (ResponseStatus[])$VALUES.clone();
    }

    public static ResponseStatus valueOf(String name)
    {
        return (ResponseStatus)Enum.valueOf(cn/sunline/adp/cedar/base/net/rpc/model/ResponseStatus, name);
    }

    private ResponseStatus(String s, int i, String msg)
    {
        super(s, i);
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public static final ResponseStatus ok;
    public static final ResponseStatus busy;
    private String msg;
    private static final ResponseStatus $VALUES[];

    static 
    {
        ok = new ResponseStatus("ok", 0, "\u6210\u529F");
        busy = new ResponseStatus("busy", 1, "\u670D\u52A1\u5668\u7E41\u5FD9");
        $VALUES = (new ResponseStatus[] {
            ok, busy
        });
    }
}
