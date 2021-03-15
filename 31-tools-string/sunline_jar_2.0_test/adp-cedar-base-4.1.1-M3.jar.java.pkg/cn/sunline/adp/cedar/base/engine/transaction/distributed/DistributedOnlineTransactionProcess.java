// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributedOnlineTransactionProcess.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed;

import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.ResponseData;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineBranchTransactionContext;
import java.util.List;

public interface DistributedOnlineTransactionProcess
{

    public abstract void beginTransaction(RequestData requestdata);

    public abstract void commitTransaction(RequestData requestdata, ResponseData responsedata);

    public abstract void rollbackTransaction(RequestData requestdata, ResponseData responsedata, Throwable throwable);

    public abstract void close();

    public abstract void createBranchTransaction(OnlineBranchTransactionContext onlinebranchtransactioncontext);

    public abstract void closeBranchTransaction();

    public abstract void commitBranchTransaction(String s, String s1, Runnable runnable);

    public abstract void rollbackBranchTransaction(String s, String s1, Runnable runnable);

    public abstract void asyncProcess(Throwable throwable);

    public abstract void rollbackTransactionNotFinalStatus(String s, String s1, Runnable runnable);

    public abstract List queryGlobalFailList(String s, String s1, int i);
}
