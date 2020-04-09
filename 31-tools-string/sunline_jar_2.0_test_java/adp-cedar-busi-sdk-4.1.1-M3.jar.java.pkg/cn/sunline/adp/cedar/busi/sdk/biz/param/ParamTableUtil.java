// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParamTableUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.param;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.adp.metadata.base.odb.OdbFactory;
import cn.sunline.adp.metadata.base.odb.OdbManager;
import cn.sunline.adp.metadata.base.util.JavaClassModelUtil;
import cn.sunline.adp.metadata.model.database.Table;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.biz.param:
//            ParamTableProcessor

public class ParamTableUtil
{

    public ParamTableUtil()
    {
    }

    public static ParamTableProcessor getParamTableProcessor(String tableName)
    {
        Table table = (Table)OdbFactory.get().getOdbManager(cn/sunline/adp/metadata/model/database/Table).selectByIndex(cn/sunline/adp/metadata/model/database/Table$TableName, new Object[] {
            tableName
        });
        if(table == null)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E011(tableName);
        if(table.getParam() == null || !table.getParam().booleanValue())
            return null;
        String className = null;
        try
        {
            cn.sunline.adp.metadata.base.util.JavaClassModelUtil.JavaClassModel javaModel = JavaClassModelUtil.getJavaClassModel(table);
            className = (new StringBuilder()).append(javaModel.getPackage()).append(".").append(javaModel.getClassName()).toString();
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E012(e);
        }
        try
        {
            return (ParamTableProcessor)ReflectionUtil.newInstance(className, cn/sunline/adp/cedar/busi/sdk/biz/param/ParamTableProcessor, new Object[0]);
        }
        catch(Exception e)
        {
            log.info(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C004(), new Object[] {
                tableName, className
            });
        }
        return null;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/biz/param/ParamTableUtil);

}
