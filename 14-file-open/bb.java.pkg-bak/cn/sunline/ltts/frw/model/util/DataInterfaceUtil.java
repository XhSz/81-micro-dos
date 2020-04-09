// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataInterfaceUtil.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.frw.model.datainterface.*;
import cn.sunline.ltts.frw.model.db.Field;
import cn.sunline.ltts.frw.model.db.Table;
import java.util.*;

public class DataInterfaceUtil
{

    public DataInterfaceUtil()
    {
    }

    public static boolean isDefined(DataInterfaceElements fields, String field)
    {
        return find(fields, field) != null;
    }

    public static DataInterfaceElement find(DataInterfaceElements fields, String field)
    {
        return find(fields.getElements(), field);
    }

    public static List getFieldIds(List fields)
    {
        if(null == fields)
            return null;
        List resultList = new ArrayList();
        Iterator i$ = fields.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            DataInterfaceElement field = (DataInterfaceElement)i$.next();
            if(field instanceof DataInterfaceFields)
            {
                resultList.addAll(getDataInterfaceElements((DataInterfaceFields)field));
            } else
            {
                resultList.add(field.getId());
                if(field.getTypeObj() instanceof Table)
                {
                    Table tb = (Table)field.getTypeObj();
                    List tb_fl = tb.getAllElements();
                    if(tb_fl != null && tb_fl.size() != 0)
                    {
                        Iterator i$ = tb_fl.iterator();
                        while(i$.hasNext()) 
                        {
                            Field item = (Field)i$.next();
                            resultList.add((new StringBuilder()).append(field.getId()).append('.').append(item.getId()).toString());
                        }
                    }
                }
            }
        } while(true);
        return resultList;
    }

    public static DataInterfaceElement find(List fields, String field)
    {
        if(fields == null || field == null || field.trim().equals(""))
            return null;
        if(field.indexOf(".") == -1)
            return findField(fields, field);
        if(field.startsWith(".") || field.endsWith("."))
            return null;
        String args[] = field.trim().split("\\.");
        DataInterfaceElement found = null;
        String arr$[] = args;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String id = arr$[i$];
            if(fields == null)
                return null;
            found = findField(fields, id);
            if(found == null)
                return null;
            if(found instanceof DataInterfaceFields)
                fields = ((DataInterfaceFields)found).getFields();
            else
                fields = null;
        }

        return found;
    }

    private static List getDataInterfaceElements(DataInterfaceFields field)
    {
        List resultList = new ArrayList();
        if(null == field)
            return resultList;
        List fields = field.getFields();
        resultList.add(field.getId());
        if(null != fields && fields.size() > 0)
        {
            for(Iterator i$ = fields.iterator(); i$.hasNext();)
            {
                DataInterfaceElement item = (DataInterfaceElement)i$.next();
                if(item instanceof DataInterfaceFields)
                {
                    List elements = getDataInterfaceElements((DataInterfaceFields)item);
                    Iterator i$ = elements.iterator();
                    while(i$.hasNext()) 
                    {
                        String itemStr = (String)i$.next();
                        resultList.add((new StringBuilder()).append(field.getId()).append(".").append(itemStr).toString());
                    }
                } else
                {
                    resultList.add((new StringBuilder()).append(field.getId()).append(".").append(item.getId()).toString());
                }
            }

        }
        return resultList;
    }

    private static DataInterfaceElement findField(List fields, String field)
    {
        if(fields == null)
            return null;
        for(Iterator i$ = fields.iterator(); i$.hasNext();)
        {
            DataInterfaceElement element = (DataInterfaceElement)i$.next();
            if(element != null && field.equals(element.getId()))
                return element;
        }

        return null;
    }
}
