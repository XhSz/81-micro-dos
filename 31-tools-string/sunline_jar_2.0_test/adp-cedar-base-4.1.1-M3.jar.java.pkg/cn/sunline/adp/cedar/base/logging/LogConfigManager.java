// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogConfigManager.java

package cn.sunline.adp.cedar.base.logging;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig;
import cn.sunline.adp.cedar.base.logging.config.LogLevelAdapt;

// Referenced classes of package cn.sunline.adp.cedar.base.logging:
//            DefaultLogLevelContext, UserDefineLogLevelContext, SysLog, LogLevelContext, 
//            Log4j2Util, SysLogUtil

public class LogConfigManager
{
    public static final class SystemType extends Enum
    {

        public static SystemType[] values()
        {
            return (SystemType[])$VALUES.clone();
        }

        public static SystemType valueOf(String name)
        {
            return (SystemType)Enum.valueOf(cn/sunline/adp/cedar/base/logging/LogConfigManager$SystemType, name);
        }

        public static final SystemType onl;
        public static final SystemType batch;
        public static final SystemType report;
        public static final SystemType ops;
        private static final SystemType $VALUES[];

        static 
        {
            onl = new SystemType("onl", 0);
            batch = new SystemType("batch", 1);
            report = new SystemType("report", 2);
            ops = new SystemType("ops", 3);
            $VALUES = (new SystemType[] {
                onl, batch, report, ops
            });
        }

        private SystemType(String s, int i)
        {
            super(s, i);
        }
    }


    private LogConfigManager()
    {
        currentSystemType = new ThreadLocal();
        logLevelContext = new DefaultLogLevelContext();
        logLevelMode = cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode.LOG4J;
    }

    public static LogConfigManager get()
    {
        return _instance;
    }

    public void init(LogConfigManagerConfig config)
    {
        if(config == null)
        {
            BOOT_LOGGER.info(BaseConst.LogConfigManager01);
            return;
        }
        logLevelMode = config.getLogLevelMode();
        if(cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode.USER_DEFINE == logLevelMode)
            logLevelContext = new UserDefineLogLevelContext(config);
    }

    public void setCurrentLogLevel(String type, String bizLevel)
    {
        logLevelContext.setCurrentLogLevel(type, bizLevel);
    }

    public String getCurrentLogLevel()
    {
        return logLevelContext.getCurrentLogLevel();
    }

    public void setCurrentLogType(String type)
    {
        logLevelContext.setCurrentLogType(type);
    }

    public LogLevelAdapt getCurrentLogLevelAdapt()
    {
        return logLevelContext.getCurrentLogLevelAdapt();
    }

    public SystemType getCurrentSystemType()
    {
        return (SystemType)currentSystemType.get();
    }

    public void setCurrentSystemType(SystemType systemType)
    {
        currentSystemType.set(systemType);
        Log4j2Util.setSystemType(systemType);
    }

    public cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode getLogLevelMode()
    {
        return logLevelMode;
    }

    private static LogConfigManager _instance = new LogConfigManager();
    private static SysLog BOOT_LOGGER = SysLogUtil.getBootLogger();
    private ThreadLocal currentSystemType;
    private LogLevelContext logLevelContext;
    private cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode logLevelMode;

}
