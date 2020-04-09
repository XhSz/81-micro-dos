// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DcpBaseException.java

package cn.sunline.adp.vine.base.exception;

import cn.sunline.edsp.base.util.lang.StringUtil;

// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            IReturnCodeMessage, ReturnCode

public class DcpBaseException extends RuntimeException
{

    public DcpBaseException(IReturnCodeMessage codeObj)
    {
        super(codeObj.getMessage());
        dcpBaseMsg = "";
        this.codeObj = codeObj;
    }

    public DcpBaseException(IReturnCodeMessage codeObj, Throwable cause)
    {
        super(cause);
        dcpBaseMsg = "";
        this.codeObj = codeObj;
        dcpBaseMsg = (new StringBuilder()).append(codeObj.getMessage()).append("[cause:").append(cause.getMessage()).append("]").toString();
    }

    public transient DcpBaseException(IReturnCodeMessage codeObj, String message, Object obj[])
    {
        super((new StringBuilder()).append("(").append(StringUtil.replaceStr(message, obj)).append(")").append(codeObj.getMessage()).toString());
        dcpBaseMsg = "";
        this.codeObj = codeObj;
    }

    public transient DcpBaseException(IReturnCodeMessage codeObj, Throwable cause, String message, Object obj[])
    {
        super(cause);
        dcpBaseMsg = "";
        this.codeObj = codeObj;
        dcpBaseMsg = (new StringBuilder()).append("(").append(StringUtil.replaceStr(message, obj)).append(")").append(codeObj.getMessage()).append("[cause:").append(cause.getMessage()).append("]").toString();
    }

    public DcpBaseException(Throwable cause)
    {
        super(cause);
        dcpBaseMsg = "";
        codeObj = ReturnCode.EXCEPTION;
        dcpBaseMsg = (new StringBuilder()).append(codeObj.getMessage()).append("[cause:").append(cause.getMessage()).append("]").toString();
    }

    public String getCode()
    {
        return codeObj.getCode();
    }

    public String getCodeNumber()
    {
        return codeObj.getCodeNumber();
    }

    public String getMessage()
    {
        if(getCause() != null)
            return (new StringBuilder()).append("[").append(getCode()).append("]").append(dcpBaseMsg).toString();
        else
            return (new StringBuilder()).append("[").append(getCode()).append("]").append(super.getMessage()).toString();
    }

    private static final long serialVersionUID = 1L;
    private static final String CAUSE = "[cause:";
    protected transient IReturnCodeMessage codeObj;
    private String dcpBaseMsg;
}
