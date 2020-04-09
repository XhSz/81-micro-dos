// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlConfigManager.java

package cn.sunline.adp.cedar.base.boot.plugin.impl;

import cn.sunline.adp.cedar.base.boot.plugin.util.PluginUtil;
import cn.sunline.adp.cedar.base.logging.IXMLListener;
import cn.sunline.edsp.base.factories.FactoriesLoader;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.*;

public class XmlConfigManager
{

    public XmlConfigManager(Class classes[])
    {
        try
        {
            context = JAXBContext.newInstance(classes);
        }
        catch(JAXBException e)
        {
            throw PluginUtil.wrapThrow("JAXBContext init error", e, new Object[0]);
        }
    }

    protected Marshaller getMarshaller()
    {
        try
        {
            Marshaller ret = context.createMarshaller();
            ret.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
            return ret;
        }
        catch(PropertyException e)
        {
            throw new IllegalArgumentException(e);
        }
        catch(JAXBException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    protected Unmarshaller getUnmarshaller()
    {
        try
        {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setListener(new javax.xml.bind.Unmarshaller.Listener() {

                public void beforeUnmarshal(Object paramObject1, Object paramObject2)
                {
                    xmlListener.beforeUnmarshal(paramObject1, paramObject2);
                }

                public void afterUnmarshal(Object paramObject1, Object paramObject2)
                {
                    xmlListener.afterUnmarshal(paramObject1, paramObject2);
                }

                final XmlConfigManager this$0;

            
            {
                this.this$0 = XmlConfigManager.this;
                super();
            }
            }
);
            return unmarshaller;
        }
        catch(JAXBException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public Object load(InputStream input)
    {
        try
        {
            return getUnmarshaller().unmarshal(input);
        }
        catch(Exception e)
        {
            throw PluginUtil.wrapThrow("jaxb load error", e, new Object[0]);
        }
    }

    public void save(Object o, OutputStream output)
    {
        try
        {
            getMarshaller().marshal(o, output);
        }
        catch(JAXBException e)
        {
            throw PluginUtil.wrapThrow("jaxb save error", e, new Object[0]);
        }
    }

    private JAXBContext context;
    private final IXMLListener xmlListener = (IXMLListener)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/logging/IXMLListener);

}
