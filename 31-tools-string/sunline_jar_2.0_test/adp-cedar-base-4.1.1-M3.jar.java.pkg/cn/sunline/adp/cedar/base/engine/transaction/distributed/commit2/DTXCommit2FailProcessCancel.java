// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTXCommit2FailProcessCancel.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed.commit2;

import cn.sunline.adp.cedar.base.engine.*;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineTransactionConstant;
import cn.sunline.adp.cedar.base.engine.transaction.ServiceTransactionStatus;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.DistributedOnlineTransactionProcess;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.transaction.distributed.commit2:
//            DTXCommit2FailProcess

public class DTXCommit2FailProcessCancel
    implements DTXCommit2FailProcess
{

    public DTXCommit2FailProcessCancel()
    {
    }

    public void process(DistributedOnlineTransactionProcess dotp, RequestData request, ResponseData response, Throwable e)
    {
        response.getHeaderData().setDTXStatus(ServiceTransactionStatus.SyncC2FailToCancel.getValue());
        response.getHeaderData().setRetStatus(cn.sunline.adp.cedar.base.engine.ResponseHeaderData.RetStatus.FAILURE.getValue());
        response.getHeaderData().setResponseMsg(e.getMessage());
        response.getHeaderData().setResponseCode(OnlineTransactionConstant.getTranErrorcd());
    }

    public static final String SPI_ID = "cancel";
}
