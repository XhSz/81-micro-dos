// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogConfig.java

package cn.sunline.adp.cedar.base.logging.config;

import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.List;

public class LogConfig
{
    public static class LogLevelConfig
    {

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getLongname()
        {
            return longname;
        }

        public void setLongname(String longname)
        {
            this.longname = longname;
        }

        public String getLevel()
        {
            return level;
        }

        public void setLevel(String level)
        {
            this.level = level;
        }

        private String type;
        private String longname;
        private String level;

        public LogLevelConfig()
        {
        }
    }

    public static class LogLevel
    {

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getLongname()
        {
            return longname;
        }

        public void setLongname(String longname)
        {
            this.longname = longname;
        }

        public String getValue()
        {
            return value;
        }

        public String[] getValues()
        {
            if(StringUtil.isEmpty(value))
                return null;
            else
                return value.split("[\\s,]+");
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        private String id;
        private String longname;
        private String value;

        public LogLevel()
        {
        }
    }

    public static class LogLevelDefine
    {

        public String getLongname()
        {
            return longname;
        }

        public void setLongname(String longname)
        {
            this.longname = longname;
        }

        public List getLevles()
        {
            return levles;
        }

        public void setLevles(List levles)
        {
            this.levles = levles;
        }

        private String longname;
        private List levles;

        public LogLevelDefine()
        {
        }
    }


    public LogConfig()
    {
    }

    public String getLongname()
    {
        return longname;
    }

    public void setLongname(String longname)
    {
        this.longname = longname;
    }

    public LogLevelDefine getLevelDefine()
    {
        return levelDefine;
    }

    public void setLevelDefine(LogLevelDefine levelDefine)
    {
        this.levelDefine = levelDefine;
    }

    public List getLogLevelConfigs()
    {
        return logLevelConfigs;
    }

    public void setLogLevelConfigs(List logLevelConfigs)
    {
        this.logLevelConfigs = logLevelConfigs;
    }

    private String longname;
    private LogLevelDefine levelDefine;
    private List logLevelConfigs;
}
