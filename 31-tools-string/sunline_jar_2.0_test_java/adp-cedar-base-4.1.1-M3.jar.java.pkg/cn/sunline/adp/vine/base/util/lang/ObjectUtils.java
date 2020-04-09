// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectUtils.java

package cn.sunline.adp.vine.base.util.lang;

import cn.sunline.adp.vine.base.exception.FlowException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ObjectUtils
{
    public static interface TypeInfo
    {

        public abstract void setDimension(int i);

        public abstract int getDimension();

        public abstract void setType(String s, String s1);

        public abstract String getType();

        public abstract void setPrimitiveType(boolean flag);

        public abstract boolean isPrimitiveType();
    }


    public ObjectUtils()
    {
    }

    public static void parseTypeInfo(Class type, TypeInfo info)
    {
        int dim = 0;
        Class t;
        for(t = type; t.isArray(); t = t.getComponentType())
            dim++;

        info.setType(t.getName(), getPrimitiveWrapperType(t).getName());
        info.setPrimitiveType(t.isPrimitive());
        info.setDimension(dim);
    }

    public static Class getPrimitiveWrapperType(Class type)
    {
        if(!type.isPrimitive())
            return type;
        else
            return getPrimitiveWrapperType(type.getName());
    }

    public static Class getPrimitiveWrapperType(String type)
    {
        if("boolean".equals(type))
            return java/lang/Boolean;
        if("byte".equals(type))
            return java/lang/Byte;
        if("char".equals(type))
            return java/lang/Character;
        if("short".equals(type))
            return java/lang/Short;
        if("int".equals(type))
            return java/lang/Integer;
        if("long".equals(type))
            return java/lang/Long;
        if("float".equals(type))
            return java/lang/Float;
        if("double".equals(type))
            return java/lang/Double;
        else
            throw new FlowException("exception.flow.type.unsupportedPrimitiveType", new Object[] {
                type
            });
    }

    public static Class getPrimitiveType(String type)
    {
        Class t = getType(type);
        return getPrimitiveType(t);
    }

    public static Class getPrimitiveType(Class type)
    {
        try
        {
            return (Class)type.getField("TYPE").get(null);
        }
        catch(Exception e)
        {
            throw new FlowException("exception.flow.type.unsupportedPrimitiveType", new Object[] {
                type
            });
        }
    }

    public static Class getType(String type)
    {
        try
        {
            return Class.forName(type);
        }
        catch(ClassNotFoundException e)
        {
            throw new FlowException("exception.flow.type.notFound", e, new Object[] {
                type
            });
        }
    }

    public static Class getType(Class type, int dimension)
    {
        return dimension != 0 ? Array.newInstance(getType(type, dimension - 1), 0).getClass() : type;
    }

    public static int getArrayTypeDimension(Class arrayType)
    {
        int dimension = 0;
        if(arrayType.isArray())
            dimension = ++dimension + getArrayTypeDimension(arrayType.getComponentType());
        return dimension;
    }

    public static String getArrayTypeCanonicalName(Class arrayType)
    {
        String name = "";
        if(arrayType.isArray())
            name = getArrayTypeCanonicalName(arrayType.getComponentType());
        else
            name = arrayType.getCanonicalName();
        return name;
    }
}
