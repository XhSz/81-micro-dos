// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorkerChannelBoardManager.java

package cn.sunline.adp.cedar.base.healthcheck;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            WorkerChannelBoard

public class WorkerChannelBoardManager
{

    private WorkerChannelBoardManager()
    {
    }

    public static WorkerChannelBoard register(Class clazz)
    {
        WorkerChannelBoard ret = new WorkerChannelBoard(clazz.getSimpleName());
        BOARDS.put(clazz.getSimpleName(), ret);
        return ret;
    }

    public static void unregister(Class clazz)
    {
        BOARDS.remove(clazz.getSimpleName());
    }

    public static WorkerChannelBoard get(Class clazz)
    {
        return (WorkerChannelBoard)BOARDS.get(clazz.getSimpleName());
    }

    private static final ConcurrentMap BOARDS = new ConcurrentHashMap();

}
