// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataMappingUtil.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.metadata.model.datainterface.DataMapping;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.datamapping:
//            DataMappingUtil2

public class DataMappingUtil
{

    public DataMappingUtil()
    {
    }

    public static Object dataMapping(List srcElmts, List destElmts, Object src, Object dest, DataMapping mapping)
    {
        return dataMapping(srcElmts, destElmts, src, dest, mapping, false);
    }

    public static Object dataMapping(List srcElmts, List destElmts, Object src, Object dest, DataMapping mapping, boolean validate)
    {
        return dataMapping(srcElmts, destElmts, src, dest, mapping, validate, false);
    }

    public static Object dataMapping(List srcElmts, List destElmts, Object src, Object dest, DataMapping mapping, boolean validate, boolean mappingNullField)
    {
        return dataMapping(null, srcElmts, destElmts, src, dest, mapping, validate, mappingNullField);
    }

    public static Object dataMapping(String mappingClassName, List srcElmts, List destElmts, Object src, Object dest, DataMapping mapping)
    {
        return dataMapping(mappingClassName, srcElmts, destElmts, src, dest, mapping, false, false);
    }

    public static Object dataMapping(String mappingClassName, List srcElmts, List destElmts, Object src, Object dest, DataMapping mapping, boolean validate, boolean mappingNullField)
    {
        Class type = ((Class) ((dest instanceof HashMap) ? java/util/HashMap : ModelUtil.getOriginalClass(dest.getClass())));
        Object ret = DataMappingUtil2.dataMapping(srcElmts, destElmts, mapping, type, src, dest, false, validate, mappingNullField);
        return ret;
    }

    public static boolean isExpression(String s)
    {
        if(s == null)
            return false;
        boolean flag = false;
        int length = s.length();
        for(int i = 0; i < length; i++)
        {
            char c = s.charAt(i);
            if(i == 0)
            {
                if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
                    continue;
                flag = true;
                break;
            }
            if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '.' || c == '_')
                continue;
            flag = true;
            break;
        }

        return flag;
    }
}
