// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LttsBusiBeanUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

class LttsBusiBeanUtil
{

    LttsBusiBeanUtil()
    {
    }

    private static transient Object newInstance(String className, String defaultClassName, Class expected, Object parms[])
        throws IllegalArgumentException
    {
        try
        {
            return newInstance(className, expected, parms);
        }
        catch(Exception e)
        {
            return newInstance(defaultClassName, expected, parms);
        }
    }

    private static transient Object newInstance(String className, Class expected, Object parms[])
        throws IllegalArgumentException
    {
        Class clazz;
        try
        {
            clazz = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
        }
        catch(ClassNotFoundException e)
        {
            throw ExceptionUtil.wrapThrow(e);
        }
        if(!expected.isAssignableFrom(clazz))
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E013(className, expected.getName());
        List parameterTypes;
        Object aobj[];
        int i;
        try
        {
            if(parms == null || parms.length == 0)
                return clazz.newInstance();
        }
        catch(Exception e)
        {
            throw ExceptionUtil.wrapThrow(e);
        }
        parameterTypes = new ArrayList();
        aobj = parms;
        i = aobj.length;
        for(int j = 0; j < i; j++)
        {
            Object o = aobj[j];
            parameterTypes.add(o.getClass());
        }

        return clazz.getConstructor((Class[])parameterTypes.toArray(new Class[0])).newInstance(parms);
    }
}
