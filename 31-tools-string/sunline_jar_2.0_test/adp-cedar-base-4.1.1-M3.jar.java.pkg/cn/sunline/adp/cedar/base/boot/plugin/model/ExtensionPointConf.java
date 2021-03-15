// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionPointConf.java

package cn.sunline.adp.cedar.base.boot.plugin.model;


// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.model:
//            ExtensionPointType, PluginConf

public class ExtensionPointConf
{

    public ExtensionPointConf()
    {
        pointType = ExtensionPointType.additional;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ExtensionPointType getPointType()
    {
        return pointType;
    }

    public void setPointType(ExtensionPointType pointType)
    {
        this.pointType = pointType;
    }

    public String getClazz()
    {
        return clazz;
    }

    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public PluginConf getParent()
    {
        return modelParent;
    }

    public void setParent(PluginConf parent)
    {
        modelParent = parent;
    }

    private PluginConf modelParent;
    private String id;
    private String name;
    private ExtensionPointType pointType;
    private String clazz;
}
