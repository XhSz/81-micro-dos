// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseProfileValidator.java

package cn.sunline.ltts.frw.model.ddv;

import cn.sunline.common.util.Convert;
import cn.sunline.ltts.base.util.*;
import cn.sunline.ltts.core.api.exception.LttsBusinessException;
import cn.sunline.ltts.core.api.model.dm.Element;
import cn.sunline.ltts.core.api.model.dm.ElementType;
import cn.sunline.ltts.core.api.util.ProfileSwitcher;
import cn.sunline.ltts.core.api.util.ProfileUtil;
import cn.sunline.ltts.frw.model.datainterface.DataInterfaceField;
import cn.sunline.ltts.frw.model.dm.*;
import cn.sunline.ltts.frw.model.util.ModelUtil;
import cu.sunline.ltts.ModelRtConst;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class BaseProfileValidator
{

    public BaseProfileValidator()
    {
    }

    public static void checkFieldValidity(Element field, Object data)
    {
        ProfileUtil.start_record("dataMapping.validate");
        __checkFieldValidity(field, data);
        ProfileUtil.end_record("dataMapping.validate");
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        ProfileUtil.end_record("dataMapping.validate");
        throw exception;
    }

    private static void __checkFieldValidity(Element field, Object data)
    {
        if(field.getMulti().booleanValue())
        {
            if(null != data && (data instanceof List))
            {
                Iterator i$ = ((List)data).iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    Object d = i$.next();
                    if(!StringUtil.isEmpty(d))
                        checkField(field, d);
                } while(true);
            }
        } else
        {
            checkField(field, data);
        }
    }

    private static void checkField(Element field, Object data)
    {
        checkFieldRequiredValidity(field, data);
        if(StringUtil.isEmpty(data))
            return;
        RestrictionType rt = ModelUtil.getRestrictionType(field.getTypeObj());
        checkDateFieldValidity(rt, field, data);
        checkEnumFieldValidity(rt, field, data);
        SimpleType st = ModelUtil.getSimpleType(field.getTypeObj());
        if(st != null)
            checkSimpleFieldValidity(rt, st, field, data);
    }

    private static void checkFieldRequiredValidity(Element field, Object data)
    {
        if(field.getRequired() != null && field.getRequired().booleanValue() && StringUtil.isEmpty(field.getDefaultValue()) && StringUtil.isEmpty(field.getFixedValue()) && StringUtil.isEmpty(data))
            throw new LttsBusinessException("0423", String.format(ModelRtConst.BaseProfileValidator01, new Object[] {
                field.getLongname()
            }));
        else
            return;
    }

    private static void checkDateFieldValidity(RestrictionType rt, Element field, Object data)
    {
        if(rt != null && (rt.getBaseTypeObj() == SimpleType.dateString || rt.getBaseTypeObj() == SimpleType.dateString8 || rt.getBaseTypeObj() == SimpleType.timeString17))
        {
            String pattern = null;
            String value = toString(data);
            try
            {
                if(rt.getBaseTypeObj() == SimpleType.dateString)
                {
                    if(StringUtil.isNotEmpty(rt.getPattern()))
                        pattern = rt.getPattern();
                    else
                        pattern = DateConvertUtil.DEFAULT_DATE8_PATTERN;
                } else
                if(rt.getBaseTypeObj() == SimpleType.timeString17)
                    pattern = DateConvertUtil.DEFAULT_DATE_TIME_PATTERN;
                else
                if(rt.getBaseTypeObj() == SimpleType.dateString8)
                    pattern = DateConvertUtil.DEFAULT_DATE8_PATTERN;
                Convert.toDate(value, pattern);
            }
            catch(Exception e)
            {
                throw new LttsBusinessException("0421", String.format(ModelRtConst.BaseProfileValidator02, new Object[] {
                    field.getLongname(), value, pattern
                }), e);
            }
        }
    }

    private static void checkEnumFieldValidity(RestrictionType rt, Element field, Object data)
    {
        String value = toString(data);
        if(rt != null && rt.getEnumerations() != null && rt.getEnumerations().size() != 0)
        {
            if(StringUtil.isNotEmpty(value) && !ModelUtil.isEnumValue(field.getTypeObj(), value))
                throw new LttsBusinessException("0423", String.format(ModelRtConst.BaseProfileValidator03, new Object[] {
                    field.getLongname(), field.getLongname(), field.getId(), value, field.getTypeObj().getFullId()
                }));
            if(field instanceof DataInterfaceField)
            {
                DataInterfaceField df = (DataInterfaceField)field;
                String s = df.getEnums();
                if(StringUtil.isNotEmpty(s))
                {
                    String ss[] = PropertyUtil.split(s);
                    boolean found = false;
                    Enumeration enu_temp = null;
                    Iterator i$ = rt.getEnumerations().iterator();
                    do
                    {
                        if(!i$.hasNext())
                            break;
                        Enumeration enu = (Enumeration)i$.next();
                        if(!enu.getValue().equals(value))
                            continue;
                        enu_temp = enu;
                        break;
                    } while(true);
                    if(enu_temp == null)
                        throw new LttsBusinessException("0424", String.format(ModelRtConst.BaseProfileValidator04, new Object[] {
                            field.getLongname(), field.getId(), value, field.getTypeObj().getLongname(), field.getTypeObj().getFullId()
                        }));
                    String arr$[] = ss;
                    int len$ = arr$.length;
                    int i$ = 0;
                    do
                    {
                        if(i$ >= len$)
                            break;
                        String s1 = arr$[i$];
                        if(s1.equals(enu_temp.getId()))
                        {
                            found = true;
                            break;
                        }
                        i$++;
                    } while(true);
                    if(!found)
                        throw new LttsBusinessException("0425", String.format(ModelRtConst.BaseProfileValidator05, new Object[] {
                            field.getLongname(), field.getLongname(), field.getId(), value, ss
                        }));
                }
            }
        } else
        if(field instanceof DataInterfaceField)
        {
            DataInterfaceField df = (DataInterfaceField)field;
            String s = df.getEnums();
            if(StringUtil.isNotEmpty(s))
            {
                boolean found = false;
                String arr$[] = PropertyUtil.split(s);
                int len$ = arr$.length;
                int i$ = 0;
                do
                {
                    if(i$ >= len$)
                        break;
                    String s1 = arr$[i$];
                    if(value.equals(s1))
                    {
                        found = true;
                        break;
                    }
                    i$++;
                } while(true);
                if(!found)
                    throw new LttsBusinessException("0423", String.format(ModelRtConst.BaseProfileValidator05, new Object[] {
                        field.getLongname(), field.getLongname(), field.getId(), value, s
                    }));
            }
        }
    }

    private static String toString(Object data)
    {
        if(data == null)
            return null;
        if(data instanceof String)
            return ((String)data).trim();
        if(data instanceof BigDecimal)
            return ((BigDecimal)data).toPlainString().trim();
        else
            return data.toString().trim();
    }

    private static void checkSimpleFieldValidity(RestrictionType rt, SimpleType st, Element field, Object data)
    {
        if(rt != null && st == SimpleType.decimal || st == SimpleType.amount)
        {
            if(!(data instanceof BigDecimal))
                try
                {
                    data = ConvertUtil.convert(data, st.getJavaClass());
                }
                catch(Throwable ex)
                {
                    throw new LttsBusinessException("0423", String.format(ModelRtConst.BaseProfileValidator06, new Object[] {
                        field.getLongname(), field.getLongname(), field.getId(), data
                    }));
                }
            String value = ((BigDecimal)data).toPlainString();
            int intPartLen;
            int dotPartLen;
            if(st == SimpleType.decimal)
            {
                dotPartLen = rt.getFractionDigits() != null ? rt.getFractionDigits().intValue() : 0;
                intPartLen = rt.getMaxLength().intValue() - dotPartLen;
            } else
            {
                intPartLen = 20;
                dotPartLen = 2;
            }
            checkStringIsLegalDecimal(field, value, intPartLen, dotPartLen);
        } else
        if(rt != null)
        {
            String value = toString(data);
            int strLen;
            if(ProfileSwitcher.get().forceCheckLenByChar || rt.getByCharacter() != null && rt.getByCharacter().booleanValue() || st == SimpleType.cString)
                strLen = value.length();
            else
                strLen = StringUtil.gbStrLen(value);
            if(rt.getMinLength() != null && strLen < rt.getMinLength().intValue())
                throw new LttsBusinessException("0423", String.format(ModelRtConst.BaseProfileValidator07, new Object[] {
                    field.getLongname(), field.getLongname(), field.getId(), value, rt.getMinLength()
                }));
            if(rt.getMaxLength() != null && strLen > rt.getMaxLength().intValue())
                throw new LttsBusinessException("0423", String.format(ModelRtConst.BaseProfileValidator08, new Object[] {
                    field.getLongname(), field.getLongname(), field.getId(), value, rt.getMaxLength()
                }));
        }
    }

    private static void checkStringIsLegalDecimal(Element field, String value, int intPartLen, int dotPartLen)
    {
        char c = value.charAt(0);
        if(c == '+' || c == '-')
            value = value.substring(1);
        int idx = value.indexOf(".");
        String intPart = idx == -1 ? value : value.substring(0, idx);
        String dotPart = idx == -1 ? "" : value.substring(idx + 1);
        intPart = trimLeftZero(intPart);
        dotPart = trimRightZero(dotPart);
        if(StringUtil.isNotEmpty(intPart))
        {
            if(!isDigits(intPart))
                throw new LttsBusinessException("0422", String.format(ModelRtConst.BaseProfileValidator09, new Object[] {
                    field.getLongname(), field.getLongname(), field.getId(), intPart
                }));
            if(intPart.length() > intPartLen)
                throw new LttsBusinessException("0422", String.format(ModelRtConst.BaseProfileValidator10, new Object[] {
                    field.getLongname(), field.getLongname(), field.getId(), value, Integer.valueOf(intPartLen)
                }));
        }
        if(StringUtil.isNotEmpty(dotPart))
        {
            if(!isDigits(dotPart))
                throw new LttsBusinessException("0422", String.format(ModelRtConst.BaseProfileValidator11, new Object[] {
                    field.getLongname(), field.getLongname(), field.getId(), dotPart
                }));
            if(dotPart.length() > dotPartLen)
                throw new LttsBusinessException("0422", String.format(ModelRtConst.BaseProfileValidator12, new Object[] {
                    field.getLongname(), field.getLongname(), field.getId(), value, Integer.valueOf(dotPartLen)
                }));
        }
    }

    private static boolean isDigits(String str)
    {
        if(str == null)
            return false;
        for(int i = 0; i < str.length(); i++)
            if(!Character.isDigit(str.charAt(i)))
                return false;

        return true;
    }

    private static String trimLeftZero(String value)
    {
        int end = value.length() - 1;
        int start;
        for(start = 0; start <= end && value.charAt(start) == '0'; start++);
        return value.substring(start);
    }

    private static String trimRightZero(String value)
    {
        int end = value.length() - 1;
        int start;
        for(start = 0; end > start && value.charAt(end) == '0'; end--);
        return value.substring(start, end + 1);
    }
}
