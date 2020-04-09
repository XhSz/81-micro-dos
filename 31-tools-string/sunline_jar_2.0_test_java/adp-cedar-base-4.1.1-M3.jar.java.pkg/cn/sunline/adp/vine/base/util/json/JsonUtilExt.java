// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonUtilExt.java

package cn.sunline.adp.vine.base.util.json;

import cn.sunline.adp.core.util.JsonUtil;
import java.util.Map;

public class JsonUtilExt
{

    public JsonUtilExt()
    {
    }

    public static Map toMap(Object o)
    {
        if(o instanceof String)
        {
            return (Map)JsonUtil.parseEntity(o.toString(), java/util/Map);
        } else
        {
            String json = JsonUtil.format(o);
            return (Map)JsonUtil.parseEntity(json, java/util/Map);
        }
    }
}
