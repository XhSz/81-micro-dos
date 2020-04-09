// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocalTmpTableCaches.java

package cn.sunline.adp.cedar.base.tmp;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.core.cache.CacheManager;
import cn.sunline.adp.core.cache.Cacher;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.tmp:
//            LocalTmpData

public class LocalTmpTableCaches
{

    public LocalTmpTableCaches()
    {
    }

    private static Cacher getTmpTableCaches()
    {
        Cacher tmpTableCacher = CacheManager.getCacher(TMP_TABLE_CACHER);
        if(tmpTableCacher == null)
            throw ExceptionUtil.wrapThrow(String.format(BaseConst.LocalTmpTableCaches01, new Object[] {
                TMP_TABLE_CACHER
            }), new String[0]);
        else
            return tmpTableCacher;
    }

    public static void clearAllTmpTable()
    {
        Cacher tables = getTmpTableCaches();
        if(tables != null)
            tables.clear();
    }

    public static LocalTmpData get(String name)
    {
        Cacher tables = getTmpTableCaches();
        LocalTmpData localTmpData = (LocalTmpData)tables.get(name);
        if(localTmpData == null)
        {
            localTmpData = new LocalTmpData();
            tables.put(name, localTmpData);
        }
        return localTmpData;
    }

    public static LocalTmpData get(Class tableClazz)
    {
        return get(tableClazz.getName());
    }

    public static LocalTmpData getList(String name)
    {
        Cacher tables = getTmpTableCaches();
        LocalTmpData localTmpData = (LocalTmpData)tables.get(name);
        if(localTmpData == null)
        {
            localTmpData = new LocalTmpData();
            tables.put(name, localTmpData);
        }
        if(localTmpData.getValue() == null)
            localTmpData.setValue(new ArrayList());
        return localTmpData;
    }

    public static LocalTmpData getList(Class tableClazz)
    {
        return getList(tableClazz.getName());
    }

    public static LocalTmpData getMap(String name)
    {
        Cacher tables = getTmpTableCaches();
        LocalTmpData localTmpData = (LocalTmpData)tables.get(name);
        if(localTmpData == null)
        {
            localTmpData = new LocalTmpData();
            tables.put(name, localTmpData);
        }
        if(localTmpData.getValue() == null)
            localTmpData.setValue(new HashMap());
        return localTmpData;
    }

    public static LocalTmpData getMap(Class tableClazz)
    {
        return getMap(tableClazz.getName());
    }

    public static void clear(String name)
    {
        Cacher tables = getTmpTableCaches();
        LocalTmpData localTmpData = (LocalTmpData)tables.get(name);
        if(localTmpData != null)
            localTmpData.setValue(null);
    }

    public static void clear(Class tableClazz)
    {
        clear(tableClazz.getName());
    }

    public static String TMP_TABLE_CACHER = "tmpTable";

}
