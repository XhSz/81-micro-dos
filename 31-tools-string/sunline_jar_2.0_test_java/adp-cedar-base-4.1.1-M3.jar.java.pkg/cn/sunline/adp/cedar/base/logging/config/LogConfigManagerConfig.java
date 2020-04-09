// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogConfigManagerConfig.java

package cn.sunline.adp.cedar.base.logging.config;


// Referenced classes of package cn.sunline.adp.cedar.base.logging.config:
//            LogConfig

public class LogConfigManagerConfig
{
    public static final class LogLevelMode extends Enum
    {

        public static LogLevelMode[] values()
        {
            return (LogLevelMode[])$VALUES.clone();
        }

        public static LogLevelMode valueOf(String name)
        {
            return (LogLevelMode)Enum.valueOf(cn/sunline/adp/cedar/base/logging/config/LogConfigManagerConfig$LogLevelMode, name);
        }

        public static final LogLevelMode USER_DEFINE;
        public static final LogLevelMode LOG4J;
        private static final LogLevelMode $VALUES[];

        static 
        {
            USER_DEFINE = new LogLevelMode("USER_DEFINE", 0);
            LOG4J = new LogLevelMode("LOG4J", 1);
            $VALUES = (new LogLevelMode[] {
                USER_DEFINE, LOG4J
            });
        }

        private LogLevelMode(String s, int i)
        {
            super(s, i);
        }
    }


    public LogConfigManagerConfig()
    {
    }

    public LogConfig getBusiLogConfig()
    {
        return busiLogConfig;
    }

    public void setBusiLogConfig(LogConfig busiLogConfig)
    {
        this.busiLogConfig = busiLogConfig;
    }

    public LogLevelMode getLogLevelMode()
    {
        return logLevelMode;
    }

    public void setLogLevelMode(LogLevelMode logLevelMode)
    {
        this.logLevelMode = logLevelMode;
    }

    private LogLevelMode logLevelMode;
    private LogConfig busiLogConfig;
}
