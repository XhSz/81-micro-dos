// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IAlert.java

package cn.sunline.adp.cedar.base.plugin.spi;

import cn.sunline.adp.cedar.base.boot.plugin.IReplaceExtension;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.edsp.base.factories.FactoriesLoader;

public abstract class IAlert
    implements IReplaceExtension
{

    public IAlert()
    {
    }

    public static IAlert getInstance()
    {
        return (IAlert)FactoriesLoader.getNewestFactory(cn/sunline/adp/cedar/base/plugin/spi/IAlert);
    }

    public void report(String key, String content)
    {
        try
        {
            reportWithoutException(key, content);
        }
        catch(Exception e)
        {
            LOGGER.error(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C050(), e, new Object[] {
                key, content
            });
        }
    }

    public abstract void reportWithoutException(String s, String s1);

    public static String POINT = "core.alert";
    private static final SysLog LOGGER = SysLogUtil.getErrorLogger();

}
