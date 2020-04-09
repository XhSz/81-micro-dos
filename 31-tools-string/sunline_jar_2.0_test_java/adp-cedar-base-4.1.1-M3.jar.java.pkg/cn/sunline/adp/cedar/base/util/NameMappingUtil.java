// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameMappingUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.constant.TspEngineConstantDef;
import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineContext;
import cn.sunline.adp.metadata.model.ComplexType;
import cn.sunline.adp.metadata.model.Element;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;

public class NameMappingUtil
{

    public NameMappingUtil()
    {
    }

    public static boolean needExchangeAlias()
    {
        RequestData requestData = EngineContext.getRequestData();
        String language = (String)requestData.getBody().getSystem().get("language");
        if(StringUtil.isNotEmpty(language))
        {
            if("en".equals(language))
                return true;
            if("zh".equals(language))
                return false;
        }
        return false;
    }

    public static Map nameMapping(Map data, List elements, boolean toAlias)
    {
        if(elements == null)
            return data;
        Map ret = new HashMap();
        for(Iterator iterator = elements.iterator(); iterator.hasNext();)
        {
            Element element = (Element)iterator.next();
            String alias = StringUtil.isEmpty(element.getAlias()) ? element.getId() : element.getAlias();
            String srcKey = toAlias ? element.getId() : alias;
            String destKey = toAlias ? alias : element.getId();
            Object value = data.get(srcKey);
            if(!toAlias && StringUtil.isEmpty(value))
                value = data.get(element.getId());
            if(StringUtil.isEmpty(value))
            {
                ret.put(destKey, value);
            } else
            {
                ComplexType ct = ModelUtil.getComplexType(element.getTypeObj());
                if(element.getMulti() == null || !element.getMulti().booleanValue())
                {
                    if(ct != null)
                    {
                        if(!(value instanceof Map))
                            throw new RuntimeException(String.format(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C016(), new Object[] {
                                value.getClass()
                            }));
                        value = nameMapping((Map)value, ct.getAllElements(), toAlias);
                    }
                } else
                {
                    if(!(value instanceof List))
                        throw new RuntimeException(String.format(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C017(), new Object[] {
                            value.getClass()
                        }));
                    if(ct != null)
                    {
                        List list = new ArrayList();
                        Object v;
                        for(Iterator iterator1 = ((List)value).iterator(); iterator1.hasNext(); list.add(nameMapping((Map)v, ct.getAllElements(), toAlias)))
                        {
                            v = iterator1.next();
                            if(!(v instanceof Map))
                                throw new RuntimeException(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C018());
                        }

                        value = list;
                    }
                }
                ret.put(destKey, value);
            }
        }

        return ret;
    }
}
