// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelClassManager.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.util.ClassUtils;
import cn.sunline.ltts.base.util.LangUtil;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.frw.model.ModuleConf;
import cn.sunline.ltts.frw.model.component.ComponentSchema;
import cn.sunline.ltts.frw.model.db.ShardingStrategyConf;
import cn.sunline.ltts.frw.model.dm.Schema;
import cn.sunline.ltts.frw.model.globalerror.GlobalErrorConf;
import cn.sunline.ltts.frw.model.report.Report;
import cn.sunline.ltts.frw.model.sql.SqlGroup;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ModelClassManager
{

    public ModelClassManager()
    {
    }

    public static void addModelClass(String type, Class modelClass)
    {
        allModelClasses.add(modelClass);
        modelClassCache.put(type, modelClass);
    }

    public static Class getModelClass(String type)
    {
        Class ret = (Class)modelClassCache.get(type);
        if(ret == null)
            throw LangUtil.wrapThrow("\u7C7B\u578B[%s]\u5BF9\u5E94\u7684Class\u4E0D\u5B58\u5728", new String[] {
                type
            });
        else
            return ret;
    }

    private static SysLog log;
    public static final List allModelClasses = new ArrayList();
    public static final Map modelClassCache = new ConcurrentHashMap();
    public static final String SCHEMA = "schema";
    public static final String ERROR = "error";
    public static final String SQLGROUP = "sqlGroup";
    public static final String COMPONENT_SCHEMA = "componentSchema";
    public static final String REPORT = "report";
    public static final String WEB_TRANSACTION = "webTransaction";
    public static final String BACTH_TRANSACTION = "batchTransaction";
    public static final String BATCH_FILETRANSACTION = "fileBatchTransaction";
    public static final String BATCH_STEP = "batchStep";
    public static final String BATCH_STEP_GROUP = "batchStepGroup";
    public static final String WEB_COMPONENT = "webComponent";
    public static final String FLOW_TRANSACTION = "transaction";
    public static final String SHARDING = "sharding";

    static 
    {
        log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/ModelClassManager);
        addModelClass("schema", cn/sunline/ltts/frw/model/dm/Schema);
        addModelClass("sqlGroup", cn/sunline/ltts/frw/model/sql/SqlGroup);
        addModelClass("error", cn/sunline/ltts/frw/model/globalerror/GlobalErrorConf);
        addModelClass("report", cn/sunline/ltts/frw/model/report/Report);
        addModelClass("componentSchema", cn/sunline/ltts/frw/model/component/ComponentSchema);
        addModelClass("sharding", cn/sunline/ltts/frw/model/db/ShardingStrategyConf);
        addModelClass("ModuleConf", cn/sunline/ltts/frw/model/ModuleConf);
        try
        {
            addModelClass("DBEnumProvider", ClassUtils.classForName("cn.sunline.ltts.dao.util.DBEnumProvider"));
        }
        catch(Exception e)
        {
            log.error((new StringBuilder()).append(e.getClass().getSimpleName()).append(":").append(e.getMessage()).toString());
        }
    }
}
