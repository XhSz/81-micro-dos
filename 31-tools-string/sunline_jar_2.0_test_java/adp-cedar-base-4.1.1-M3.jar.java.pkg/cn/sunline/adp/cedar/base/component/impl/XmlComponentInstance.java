// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlComponentInstance.java

package cn.sunline.adp.cedar.base.component.impl;

import cn.sunline.adp.cedar.base.component.ComponentInstance;
import cn.sunline.adp.metadata.model.component.ComponentInstanceConf;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.component.impl:
//            AbstractComponentInstanceSupport

public class XmlComponentInstance extends AbstractComponentInstanceSupport
{
    public static class XmlProperty
        implements cn.sunline.adp.cedar.base.component.ComponentInstance.Property
    {

        public String getName()
        {
            return property.getName();
        }

        public String getValue()
        {
            return property.getValue();
        }

        private cn.sunline.adp.metadata.model.component.ComponentInstanceConf.Property property;

        public XmlProperty(cn.sunline.adp.metadata.model.component.ComponentInstanceConf.Property property)
        {
            this.property = property;
        }
    }


    public XmlComponentInstance(ComponentInstanceConf config)
    {
        this.config = config;
        if(config != null && config.getProperties() != null)
        {
            properties = new ArrayList();
            cn.sunline.adp.metadata.model.component.ComponentInstanceConf.Property property;
            for(Iterator iterator = config.getProperties().iterator(); iterator.hasNext(); properties.add(new XmlProperty(property)))
                property = (cn.sunline.adp.metadata.model.component.ComponentInstanceConf.Property)iterator.next();

        }
    }

    public String getId()
    {
        return config.getId();
    }

    public String getLongname()
    {
        return config.getLongname();
    }

    public String getDescription()
    {
        return config.getDescription();
    }

    public String getComponentType()
    {
        return config.getType();
    }

    public String getAbstractComponentInstance()
    {
        return config.getAbst();
    }

    public List getProperties()
    {
        return properties;
    }

    public String getComponentImplementation()
    {
        return config.getImpl();
    }

    public boolean getSingleton()
    {
        return config.getSingleton().booleanValue();
    }

    public boolean getSynchronous()
    {
        return config.getSynchronous().booleanValue();
    }

    private ComponentInstanceConf config;
    private List properties;
}
