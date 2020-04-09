// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BusinessException.java

package cn.sunline.adp.vine.base.exception;

import java.util.Map;

// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            FlowSensitiveException, ErrorManager

public class BusinessException extends RuntimeException
    implements FlowSensitiveException
{

    public BusinessException(String msg)
    {
        super(msg);
        flowInfoInited = false;
    }

    public transient BusinessException(String errId, Object args[])
    {
        super(ErrorManager.getInstance().getMessage(errId, args));
        flowInfoInited = false;
        this.errId = errId;
        this.args = args;
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
        return errId;
    }

    public String getErrMsg()
    {
        return super.getMessage();
    }

    public String getMessage()
    {
        String msg = super.getMessage();
        if(flowInfoInited)
            msg = ErrorManager.getInstance().getMessage("exception.flow.runtime.flow.commExceptionMsg", new String[] {
                getFlowName(), getFlowNodeId(), getFlowNodeName(), getExceptionSource(), msg
            });
        return msg;
    }

    public void setFlowNodeContext(Map context)
    {
        this.context = context;
    }

    public Map getFlowNodeContext()
    {
        return context;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public void setArgs(Object args[])
    {
        this.args = args;
    }

    private static final long serialVersionUID = 0x8bc1e8d74354ab5aL;
    private String errId;
    private transient Object args[];
    private boolean flowInfoInited;
    private String flowName;
    private String flowNodeId;
    private String flowNodeName;
    private transient Map context;
    private final String MESSAGEKRY = "exception.flow.runtime.flow.commExceptionMsg";
    private String exceptionSource;
}
