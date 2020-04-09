// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineTransactionUtil.java

package cn.sunline.adp.cedar.base.engine.transaction;

import cn.sunline.adp.cedar.base.engine.transaction.distributed.DistributedOnlineTransactionProcess;
import cn.sunline.edsp.base.factories.FactoriesLoader;
import java.util.List;

public class OnlineTransactionUtil
{

    public OnlineTransactionUtil()
    {
    }

    public static void commitGlobal(String globalTranSeqNo)
    {
        DistributedOnlineTransactionProcess dotp = (DistributedOnlineTransactionProcess)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/engine/transaction/distributed/DistributedOnlineTransactionProcess);
        dotp.commitBranchTransaction(globalTranSeqNo, globalTranSeqNo, emptyRun);
    }

    public static void rollbackGlobal(String globalTranSeqNo)
    {
        DistributedOnlineTransactionProcess dotp = (DistributedOnlineTransactionProcess)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/engine/transaction/distributed/DistributedOnlineTransactionProcess);
        dotp.rollbackBranchTransaction(globalTranSeqNo, globalTranSeqNo, emptyRun);
    }

    public static void rollbackTransactionNotFinalStatus(String globalTranSeq)
    {
        DistributedOnlineTransactionProcess dotp = (DistributedOnlineTransactionProcess)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/engine/transaction/distributed/DistributedOnlineTransactionProcess);
        dotp.rollbackTransactionNotFinalStatus(globalTranSeq, globalTranSeq, emptyRun);
    }

    public static List queryGlobalFailList(String subSystemCode, String dcnNo, int rowNum)
    {
        DistributedOnlineTransactionProcess dotp = (DistributedOnlineTransactionProcess)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/engine/transaction/distributed/DistributedOnlineTransactionProcess);
        return dotp.queryGlobalFailList(subSystemCode, dcnNo, rowNum);
    }

    private static final Runnable emptyRun = new Runnable() {

        public void run()
        {
        }

    }
;

}
