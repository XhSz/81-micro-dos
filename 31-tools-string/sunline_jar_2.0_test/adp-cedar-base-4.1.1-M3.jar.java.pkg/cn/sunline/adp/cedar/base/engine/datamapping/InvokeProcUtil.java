// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvokeProcUtil.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.util.MethodUtil;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.metadata.model.datainterface.*;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.lang.RunnableWithReturn;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InvokeProcUtil
{

    public InvokeProcUtil()
    {
    }

    public static transient Object callProfile(Object obj, Class clazz, String methodName, DataInterface dataInterface, Object params[])
    {
        if(dataInterface == null)
            throw new IllegalArgumentException(EngineConst.InvokeProcUtil06);
        if(params == null || params.length != 3)
            throw new IllegalArgumentException(String.format(EngineConst.InvokeProcUtil07, new Object[] {
                Integer.valueOf(3)
            }));
        Method m = getMethod(clazz, methodName, true, MethodUtil.getReturnType(dataInterface.getOutput(), params[2]), MethodUtil.getMethodParamsType(dataInterface, true));
        if(log.isDebugEnabled())
            log.debug(EngineConst.InvokeProcUtil01, new Object[] {
                methodName, Arrays.asList(params)
            });
        return invokeMethod(obj, clazz, m, dataInterface, params[2], MethodUtil.getMethodParams(dataInterface, params, true));
    }

    public static transient Object callProfile(String className, String methodName, DataInterface dataInterface, Object params[])
    {
        Class clazz = null;
        try
        {
            clazz = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
        }
        catch(ClassNotFoundException e)
        {
            throw new IllegalArgumentException(String.format(EngineConst.InvokeProcUtil08, new Object[] {
                methodName, className
            }), e);
        }
        return callProfile(ModelObjectCreatorUtil.getModelObjectCreator().create(clazz), clazz, methodName, dataInterface, params);
    }

    public static Object invokeMethodWithoutParam(Class clazz, Method m, boolean isStatic)
    {
        return ProfileUtil.doProfile((new StringBuilder()).append("invoke_").append(m.getName()).toString(), new RunnableWithReturn(m, clazz) {

            public Object execute()
            {
                try
                {
                    Object result = m.invoke(ModelObjectCreatorUtil.getModelObjectCreator().create(clazz), new Object[0]);
                    if(InvokeProcUtil.log.isDebugEnabled())
                        InvokeProcUtil.log.debug(EngineConst.InvokeProcUtil02, new Object[] {
                            m.getName()
                        });
                    return result;
                }
                catch(Exception e)
                {
                    if(InvokeProcUtil.log.isDebugEnabled())
                        InvokeProcUtil.log.debug(EngineConst.InvokeProcUtil03, e, new Object[] {
                            m.getName(), e.getMessage()
                        });
                    throw ExceptionUtil.wrapThrow(e);
                }
            }

            final Method val$m;
            final Class val$clazz;

            
            {
                m = method;
                clazz = class1;
                super();
            }
        }
);
    }

    public static transient Object invokeMethod(Class clazz, Method m, DataInterface dataInterface, Object outData, Object params[])
    {
        return invokeMethod(ModelObjectCreatorUtil.getModelObjectCreator().create(clazz), clazz, m, dataInterface, outData, params);
    }

    public static transient Object invokeMethod(Object obj, Class clazz, Method m, DataInterface dataInterface, Object outData, Object params[])
    {
        return ProfileUtil.doProfile((new StringBuilder()).append("invoke_").append(m.getName()).toString(), new RunnableWithReturn(m, obj, params, dataInterface, outData) {

            public Object execute()
            {
                try
                {
                    Object result = m.invoke(obj, params);
                    if(InvokeProcUtil.log.isDebugEnabled())
                        InvokeProcUtil.log.debug(EngineConst.InvokeProcUtil04, new Object[] {
                            m.getName()
                        });
                    return InvokeProcUtil.doOutData(dataInterface.getOutput(), outData, result);
                }
                catch(Exception e)
                {
                    InvokeProcUtil.log.error(EngineConst.InvokeProcUtil05, new Object[] {
                        ModelUtil.getOriginalClass(m.getDeclaringClass()), m.getName(), e.getMessage()
                    });
                    throw ExceptionUtil.wrapThrow(e);
                }
            }

            final Method val$m;
            final Object val$obj;
            final Object val$params[];
            final DataInterface val$dataInterface;
            final Object val$outData;

            
            {
                m = method;
                obj = obj1;
                params = aobj;
                dataInterface = datainterface;
                outData = obj2;
                super();
            }
        }
);
    }

    public static transient Method getMethod(Class clazz, String methodName, boolean isStatic, Class returnType, Class parametersType[])
    {
        String key = (new StringBuilder()).append(clazz.getName()).append(".").append(methodName).toString();
        if(methods.containsKey(key))
        {
            return (Method)methods.get(key);
        } else
        {
            Method m = MethodUtil.getMethod(clazz, methodName, isStatic, returnType, parametersType);
            methods.put(key, m);
            return m;
        }
    }

    private static Object doOutData(Output output, Object param, Object result)
    {
        if(output.getElements() == null || output.getElements().size() == 0)
            return new HashMap();
        if(output.isOutputAsParm())
            return param;
        if(output.isInterfacePackMode())
            return result;
        if(result == null)
        {
            return result;
        } else
        {
            Map ret = (Map)param;
            ret.put(((DataInterfaceElement)output.getElements().get(0)).getId(), result);
            return ret;
        }
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/datamapping/InvokeProcUtil);
    private static Map methods = new ConcurrentHashMap();



}
