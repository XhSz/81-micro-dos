// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncStringConvertUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.metadata.model.Element;
import cn.sunline.adp.metadata.model.RestrictionType;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.lang.EncString;
import cn.sunline.edsp.base.util.lang.StringUtil;

public class EncStringConvertUtil
{

    public EncStringConvertUtil()
    {
    }

    public static Object convert(Object value, Class type, Element field)
        throws IllegalArgumentException
    {
        if(type == null)
            throw new IllegalArgumentException("type is null.");
        if(value == null)
            return null;
        if(type.isAssignableFrom(value.getClass()))
            return value;
        if(!type.isAssignableFrom(value.getClass()) && StringUtil.isEmpty(value))
            return null;
        if(cn/sunline/edsp/base/lang/EncString.isAssignableFrom(type))
        {
            RestrictionType rt = ModelUtil.getRestrictionType(field.getTypeObj());
            return new EncString(value.toString(), null, rt.getTags());
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can't convert value '").append(value).append("' to ").append(type).toString());
        }
    }
}
