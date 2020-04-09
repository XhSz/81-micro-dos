// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OgnlUtils.java

package cn.sunline.adp.vine.base.util.expression;

import cn.sunline.adp.core.util.JsonUtil;
import cn.sunline.adp.vine.base.exception.DcpBaseReturnCode;
import cn.sunline.adp.vine.base.exception.FlowExpressionException;
import cn.sunline.edsp.base.util.lang.StringUtil;
import commonj.sdo.DataObject;
import java.lang.reflect.Array;
import java.util.*;
import ognl.*;
import org.springframework.cglib.beans.BeanMap;

public class OgnlUtils
{

    public OgnlUtils()
    {
    }

    public static Object nop(Object o)
    {
        return o;
    }

    public static String json(Object o)
    {
        return JsonUtil.formatWithPretty(o).replace('"', '\'');
    }

    public static String replace(String ret, String src, String desc)
    {
        return ret.replace(src, desc);
    }

    public static Map map(Object o)
    {
        if(o == null)
            return new HashMap();
        if(o instanceof Map)
            return (Map)o;
        else
            return BeanMap.create(o);
    }

    public static String join(Object c, Object delimiter)
    {
        List list = list(c);
        if(list == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++)
        {
            sb.append(list.get(i));
            if(i < list.size() - 1)
                sb.append(delimiter);
        }

        return sb.toString();
    }

    public static List list(Object c)
    {
        if(c == null)
            return Collections.emptyList();
        if(c instanceof List)
            return (List)c;
        if(c instanceof Collection)
            return new ArrayList((Collection)c);
        if(c.getClass().isArray())
        {
            List ret = new ArrayList();
            for(int i = 0; i < Array.getLength(c); i++)
                ret.add(Array.get(c, i));

            return ret;
        } else
        {
            List ret = new ArrayList();
            ret.add(c);
            return ret;
        }
    }

    public static Integer size(Object c)
    {
        if(c == null)
            return Integer.valueOf(0);
        if(c instanceof Collection)
            return Integer.valueOf(((Collection)c).size());
        if(c instanceof Map)
            return Integer.valueOf(((Map)c).size());
        if(c.getClass().isArray())
            return Integer.valueOf(Array.getLength(c));
        if(c instanceof String)
            return Integer.valueOf(((String)c).length());
        else
            return Integer.valueOf(1);
    }

    public static Object getValue(String ognlExpr, Map context, Object root)
    {
        try
        {
            OgnlContext ognlContext = new OgnlContext(context != null ? context : Collections.EMPTY_MAP);
            Object exp = parseExpression(ognlExpr);
            return Ognl.getValue(exp, ognlContext, root);
        }
        catch(OgnlException e)
        {
            throw new FlowExpressionException(DcpBaseReturnCode.Ognl_Eval, e, ognlExpr);
        }
    }

    public static Object getValue(String ognlExpr, Map context, Object root, Class resultType)
    {
        try
        {
            OgnlContext ognlContext = new OgnlContext(context != null ? context : Collections.EMPTY_MAP);
            Object exp = parseExpression(ognlExpr);
            return Ognl.getValue(exp, ognlContext, root, resultType);
        }
        catch(OgnlException e)
        {
            throw new FlowExpressionException(DcpBaseReturnCode.Ognl_Eval, e, ognlExpr);
        }
    }

    public static void setValue(String ognlExpr, Map context, Object root, Object val)
    {
        try
        {
            OgnlContext ognlContext = new OgnlContext(context != null ? context : Collections.EMPTY_MAP);
            Object exp = parseExpression(ognlExpr);
            Ognl.setValue(exp, ognlContext, root, val);
        }
        catch(OgnlException e)
        {
            throw new FlowExpressionException(DcpBaseReturnCode.Ognl_SetValue, e, ognlExpr);
        }
    }

    public static Object parseExpression(String ognlExpr)
    {
        if(StringUtil.isEmpty(ognlExpr))
            throw new FlowExpressionException(DcpBaseReturnCode.Ognl_InvalidExpression, ognlExpr);
        /*<invalid signature>*/java.lang.Object local = cn/sunline/adp/vine/base/util/expression/OgnlUtils;
        JVM INSTR monitorenter ;
        Object ret = exprNodeCaches.get(ognlExpr);
        if(ret == null)
        {
            ret = Ognl.parseExpression(ognlExpr);
            exprNodeCaches.put(ognlExpr, ret);
        }
        return ret;
        Exception exception;
        exception;
        throw exception;
        OgnlException e;
        e;
        throw new FlowExpressionException(DcpBaseReturnCode.Ognl_InvalidExpression, e, ognlExpr);
    }

    public static String eval(String str, Map root)
    {
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        int sidx;
        do
        {
            sidx = str.indexOf("${", idx);
            if(sidx != -1)
            {
                sb.append(str.substring(idx, sidx));
                int eidx = str.indexOf("}", sidx);
                if(eidx == -1)
                    throw new FlowExpressionException(DcpBaseReturnCode.Ognl_EvalStr_UnclosedExp, str);
                String expr = str.substring(sidx + 2, eidx);
                sb.append(getValue(expr, null, root));
                idx = eidx + 1;
            }
        } while(sidx != -1);
        sb.append(str.substring(idx));
        return sb.toString();
    }

    private static Map exprNodeCaches = new HashMap();

    static 
    {
        OgnlRuntime.setPropertyAccessor(commonj/sdo/DataObject, new PropertyAccessor() {

            public Object getProperty(Map context, Object target, Object name)
                throws OgnlException
            {
                return ((DataObject)target).get((String)name);
            }

            public String getSourceAccessor(OgnlContext arg0, Object arg1, Object arg2)
            {
                return null;
            }

            public String getSourceSetter(OgnlContext arg0, Object arg1, Object arg2)
            {
                return null;
            }

            public void setProperty(Map context, Object target, Object name, Object value)
                throws OgnlException
            {
                if(value == null && !((DataObject)target).isSet((String)name))
                {
                    return;
                } else
                {
                    ((DataObject)target).set((String)name, value);
                    return;
                }
            }

        }
);
    }
}
