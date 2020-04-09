// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PkgUTil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.adp.core.util.JsonUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;

public class PkgUTil
{

    public PkgUTil()
    {
    }

    public static Map deserialize(String msg)
    {
        if(StringUtil.isEmpty(msg))
            return new HashMap(2);
        else
            return JsonUtil.parse(msg);
    }

    public static String serialize(Map map)
    {
        if(map != null)
            return JsonUtil.format(map);
        else
            return "";
    }

    public static String serialize(List list)
    {
        if(list != null)
            return JsonUtil.format(list);
        else
            return "";
    }
}
