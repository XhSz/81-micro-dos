// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractComponentInstanceSupport.java

package cn.sunline.adp.cedar.base.component.impl;

import cn.sunline.adp.cedar.base.component.ComponentInstance;
import cn.sunline.adp.metadata.base.odb.OdbFactory;
import cn.sunline.adp.metadata.base.odb.OdbManager;
import cn.sunline.adp.metadata.model.component.AbstractComponentInstance;
import cn.sunline.adp.metadata.model.component.ComponentImplementation;

public abstract class AbstractComponentInstanceSupport
    implements ComponentInstance
{

    public AbstractComponentInstanceSupport()
    {
    }

    public AbstractComponentInstance getAbstractComponentInstanceObj()
    {
        if(aci != null)
            return aci;
        else
            return aci = (AbstractComponentInstance)OdbFactory.get().getOdbManager(cn/sunline/adp/metadata/model/component/AbstractComponentInstance).selectByKey(new Object[] {
                getAbstractComponentInstance()
            });
    }

    public ComponentImplementation getComponentImplementationObj()
    {
        if(ci != null)
            return ci;
        else
            return ci = (ComponentImplementation)OdbFactory.get().getOdbManager(cn/sunline/adp/metadata/model/component/ComponentImplementation).selectByKey(new Object[] {
                getComponentImplementation()
            });
    }

    private AbstractComponentInstance aci;
    private ComponentImplementation ci;
}
