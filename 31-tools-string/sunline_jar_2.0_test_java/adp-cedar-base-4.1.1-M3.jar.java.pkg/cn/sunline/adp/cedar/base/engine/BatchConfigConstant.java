// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BatchConfigConstant.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.constant.ConstantValueManager;

public class BatchConfigConstant
{

    public BatchConfigConstant()
    {
    }

    public static final String TASK_ID = ConstantValueManager.get().getValue("batchTaskId", "pljypich");
    public static final String BATCH_TIMER_NAME = ConstantValueManager.get().getValue("timerName", "timerName");
    public static final String JOB_NAME = ConstantValueManager.get().getValue("jobName", "jobname");
    public static final String BATCH_TRAN_FLOW = ConstantValueManager.get().getValue("batchTranFlow", "pljylcbs");
    public static final String BATCH_TRAN_FLOW_STEP = ConstantValueManager.get().getValue("batchTranFlowStep", "liucbuzh");
    public static final String BATCH_CONTEXT = ConstantValueManager.get().getValue("batchContext", "context");
    public static final String BATCH_TASK_CONTEXT = ConstantValueManager.get().getValue("batchTaskContext", "task_context");
    public static final String FILE_TRANSFER_PROPERTIES = ConstantValueManager.get().getValue("fileTransferProperties", "file_properties");
    public static final String BATCH_TRAN_DATE = ConstantValueManager.get().getValue("batchTranDate", "jiaoyirq");
    public static final String BATCH_TRAN_GROUP = ConstantValueManager.get().getValue("batchTranGroup", "pljyzbsh");
    public static final String BATCH_TRAN_ID = ConstantValueManager.get().getValue("batchTranId", "pljioyma");
    public static final String BATCH_TENANT_ID = ConstantValueManager.get().getValue("batchTenantId", "farendma");
    public static final String BATCH_TASK_EXECUTEID = ConstantValueManager.get().getValue("batchTaskExecuteId", "plrwzxpc");
    public static final String BATCH_CURRENT_DATE = ConstantValueManager.get().getValue("batchCurrentDate", "trandate");
    public static final String TASK = ConstantValueManager.get().getValue("batchTask", "task");

}
