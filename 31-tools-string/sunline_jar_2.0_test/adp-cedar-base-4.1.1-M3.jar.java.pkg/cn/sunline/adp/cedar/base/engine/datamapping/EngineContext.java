// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EngineContext.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.engine.ProcessEventListener;
import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineGlobalTransaction;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.datamapping:
//            ProcessEventListenerSupport, DebugProcessEventListener, EngineRuntimeContext

public class EngineContext
{

    public EngineContext(RequestData requestData, EngineRuntimeContext runtimeContext)
    {
        this(requestData, runtimeContext, ((Map) (new HashMap())));
    }

    private EngineContext(RequestData requestData, EngineRuntimeContext runtimeContext, Map txnTempObjMap)
    {
        listener = new ProcessEventListenerSupport();
        this.requestData = requestData;
        this.runtimeContext = runtimeContext;
        this.txnTempObjMap = txnTempObjMap;
        txnShardingInfoMap = new HashMap();
    }

    public static EngineContext clone(EngineRuntimeContext runtimeContext)
    {
        EngineContext ret = new EngineContext(get().requestData, runtimeContext, get().txnTempObjMap);
        ret.listener = get().listener;
        ret.onlineTransactionManager = get().onlineTransactionManager;
        ret.tranException = get().tranException;
        return ret;
    }

    private static EngineContext get()
    {
        if(((Stack)data.get()).empty())
            throw new RuntimeException(EngineConst.EngineContext01);
        else
            return (EngineContext)((Stack)data.get()).peek();
    }

    public static void push(EngineContext engineContext)
    {
        ((Stack)data.get()).push(engineContext);
    }

    public static boolean isEmpty()
    {
        return ((Stack)data.get()).isEmpty();
    }

    public static EngineContext pop()
    {
        if(((Stack)data.get()).empty())
            throw new RuntimeException(EngineConst.EngineContext01);
        else
            return (EngineContext)((Stack)data.get()).pop();
    }

    public static RequestData getRequestData()
    {
        return get().requestData;
    }

    public static EngineRuntimeContext getEngineRuntimeContext()
    {
        return get().runtimeContext;
    }

    public static Map getTxnTempObjMap()
    {
        return get().txnTempObjMap;
    }

    public static void clear()
    {
        ((Stack)data.get()).clear();
    }

    public static void setListener(ProcessEventListener listener)
    {
        get().listener = listener;
    }

    public static ProcessEventListener getListener()
    {
        return get().listener;
    }

    public static boolean isDebugEventListener()
    {
        return get().listener instanceof DebugProcessEventListener;
    }

    public static Object getTxnTempObj(String key)
    {
        return get().txnTempObjMap.get(key);
    }

    public static void setTxnTempObj(String key, Object value)
    {
        get().txnTempObjMap.put(key, value);
    }

    public static Throwable getTranException()
    {
        return get().tranException;
    }

    public static void setTranException(Throwable tranException)
    {
        get().tranException = tranException;
    }

    public static OnlineGlobalTransaction getOnlineTransactionManager()
    {
        return get().onlineTransactionManager;
    }

    public static void setOnlineTransactionManager(OnlineGlobalTransaction onlineTransactionManager)
    {
        get().onlineTransactionManager = onlineTransactionManager;
    }

    public static Object getTxnShardingInfoMap(String key)
    {
        return ((EngineContext)((Stack)data.get()).peek()).txnShardingInfoMap.get(key);
    }

    public static void setTxnShardingInfoMap(String key, Object value)
    {
        ((EngineContext)((Stack)data.get()).peek()).txnShardingInfoMap.put(key, value);
    }

    private final RequestData requestData;
    private final EngineRuntimeContext runtimeContext;
    private final Map txnTempObjMap;
    private final Map txnShardingInfoMap;
    private OnlineGlobalTransaction onlineTransactionManager;
    private Throwable tranException;
    private ProcessEventListener listener;
    private static final ThreadLocal data = new ThreadLocal() {

        protected Stack initialValue()
        {
            return new Stack();
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;

}
