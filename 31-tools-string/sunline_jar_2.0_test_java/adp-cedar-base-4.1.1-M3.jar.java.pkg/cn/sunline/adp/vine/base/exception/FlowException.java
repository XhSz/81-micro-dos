// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FlowException.java

package cn.sunline.adp.vine.base.exception;


// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            ErrorManager

public class FlowException extends RuntimeException
{

    public FlowException(String msg)
    {
        super(msg);
    }

    public FlowException(Throwable cause)
    {
        super(cause);
    }

    public FlowException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public transient FlowException(String errId, Throwable cause, Object args[])
    {
        super(ErrorManager.getInstance().getMessage(errId, args), cause);
        this.errId = errId;
        this.args = args;
    }

    public transient FlowException(String errId, Object args[])
    {
        super(ErrorManager.getInstance().getMessage(errId, args));
        this.errId = errId;
        this.args = args;
    }

    public String getErrId()
    {
        return errId;
    }

    public Object[] getArgs()
    {
        return args;
    }

    private static final long serialVersionUID = 0xed5bc7465005bc2bL;
    private String errId;
    private transient Object args[];
}
