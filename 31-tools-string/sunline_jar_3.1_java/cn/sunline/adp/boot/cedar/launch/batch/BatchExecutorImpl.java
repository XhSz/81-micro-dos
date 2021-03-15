// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BatchExecutorImpl.java

package cn.sunline.adp.boot.cedar.launch.batch;

import cn.sunline.adp.boot.cedar.launch.IBatchIDERunner;
import cn.sunline.adp.cedar.base.boot.Boot;
import cn.sunline.adp.cedar.base.engine.BatchConfigConstant;
import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.logging.*;
import cn.sunline.adp.cedar.base.pkg.*;
import cn.sunline.adp.cedar.base.util.CommUtil;
import cn.sunline.adp.cedar.server.batch.constant.EngineBatchPluginConstantDef;
import cn.sunline.adp.cedar.server.batch.engine.BatchTaskEngine;
import cn.sunline.adp.cedar.server.batch.errors.EngineBatchPluginErrorDef;
import cn.sunline.adp.cedar.server.batch.util.BatchUtil;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;

public class BatchExecutorImpl
    implements IBatchIDERunner
{

    public BatchExecutorImpl()
    {
    }

    private void _run(String args[])
    {
        if(args.length != 1)
        {
            ExceptionUtil.println("Usage: TaskExecutor \"{\"sys\" : {\"groupid\" : \"\", \"prcscd\" : \"\"}, \"input\" : {}}\" ");
            return;
        }
        Log4j2Util.setLogType("domain");
        LogConfigManager.get().setCurrentSystemType(cn.sunline.adp.cedar.base.logging.LogConfigManager.SystemType.batch);
        if(log.isDebugEnabled())
            log.debug(cn.sunline.adp.cedar.server.batch.constant.EngineBatchPluginConstantDef.SPC_EB.C031(), new Object[] {
                args[0]
            });
        String pkgType = System.getProperty("pkgType");
        PkgParser parser = PkgFactory.get().getPkgParser();
        if(CommUtil.isNotNull(System.getProperty("pkgType")))
            parser = PkgFactory.get().getPkgParser(pkgType);
        DataArea taskArea = parser.parse(args[0], null, PkgMode.request);
        String prcscd = taskArea.getSystem().getString("prcscd");
        String groupId = taskArea.getSystem().getString("groupId");
        if(StringUtil.isEmpty(prcscd) && StringUtil.isEmpty(groupId))
            throw cn.sunline.adp.cedar.server.batch.errors.EngineBatchPluginErrorDef.SP_EB.E066();
        String taskId = taskArea.getInput().getString(BatchConfigConstant.TASK_ID);
        if(StringUtil.isEmpty(taskId))
            taskId = BatchUtil.getTaskId();
        taskArea.getSystem().add(BatchConfigConstant.TASK_ID, taskId);
        taskArea.getSystem().add(BatchConfigConstant.BATCH_TRAN_DATE, taskArea.getCommReq().getString(BatchConfigConstant.BATCH_TRAN_DATE));
        taskArea.getSystem().add(BatchConfigConstant.BATCH_TENANT_ID, taskArea.getCommReq().getString(BatchConfigConstant.BATCH_TENANT_ID));
        DataArea dataArea = DataArea.buildWithInput(taskArea.getInput().getMap());
        dataArea.setSystem(taskArea.getSystem().getMap());
        dataArea.setCommReq(taskArea.getCommReq().getMap());
        BatchTaskEngine.local().runDebug(groupId, prcscd, dataArea);
    }

    public void run(String args[])
    {
        try
        {
            _run(args);
            Boot.get().stop();
        }
        catch(Exception e)
        {
            log.error("BatchExecutorImpl.run error!", e, new Object[0]);
        }
        System.exit(0);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/boot/cedar/launch/batch/BatchExecutorImpl);
    private static final String PKGTYPE = "pkgType";

}
