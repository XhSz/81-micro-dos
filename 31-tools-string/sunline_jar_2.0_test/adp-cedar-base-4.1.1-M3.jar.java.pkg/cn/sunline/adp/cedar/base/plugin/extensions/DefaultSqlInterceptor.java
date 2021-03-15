// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultSqlInterceptor.java

package cn.sunline.adp.cedar.base.plugin.extensions;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.metadata.mybatis.spi.SqlInterceptor;

public class DefaultSqlInterceptor
    implements SqlInterceptor
{

    public DefaultSqlInterceptor()
    {
    }

    public String intercept(String sql)
    {
        return sql;
    }

    private static final SysLog logger = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/plugin/extensions/DefaultSqlInterceptor);

}
