// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LttsTestUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;

public class LttsTestUtil
{

    public LttsTestUtil()
    {
    }

    public static void println(Object message)
    {
        log.info(message != null ? ((Object) (message.toString())) : "NULL");
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/util/LttsTestUtil);

}
