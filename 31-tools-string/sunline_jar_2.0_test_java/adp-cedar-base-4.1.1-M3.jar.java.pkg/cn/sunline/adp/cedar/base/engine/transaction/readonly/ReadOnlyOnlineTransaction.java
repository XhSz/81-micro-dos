// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadOnlyOnlineTransaction.java

package cn.sunline.adp.cedar.base.engine.transaction.readonly;

import cn.sunline.adp.cedar.base.engine.ResponseData;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineBranchTransactionContext;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineGlobalTransaction;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;

public class ReadOnlyOnlineTransaction
    implements OnlineGlobalTransaction
{

    public ReadOnlyOnlineTransaction()
    {
    }

    public static ReadOnlyOnlineTransaction get()
    {
        return instance;
    }

    public void beginTransaction()
    {
    }

    public void commitTransaction(ResponseData responsedata)
    {
    }

    public void rollbackTransaction(ResponseData responsedata)
    {
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

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/transaction/readonly/ReadOnlyOnlineTransaction);
    private static final ReadOnlyOnlineTransaction instance = new ReadOnlyOnlineTransaction();

}
