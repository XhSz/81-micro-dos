// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EdspServiceException.java

package cn.sunline.adp.cedar.base.exception;

import cn.sunline.adp.core.exception.AdpException;
import cn.sunline.adp.core.lang.IString;

public class EdspServiceException extends AdpException
{

    public EdspServiceException(String code, String message)
    {
        super(message);
        this.code = code;
    }

    public EdspServiceException(String code, IString message)
    {
        super(message);
        this.code = code;
    }

    public EdspServiceException(String code, String message, Throwable cause)
    {
        super(message, cause);
        this.code = code;
    }

    public EdspServiceException(String code, IString message, Throwable cause)
    {
        super(message, cause);
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public String toString()
    {
        return (new StringBuilder()).append("LttsServiceException [code=").append(code).append("] message=[").append(getMessage()).append("]").toString();
    }

    private static final long serialVersionUID = 0xd227110d94d50660L;
    private String code;
}
