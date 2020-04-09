// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClassUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.adp.metadata.base.odb.OdbFactory;
import cn.sunline.adp.metadata.base.odb.OdbManager;
import cn.sunline.adp.metadata.model.annotation.Index;
import cn.sunline.adp.metadata.model.database.Table;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;

public class ClassUtil
{

    public ClassUtil()
    {
    }

    public static Class getTableClass(String tableName)
    {
        Table table = (Table)OdbFactory.get().getOdbManager(cn/sunline/adp/metadata/model/database/Table).selectByIndex(cn/sunline/adp/metadata/model/database/Table$TableName, new Object[] {
            tableName
        });
        if(table == null)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E001(tableName);
        else
            return table.getJavaClass();
    }

    public static Class getOdbClass(Class ownerClass, String className)
    {
        return ReflectionUtil.classForName((new StringBuilder()).append(ownerClass.getName()).append("$").append(className).toString());
    }
}
