// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MethodUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.metadata.model.ComplexType;
import cn.sunline.adp.metadata.model.datainterface.*;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;
import java.lang.reflect.Method;
import java.util.*;

public class MethodUtil
{

    public MethodUtil()
    {
    }

    public static transient Method getMethod(String className, String method, boolean isStatic, Class returnType, Class parametersType[])
    {
        try
        {
            Class clazz = ReflectionUtil.classForName(className);
            return getMethod(clazz, method, isStatic, returnType, parametersType);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(BaseConst.MethodUtil01, new Object[] {
                method, className
            }), e);
        }
    }

    public static transient Method getMethod(Class clazz, String methodName, boolean isStatic, Class returnType, Class parametersType[])
    {
        if(clazz == null)
            return null;
        Method amethod[];
        int i;
        int j;
        amethod = clazz.getMethods();
        i = amethod.length;
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_97;
        Method m = amethod[j];
        if(m.getName().equalsIgnoreCase(methodName) && matchParameterType(m.getParameterTypes(), parametersType))
            if(returnType != null && !m.getReturnType().isAssignableFrom(returnType))
                throw new NoSuchMethodException(m.getName());
            else
                return m;
        j++;
          goto _L1
        throw new NoSuchMethodException(methodName);
        SecurityException e;
        e;
        throw new IllegalArgumentException(e);
        e;
        throw new IllegalArgumentException(String.format(BaseConst.MethodUtil02, new Object[] {
            clazz.getName(), methodName, getSignature(methodName, returnType, parametersType)
        }));
    }

    private static transient String getSignature(String method, Class returnType, Class parametersType[])
    {
        String signature = returnType != null ? (new StringBuilder()).append(returnType.getSimpleName()).append(" ").toString() : "";
        signature = (new StringBuilder()).append(signature).append(method).append("(").toString();
        if(parametersType != null)
        {
            for(int i = 0; i < parametersType.length; i++)
            {
                signature = (new StringBuilder()).append(signature).append(parametersType[i] != null ? parametersType[i].getName() : "Object").append(" arg").append(i).toString();
                if(i < parametersType.length - 1)
                    signature = (new StringBuilder()).append(signature).append(",").toString();
            }

        }
        signature = (new StringBuilder()).append(signature).append(")").toString();
        return signature;
    }

    private static boolean matchParameterType(Class methodParameterTypes[], Class parametersType[])
    {
        if((methodParameterTypes == null || methodParameterTypes.length == 0) && (parametersType == null || parametersType.length == 0))
            return true;
        if(methodParameterTypes == null || methodParameterTypes.length == 0 || parametersType == null || parametersType.length == 0)
            return false;
        if(methodParameterTypes.length != parametersType.length)
            return false;
        for(int i = 0; i < methodParameterTypes.length; i++)
        {
            if(parametersType[i] == null)
                continue;
            Class t1 = methodParameterTypes[i];
            if(!t1.isAssignableFrom(parametersType[i]))
                return false;
        }

        return true;
    }

    public static Class getReturnType(Output output, Object param)
    {
        if(output == null)
            throw new IllegalArgumentException(BaseConst.MethodUtil03);
        if(output.isOutputAsParm())
            return Void.TYPE;
        if(output.isInterfacePackMode())
            return param.getClass();
        if(output.getElements() == null || output.getElements().size() > 1)
            throw new IllegalArgumentException(BaseConst.MethodUtil04);
        if(output.getElements().size() == 0)
            return Void.TYPE;
        else
            return ((DataInterfaceElement)output.getElements().get(0)).getElementJavaClass();
    }

    public static Class[] getMethodParamsType(DataInterface dataInterface, boolean hasPropertyIntf)
    {
        List ret = new ArrayList();
        ret.addAll(getMethodParamsType(((DataInterfaceElements) (dataInterface.getInput()))));
        if(hasPropertyIntf)
            ret.addAll(getMethodParamsType(((DataInterfaceElements) (dataInterface.getProperty()))));
        if(dataInterface.getOutput().isOutputAsParm())
            ret.addAll(getMethodParamsType(((DataInterfaceElements) (dataInterface.getOutput()))));
        return (Class[])ret.toArray(new Class[0]);
    }

    private static List getMethodParamsType(DataInterfaceElements intf)
    {
        List ret = new ArrayList();
        if(intf.isInterfacePackMode())
        {
            ret.add(intf.getJavaClass());
        } else
        {
            DataInterfaceElement element;
            for(Iterator iterator = intf.getElements().iterator(); iterator.hasNext(); ret.add(element.getElementJavaClass()))
                element = (DataInterfaceElement)iterator.next();

        }
        return ret;
    }

    public static Object[] getMethodParams(DataInterface dataInterface, Object params[], boolean hasPropertyIntf)
    {
        List ret = new ArrayList();
        ret.addAll(getMethodParams(((DataInterfaceElements) (dataInterface.getInput())), params[0]));
        if(hasPropertyIntf)
            ret.addAll(getMethodParams(((DataInterfaceElements) (dataInterface.getProperty())), params[1]));
        if(dataInterface.getOutput().isOutputAsParm())
            ret.addAll(getMethodParams(((DataInterfaceElements) (dataInterface.getOutput())), params[params.length - 1]));
        return ret.toArray(new Object[0]);
    }

    private static List getMethodParams(DataInterfaceElements intf, Object param)
    {
        List ret = new ArrayList();
        if(intf.isInterfacePackMode())
        {
            ret.add(param);
        } else
        {
            Map params = (Map)param;
            for(Iterator iterator = intf.getElements().iterator(); iterator.hasNext();)
            {
                DataInterfaceElement element = (DataInterfaceElement)iterator.next();
                if(intf instanceof Output)
                {
                    ComplexType ct = ModelUtil.getComplexType(element.getTypeObj());
                    if(ct != null)
                    {
                        Object value = ModelObjectCreatorUtil.getModelObjectCreator().create(ct.getJavaClass());
                        params.put(element.getId(), value);
                        ret.add(value);
                    } else
                    {
                        ret.add(null);
                    }
                } else
                {
                    ret.add(params.get(element.getId()));
                }
            }

        }
        return ret;
    }
}
