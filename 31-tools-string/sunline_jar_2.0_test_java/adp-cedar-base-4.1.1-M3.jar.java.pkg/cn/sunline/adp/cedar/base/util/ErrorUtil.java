// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.errors.CorePluginErrorDef;
import cn.sunline.adp.core.expression.ExpressionEvaluator;
import cn.sunline.adp.core.expression.ExpressionEvaluatorFactory;
import cn.sunline.edsp.base.lang.Params;

public class ErrorUtil
{

    public ErrorUtil()
    {
    }

    public static String getErrorMessage(String errorMessage, Params context)
    {
        ExpressionEvaluator ee = ExpressionEvaluatorFactory.getInstance();
        try
        {
            return (String)ee.eval(errorMessage, context, context);
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E027(errorMessage, e.getMessage(), e);
        }
    }
}
