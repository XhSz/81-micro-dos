// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonUtils.java

package cn.sunline.adp.vine.base.util.json;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.util.Assert;

public class JsonUtils
    implements Serializable
{

    public JsonUtils()
    {
    }

    public static List toList(String json, Class clazz)
    {
        Assert.notNull(json, "parameter json must not be null.");
        return JSON.parseArray(json, clazz);
    }

    public static Object toObject(String json, Class clazz)
    {
        Assert.notNull(json, "parameter json must not be null.");
        return JSON.parseObject(json, clazz);
    }

    public static String fromObject(Object obj)
    {
        return JSON.toJSONString(obj);
    }

    public static Map toMap(Object o)
    {
        if(o instanceof String)
        {
            return (Map)toObject(o.toString(), java/util/Map);
        } else
        {
            String json = fromObject(o);
            return (Map)toObject(json, java/util/Map);
        }
    }

    private static final long serialVersionUID = 0xc237a5c4c04c0da4L;
}
