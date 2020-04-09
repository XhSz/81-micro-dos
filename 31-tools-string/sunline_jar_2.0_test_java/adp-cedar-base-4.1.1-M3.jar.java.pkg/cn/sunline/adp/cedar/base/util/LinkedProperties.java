// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LinkedProperties.java

package cn.sunline.adp.cedar.base.util;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class LinkedProperties extends Properties
{

    public LinkedProperties()
    {
        linkMap = new LinkedHashMap();
    }

    public synchronized Enumeration keys()
    {
        List keys = new ArrayList();
        java.util.Map.Entry entry;
        for(Iterator iterator = linkMap.entrySet().iterator(); iterator.hasNext(); keys.add(entry.getKey()))
            entry = (java.util.Map.Entry)iterator.next();

        return Collections.enumeration(keys);
    }

    public synchronized Object put(Object key, Object value)
    {
        super.put(key, value);
        return linkMap.put(key, value);
    }

    public synchronized Object remove(Object key)
    {
        super.remove(key);
        return linkMap.remove(key);
    }

    public synchronized boolean contains(Object value)
    {
        return linkMap.containsValue(value);
    }

    public boolean containsValue(Object value)
    {
        return linkMap.containsValue(value);
    }

    public synchronized Enumeration elements()
    {
        throw new UnsupportedOperationException("Enumerations are so old-school, don't use them, use keySet() or entrySet() instead");
    }

    public Set entrySet()
    {
        return linkMap.entrySet();
    }

    public synchronized void clear()
    {
        super.clear();
        linkMap.clear();
    }

    public synchronized boolean containsKey(Object key)
    {
        return linkMap.containsKey(key);
    }

    public void store(Writer writer, String comments)
        throws IOException
    {
        super.putAll(linkMap);
        super.store(writer, comments);
    }

    private static final long serialVersionUID = 1L;
    private Map linkMap;
}
