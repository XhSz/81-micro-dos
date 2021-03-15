// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceManager.java

package cn.sunline.adp.cedar.base.engine.sequence;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.bean.SequenceMessage;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.sequence:
//            SequenceCallback, SequenceManagerServiceConfig

public class SequenceManager
{

    public SequenceManager()
    {
    }

    public static SequenceManager get()
    {
        return instance;
    }

    public static void reset()
    {
        instance = new SequenceManager();
    }

    public void init(SequenceManagerServiceConfig config)
    {
        try
        {
            callback = (SequenceCallback)Class.forName(config.getCallbackClass(), true, Thread.currentThread().getContextClassLoader()).newInstance();
        }
        catch(ClassNotFoundException e1)
        {
            try
            {
                SequenceManagerServiceConfig _tmp = config;
                callback = (SequenceCallback)Class.forName(SequenceManagerServiceConfig.DEFAULT_SEQUENCE_CALLBACK_CLASS, true, Thread.currentThread().getContextClassLoader()).newInstance();
            }
            catch(Exception e2)
            {
                throw new IllegalArgumentException(e2);
            }
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(e);
        }
        callback.init();
    }

    public void collection()
    {
        if(callback != null)
            callback.collection();
    }

    public static SequenceMessage nextval(String type, String sequenceName)
    {
        if(get().callback == null)
            throw ExceptionUtil.wrapThrow(EngineConst.SequenceManager01, new String[0]);
        else
            return get().callback.nextval(type, sequenceName);
    }

    private static SequenceManager instance = new SequenceManager();
    private SequenceCallback callback;

}
