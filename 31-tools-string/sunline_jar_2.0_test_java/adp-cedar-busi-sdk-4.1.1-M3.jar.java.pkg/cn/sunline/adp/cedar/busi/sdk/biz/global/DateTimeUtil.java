// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateTimeUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.edsp.base.constant.DateConstant;
import cn.sunline.edsp.base.util.date.DateUtil;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.biz.global:
//            ConvertUtil

public class DateTimeUtil
{

    public DateTimeUtil()
    {
    }

    public static String dateAdd(String type, String date, int num)
    {
        return DateUtil.dateAdd(type, date, num);
    }

    public static int dateDiff(String type, String firstDate, String secondDate)
    {
        return DateUtil.dateDiff(type, firstDate, secondDate);
    }

    public static String lastDay(String date, String type)
    {
        return DateUtil.lastDay(date, type);
    }

    public static String firstDay(String date, String type)
    {
        return DateUtil.firstDay(date, type);
    }

    public static int datePart(String type, String date)
    {
        return DateUtil.datePart(type, date);
    }

    public static String getNow(String format)
    {
        return DateUtil.getNow(format);
    }

    public static String getNow()
    {
        return getNow(ConvertUtil.DEFAULT_DATE8_PATTERN);
    }

    public static boolean isDate(String val, String pattern)
    {
        boolean ret = false;
        Date date = null;
        try
        {
            date = ConvertUtil.toDate(val, pattern);
        }
        catch(Exception e)
        {
            ret = false;
        }
        if(date != null)
            ret = true;
        return ret;
    }

    public static String mountDate(String dateString, int day)
    {
        Date date = ConvertUtil.toDate(dateString, "yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(DateUtil.getMillis(date) - (long)day * 24L * 3600L * 1000L);
        return DateUtil.toString(c.getTime(), "yyyyMMdd");
    }
}
