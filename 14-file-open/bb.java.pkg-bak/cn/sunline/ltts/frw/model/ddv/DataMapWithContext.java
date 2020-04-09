// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataMapWithContext.java

package cn.sunline.ltts.frw.model.ddv;

import cn.sunline.common.util.StringUtil;
import java.util.*;

class DataMapWithContext
    implements Map
{

    public DataMapWithContext(Map map)
    {
        this.map = map;
    }

    public String getCurrentContext()
    {
        return currentContext;
    }

    public void setCurrentContext(String currentContext)
    {
        this.currentContext = currentContext;
    }

    public Object getKey(Object key)
    {
        if(StringUtil.isEmpty(key))
            return key;
        if(StringUtil.isNotEmpty(currentContext))
            key = (new StringBuilder()).append(currentContext).append(".").append(key.toString()).toString();
        return key;
    }

    public int size()
    {
        return map.size();
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public boolean containsKey(Object key)
    {
        return map.containsKey(getKey(key));
    }

    public boolean containsValue(Object value)
    {
        return map.containsValue(value);
    }

    public Object get(Object key)
    {
        return map.get(getKey(key));
    }

    public Object put(String key, Object value)
    {
        throw new IllegalArgumentException();
    }

    public Object remove(Object key)
    {
        throw new IllegalArgumentException();
    }

    public void putAll(Map m)
    {
        throw new IllegalArgumentException();
    }

    public void clear()
    {
        throw new IllegalArgumentException();
    }

    public Set keySet()
    {
        return map.keySet();
    }

    public Collection values()
    {
        return map.values();
    }

    public Set entrySet()
    {
        return map.entrySet();
    }

    public volatile Object put(Object obj, Object obj1)
    {
        return put((String)obj, obj1);
    }

    private Map map;
    private String currentContext;
}
