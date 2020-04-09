// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConcurrentHashSet.java

package cn.sunline.adp.cedar.base.boot.spi;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashSet extends AbstractSet
    implements Set, Serializable
{

    public ConcurrentHashSet()
    {
        map = new ConcurrentHashMap();
    }

    public ConcurrentHashSet(int initialCapacity)
    {
        map = new ConcurrentHashMap(initialCapacity);
    }

    public Iterator iterator()
    {
        return map.keySet().iterator();
    }

    public int size()
    {
        return map.size();
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }

    public boolean add(Object e)
    {
        return map.put(e, PRESENT) == null;
    }

    public boolean remove(Object o)
    {
        return map.remove(o) == PRESENT;
    }

    public void clear()
    {
        map.clear();
    }

    private static final long serialVersionUID = 0x87a672d1a8b27cd2L;
    private static final Object PRESENT = new Object();
    private final ConcurrentMap map;

}
