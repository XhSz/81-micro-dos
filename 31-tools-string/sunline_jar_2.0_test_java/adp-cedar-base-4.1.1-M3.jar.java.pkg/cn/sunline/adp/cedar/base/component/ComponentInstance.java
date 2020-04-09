// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentInstance.java

package cn.sunline.adp.cedar.base.component;

import cn.sunline.adp.metadata.model.component.AbstractComponentInstance;
import cn.sunline.adp.metadata.model.component.ComponentImplementation;
import java.util.List;

public interface ComponentInstance
{
    public static interface Property
    {

        public abstract String getName();

        public abstract String getValue();
    }


    public abstract String getId();

    public abstract String getLongname();

    public abstract String getDescription();

    public abstract String getComponentType();

    public abstract String getAbstractComponentInstance();

    public abstract AbstractComponentInstance getAbstractComponentInstanceObj();

    public abstract List getProperties();

    public abstract String getComponentImplementation();

    public abstract ComponentImplementation getComponentImplementationObj();

    public abstract boolean getSingleton();

    public abstract boolean getSynchronous();
}
