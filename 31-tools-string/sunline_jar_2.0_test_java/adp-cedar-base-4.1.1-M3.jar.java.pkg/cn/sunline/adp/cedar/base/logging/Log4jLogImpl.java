// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log4jLogImpl.java

package cn.sunline.adp.cedar.base.logging;

import cn.sunline.adp.cedar.base.util.SysPropertyUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.spi.ExtendedLogger;

// Referenced classes of package cn.sunline.adp.cedar.base.logging:
//            ReusableFormatMessage, BizLog, SysLog, CustomLog4j2Level

public class Log4jLogImpl
    implements BizLog, SysLog
{

    private static ReusableFormatMessage getMessage()
    {
        ReusableFormatMessage result = (ReusableFormatMessage)threadLocalParameterized.get();
        if(result == null)
        {
            result = new ReusableFormatMessage();
            threadLocalParameterized.set(result);
        }
        return result;
    }

    public Log4jLogImpl(String name)
    {
        fqcn = cn/sunline/adp/cedar/base/logging/Log4jLogImpl.getName();
        log4j_native_message = SysPropertyUtil.getBooleanProperty("log4j_native_message", false);
        log4j = (ExtendedLogger)LogManager.getLogger(name);
    }

    public Log4jLogImpl(ExtendedLogger log4j)
    {
        fqcn = cn/sunline/adp/cedar/base/logging/Log4jLogImpl.getName();
        log4j_native_message = SysPropertyUtil.getBooleanProperty("log4j_native_message", false);
        this.log4j = log4j;
    }

    public void debug(Object message)
    {
        logWithParam(Level.DEBUG, message, new Object[0]);
    }

    public transient void dataArea(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.DATAAREA(), message, args);
    }

    public void dataArea(Object message)
    {
        logWithParam(CustomLog4j2Level.DATAAREA(), message, new Object[0]);
    }

    public transient void sql(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.SQL(), message, args);
    }

    public void sql(Object message)
    {
        logWithParam(CustomLog4j2Level.SQL(), message, new Object[0]);
    }

    public transient void sqlInfo(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.SQLINFO(), message, args);
    }

    public void sqlInfo(Object message)
    {
        logWithParam(CustomLog4j2Level.SQLINFO(), message, new Object[0]);
    }

    public transient void service(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.SERVICE(), message, args);
    }

    public void service(Object message)
    {
        logWithParam(CustomLog4j2Level.SERVICE(), message, new Object[0]);
    }

    public void error(Object message)
    {
        logWithParam(Level.ERROR, message, new Object[0]);
    }

    public void info(Object message)
    {
        logWithParam(Level.INFO, message, new Object[0]);
    }

    public transient void warn(String message, Throwable t, Object args[])
    {
        logWithParam(Level.WARN, message, t, args);
    }

    public transient void warn(String message, Object args[])
    {
        logWithParam(Level.WARN, message, args);
    }

    public void warn(Object message)
    {
        logWithParam(Level.WARN, message, new Object[0]);
    }

    public transient void fatal(String message, Throwable t, Object args[])
    {
        logWithParam(Level.FATAL, message, t, args);
    }

    public transient void fatal(String message, Object args[])
    {
        logWithParam(Level.FATAL, message, args);
    }

    public void fatal(Object message)
    {
        logWithParam(Level.FATAL, message, new Object[0]);
    }

    public transient void profile(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.PROFILE(), message, args);
    }

    public transient void debug(String message, Throwable t, Object args[])
    {
        logWithParam(Level.DEBUG, message, t, args);
    }

    public transient void debug(String message, Object args[])
    {
        logWithParam(Level.DEBUG, message, args);
    }

    public transient void error(String message, Throwable t, Object args[])
    {
        logWithParam(Level.ERROR, message, t, args);
    }

    public transient void error(String message, Object args[])
    {
        logWithParam(Level.ERROR, message, args);
    }

    public transient void info(String message, Throwable t, Object args[])
    {
        logWithParam(Level.INFO, message, t, args);
    }

    public transient void info(String message, Object args[])
    {
        logWithParam(Level.INFO, message, args);
    }

    public transient void method(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.METHOD(), message, args);
    }

    public transient void techMethod(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.TECH_METHOD(), message, args);
    }

    public transient void parm(String message, Object args[])
    {
        logWithParam(CustomLog4j2Level.PARM(), message, args);
    }

    public boolean isWarnEnabled()
    {
        return log4j.isWarnEnabled();
    }

    public boolean isProfileEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.PROFILE());
    }

    public boolean isFatalEnabled()
    {
        return log4j.isEnabled(Level.FATAL);
    }

    public boolean isSqlEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.SQL());
    }

    public boolean isSqlInfoEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.SQLINFO());
    }

    public boolean isServiceEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.SERVICE());
    }

    public boolean isDataAreaEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.DATAAREA());
    }

    public boolean isDebugEnabled()
    {
        return log4j.isEnabled(Level.DEBUG);
    }

    public boolean isErrorEnabled()
    {
        return log4j.isEnabled(Level.ERROR);
    }

    public boolean isInfoEnabled()
    {
        return log4j.isEnabled(Level.INFO);
    }

    public boolean isParmEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.PARM());
    }

    public boolean isMethodEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.METHOD());
    }

    public boolean isTechMethodEnabled()
    {
        return log4j.isEnabled(CustomLog4j2Level.TECH_METHOD());
    }

    private transient void logWithParam(Level level, Object message, Object args[])
    {
        logWithParam(level, message, null, args);
    }

    private transient void logWithParam(Level level, Object message, Throwable t, Object args[])
    {
        if(args == null)
            log4j.logIfEnabled(fqcn, level, null, message, t);
        if(log4j_native_message)
            log4j.logIfEnabled(fqcn, level, null, String.valueOf(message), ((Object) (args)), t);
        else
            log4j.logIfEnabled(fqcn, level, null, getMessage().set(String.valueOf(message), args), t);
    }

    private static ThreadLocal threadLocalParameterized = new ThreadLocal();
    private String fqcn;
    private boolean log4j_native_message;
    private ExtendedLogger log4j;

}
