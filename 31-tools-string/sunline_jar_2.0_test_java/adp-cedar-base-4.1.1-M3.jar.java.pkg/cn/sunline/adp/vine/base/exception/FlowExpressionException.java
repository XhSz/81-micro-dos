// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FlowExpressionException.java

package cn.sunline.adp.vine.base.exception;


// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            FlowRuntimeException, IReturnCodeMessage

public class FlowExpressionException extends FlowRuntimeException
{

    public FlowExpressionException(IReturnCodeMessage codeObj, String expression)
    {
        super(codeObj, "Expression:{}", new Object[] {
            expression
        });
        this.expression = expression;
    }

    public FlowExpressionException(IReturnCodeMessage codeObj, Throwable cause, String expression)
    {
        super(codeObj, cause, "Expression:{}", new Object[] {
            expression
        });
        this.expression = expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public String getExpression()
    {
        return expression;
    }

    private String expression;
}
