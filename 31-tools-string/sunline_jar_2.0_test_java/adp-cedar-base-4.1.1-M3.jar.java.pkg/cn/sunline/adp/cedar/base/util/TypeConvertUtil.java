// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TypeConvertUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.edsp.base.util.convert.ConvertUtil;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;

public class TypeConvertUtil
{

    public TypeConvertUtil()
    {
    }

    public static Object convert(Object value, Class type, String propertyName)
    {
        try
        {
            return ConvertUtil.convert(value, type);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(EngineConst.TypeConvertUtil01, new Object[] {
                propertyName, value, type.getName()
            }), e);
        }
    }

    public static Object convert(Object value, Class type)
    {
        return ConvertUtil.convert(value, type);
    }

    private static Class getGenericType(Class type)
    {
        Class genericType = ReflectionUtil.getGenericClass(type);
        if(genericType == null)
            throw new IllegalArgumentException(String.format(EngineConst.TypeConvertUtil02, new Object[] {
                type
            }));
        else
            return genericType;
    }
}
