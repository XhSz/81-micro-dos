// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultDBPassSecurityManager.java

package cn.sunline.adp.cedar.base.security;

import cn.sunline.adp.cedar.base.ConfigConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;

// Referenced classes of package cn.sunline.adp.cedar.base.security:
//            DefaultDBPassSecurityImpl, DBPassSecurity, DBPassSecurityManager

public class DefaultDBPassSecurityManager
    implements DBPassSecurityManager
{

    public DefaultDBPassSecurityManager()
    {
        security = new DefaultDBPassSecurityImpl();
    }

    public void init(String callback)
    {
        try
        {
            security = (DBPassSecurity)ReflectionUtil.classForName(callback).newInstance();
        }
        catch(Exception e)
        {
            syslog.warn(ConfigConst.DefaultDBPassSecurityManager01, new Object[] {
                callback
            });
        }
    }

    public DBPassSecurity getSecurity()
    {
        return security;
    }

    private static final SysLog syslog = SysLogUtil.getBootLogger();
    private DBPassSecurity security;

}
