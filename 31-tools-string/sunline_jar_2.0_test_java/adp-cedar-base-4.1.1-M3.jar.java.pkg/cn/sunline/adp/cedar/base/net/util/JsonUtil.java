// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonUtil.java

package cn.sunline.adp.cedar.base.net.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil
{

    public JsonUtil()
    {
    }

    public static String toJson(Object model)
    {
        return JSON.toJSONString(model);
    }

    public static Object toModel(String json, Class type)
    {
        return JSON.parseObject(json, type);
    }
}
