// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConvertUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.edsp.base.util.date.DateUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.time.DateFormatUtils;

public class ConvertUtil
{

    public ConvertUtil()
    {
    }

    public static Date toDate(String val)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toDate(val, DEFAULT_DATE8_PATTERN);
    }

    public static boolean isDate(String val)
    {
        Date data = null;
        try
        {
            data = toDate(val);
        }
        catch(Exception e)
        {
            return false;
        }
        return data != null;
    }

    public static Date toDate(String val, String pattern)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toDate(val, pattern);
    }

    public static String dateToString8(Date val)
    {
        return DateUtil.formatDate(val, DEFAULT_DATE8_PATTERN);
    }

    public static String dateToString(Date val, String pattern)
    {
        if(StringUtil.isEmpty(pattern))
            pattern = DEFAULT_DATE8_PATTERN;
        return DateUtil.formatDate(val, pattern);
    }

    public static String dateToString(Date val, String pattern, Locale locale)
    {
        if(StringUtil.isEmpty(pattern))
            pattern = DEFAULT_DATE8_PATTERN;
        return DateFormatUtils.format(val, pattern, locale);
    }

    public static BigDecimal toBigDecimal(Object val)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toBigDecimal(val, (BigDecimal)null);
    }

    public static BigDecimal toAmount(Object value)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toBigDecimal(value, "#,##0.##", null);
    }

    public static Float toFloat(Object value)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toFloat(value);
    }

    public static Double toDouble(Object value)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toDouble(value);
    }

    public static Integer toInteger(Object value)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toInteger(value);
    }

    public static Long toLong(Object value)
    {
        return cn.sunline.edsp.base.util.convert.ConvertUtil.toLong(value);
    }

    public static String DEFAULT_DATE8_PATTERN = "yyyyMMdd";
    public static String DEFAULT_DATE_TIME_PATTERN = "yyyyMMdd hh:mm:ss";
    public static String DEFAULT_TIME_PATTERN = "HH:mm:ss";

}
