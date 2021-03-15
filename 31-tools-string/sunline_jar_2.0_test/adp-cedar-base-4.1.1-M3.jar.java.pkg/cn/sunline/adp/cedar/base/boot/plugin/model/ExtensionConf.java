// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionConf.java

package cn.sunline.adp.cedar.base.boot.plugin.model;


// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.model:
//            PluginConf

public class ExtensionConf
{

    public ExtensionConf()
    {
        enable = true;
        singleton = false;
    }

    public boolean isSingleton()
    {
        return singleton;
    }

    public void setSingleton(boolean singleton)
    {
        this.singleton = singleton;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getClazzImpl()
    {
        return clazzImpl;
    }

    public void setClazzImpl(String clazzImpl)
    {
        this.clazzImpl = clazzImpl;
    }

    public boolean isEnable()
    {
        return enable;
    }

    public void setEnable(boolean enable)
    {
        this.enable = enable;
    }

    public String getPointId()
    {
        return pointId;
    }

    public void setPointId(String pointId)
    {
        this.pointId = pointId;
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
    private String pointId;
    private String clazzImpl;
    private boolean enable;
    private boolean singleton;
}
