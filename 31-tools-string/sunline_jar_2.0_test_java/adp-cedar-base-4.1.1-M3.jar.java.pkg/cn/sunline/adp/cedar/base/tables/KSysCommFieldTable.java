// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KSysCommFieldTable.java

package cn.sunline.adp.cedar.base.tables;

import cn.sunline.adp.metadata.model.annotation.ConfigType;

public interface KSysCommFieldTable
{
    public static interface tsp_comm_filed_system
    {

        public abstract String getSystem_code();

        public abstract void setSystem_code(String s);

        public abstract String toString();
    }

    public static interface tsp_comm_filed
    {

        public abstract String getSystem_code();

        public abstract void setSystem_code(String s);

        public abstract String getCorporate_code();

        public abstract void setCorporate_code(String s);

        public abstract String toString();
    }

}
