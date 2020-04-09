// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AtomicOnlineTransaction.java

package cn.sunline.adp.cedar.base.engine.transaction.atomic;

import cn.sunline.adp.cedar.base.constant.TspEngineConstantDef;
import cn.sunline.adp.cedar.base.engine.*;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineContext;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineBranchTransactionContext;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineGlobalTransaction;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.util.SystemParams;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.dao.base.conn.DBConnectionManager;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;

public class AtomicOnlineTransaction
    implements OnlineGlobalTransaction
{

    public AtomicOnlineTransaction(RequestData request)
    {
        this.request = request;
    }

    public void beginTransaction()
    {
        EdspCoreBeanUtil.getDBConnectionManager().beginTransation();
        EdspCoreBeanUtil.getDBConnectionManager().setApplicationModule((new StringBuilder()).append(SystemParams.get().getSystemId()).append("-onl").toString(), request.getRequestHeader().getServiceCode());
    }

    public void commitTransaction(ResponseData response)
    {
        if("true".equals(System.getProperty("ide_debug")))
        {
            log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C001());
            rollbackTransaction(response);
            return;
        }
        if(EngineContext.getRequestData().getRequestHeader().enableDBRollback())
        {
            log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C002());
            rollbackTransaction(response);
            return;
        } else
        {
            commitDB();
            return;
        }
    }

    private void commitDB()
    {
        ProfileUtil.start_record2("facade2.commitDB.process");
        EdspCoreBeanUtil.getDBConnectionManager().commit();
        ProfileUtil.end_record2("facade2.commitDB.process");
        break MISSING_BLOCK_LABEL_29;
        Exception exception;
        exception;
        ProfileUtil.end_record2("facade2.commitDB.process");
        throw exception;
    }

    public void rollbackTransaction(ResponseData response)
    {
        EdspCoreBeanUtil.getDBConnectionManager().rollback();
    }

    public void close()
    {
    }

    public void createBranchTransaction(OnlineBranchTransactionContext onlinebranchtransactioncontext)
    {
    }

    public void closeBranchTransaction()
    {
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/transaction/atomic/AtomicOnlineTransaction);
    private final RequestData request;

}
