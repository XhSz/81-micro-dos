// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AdpSpringConfiguration.java

package cn.sunline.adp.boot.configuration;

import cn.sunline.adp.cedar.base.boot.Boot;
import cn.sunline.adp.core.AdpApplicationListener;
import cn.sunline.adp.core.GlobalContext;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import org.springframework.boot.context.event.ApplicationReadyEvent;

class AdpSpringConfiguration
{

    AdpSpringConfiguration()
    {
    }

    public void setAdpApplicationListener(AdpApplicationListener adpApplicationListener)
    {
        GlobalContext.get().setStatus(cn.sunline.adp.core.GlobalContext.SystemStatus.STARTING);
        try
        {
            Boot.get().init();
            adpApplicationListener.setInitEventClass(org/springframework/boot/context/event/ApplicationReadyEvent);
        }
        catch(Exception e)
        {
            System.err.println("Service Started Fail!");
            GlobalContext.get().setStatus(cn.sunline.adp.core.GlobalContext.SystemStatus.FAILED);
            throw new RuntimeException("Service Startup Failure", e);
        }
        return;
    }
}
