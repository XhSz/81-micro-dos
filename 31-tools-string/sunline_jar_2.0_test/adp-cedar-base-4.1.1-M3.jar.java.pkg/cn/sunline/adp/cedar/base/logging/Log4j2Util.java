// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log4j2Util.java

package cn.sunline.adp.cedar.base.logging;

import cn.sunline.adp.cedar.base.boot.plugin.util.Log4j2ThreadContext;
import cn.sunline.edsp.base.util.date.DateUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;
import org.apache.logging.log4j.ThreadContext;

// Referenced classes of package cn.sunline.adp.cedar.base.logging:
//            LogConfigManager

public class Log4j2Util extends Log4j2ThreadContext
{
    public static class LogType
    {

        public static final String POLL = "poll";
        public static final String LINKS = "links";
        public static final String TIMER = "timer";
        public static final String DOMAIN = "domain";
        public static final String BIZ_ONL = "biz.onl";
        public static final String BIZ_JOB = "biz.job";
        public static final String RESULT_ONL_TRAN = "result.onl.tran";

        public LogType()
        {
        }
    }

    public static class LogKey
    {

        public static final String LOG_KIDE = "kind";
        public static final String LINKS_PORT = "links.port";
        public static final String PACKAGE_DATE = "pckgdt";
        public static final String THREAD_NAME = "threadName";
        public static final String TRANS_DATE = "transDate";
        public static final String TRACE_ID = "trace.id";
        public static final String LOG_CORRELATION_ID = "log.correlation.id";
        public static final String LOGSTACK_CLIENT_ID = "logstack.client.id";

        public LogKey()
        {
        }
    }

    public static abstract class RunnableWithLogContext
        implements Runnable, WithLogContext
    {

        public Map getParentThreadLogContext()
        {
            return parentThreadLogContext;
        }

        public abstract void run();

        private Map parentThreadLogContext;

        public RunnableWithLogContext()
        {
            parentThreadLogContext = Log4j2Util.getContext();
        }
    }

    public static interface WithLogContext
    {

        public abstract Map getParentThreadLogContext();
    }


    public Log4j2Util()
    {
    }

    public static Map getContext()
    {
        return ThreadContext.getContext();
    }

    public static void putContext(Map map)
    {
        Iterator iterator = map.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry m = (java.util.Map.Entry)iterator.next();
            if(!StringUtil.isEmpty((String)m.getKey()))
                ThreadContext.put((String)m.getKey(), (String)m.getValue());
        } while(true);
    }

    public static void putContext(String key, String value)
    {
        ThreadContext.put(key, value);
    }

    public static void clearThreadContext()
    {
        ThreadContext.remove("systemType");
        ThreadContext.remove("http");
        ThreadContext.remove("tcp");
        ThreadContext.remove("kind");
        ThreadContext.remove("links.port");
        ThreadContext.remove("pckgdt");
        ThreadContext.remove("threadName");
        ThreadContext.remove("transDate");
        ThreadContext.remove("trace.id");
        ThreadContext.remove("log.correlation.id");
        ThreadContext.remove("logstack.client.id");
        ThreadContext.remove("poll");
        ThreadContext.remove("links");
        ThreadContext.remove("timer");
        ThreadContext.remove("domain");
        ThreadContext.remove("biz.onl");
        ThreadContext.remove("biz.job");
        ThreadContext.remove("result.onl.tran");
        ThreadContext.remove("ltts_log_type");
        ThreadContext.remove("token2");
    }

    public static void clearAllThreadContext()
    {
        clearThreadContext();
        ThreadContext.clearStack();
    }

    public static void setSystemType(LogConfigManager.SystemType type)
    {
        ThreadContext.put("systemType", type.toString());
    }

    public static String getLogType()
    {
        return ThreadContext.get("ltts_log_type");
    }

    public static void setLogType(String logType)
    {
        ThreadContext.put("ltts_log_type", logType);
    }

    public static void setLinkLogContext(String kind, String port)
    {
        clearThreadContext();
        setLogType("links");
        ThreadContext.put("kind", kind);
        ThreadContext.put("links.port", port);
        ThreadContext.put("token2", genToken());
        ThreadContext.put("threadName", Thread.currentThread().getName());
        ThreadContext.put("pckgdt", DateUtil.getNow("yyyyMMdd"));
    }

    public static void setLinkLogContext(String busiSeqNo, String callSeqNo, String serverId)
    {
        ThreadContext.put("trace.id", busiSeqNo);
        ThreadContext.put("log.correlation.id", callSeqNo);
        ThreadContext.put("logstack.client.id", serverId);
    }

    public static String genToken()
    {
        return StringUtil.random(8, true);
    }

    public static void setPollThreadLogContext(String kindName)
    {
        setSystemType(LogConfigManager.SystemType.batch);
        setLogType("poll");
        ThreadContext.put("kind", kindName);
        set_token();
    }

    public static void resetLogLevel(String logType, String logLevel)
    {
        setLogType(logType);
        LogConfigManager.get().setCurrentLogLevel(logType, logLevel);
    }

    private static final String SYSTEM_TYPE = "systemType";
    public static final String LINKS_HTTP = "http";
    public static final String LINKS_RPC = "rpc";
    public static final String LINKS_TCP = "tcp";
}
