// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlConfigManager.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.util.JaxbUtil;
import cn.sunline.ltts.base.util.LogUtil;
import cn.sunline.ltts.core.api.model.*;
import cn.sunline.ltts.core.util.LanguageListener;
import java.io.*;
import java.util.*;
import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelClassManager, JaxbProvider, ModelObjectPartnerAware, ModelObjectPartner, 
//            ModelObjectLocator

public class XmlConfigManager
{
    private static class OwnerAwareSolver extends javax.xml.bind.Unmarshaller.Listener
    {

        public void beforeUnmarshal(Object target, Object parent)
        {
            LanguageListener.relevance(target, parent);
            if(target instanceof OwnerAware)
                ((OwnerAware)target).setOwner(parent);
            if((target instanceof ModelObjectPartnerAware) && XmlConfigManager.isGetFieldLocation())
            {
                ModelObjectPartnerAware p = (ModelObjectPartnerAware)target;
                if(p.getModelObjectPartner().getLocator() != null)
                {
                    p.getModelObjectPartner().getLocator().setStartElementEndLocator(JaxbUtil.getLocator());
                    p.getModelObjectPartner().getLocator().setFile(XmlConfigManager.getModelFile());
                }
            }
            if(JaxbUtil.Vars.get() != null)
            {
                for(int i = ((List)JaxbUtil.Vars.get()).size() - 1; i >= 0 && ((cn.sunline.ltts.base.util.JaxbUtil.Var)((List)JaxbUtil.Vars.get()).get(i)).type == null; i--)
                    ((cn.sunline.ltts.base.util.JaxbUtil.Var)((List)JaxbUtil.Vars.get()).get(i)).type = target.getClass();

            }
            if(XmlConfigManager.UnmarshallListener.get() != null)
                ((javax.xml.bind.Unmarshaller.Listener)XmlConfigManager.UnmarshallListener.get()).beforeUnmarshal(target, parent);
        }

        public void afterUnmarshal(Object target, Object parent)
        {
            LanguageListener.convertLanguage(target, parent);
            if((target instanceof ModelObjectPartnerAware) && XmlConfigManager.isGetFieldLocation())
            {
                ModelObjectPartnerAware p = (ModelObjectPartnerAware)target;
                if(p.getModelObjectPartner().getLocator() != null)
                    p.getModelObjectPartner().getLocator().setEndElementEndLocator(JaxbUtil.getLocator());
            }
            if(target instanceof ModelFileAware)
                ((ModelFileAware)target).setModelFile(XmlConfigManager.getModelFile());
            if(XmlConfigManager.UnmarshallListener.get() != null)
                ((javax.xml.bind.Unmarshaller.Listener)XmlConfigManager.UnmarshallListener.get()).afterUnmarshal(target, parent);
        }

        private OwnerAwareSolver()
        {
        }

    }


    public static void setModelFile(ModelFile emptyPropertyToNull)
    {
        ModelFile.set(emptyPropertyToNull);
    }

    public static ModelFile getModelFile()
    {
        return (ModelFile)ModelFile.get();
    }

    public static void setTypObjToNull(boolean isTypObjToNull)
    {
        TypObjToNull.set(Boolean.valueOf(isTypObjToNull));
    }

    public static boolean isTypObjToNull()
    {
        return TypObjToNull.get() != null ? ((Boolean)TypObjToNull.get()).booleanValue() : false;
    }

    public static void setLazyResolveModelRelation(boolean lazyResolveModelRelation)
    {
        LazyResolveModelRelation.set(Boolean.valueOf(lazyResolveModelRelation));
    }

    public static boolean isLazyResolveModelRelation()
    {
        return LazyResolveModelRelation.get() != null ? ((Boolean)LazyResolveModelRelation.get()).booleanValue() : true;
    }

    public static void setEmptyPropertyToNull(boolean emptyPropertyToNull)
    {
        EmptyPropertyToNull.set(Boolean.valueOf(emptyPropertyToNull));
    }

    public static boolean isEmptyPropertyToNull()
    {
        return EmptyPropertyToNull.get() != null ? ((Boolean)EmptyPropertyToNull.get()).booleanValue() : false;
    }

    private static void setCreatingModelObject(boolean creatingModelObject)
    {
        CreatingModelObject.set(Boolean.valueOf(creatingModelObject));
    }

    public static boolean isCreatingModelObject()
    {
        return CreatingModelObject.get() != null ? ((Boolean)CreatingModelObject.get()).booleanValue() : false;
    }

    public static void setGetFieldLocation(boolean emptyPropertyToNull)
    {
        GetFieldLocation.set(Boolean.valueOf(emptyPropertyToNull));
    }

    public static boolean isGetFieldLocation()
    {
        return GetFieldLocation.get() != null ? ((Boolean)GetFieldLocation.get()).booleanValue() : false;
    }

    public XmlConfigManager()
    {
        this((Class[])ModelClassManager.allModelClasses.toArray(new Class[ModelClassManager.allModelClasses.size()]));
    }

    public XmlConfigManager(Class classes[])
    {
        try
        {
            context = JAXBContext.newInstance(classes);
        }
        catch(Exception e)
        {
            if(!autoFirePropertyChangeEvent.booleanValue())
                throw new IllegalArgumentException(e);
            LogUtil.log.error((new StringBuilder()).append("ModelFactoryUtil======").append(e.getMessage()).toString());
            LogUtil.log.error(e);
        }
    }

    protected Marshaller getMarshaller()
    {
        try
        {
            Marshaller ret = context.createMarshaller();
            ret.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
            ret.setProperty("jaxb.noNamespaceSchemaLocation", "ltts-model.xsd");
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
            unmarshaller.setListener(new OwnerAwareSolver());
            return unmarshaller;
        }
        catch(JAXBException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public Object load(InputStream input)
    {
        Boolean old;
        old = autoFirePropertyChangeEvent;
        autoFirePropertyChangeEvent = Boolean.valueOf(false);
        setCreatingModelObject(true);
        Object obj;
        try
        {
            obj = getUnmarshaller().unmarshal(input);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(e);
        }
        setCreatingModelObject(false);
        autoFirePropertyChangeEvent = old;
        return obj;
        Exception exception;
        exception;
        setCreatingModelObject(false);
        autoFirePropertyChangeEvent = old;
        throw exception;
    }

    public Object load(ModelFile file, InputStream input)
    {
        return load(file, input, null);
    }

    public Object load(ModelFile file, InputStream input, javax.xml.bind.Unmarshaller.Listener listener)
    {
        Boolean isLazyResolveModelRelation;
        final List events;
        isLazyResolveModelRelation = Boolean.valueOf(isLazyResolveModelRelation());
        events = new ArrayList();
        setModelFile(file);
        setGetFieldLocation(true);
        setLazyResolveModelRelation(false);
        setCreatingModelObject(true);
        UnmarshallListener.set(listener);
        Object obj;
        try
        {
            getUnmarshaller().setEventHandler(new ValidationEventHandler() {

                public boolean handleEvent(ValidationEvent event)
                {
                    events.add(event);
                    return false;
                }

                final List val$events;
                final XmlConfigManager this$0;

            
            {
                this$0 = XmlConfigManager.this;
                events = list;
                super();
            }
            }
);
            obj = getUnmarshaller().unmarshal(input);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Error: ").append(dumpError(events)).toString(), e);
        }
        UnmarshallListener.set(null);
        setModelFile(null);
        setGetFieldLocation(false);
        setCreatingModelObject(false);
        setLazyResolveModelRelation(isLazyResolveModelRelation.booleanValue());
        return obj;
        Exception exception;
        exception;
        UnmarshallListener.set(null);
        setModelFile(null);
        setGetFieldLocation(false);
        setCreatingModelObject(false);
        setLazyResolveModelRelation(isLazyResolveModelRelation.booleanValue());
        throw exception;
    }

    private String dumpError(List events)
    {
        StringBuffer ret = new StringBuffer();
        ValidationEvent event;
        for(Iterator i$ = events.iterator(); i$.hasNext(); ret.append(event.getMessage()).append("|"))
        {
            event = (ValidationEvent)i$.next();
            ret.append("[").append(getModelFile()).append("]");
            ret.append("[").append(event.getLocator().getLineNumber());
            ret.append(":").append(event.getLocator().getColumnNumber()).append("]");
        }

        return ret.toString();
    }

    public void save(Object o, OutputStream output)
    {
        setEmptyPropertyToNull(true);
        try
        {
            getMarshaller().marshal(o, output);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(e);
        }
        setEmptyPropertyToNull(false);
        break MISSING_BLOCK_LABEL_41;
        Exception exception;
        exception;
        setEmptyPropertyToNull(false);
        throw exception;
    }

    public Object clone(Object t)
        throws JAXBException, IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        getMarshaller().marshal(t, output);
        InputStream input = new ByteArrayInputStream(output.toByteArray());
        output.close();
        input.close();
        return getUnmarshaller().unmarshal(input);
    }

    public List parseVar(InputStream input)
    {
        final List events = new ArrayList();
        List list;
        try
        {
            JaxbUtil.Vars.set(new ArrayList());
            javax.xml.stream.XMLEventReader xer = XMLInputFactory.newInstance().createXMLEventReader(input);
            xer = new cn.sunline.ltts.base.util.JaxbUtil.MyXMLEventReader(xer);
            getUnmarshaller().setEventHandler(new ValidationEventHandler() {

                public boolean handleEvent(ValidationEvent event)
                {
                    events.add(event);
                    return false;
                }

                final List val$events;
                final XmlConfigManager this$0;

            
            {
                this$0 = XmlConfigManager.this;
                events = list;
                super();
            }
            }
);
            getUnmarshaller().unmarshal(xer);
            List ret = (List)JaxbUtil.Vars.get();
            list = ret;
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Error: ").append(dumpError(events)).toString(), e);
        }
        JaxbUtil.Vars.set(null);
        return list;
        Exception exception;
        exception;
        JaxbUtil.Vars.set(null);
        throw exception;
    }

    public static Boolean autoFirePropertyChangeEvent = Boolean.valueOf(false);
    private static final ThreadLocal ModelFile = new ThreadLocal();
    private static final ThreadLocal LazyResolveModelRelation = new ThreadLocal();
    private static final ThreadLocal EmptyPropertyToNull = new ThreadLocal();
    private static final ThreadLocal CreatingModelObject = new ThreadLocal();
    private static final ThreadLocal GetFieldLocation = new ThreadLocal();
    private static final ThreadLocal UnmarshallListener = new ThreadLocal();
    private static final ThreadLocal TypObjToNull = new ThreadLocal();
    public static boolean isSchemaValidate = true;
    public static boolean RUNTIME = false;
    public static final String SCHEMA_FILE = "ltts-model.xsd";
    private JAXBContext context;

    static 
    {
        JaxbProvider.useDefault();
    }

}
