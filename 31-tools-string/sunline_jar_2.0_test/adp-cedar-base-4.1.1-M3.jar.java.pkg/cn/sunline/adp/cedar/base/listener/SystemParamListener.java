// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemParamListener.java

package cn.sunline.adp.cedar.base.listener;

import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.plugin.config.SystemParamManagerConfig;
import cn.sunline.adp.cedar.base.util.LttsPkgUtil;
import cn.sunline.adp.cedar.base.util.SystemParams;
import cn.sunline.adp.core.AdpBootListener;
import cn.sunline.adp.core.profile.ProfileSwitcher;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.Map;

public class SystemParamListener
    implements AdpBootListener
{

    public SystemParamListener()
    {
    }

    public void onBootEvent(cn.sunline.adp.core.AdpBootListener.AdpBootEvent event)
    {
        if(config == null)
        {
            log.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C038());
            return;
        }
        System.setProperty("edsp_system_id", config.getSystemCode());
        System.setProperty("edsp_tenant_id", config.getTenantId());
        System.setProperty("edsp_subSystem_id", config.getSubSystemId());
        System.setProperty("edsp_dcnNo", config.getDcnNo());
        System.setProperty("edsp_distributedSystem", String.valueOf(config.isDistributedSystem()));
        Map globalSystemOptions = null;
        if(StringUtil.isNotEmpty(config.getOptions()))
            globalSystemOptions = LttsPkgUtil.parse(config.getOptions());
        log.info(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C039(), new Object[] {
            globalSystemOptions
        });
        ProfileSwitcher.initGlobalSystemOptions(globalSystemOptions);
        ProfileSwitcher.reset();
    }

    private static final SysLog log = SysLogUtil.getBootLogger();
    SystemParamManagerConfig config;

}
