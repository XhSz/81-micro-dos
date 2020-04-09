// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentConfig.java

package cn.sunline.adp.cedar.base.component;

import cn.sunline.adp.cedar.base.component.impl.XmlComponentInstanceController;

public class ComponentConfig
{

    public ComponentConfig()
    {
        componentInstanceController = DEFAULT_COMPONENT_INSTANCE_CONTROLLER;
    }

    public String getComponentInstanceController()
    {
        return componentInstanceController;
    }

    public void setComponentInstanceController(String componentInstanceController)
    {
        this.componentInstanceController = componentInstanceController;
    }

    private static final String DEFAULT_COMPONENT_INSTANCE_CONTROLLER = cn/sunline/adp/cedar/base/component/impl/XmlComponentInstanceController.getName();
    private String componentInstanceController;

}
