// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EdspDynamicThresholdFilter.java

package cn.sunline.adp.cedar.base.logging.filter;

import cn.sunline.adp.cedar.base.boot.plugin.util.Log4j2ThreadContext;
import cn.sunline.adp.cedar.base.logging.LogConfigManager;
import cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig;
import cn.sunline.adp.cedar.base.logging.config.LogLevelAdapt;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.core.impl.ContextDataInjectorFactory;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

public final class EdspDynamicThresholdFilter extends AbstractFilter
{

    public static EdspDynamicThresholdFilter createFilter(String types, KeyValuePair pairs[], Level defaultThreshold, org.apache.logging.log4j.core.Filter.Result onMatch, org.apache.logging.log4j.core.Filter.Result onMismatch)
    {
        Level level = defaultThreshold != null ? defaultThreshold : Level.ERROR;
        List typeArray = StringUtil.isEmpty(types) ? null : StringUtil.split(types);
        Set typeSet = new HashSet();
        if(typeArray != null)
        {
            String type;
            for(Iterator iterator = typeArray.iterator(); iterator.hasNext(); typeSet.add(type))
                type = (String)iterator.next();

        }
        Map map = new HashMap();
        KeyValuePair akeyvaluepair[] = pairs;
        int i = akeyvaluepair.length;
        for(int j = 0; j < i; j++)
        {
            KeyValuePair pair = akeyvaluepair[j];
            map.put(pair.getKey(), Level.toLevel(pair.getValue()));
        }

        return new EdspDynamicThresholdFilter(map, typeSet, level, onMatch, onMismatch);
    }

    private EdspDynamicThresholdFilter(Map pairs, Set types, Level defaultLevel, org.apache.logging.log4j.core.Filter.Result onMatch, org.apache.logging.log4j.core.Filter.Result onMismatch)
    {
        super(onMatch, onMismatch);
        defaultThreshold = Level.ERROR;
        levelMap = new HashMap();
        levelMap = pairs;
        defaultThreshold = defaultLevel;
        this.types = types;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!super.equalsImpl(obj))
            return false;
        if(getClass() != obj.getClass())
            return false;
        EdspDynamicThresholdFilter other = (EdspDynamicThresholdFilter)obj;
        if(defaultThreshold == null)
        {
            if(other.defaultThreshold != null)
                return false;
        } else
        if(!defaultThreshold.equals(other.defaultThreshold))
            return false;
        if(types == null)
        {
            if(other.types != null)
                return false;
        } else
        if(!types.equals(other.types))
            return false;
        if(levelMap == null)
        {
            if(other.levelMap != null)
                return false;
        } else
        if(!levelMap.equals(other.levelMap))
            return false;
        return true;
    }

    private org.apache.logging.log4j.core.Filter.Result filter(Level level, ReadOnlyStringMap contextMap)
    {
        if(types.contains(contextMap.getValue("ltts_log_type")))
        {
            String currentLogLevel = LogConfigManager.get().getCurrentLogLevel();
            if(currentLogLevel == null)
                return level.isMoreSpecificThan(defaultThreshold) ? onMatch : onMismatch;
            static class _cls1
            {

                static final int $SwitchMap$cn$sunline$adp$cedar$base$logging$config$LogConfigManagerConfig$LogLevelMode[];

                static 
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$logging$config$LogConfigManagerConfig$LogLevelMode = new int[cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode.values().length];
                    try
                    {
                        $SwitchMap$cn$sunline$adp$cedar$base$logging$config$LogConfigManagerConfig$LogLevelMode[cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode.LOG4J.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$cn$sunline$adp$cedar$base$logging$config$LogConfigManagerConfig$LogLevelMode[cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode.USER_DEFINE.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                }
            }

            switch(_cls1..SwitchMap.cn.sunline.adp.cedar.base.logging.config.LogConfigManagerConfig.LogLevelMode[LogConfigManager.get().getLogLevelMode().ordinal()])
            {
            case 1: // '\001'
                return log4jModeFilter(currentLogLevel, level);

            case 2: // '\002'
                return userDefineModeFilter(currentLogLevel, level);
            }
        }
        return org.apache.logging.log4j.core.Filter.Result.NEUTRAL;
    }

    private org.apache.logging.log4j.core.Filter.Result log4jModeFilter(String logLevel, Level level)
    {
        Level ctxLevel = (Level)levelMap.get(logLevel);
        if(ctxLevel == null)
        {
            ctxLevel = Level.getLevel(logLevel);
            if(ctxLevel == null)
                ctxLevel = defaultThreshold;
        }
        return !level.isMoreSpecificThan(ctxLevel) && !Level.ERROR.equals(level) ? onMismatch : onMatch;
    }

    private org.apache.logging.log4j.core.Filter.Result userDefineModeFilter(String logLevel, Level level)
    {
        LogLevelAdapt adapt = LogConfigManager.get().getCurrentLogLevelAdapt();
        if(adapt == null)
            return level.isMoreSpecificThan(defaultThreshold) ? onMatch : onMismatch;
        else
            return adapt.match(level) ? onMatch : onMismatch;
    }

    public org.apache.logging.log4j.core.Filter.Result filter(LogEvent event)
    {
        return filter(event.getLevel(), event.getContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t)
    {
        return filter(level, currentContextData());
    }

    public transient org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object params[])
    {
        return filter(level, currentContextData());
    }

    private ReadOnlyStringMap currentContextData()
    {
        return injector.rawContextData();
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, 
            Object p3)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, 
            Object p3, Object p4)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, 
            Object p3, Object p4, Object p5)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, 
            Object p3, Object p4, Object p5, Object p6)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, 
            Object p3, Object p4, Object p5, Object p6, Object p7)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, 
            Object p3, Object p4, Object p5, Object p6, Object p7, Object p8)
    {
        return filter(level, currentContextData());
    }

    public org.apache.logging.log4j.core.Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, 
            Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9)
    {
        return filter(level, currentContextData());
    }

    public int hashCode()
    {
        int prime = 31;
        int result = super.hashCodeImpl();
        result = 31 * result + (defaultThreshold != null ? defaultThreshold.hashCode() : 0);
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (levelMap != null ? levelMap.hashCode() : 0);
        return result;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("types=");
        sb.append('{');
        if(types != null && types.size() > 0)
        {
            boolean first = true;
            String entry;
            for(Iterator iterator = types.iterator(); iterator.hasNext(); sb.append(entry))
            {
                entry = (String)iterator.next();
                if(!first)
                {
                    sb.append(", ");
                    first = false;
                }
            }

        }
        sb.append('}');
        sb.append(", default=").append(defaultThreshold);
        if(levelMap.size() > 0)
        {
            sb.append('{');
            boolean first = true;
            java.util.Map.Entry entry;
            for(Iterator iterator1 = levelMap.entrySet().iterator(); iterator1.hasNext(); sb.append((String)entry.getKey()).append('=').append(entry.getValue()))
            {
                entry = (java.util.Map.Entry)iterator1.next();
                if(!first)
                {
                    sb.append(", ");
                    first = false;
                }
            }

            sb.append('}');
        }
        return sb.toString();
    }

    private Level defaultThreshold;
    private final ContextDataInjector injector = ContextDataInjectorFactory.createInjector();
    private Map levelMap;
    private final Set types;
}
