// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReusableFormatMessage.java

package cn.sunline.adp.cedar.base.logging;

import cn.sunline.edsp.base.util.lang.StringUtil;
import org.apache.logging.log4j.message.*;

public class ReusableFormatMessage
    implements ReusableMessage
{

    public ReusableFormatMessage()
    {
    }

    public transient ReusableFormatMessage set(String messagePattern, Object arguments[])
    {
        this.messagePattern = messagePattern;
        this.arguments = arguments;
        return this;
    }

    public String getFormattedMessage()
    {
        StringBuilder sb = new StringBuilder();
        StringUtil.format(sb, messagePattern, arguments);
        return sb.toString();
    }

    public String getFormat()
    {
        return messagePattern;
    }

    public Object[] getParameters()
    {
        return arguments;
    }

    public Throwable getThrowable()
    {
        return null;
    }

    public void formatTo(StringBuilder sb)
    {
        StringUtil.format(sb, messagePattern, arguments);
    }

    public Object[] swapParameters(Object emptyReplacement[])
    {
        return emptyReplacement;
    }

    public short getParameterCount()
    {
        return (short)arguments.length;
    }

    public Message memento()
    {
        return new ParameterizedMessage(messagePattern, arguments);
    }

    private String messagePattern;
    private Object arguments[];
}
