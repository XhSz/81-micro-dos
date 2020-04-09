// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DebugProcessEventListener.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.engine.ProcessEventListener;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.core.profile.ProfileSwitcher;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DebugProcessEventListener
    implements ProcessEventListener
{

    public DebugProcessEventListener()
    {
        content = new ByteArrayOutputStream();
        out = new PrintStream(content);
        level = 0;
    }

    public String getDebugInfo()
    {
        return content.toString();
    }

    public void beginProcess(String message)
    {
        out.format("%s%s%n", new Object[] {
            n(level++, "\t"), message
        });
    }

    public void endProcess()
    {
        level--;
    }

    private static String n(int n, String s)
    {
        StringBuffer ret = new StringBuffer();
        for(int i = 0; i < n; i++)
            ret.append(s);

        return ret.toString();
    }

    public void beginProcess(String serviceId, String longname)
    {
        log.debug(EngineConst.DebugProcessEventListener01, new Object[] {
            serviceId, longname
        });
        out.format(EngineConst.DebugProcessEventListener02, new Object[] {
            n(level++, "\t"), serviceId, longname
        });
    }

    public void endProcess(String serviceId, String longname)
    {
        level--;
    }

    public void beginDataMappingProcess(String message)
    {
        if(ProfileSwitcher.get().printDataMappingStack)
            beginProcess(message);
    }

    public void endDataMappingProcess()
    {
        if(ProfileSwitcher.get().printDataMappingStack)
            endProcess();
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/datamapping/DebugProcessEventListener);
    private ByteArrayOutputStream content;
    private PrintStream out;
    private int level;
    private static final String INDENT_STRING = "\t";

}
