// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorManager.java

package cn.sunline.adp.vine.base.exception;

import cn.sunline.adp.vine.base.util.lang.StringUtils;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            FlowException

public class ErrorManager
{

    public ErrorManager()
    {
        msgFmt = new MessageFormat("");
        pp = new Properties();
    }

    private Properties load(String file)
    {
        InputStream in = null;
        Properties properties;
        try
        {
            in = getClass().getResourceAsStream(file);
            pp.load(in);
            properties = pp;
        }
        catch(Exception e)
        {
            throw new FlowException(e.getMessage(), e);
        }
        if(in != null)
            try
            {
                in.close();
            }
            catch(Exception exception) { }
        return properties;
        Exception exception1;
        exception1;
        if(in != null)
            try
            {
                in.close();
            }
            catch(Exception exception2) { }
        throw exception1;
    }

    public String getMessage(String key, Object args[])
    {
        String msg = pp.getProperty(key);
        if(StringUtils.isEmpty(msg))
        {
            String errMsg = (new StringBuilder()).append("can't find the error message with key '").append(key).append("' !").toString();
            log.error(errMsg);
            return key;
        } else
        {
            msgFmt.applyPattern(msg);
            return msgFmt.format(((Object) (args)));
        }
    }

    public static synchronized ErrorManager getInstance()
    {
        if(instance == null)
            try
            {
                instance = new ErrorManager();
                instance.load("errorMsg.properties");
            }
            catch(Throwable e)
            {
                log.error(e.getMessage(), e);
            }
        return instance;
    }

    public static final String FILE_ERR = "errorMsg.properties";
    private static ErrorManager instance;
    private static Log log = LogFactory.getLog(cn/sunline/adp/vine/base/exception/ErrorManager);
    private MessageFormat msgFmt;
    private Properties pp;

}
