// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MapListDataContext.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.core.bean.accessor.PropertyAccessor;
import cn.sunline.adp.metadata.base.util.PropertyUtil;
import cn.sunline.edsp.base.util.convert.ConvertUtil;
import java.util.*;

public class MapListDataContext
    implements Map
{

    public MapListDataContext()
    {
        data = new LinkedHashMap();
    }

    public MapListDataContext(Map data)
    {
        this.data = data;
    }

    public MapListDataContext(MapListDataContext dataContext)
    {
        data = dataContext.data;
    }

    public Map getMap()
    {
        return data;
    }

    public Map getMap(String key)
    {
        Map ret = (Map)data.get(key);
        if(ret == null)
        {
            ret = new LinkedHashMap();
            data.put(key, ret);
        }
        return ret;
    }

    public Object get(String name, int index)
    {
        return index < 0 || index >= getRecordCount() ? null : getRecord(index).get(name);
    }

    private List getDefaultRecords()
    {
        List records = null;
        if(!containsKey(DEFAULT_NAME_OF_RECORDS))
        {
            records = new ArrayList();
            put(DEFAULT_NAME_OF_RECORDS, records);
        } else
        {
            records = (List)get(DEFAULT_NAME_OF_RECORDS);
        }
        return records;
    }

    public boolean addRecord(MapListDataContext record)
    {
        getDefaultRecords().add(record);
        return true;
    }

    public void setRecord(int index, MapListDataContext record)
    {
        getDefaultRecords().set(index, record);
    }

    public int getRecordCount()
    {
        return getDefaultRecords().size();
    }

    public List getRecords()
    {
        return getDefaultRecords();
    }

    public MapListDataContext getRecord(int index)
    {
        if(index >= 0 && index < getRecordCount())
            return (MapListDataContext)getDefaultRecords().get(index);
        else
            return null;
    }

    public MapListDataContext add(String name, Object value)
    {
        put(name, value);
        return this;
    }

    public void copyFrom(MapListDataContext dataContext)
    {
        if(dataContext == null)
            return;
        clear();
        String name;
        for(Iterator iterator = dataContext.keySet().iterator(); iterator.hasNext(); put(name, dataContext.get(name)))
            name = (String)iterator.next();

        copyRecordsFrom(dataContext);
    }

    protected void copyRecordsFrom(MapListDataContext dataContext)
    {
        if(dataContext.getRecords() != null)
        {
            MapListDataContext r;
            for(Iterator iterator = dataContext.getRecords().iterator(); iterator.hasNext(); addRecord(r))
                r = (MapListDataContext)iterator.next();

        }
    }

    public String getString(String name)
    {
        return get(name) == null ? null : get(name).toString();
    }

    public Object getNestedData(String name)
    {
        try
        {
            return PropertyUtil.createAccessor(data).getNestedProperty(name);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public void setString(String name, String value)
    {
        put(name, value);
    }

    public Date getDate(String name)
    {
        return ConvertUtil.toDate(get(name));
    }

    public int getInt(String name, int defaultValue)
    {
        Object ret = get(name);
        return ret != null ? ConvertUtil.toInteger(ret, Integer.valueOf(defaultValue)).intValue() : defaultValue;
    }

    public String getString(String name, int index)
    {
        Object ret = get(name, index);
        return ret != null ? ret.toString() : null;
    }

    public int size()
    {
        return data.size();
    }

    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    public boolean containsKey(Object key)
    {
        return data.containsKey(key);
    }

    public boolean containsValue(Object value)
    {
        return data.containsValue(value);
    }

    public Object get(Object key)
    {
        return data.get(key);
    }

    public Object put(String key, Object value)
    {
        return data.put(key, value);
    }

    public Object remove(Object key)
    {
        return data.remove(key);
    }

    public void putAll(Map m)
    {
        data.putAll(m);
    }

    public void clear()
    {
        data.clear();
    }

    public Set keySet()
    {
        return data.keySet();
    }

    public Collection values()
    {
        return data.values();
    }

    public Set entrySet()
    {
        return data.entrySet();
    }

    public String toString()
    {
        return data.toString();
    }

    public volatile Object put(Object obj, Object obj1)
    {
        return put((String)obj, obj1);
    }

    private static String DEFAULT_NAME_OF_RECORDS = "records";
    private final Map data;

}
