// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataContext.java

package cn.sunline.adp.cedar.base.engine;

import java.util.List;
import java.util.Map;

public interface DataContext
    extends Map
{

    public abstract boolean addRecord(DataContext datacontext);

    public abstract List getRecords();

    public abstract int getRecordCount();

    public abstract DataContext getRecord(int i);

    public abstract DataContext add(Object obj, Object obj1);

    public abstract String getString(Object obj);

    public abstract String getString(Object obj, int i);

    public abstract int getInt(Object obj, int i);

    public abstract void setRecord(int i, DataContext datacontext);

    public abstract void copyFrom(DataContext datacontext);

    public abstract Object get(Object obj, int i);
}
