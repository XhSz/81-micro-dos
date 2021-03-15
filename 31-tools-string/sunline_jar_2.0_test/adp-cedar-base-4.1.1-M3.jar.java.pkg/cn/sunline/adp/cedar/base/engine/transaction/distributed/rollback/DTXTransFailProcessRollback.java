// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTXTransFailProcessRollback.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed.rollback;

import cn.sunline.adp.cedar.base.engine.*;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.DistributedOnlineTransactionProcess;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.transaction.distributed.rollback:
//            DTXTransFailProcess

public class DTXTransFailProcessRollback
    implements DTXTransFailProcess
{

    public DTXTransFailProcessRollback()
    {
    }

    public void process(DistributedOnlineTransactionProcess dotp, RequestData request, ResponseData response)
    {
        dotp.rollbackTransaction(request, response, response.getHeaderData().getException());
    }

    public static final String SPI_ID = "rollback";
    public static String transFailProcessId;
}
