// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTXTransFailProcess.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed.rollback;

import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.ResponseData;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.DistributedOnlineTransactionProcess;

public interface DTXTransFailProcess
{

    public abstract void process(DistributedOnlineTransactionProcess distributedonlinetransactionprocess, RequestData requestdata, ResponseData responsedata);
}
