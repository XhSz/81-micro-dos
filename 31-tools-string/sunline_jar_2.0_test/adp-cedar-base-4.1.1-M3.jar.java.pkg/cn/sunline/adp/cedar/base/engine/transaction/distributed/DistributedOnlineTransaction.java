// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributedOnlineTransaction.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed;

import cn.sunline.adp.cedar.base.constant.TspEngineConstantDef;
import cn.sunline.adp.cedar.base.engine.*;
import cn.sunline.adp.cedar.base.engine.service.ServiceTypeEnum;
import cn.sunline.adp.cedar.base.engine.transaction.*;
import cn.sunline.adp.cedar.base.engine.transaction.atomic.AtomicOnlineTransaction;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.commit2.DTXCommit2FailProcess;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.commit2.DTXCommit2FailProcessAsync;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.commit2.DTXCommit2FailProcessCancel;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.rollback.DTXTransFailProcess;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.rollback.DTXTransFailProcessRollback;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.edsp.base.factories.FactoriesLoader;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.transaction.distributed:
//            DistributedOnlineTransactionProcess

public class DistributedOnlineTransaction
    implements OnlineGlobalTransaction
{

    public DistributedOnlineTransaction(RequestData request, ServiceTypeEnum serviceType)
    {
        hasDBTransaction = false;
        branchTransactionFlag = new Stack();
        this.request = request;
        this.serviceType = serviceType;
        normal = new AtomicOnlineTransaction(request);
        dotp = (DistributedOnlineTransactionProcess)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/engine/transaction/distributed/DistributedOnlineTransactionProcess);
    }

    public void beginTransaction()
    {
        if(serviceType != ServiceTypeEnum.CONFIRM_SERVICE && serviceType != ServiceTypeEnum.CANCEL_SERVICE)
            dotp.beginTransaction(request);
        normal.beginTransaction();
        hasDBTransaction = true;
    }

    public void commitTransaction(ResponseData response)
    {
        normal.commitTransaction(response);
        hasDBTransaction = false;
        response.getHeaderData().setDTXStatus(ServiceTransactionStatus.SyncC2Success.getValue());
        if(serviceType == ServiceTypeEnum.CONFIRM_SERVICE || serviceType == ServiceTypeEnum.CANCEL_SERVICE)
            return;
        log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C003());
        try
        {
            normal.beginTransaction();
            hasDBTransaction = true;
            dotp.commitTransaction(request, response);
        }
        catch(Throwable e)
        {
            log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C004(), e, new Object[] {
                e.getMessage()
            });
            processId = DTXCommit2FailProcessAsync.commit2FailProcessId;
            try
            {
                log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C005());
                normal.rollbackTransaction(response);
                hasDBTransaction = false;
                DTXCommit2FailProcess commit2FailProcess = (DTXCommit2FailProcess)FactoriesLoader.getFactoryById(cn/sunline/adp/cedar/base/engine/transaction/distributed/commit2/DTXCommit2FailProcess, processId);
                commit2FailProcess.process(dotp, request, response, e);
                throwable = e;
            }
            catch(Throwable e2)
            {
                log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C006(), e2, new Object[] {
                    e2.getMessage()
                });
            }
            if("cancel".equals(processId) && throwable != null)
                throw ExceptionUtil.wrapThrow(throwable);
        }
    }

    public void rollbackTransaction(ResponseData response)
    {
        normal.rollbackTransaction(response);
        hasDBTransaction = false;
        boolean needAsyncProcess = !"cancel".equals(this.processId);
        if(needAsyncProcess)
            response.getHeaderData().setDTXStatus(ServiceTransactionStatus.SyncRbSuccess.getValue());
        if(serviceType == ServiceTypeEnum.CONFIRM_SERVICE || serviceType == ServiceTypeEnum.CANCEL_SERVICE)
            return;
        log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C007());
        try
        {
            normal.beginTransaction();
            hasDBTransaction = true;
            String processId = DTXTransFailProcessRollback.transFailProcessId;
            DTXTransFailProcess transFailProcess = (DTXTransFailProcess)FactoriesLoader.getFactoryById(cn/sunline/adp/cedar/base/engine/transaction/distributed/rollback/DTXTransFailProcess, processId);
            transFailProcess.process(dotp, request, response);
        }
        catch(Throwable e)
        {
            log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C008(), e, new Object[] {
                e.getMessage()
            });
            String dtxErrorcd = OnlineTransactionConstant.getDtxErrorcd();
            String dtxErrortx = OnlineTransactionConstant.getDtxErrortx();
            response.getHeaderData().setDTXStatus(ServiceTransactionStatus.SyncRbFailToAsync.getValue());
            response.getHeaderData().setDTXErrorcd(dtxErrorcd);
            response.getHeaderData().setDTXErrortx(StringUtils.isEmpty(dtxErrortx) ? e.getMessage() : dtxErrortx);
            try
            {
                normal.rollbackTransaction(response);
                hasDBTransaction = false;
                log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C009());
                if(needAsyncProcess)
                    dotp.asyncProcess(e);
            }
            catch(Throwable e2)
            {
                response.getHeaderData().setDTXErrorcd(dtxErrorcd);
                response.getHeaderData().setDTXErrortx(StringUtils.isEmpty(dtxErrortx) ? e2.getMessage() : dtxErrortx);
                log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C010(), e2, new Object[] {
                    e2.getMessage()
                });
            }
        }
    }

    public void close()
    {
    }

    public void createBranchTransaction(OnlineBranchTransactionContext btc)
    {
        btc.setServiceTransactionMode(btc.getServiceController().getServiceTransactionMode());
        if(ServiceTransactionMode.NotSupported == btc.getServiceTransactionMode())
        {
            if(!hasDBTransaction)
            {
                normal.beginTransaction();
                hasDBTransaction = true;
            }
            if(btc.getServiceController().getServiceType() == ServiceTypeEnum.CONFIRM_SERVICE || btc.getServiceController().getServiceType() == ServiceTypeEnum.CANCEL_SERVICE)
            {
                log.debug(">>>>>createBranchTransaction_NotSupported:ServiceType=[%s], isLocal=[%s]", new Object[] {
                    btc.getServiceController().getServiceType(), Boolean.valueOf(btc.isLocal())
                });
                if(hasDBTransaction && !btc.isLocal())
                {
                    normal.commitTransaction(null);
                    normal.beginTransaction();
                    hasDBTransaction = true;
                }
            }
        } else
        {
            if(hasDBTransaction && !btc.isLocal())
            {
                btc.setHasDBTransaction(hasDBTransaction);
                dotp.createBranchTransaction(btc);
                normal.commitTransaction(null);
                hasDBTransaction = false;
            } else
            if(!hasDBTransaction && btc.isLocal())
            {
                normal.beginTransaction();
                hasDBTransaction = true;
                btc.setHasDBTransaction(hasDBTransaction);
                dotp.createBranchTransaction(btc);
            } else
            {
                btc.setHasDBTransaction(hasDBTransaction);
                dotp.createBranchTransaction(btc);
            }
            branchTransactionFlag.push(btc);
        }
    }

    public void closeBranchTransaction()
    {
        if(!branchTransactionFlag.isEmpty())
        {
            OnlineBranchTransactionContext btc = (OnlineBranchTransactionContext)branchTransactionFlag.pop();
            dotp.closeBranchTransaction();
            if(!hasDBTransaction && (!branchTransactionFlag.isEmpty() || btc.isHasDBTransaction()))
            {
                normal.beginTransaction();
                hasDBTransaction = true;
            }
        }
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/transaction/distributed/DistributedOnlineTransaction);
    private RequestData request;
    private ServiceTypeEnum serviceType;
    private AtomicOnlineTransaction normal;
    private DistributedOnlineTransactionProcess dotp;
    private boolean hasDBTransaction;
    private Stack branchTransactionFlag;
    private Throwable throwable;
    private String processId;

}
