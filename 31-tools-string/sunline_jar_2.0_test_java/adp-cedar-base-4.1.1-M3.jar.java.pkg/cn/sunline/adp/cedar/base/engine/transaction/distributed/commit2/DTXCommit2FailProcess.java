// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTXCommit2FailProcess.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed.commit2;

import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.ResponseData;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.DistributedOnlineTransactionProcess;

public interface DTXCommit2FailProcess
{

    public abstract void process(DistributedOnlineTransactionProcess distributedonlinetransactionprocess, RequestData requestdata, ResponseData responsedata, Throwable throwable);
}
