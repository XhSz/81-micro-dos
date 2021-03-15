// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CedarListener.java

package cn.sunline.adp.cedar.base.listener;

import cn.sunline.adp.cedar.base.boot.Boot;
import cn.sunline.adp.core.AdpBootListener;
import cn.sunline.adp.core.GlobalContext;

public class CedarListener
    implements AdpBootListener
{

    public CedarListener()
    {
    }

    public void onBootEvent(cn.sunline.adp.core.AdpBootListener.AdpBootEvent event)
    {
        if(cn.sunline.adp.core.AdpBootListener.AdpEventType.INIT == event.getAdpEventType())
        {
            GlobalContext.get().setStatus(cn.sunline.adp.core.GlobalContext.SystemStatus.STARTING);
            Boot.get().start();
            GlobalContext.get().setStatus(cn.sunline.adp.core.GlobalContext.SystemStatus.STARTED);
        } else
        if(cn.sunline.adp.core.AdpBootListener.AdpEventType.BEFORE_CLOSE == event.getAdpEventType())
        {
            GlobalContext.get().setStatus(cn.sunline.adp.core.GlobalContext.SystemStatus.STOPPING);
            Boot.get().stop();
            GlobalContext.get().setStatus(cn.sunline.adp.core.GlobalContext.SystemStatus.STOPPED);
        }
    }
}
