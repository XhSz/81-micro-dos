// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FlowRuntimeException.java

package cn.sunline.adp.vine.base.exception;

import java.util.Map;

// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            DcpBaseException, FlowSensitiveException, IReturnCodeMessage

public class FlowRuntimeException extends DcpBaseException
    implements FlowSensitiveException
{

    public FlowRuntimeException(IReturnCodeMessage codeObj)
    {
        super(codeObj);
        flowInfoInited = false;
    }

    public FlowRuntimeException(IReturnCodeMessage codeObj, Throwable e)
    {
        super(codeObj, e);
        flowInfoInited = false;
    }

    public FlowRuntimeException(Throwable cause)
    {
        super(cause);
        flowInfoInited = false;
    }

    public transient FlowRuntimeException(IReturnCodeMessage codeObj, String message, Object args[])
    {
        super(codeObj, message, args);
        flowInfoInited = false;
    }

    public transient FlowRuntimeException(IReturnCodeMessage codeObj, Throwable cause, String message, Object args[])
    {
        super(codeObj, cause, message, args);
        flowInfoInited = false;
    }

    public boolean isFlowInfoInited()
    {
        return flowInfoInited;
    }

    public void setFlowInfoInited(boolean flowInfoInited)
    {
        this.flowInfoInited = flowInfoInited;
    }

    public void setFlowName(String flowName)
    {
        flowInfoInited = true;
        this.flowName = flowName;
    }

    public String getFlowName()
    {
        return flowName;
    }

    public void setFlowNodeInfo(String nodeId, String nodeName)
    {
        flowNodeId = nodeId;
        flowNodeName = nodeName;
    }

    public String getFlowNodeId()
    {
        return flowNodeId;
    }

    public String getFlowNodeName()
    {
        return flowNodeName;
    }

    public void setExceptionSource(String source)
    {
        exceptionSource = source;
    }

    public String getExceptionSource()
    {
        return exceptionSource;
    }

    public String getErrId()
    {
        return getCode();
    }

    public String getErrMsg()
    {
        return super.getMessage();
    }

    public void setFlowNodeContext(Map context)
    {
        this.context = context;
    }

    public Map getFlowNodeContext()
    {
        return context;
    }

    private boolean flowInfoInited;
    private String flowName;
    private String flowNodeId;
    private String flowNodeName;
    private transient Map context;
    private String exceptionSource;
}
