// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.core.bean.accessor.PropertyAccessor;
import cn.sunline.adp.metadata.base.util.CommUtil_;
import cn.sunline.adp.metadata.base.util.PropertyUtil;
import cn.sunline.edsp.base.util.convert.ConvertUtil;
import cn.sunline.edsp.base.util.convert.EnumUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.reflect.FieldUtils;

public class CommUtil
{
    public static class Operator extends Enum
    {

        public static Operator[] values()
        {
            return (Operator[])$VALUES.clone();
        }

        public static Operator valueOf(String name)
        {
            return (Operator)Enum.valueOf(cn/sunline/adp/cedar/base/util/CommUtil$Operator, name);
        }

        public Object add(Object data1, Object data2)
        {
            return null;
        }

        public Object sub(Object data1, Object data2)
        {
            return null;
        }

        public Object mul(Object data1, Object data2)
        {
            return null;
        }

        public Object div(Object data1, Object data2)
        {
            return null;
        }

        public static final Operator add;
        public static final Operator sub;
        public static final Operator mul;
        public static final Operator div;
        private String id;
        private String value;
        private String longName;
        private static final Operator $VALUES[];

        static 
        {
            add = new Operator("add", 0, "add", "add", "\u52A0") {

                public Object add(Object data1, Object data2)
                {
                    if(data1.getClass() == java/lang/Integer && data2.getClass() == java/lang/Integer)
                    {
                        Integer l1 = (Integer)data1;
                        Integer l2 = (Integer)data2;
                        return new Integer(l1.intValue() + l2.intValue());
                    }
                    if(data1.getClass() == java/lang/Long && data2.getClass() == java/lang/Long)
                    {
                        Long l1 = (Long)data1;
                        Long l2 = (Long)data2;
                        return new Long(l1.longValue() + l2.longValue());
                    }
                    if(data1.getClass() == java/math/BigDecimal && data2.getClass() == java/math/BigDecimal)
                    {
                        BigDecimal l1 = (BigDecimal)data1;
                        BigDecimal l2 = (BigDecimal)data2;
                        return l1.add(l2);
                    } else
                    {
                        return null;
                    }
                }

            }
;
            sub = new Operator("sub", 1, "sub", "sub", "\u51CF") {

                public Object sub(Object data1, Object data2)
                {
                    if(data1.getClass() == java/lang/Integer && data2.getClass() == java/lang/Integer)
                    {
                        Integer l1 = (Integer)data1;
                        Integer l2 = (Integer)data2;
                        return new Integer(l1.intValue() - l2.intValue());
                    }
                    if(data1.getClass() == java/lang/Long && data2.getClass() == java/lang/Long)
                    {
                        Long l1 = (Long)data1;
                        Long l2 = (Long)data2;
                        return new Long(l1.longValue() - l2.longValue());
                    }
                    if(data1.getClass() == java/math/BigDecimal && data2.getClass() == java/math/BigDecimal)
                    {
                        BigDecimal l1 = (BigDecimal)data1;
                        BigDecimal l2 = (BigDecimal)data2;
                        return l1.subtract(l2);
                    } else
                    {
                        return null;
                    }
                }

            }
;
            mul = new Operator("mul", 2, "mul", "mul", "\u4E58") {

                public Object mul(Object data1, Object data2)
                {
                    if(data1.getClass() == java/lang/Integer && data2.getClass() == java/lang/Integer)
                    {
                        Integer l1 = (Integer)data1;
                        Integer l2 = (Integer)data2;
                        return new Integer(l1.intValue() * l2.intValue());
                    }
                    if(data1.getClass() == java/lang/Long && data2.getClass() == java/lang/Long)
                    {
                        Long l1 = (Long)data1;
                        Long l2 = (Long)data2;
                        return new Long(l1.longValue() * l2.longValue());
                    }
                    if(data1.getClass() == java/math/BigDecimal && data2.getClass() == java/math/BigDecimal)
                    {
                        BigDecimal l1 = (BigDecimal)data1;
                        BigDecimal l2 = (BigDecimal)data2;
                        return l1.multiply(l2);
                    } else
                    {
                        return null;
                    }
                }

            }
;
            div = new Operator("div", 3, "div", "div", "\u9664") {

                public Object div(Object data1, Object data2)
                {
                    if(data1.getClass() == java/lang/Integer && data2.getClass() == java/lang/Integer)
                    {
                        Integer l1 = (Integer)data1;
                        Integer l2 = (Integer)data2;
                        return new Integer(l1.intValue() / l2.intValue());
                    }
                    if(data1.getClass() == java/lang/Long && data2.getClass() == java/lang/Long)
                    {
                        Long l1 = (Long)data1;
                        Long l2 = (Long)data2;
                        return new Long(l1.longValue() / l2.longValue());
                    }
                    if(data1.getClass() == java/math/BigDecimal && data2.getClass() == java/math/BigDecimal)
                    {
                        BigDecimal l1 = (BigDecimal)data1;
                        BigDecimal l2 = (BigDecimal)data2;
                        return l1.divide(l2);
                    } else
                    {
                        return null;
                    }
                }

            }
;
            $VALUES = (new Operator[] {
                add, sub, mul, div
            });
        }

        private Operator(String s, int i, String id, String value, String longName)
        {
            super(s, i);
            this.id = id;
            this.value = value;
            this.longName = longName;
        }

    }


    public CommUtil()
    {
    }

    public static Map toMap(Object bean)
    {
        return CommUtil_.toMap(bean);
    }

    public static List toListMap(List list)
    {
        return CommUtil_.toListMap(list);
    }

    public static void copyProperties(Object dest, Object src)
    {
        CommUtil_.copyProperties(dest, src);
    }

    public static void copyProperties(Object dest, Object src, boolean withNoEmpty)
    {
        CommUtil_.copyProperties(src, dest, withNoEmpty);
    }

    public static transient void copyPropertyList(Object dest, Object src, String propList[])
    {
        CommUtil_.copyPropertyList(dest, src, propList);
    }

    public static void copyPropertiesWithTypeConvert(Object dest, Object src)
    {
        CommUtil_.copyPropertiesWithTypeConvert(dest, src);
    }

    public static void copyPropertiesWithTypeConvert(Object dest, Object src, boolean withNoEmpty)
    {
        CommUtil_.copyPropertiesWithTypeConvert(dest, src, withNoEmpty);
    }

    public static Object getProperty(Object o, String fieldName)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        PropertyAccessor pa = PropertyUtil.createAccessor(o);
        if(pa == null)
            return null;
        else
            return pa.getNestedProperty(fieldName);
    }

    public static void setProperty(Object o, String fieldName, Object value)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        PropertyAccessor pa = PropertyUtil.createAccessor(o);
        if(pa == null)
        {
            return;
        } else
        {
            pa.setNestedProperty(fieldName, value);
            return;
        }
    }

    public static Class getPropType(Class clazz, String fieldName)
    {
        Field field = FieldUtils.getField(clazz, fieldName);
        if(field != null)
            return field.getType();
        else
            return null;
    }

    public static Object nvl(Object s, Object defaultValue)
    {
        return CommUtil_.nvl(s, defaultValue);
    }

    public static boolean isNull(Object o)
    {
        return CommUtil_.isNull(o);
    }

    public static boolean isNotNull(Object o)
    {
        return CommUtil_.isNotNull(o);
    }

    public static String trim(String s)
    {
        return CommUtil_.trim(s);
    }

    public static String rtrim(String s)
    {
        return CommUtil_.rtrim(s);
    }

    public static String ltrim(String s)
    {
        return CommUtil_.ltrim(s);
    }

    public static String lpad(String s, int i, String s1)
    {
        return CommUtil_.lpad(s, i, s1);
    }

    public static String lpad(String s, int i, String s1, String enCoding)
    {
        return CommUtil_.lpad(s, i, s1, enCoding);
    }

    public static String rpad(String s, int i, String s1)
    {
        return CommUtil_.rpad(s, i, s1);
    }

    public static String rpad(String s, int i, String s1, String enCoding)
    {
        return CommUtil_.rpad(s, i, s1, enCoding);
    }

    public static boolean like(String s1, String s2)
    {
        return CommUtil_.like(s1, s2);
    }

    public static boolean isInEnum(Class enumCls, Object value)
    {
        return EnumUtils.isInEnum(enumCls, value);
    }

    public static Enum toEnum(Class enumCls, Object value)
    {
        return EnumUtils.toEnum(enumCls, value);
    }

    public static transient boolean in(Object a, Object a1[])
    {
        return CommUtil_.in(a, a1);
    }

    public static boolean in(Object object, List objects)
    {
        return CommUtil_.in(object, objects);
    }

    public static boolean Between(String a, String start, String end)
    {
        return CommUtil_.Between(a, start, end);
    }

    public static boolean Between(Integer a, int start, int end)
    {
        return CommUtil_.Between(a, Integer.valueOf(start), Integer.valueOf(end));
    }

    public static boolean Between(int a, int start, int end)
    {
        return CommUtil_.Between(Integer.valueOf(a), Integer.valueOf(start), Integer.valueOf(end));
    }

    public static boolean Between(BigDecimal a, BigDecimal start, BigDecimal end)
    {
        return CommUtil_.Between(a, start, end);
    }

    public static int compare(Comparable o1, Comparable o2)
    {
        return CommUtil_.compare(o1, o2, false, true);
    }

    public static int compareIgnoreCase(String o1, String o2)
    {
        return CommUtil_.compare(o1, o2, true, true);
    }

    public static boolean equals(BigDecimal o1, BigDecimal o2)
    {
        return CommUtil_.compare(o1, o2) == 0;
    }

    public static boolean equals(String str1, String str2)
    {
        return CommUtil_.compare(str1, str2, false, true) == 0;
    }

    public static boolean equalsIgnoreCase(String str1, String str2)
    {
        return CommUtil_.compare(str1, str2, true, true) == 0;
    }

    public static boolean equals(String str1, String str2, boolean ignoreCase, boolean ignoreNullAndEmpty)
    {
        return CommUtil_.compare(str1, str2, ignoreCase, ignoreNullAndEmpty) == 0;
    }

    public static BigDecimal trunc(BigDecimal am)
    {
        return am != null ? ConvertUtil.toBigDecimal(am, (BigDecimal)null) : null;
    }

    public static BigDecimal round(BigDecimal amt, int scale)
    {
        return round(amt, scale, 4);
    }

    private static BigDecimal round(BigDecimal amt, int scale, int roundingMode)
    {
        return CommUtil_.round(amt, scale, roundingMode);
    }

    public static long floor(Object val)
    {
        return CommUtil_.floor(val);
    }

    public static long ceil(Object val)
    {
        return CommUtil_.ceil(val);
    }
}
