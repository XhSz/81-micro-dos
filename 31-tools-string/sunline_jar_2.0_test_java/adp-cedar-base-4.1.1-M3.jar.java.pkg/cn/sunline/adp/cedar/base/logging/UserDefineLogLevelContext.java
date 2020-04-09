// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserDefineLogLevelContext.java

package cn.sunline.adp.cedar.base.logging;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.logging.config.LogConfig;
import cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig;
import cn.sunline.adp.cedar.base.logging.config.LogLevelAdapt;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.logging:
//            LogLevelContext, SysLog, SysLogUtil

public class UserDefineLogLevelContext
    implements LogLevelContext
{

    public UserDefineLogLevelContext(LogConfigManagerConfig config)
    {
        currentLogType = new ThreadLocal();
        currentLogLevel = new ThreadLocal();
        logLevelDefine = new HashMap();
        globalLogLevel = new HashMap();
        LogConfig bizLogConfig = config.getBusiLogConfig();
        if(bizLogConfig.getLevelDefine() == null || StringUtil.isEmpty(bizLogConfig.getLevelDefine().getLevles()))
        {
            BOOT_LOGGER.info(BaseConst.LogConfigManager02);
        } else
        {
            cn.sunline.adp.cedar.base.logging.config.LogConfig.LogLevel level;
            for(Iterator iterator = bizLogConfig.getLevelDefine().getLevles().iterator(); iterator.hasNext(); logLevelDefine.put(level.getId(), new LogLevelAdapt(level.getId(), level.getValues())))
                level = (cn.sunline.adp.cedar.base.logging.config.LogConfig.LogLevel)iterator.next();

        }
        if(StringUtil.isNotEmpty(bizLogConfig.getLogLevelConfigs()))
        {
            cn.sunline.adp.cedar.base.logging.config.LogConfig.LogLevelConfig levelConfig;
            LogLevelAdapt logLevelAdapt;
            for(Iterator iterator1 = bizLogConfig.getLogLevelConfigs().iterator(); iterator1.hasNext(); globalLogLevel.put(levelConfig.getType(), logLevelAdapt))
            {
                levelConfig = (cn.sunline.adp.cedar.base.logging.config.LogConfig.LogLevelConfig)iterator1.next();
                logLevelAdapt = (LogLevelAdapt)logLevelDefine.get(levelConfig.getLevel());
                if(logLevelAdapt == null)
                    throw ExceptionUtil.wrapThrow(BaseConst.LogConfigManager04, new String[] {
                        levelConfig.getLevel()
                    });
            }

        }
    }

    public void setCurrentLogLevel(String type, String bizLevel)
    {
        currentLogType.set(type);
        LogLevelAdapt logLevelAdapt = (LogLevelAdapt)logLevelDefine.get(bizLevel);
        if(logLevelAdapt == null)
        {
            logLevelAdapt = (LogLevelAdapt)globalLogLevel.get(type);
            if(logLevelAdapt == null)
                BOOT_LOGGER.info(String.format(BaseConst.LogConfigManager03, new Object[] {
                    bizLevel
                }));
        }
        currentLogLevel.set(logLevelAdapt);
    }

    public String getCurrentLogLevel()
    {
        LogLevelAdapt logLevelAdapt = getCurrentLogLevelAdapt();
        if(logLevelAdapt == null)
            return null;
        else
            return logLevelAdapt.getLevelDefine();
    }

    public LogLevelAdapt getCurrentLogLevelAdapt()
    {
        LogLevelAdapt logLevelAdapt = (LogLevelAdapt)currentLogLevel.get();
        if(logLevelAdapt == null)
        {
            String logType = (String)currentLogType.get();
            if(logType != null)
                logLevelAdapt = (LogLevelAdapt)globalLogLevel.get(logType);
        }
        return logLevelAdapt;
    }

    public void setCurrentLogType(String type)
    {
        currentLogType.set(type);
    }

    private static SysLog BOOT_LOGGER = SysLogUtil.getBootLogger();
    private ThreadLocal currentLogType;
    private ThreadLocal currentLogLevel;
    private Map logLevelDefine;
    private Map globalLogLevel;

}
