// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginConf.java

package cn.sunline.adp.cedar.base.boot.plugin.model;

import cn.sunline.adp.metadata.base.util.ModelFullId;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.model:
//            IOrder

public class PluginConf
    implements IOrder, ModelFullId
{

    public PluginConf()
    {
        id = "";
        displayName = "";
        activator = "";
        order = 0;
        enable = true;
        failOnInitError = true;
        extensions = new ArrayList();
        extensionPoints = new ArrayList();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public boolean isEnable()
    {
        return enable;
    }

    public void setEnable(boolean enable)
    {
        this.enable = enable;
    }

    public boolean isFailOnInitError()
    {
        return failOnInitError;
    }

    public void setFailOnInitError(boolean failOnInitError)
    {
        this.failOnInitError = failOnInitError;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public String getActivator()
    {
        return activator;
    }

    public void setActivator(String activator)
    {
        this.activator = activator;
    }

    public List getExtensionPoints()
    {
        return extensionPoints;
    }

    public void setExtensionPoints(List extensionPoints)
    {
        this.extensionPoints = extensionPoints;
    }

    public List getExtensions()
    {
        return extensions;
    }

    public void setExtensions(List extensions)
    {
        this.extensions = extensions;
    }

    public String getFullId()
    {
        return id;
    }

    private String id;
    private String displayName;
    private String activator;
    private int order;
    private boolean enable;
    private boolean failOnInitError;
    private List extensions;
    private List extensionPoints;
}
