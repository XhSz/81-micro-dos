// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PkgMode.java

package cn.sunline.adp.cedar.base.pkg;


public final class PkgMode extends Enum
{

    public static PkgMode[] values()
    {
        return (PkgMode[])$VALUES.clone();
    }

    public static PkgMode valueOf(String name)
    {
        return (PkgMode)Enum.valueOf(cn/sunline/adp/cedar/base/pkg/PkgMode, name);
    }

    private PkgMode(String s, int i)
    {
        super(s, i);
    }

    public static final PkgMode request;
    public static final PkgMode response;
    private static final PkgMode $VALUES[];

    static 
    {
        request = new PkgMode("request", 0);
        response = new PkgMode("response", 1);
        $VALUES = (new PkgMode[] {
            request, response
        });
    }
}
