// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChunkOnlineTransaction.java

package cn.sunline.adp.cedar.base.engine.transaction.chunk;

import cn.sunline.adp.cedar.base.constant.TspEngineConstantDef;
import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.ResponseData;
import cn.sunline.adp.cedar.base.engine.transaction.*;
import cn.sunline.adp.cedar.base.engine.transaction.atomic.AtomicOnlineTransaction;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;

public class ChunkOnlineTransaction
    implements OnlineGlobalTransaction
{

    public ChunkOnlineTransaction(RequestData request)
    {
        hasDBTransaction = false;
        normal = new AtomicOnlineTransaction(request);
    }

    public void beginTransaction()
    {
        normal.beginTransaction();
        hasDBTransaction = true;
    }

    public void commitTransaction(ResponseData response)
    {
        normal.commitTransaction(response);
        hasDBTransaction = false;
        log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C003());
        try
        {
            normal.beginTransaction();
            hasDBTransaction = true;
        }
        catch(Exception e)
        {
            log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C004(), e, new Object[] {
                e.getMessage()
            });
            try
            {
                log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C005());
                hasDBTransaction = false;
                normal.rollbackTransaction(response);
            }
            catch(Exception e2)
            {
                log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C006(), e2, new Object[] {
                    e2.getMessage()
                });
            }
        }
    }

    public void rollbackTransaction(ResponseData response)
    {
        normal.rollbackTransaction(response);
        hasDBTransaction = false;
        log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C007());
        try
        {
            normal.beginTransaction();
            hasDBTransaction = true;
        }
        catch(Exception e)
        {
            log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C008(), e, new Object[] {
                e.getMessage()
            });
            try
            {
                hasDBTransaction = false;
                normal.rollbackTransaction(response);
                log.info(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C009());
            }
            catch(Exception e2)
            {
                log.error(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C010(), e2, new Object[] {
                    e2.getMessage()
                });
            }
        }
    }

    public void createBranchTransaction(OnlineBranchTransactionContext btc)
    {
        if(ServiceTransactionMode.NotSupported == btc.getServiceTransactionMode())
        {
            if(!hasDBTransaction)
            {
                normal.beginTransaction();
                hasDBTransaction = true;
            }
        } else
        if(hasDBTransaction && !btc.isLocal())
        {
            normal.commitTransaction(null);
            hasDBTransaction = false;
        } else
        if(!hasDBTransaction && btc.isLocal())
        {
            normal.beginTransaction();
            hasDBTransaction = true;
        }
    }

    public void close()
    {
    }

    public void closeBranchTransaction()
    {
        if(!hasDBTransaction)
        {
            normal.beginTransaction();
            hasDBTransaction = true;
        }
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/transaction/chunk/ChunkOnlineTransaction);
    private AtomicOnlineTransaction normal;
    private boolean hasDBTransaction;

}
