// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComplexTypeUtil.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.frw.model.util.ModelPropertyDescriptor;
import cn.sunline.ltts.base.util.ClassUtils;
import cn.sunline.ltts.core.api.model.ModelObjectCreator;
import cn.sunline.ltts.core.api.model.dm.ComplexType;
import cn.sunline.ltts.core.api.model.dm.Element;
import cn.sunline.ltts.core.api.util.LttsCoreBeanUtil;
import cn.sunline.ltts.frw.model.ModelFactory;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelFactoryUtil

public class ComplexTypeUtil
{

    public ComplexTypeUtil()
    {
    }

    public static transient Object merge(ComplexType type, boolean useProxy, Object objectsWithSuper2Sub[])
    {
        return useProxy ? mergeWithProxy(type, objectsWithSuper2Sub) : mergeWithoutProxy(type, objectsWithSuper2Sub);
    }

    public static transient Object mergeWithProxy(ComplexType type, Object objectsWithSuper2Sub[])
    {
        Class retType = type.getJavaClass();
        return Proxy.newProxyInstance(retType.getClassLoader(), new Class[] {
            retType
        }, new InvocationHandler(objectsWithSuper2Sub) {

            public Object invoke(Object proxy, Method method, Object args[])
                throws Throwable
            {
                if(ClassUtils.isGetterMethod(method))
                    return ComplexTypeUtil.getProperty(ModelPropertyDescriptor.get(method, true).getProperty(), objectsWithSuper2Sub);
                else
                    return method.invoke(proxy, args);
            }

            final Object val$objectsWithSuper2Sub[];

            
            {
                objectsWithSuper2Sub = aobj;
                super();
            }
        }
);
    }

    public static transient Object mergeWithoutProxy(ComplexType type, Object objectsWithSuper2Sub[])
    {
        Class retType = type.getJavaClass();
        Object ret = LttsCoreBeanUtil.getModelObjectCreator().create(retType);
        mergeComplexType(ret, type, objectsWithSuper2Sub);
        return ret;
    }

    private static transient void mergeComplexType(Object obj, ComplexType type, Object objectsWithSuper2Sub[])
    {
        Iterator i$ = type.getElements().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Element e = (Element)i$.next();
            Object value = getProperty(e.getId(), objectsWithSuper2Sub);
            if(value != null)
                ModelPropertyDescriptor.get(obj.getClass(), e.getId()).setProperty(obj, value);
        } while(true);
        if(type.getExtensionType() != null)
        {
            ComplexType arr$[] = type.getExtensionType();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                ComplexType ct = arr$[i$];
                mergeComplexType(obj, ct, objectsWithSuper2Sub);
            }

        }
    }

    private static transient Object getProperty(String property, Object objectsWithSuper2Sub[])
    {
        Object ret = null;
        Object arr$[] = objectsWithSuper2Sub;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Object o = arr$[i$];
            Object value = ModelPropertyDescriptor.get(o.getClass(), property).getProperty(o);
            if(value == null)
                continue;
            Element e = getElementByProperty(o.getClass(), property);
            if(e != null && e.getFinal() != null && e.getFinal().booleanValue())
                return value;
            ret = value;
        }

        return ret;
    }

    public static void init()
    {
        Map map = new ConcurrentHashMap();
        for(Iterator i$ = ModelFactoryUtil.getModelFactory().getModels(cn/sunline/ltts/core/api/model/dm/ComplexType).iterator(); i$.hasNext();)
        {
            ComplexType ct = (ComplexType)i$.next();
            try
            {
                map.put(ct.getJavaClass(), ct);
            }
            catch(Exception e) { }
        }

        map = map;
    }

    public static void init(Map map)
    {
        map = map;
    }

    public static ComplexType getTypeByJavaClass(Class javaClass)
    {
        ComplexType ret = (ComplexType)map.get(javaClass);
        if(ret == null)
            ret = ModelFactoryUtil.getClassComplexType(javaClass);
        if(ret == null && isProxyClass(javaClass) && javaClass.getInterfaces() != null && javaClass.getInterfaces().length > 0)
        {
            javaClass = javaClass.getInterfaces()[0];
            ret = ModelFactoryUtil.getClassComplexType(javaClass);
        }
        return ret;
    }

    private static boolean isProxyClass(Class javaClass)
    {
        return true;
    }

    public static Element getElementByMethod(Method method)
    {
        return getElementByProperty(method.getDeclaringClass(), ModelPropertyDescriptor.get(method, false).getProperty());
    }

    public static Element getElementByProperty(Class javaClass, String property)
    {
        ComplexType ct = getTypeByJavaClass(javaClass);
        if(ct != null)
            return getElementByProperty(ct, property);
        else
            return null;
    }

    public static Element getElementByProperty(ComplexType ct, String property)
    {
        for(Iterator i$ = ct.getElements().iterator(); i$.hasNext();)
        {
            Element e = (Element)i$.next();
            if(e.getId().equals(property))
                return e;
        }

        if(ct.getExtensionType() != null)
        {
            ComplexType arr$[] = ct.getExtensionType();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                ComplexType st = arr$[i$];
                Element e = getElementByProperty(st, property);
                if(e != null)
                    return e;
            }

        }
        return null;
    }

    public static Map map;

}
