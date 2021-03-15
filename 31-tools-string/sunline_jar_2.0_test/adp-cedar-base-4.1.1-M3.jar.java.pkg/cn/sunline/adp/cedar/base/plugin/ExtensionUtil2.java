// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionUtil2.java

package cn.sunline.adp.cedar.base.plugin;

import cn.sunline.adp.cedar.base.boot.plugin.IProcess;
import cn.sunline.adp.cedar.base.boot.plugin.util.ExtensionUtil;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.plugin.spi.IAlert;

public class ExtensionUtil2
{

    public ExtensionUtil2()
    {
    }

    public static void executeExtensionPointWithoutException(String pointId, IProcess p, String alertKey)
    {
        try
        {
            ExtensionUtil.executeExtensionPoint(pointId, p);
        }
        catch(Exception e)
        {
            LOGGERPLUGIN.error(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C015(), e, new Object[] {
                pointId
            });
            IAlert.getInstance().report(alertKey, e.getMessage());
        }
    }

    private static final SysLog LOGGERPLUGIN = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/plugin/ExtensionUtil2);

}
