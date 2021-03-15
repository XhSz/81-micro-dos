// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineGlobalTransaction.java

package cn.sunline.adp.cedar.base.engine.transaction;

import cn.sunline.adp.cedar.base.engine.ResponseData;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.transaction:
//            OnlineBranchTransactionContext

public interface OnlineGlobalTransaction
{

    public abstract void beginTransaction();

    public abstract void commitTransaction(ResponseData responsedata);

    public abstract void rollbackTransaction(ResponseData responsedata);

    public abstract void close();

    public abstract void createBranchTransaction(OnlineBranchTransactionContext onlinebranchtransactioncontext);

    public abstract void closeBranchTransaction();
}
