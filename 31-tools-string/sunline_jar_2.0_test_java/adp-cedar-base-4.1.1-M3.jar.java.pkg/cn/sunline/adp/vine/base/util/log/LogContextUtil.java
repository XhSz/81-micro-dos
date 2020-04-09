// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogContextUtil.java

package cn.sunline.adp.vine.base.util.log;

import cn.sunline.adp.vine.base.constant.Constant;
import cn.sunline.edsp.base.util.date.DateUtil;
import org.apache.logging.log4j.ThreadContext;

public class LogContextUtil
{

    private LogContextUtil()
    {
    }

    public static void setLinkLogContext(String kind)
    {
        ThreadContext.clearAll();
        ThreadContext.put("type", "links");
        ThreadContext.put("kind", kind);
        ThreadContext.put("pkgdt", DateUtil.getNow("yyyyMMdd"));
    }
}
