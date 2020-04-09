// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonExpressionUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineContext;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineRuntimeContext;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.core.expression.ExpressionEvaluator;
import cn.sunline.adp.core.expression.ExpressionEvaluatorFactory;
import cn.sunline.adp.core.expression.enhance.OgnlType;
import cn.sunline.adp.metadata.base.expression.type.MapOgnlType;
import cn.sunline.adp.metadata.loader.util.ModelFactoryUtil;
import cn.sunline.adp.metadata.model.ComplexType;
import cn.sunline.adp.metadata.model.ModelFactory;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.HashMap;
import java.util.Map;
import ognl.OgnlOps;

public class CommonExpressionUtil
{

    public CommonExpressionUtil()
    {
    }

    public static Map getExprContext()
    {
        Map context = new HashMap();
        if(!EngineContext.isEmpty())
            context.put("run", EngineContext.getEngineRuntimeContext().getTrxRunEnvs());
        return context;
    }

    public static OgnlType getExprContextType()
    {
        Map context = new HashMap();
        context.put("run", ModelFactoryUtil.getModelFactory().getModel(cn/sunline/adp/metadata/model/ComplexType, "TrxEnvs.RunEnvs"));
        return new MapOgnlType(context);
    }

    public static boolean getConditionResult(String condition, OgnlType dataAreaType, Object dataArea)
    {
        if(StringUtil.isEmpty(condition))
            return true;
        long start = System.currentTimeMillis();
        Object ret = ExpressionEvaluatorFactory.getInstance().findValue(condition, java/lang/Boolean, dataArea, getExprContext());
        if(ret == null)
            throw new IllegalArgumentException(String.format(EngineConst.CommonExpressionUtil01, new Object[] {
                condition
            }));
        if(syslog.isDebugEnabled())
            syslog.debug((new StringBuilder()).append("[").append(System.currentTimeMillis() - start).append("]EXPR[").append(condition).append("]=[").append(ret).append("]").toString());
        return OgnlOps.booleanValue(ret);
    }

    public static void evalExpression(String expr, OgnlType dataAreaType, Object dataArea)
    {
        if(StringUtil.isEmpty(expr))
            return;
        long start = System.currentTimeMillis();
        ExpressionEvaluatorFactory.getInstance().findValue(expr, java/lang/Object, dataArea, getExprContext());
        if(syslog.isDebugEnabled())
            syslog.debug((new StringBuilder()).append("[").append(System.currentTimeMillis() - start).append("]EVAL[").append(expr).append("]").toString());
    }

    public static Object evalExpressionAndReturn(String expr, OgnlType dataAreaType, Object dataArea)
    {
        if(StringUtil.isEmpty(expr))
            return null;
        long start = System.currentTimeMillis();
        Object ret = ExpressionEvaluatorFactory.getInstance().findValue(expr, java/lang/Object, dataArea, getExprContext());
        if(syslog.isDebugEnabled())
            syslog.debug((new StringBuilder()).append("[").append(System.currentTimeMillis() - start).append("]EVAL[").append(expr).append("]=[").append(ret).append("]").toString());
        return ret;
    }

    private static final SysLog syslog = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/util/CommonExpressionUtil);

}
