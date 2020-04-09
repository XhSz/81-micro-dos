// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApplicationBeanResources.java

package cn.sunline.adp.cedar.base.bean;

import cn.sunline.adp.cedar.base.ApiConst;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationBeanResources
{

    private ApplicationBeanResources()
    {
        beanMappings = new ConcurrentHashMap();
        defaultBeanMappings = new ConcurrentHashMap();
    }

    public void init()
    {
    }

    public static ApplicationBeanResources getInstance()
    {
        return instance;
    }

    public void putDefaultBeanMapping(Class intfClass, Class defaultImplClass)
    {
        defaultBeanMappings.put(intfClass, creatBean(intfClass, defaultImplClass));
    }

    public void putBeanMapping(Class intfClass, Class implClass)
    {
        beanMappings.put(intfClass, creatBean(intfClass, implClass));
    }

    public void putBeanMapping(Class intfClass, Object implBean)
    {
        if(intfClass == null || implBean == null)
            return;
        if(!intfClass.isAssignableFrom(implBean.getClass()))
        {
            throw new IllegalArgumentException(String.format(ApiConst.ApplicationBeanResources01, new Object[] {
                intfClass.getName(), implBean.getClass().getName()
            }));
        } else
        {
            beanMappings.put(intfClass, implBean);
            return;
        }
    }

    public Object getBean(Class intfClass)
    {
        Object ret = beanMappings.get(intfClass);
        if(ret == null)
        {
            ret = defaultBeanMappings.get(intfClass);
            if(ret != null)
                putBeanMapping(intfClass, ret);
        }
        return ret;
    }

    public void clear()
    {
        beanMappings.clear();
        defaultBeanMappings.clear();
    }

    private Object creatBean(Class intfClass, Class implClass)
    {
        if(!intfClass.isAssignableFrom(implClass))
            throw new IllegalArgumentException(String.format(ApiConst.ApplicationBeanResources01, new Object[] {
                intfClass.getName(), implClass.getName()
            }));
        Object bean = null;
        try
        {
            bean = implClass.newInstance();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(ApiConst.ApplicationBeanResources02, new Object[] {
                implClass.getName()
            }), e);
        }
        return bean;
    }

    private static ApplicationBeanResources instance = new ApplicationBeanResources();
    private Map beanMappings;
    private Map defaultBeanMappings;

}
