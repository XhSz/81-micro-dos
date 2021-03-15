// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionPointType.java

package cn.sunline.adp.cedar.base.boot.plugin.model;


public final class ExtensionPointType extends Enum
{

    public static ExtensionPointType[] values()
    {
        return (ExtensionPointType[])$VALUES.clone();
    }

    public static ExtensionPointType valueOf(String name)
    {
        return (ExtensionPointType)Enum.valueOf(cn/sunline/adp/cedar/base/boot/plugin/model/ExtensionPointType, name);
    }

    private ExtensionPointType(String s, int i)
    {
        super(s, i);
    }

    public static final ExtensionPointType replace;
    public static final ExtensionPointType additional;
    private static final ExtensionPointType $VALUES[];

    static 
    {
        replace = new ExtensionPointType("replace", 0);
        additional = new ExtensionPointType("additional", 1);
        $VALUES = (new ExtensionPointType[] {
            replace, additional
        });
    }
}
