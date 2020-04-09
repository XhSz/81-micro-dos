// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineGlobalTransactionManager.java

package cn.sunline.adp.cedar.base.engine.transaction;

import cn.sunline.adp.cedar.base.constant.TspEngineConstantDef;
import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.service.ServiceTypeEnum;
import cn.sunline.adp.cedar.base.engine.transaction.atomic.AtomicOnlineTransaction;
import cn.sunline.adp.cedar.base.engine.transaction.chunk.ChunkOnlineTransaction;
import cn.sunline.adp.cedar.base.engine.transaction.distributed.DistributedOnlineTransaction;
import cn.sunline.adp.cedar.base.engine.transaction.readonly.ReadOnlyOnlineTransaction;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.transaction:
//            OnlineTransactionMode, OnlineGlobalTransaction

public class OnlineGlobalTransactionManager
{

    public OnlineGlobalTransactionManager()
    {
    }

    public static OnlineGlobalTransaction newOnlineGlobalTransaction(RequestData request, OnlineTransactionMode transactionMode, ServiceTypeEnum serviceType)
    {
        if(transactionMode == null)
            return new AtomicOnlineTransaction(request);
        static class _cls1
        {

            static final int $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$OnlineTransactionMode[];

            static 
            {
                $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$OnlineTransactionMode = new int[OnlineTransactionMode.values().length];
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$OnlineTransactionMode[OnlineTransactionMode.ATOMIC.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$OnlineTransactionMode[OnlineTransactionMode.READ_ONLY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$OnlineTransactionMode[OnlineTransactionMode.CHUNK.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$transaction$OnlineTransactionMode[OnlineTransactionMode.DISTRIBUTED.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
            }
        }

        switch(_cls1..SwitchMap.cn.sunline.adp.cedar.base.engine.transaction.OnlineTransactionMode[transactionMode.ordinal()])
        {
        case 1: // '\001'
            return new AtomicOnlineTransaction(request);

        case 2: // '\002'
            return ReadOnlyOnlineTransaction.get();

        case 3: // '\003'
            return new ChunkOnlineTransaction(request);

        case 4: // '\004'
            return new DistributedOnlineTransaction(request, serviceType);
        }
        throw new RuntimeException(String.format(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C012(), new Object[] {
            transactionMode
        }));
    }
}
