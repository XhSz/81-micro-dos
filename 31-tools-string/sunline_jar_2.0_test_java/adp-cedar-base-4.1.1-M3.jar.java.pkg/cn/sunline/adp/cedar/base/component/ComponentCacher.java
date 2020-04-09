// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentCacher.java

package cn.sunline.adp.cedar.base.component;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.util.ConfigSecurityUtil;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.core.bean.accessor.PropertyAccessor;
import cn.sunline.adp.metadata.base.util.PropertyUtil;
import cn.sunline.adp.metadata.model.ElementType;
import cn.sunline.adp.metadata.model.component.AbstractComponentInstance;
import cn.sunline.adp.metadata.model.component.ComponentImplementation;
import cn.sunline.edsp.base.util.convert.ConvertUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.component:
//            JDKComponentServiceProxy, ComponentInstance, ComponentManager, StrParmResolveUtil

public class ComponentCacher
{

    public ComponentCacher()
    {
    }

    private static Object getInstance(Class type, ComponentInstance ci)
    {
        Object ret = null;
        if(ci == null || StringUtil.isEmpty(ci.getComponentType()))
            throw new RuntimeException(String.format(EngineConst.ComponentCacher04, new Object[] {
                type
            }));
        String key = (new StringBuilder()).append(ci.getComponentType()).append(".").append(StringUtil.nullable(ci.getAbstractComponentInstance(), "default")).toString();
        if(ci.getSingleton())
            ret = cache.get(key);
        if(ret == null)
        {
            if(ci.getComponentImplementationObj() == null || ci.getComponentImplementationObj().getImpl() == null)
                throw new RuntimeException(String.format(EngineConst.ComponentCacher05, new Object[] {
                    type, ci.getAbstractComponentInstance()
                }));
            String className = ci.getComponentImplementationObj().getImpl().getClasz();
            Class clazz = null;
            try
            {
                clazz = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
            }
            catch(Exception e)
            {
                throw new RuntimeException(String.format(EngineConst.ComponentCacher06, new Object[] {
                    type
                }), e);
            }
            if(!type.isAssignableFrom(clazz))
                throw new RuntimeException(String.format(EngineConst.ComponentCacher07, new Object[] {
                    type, clazz
                }));
            try
            {
                ret = ModelObjectCreatorUtil.getModelObjectCreator().create(clazz);
            }
            catch(Exception e)
            {
                throw new RuntimeException(String.format(EngineConst.ComponentCacher08, new Object[] {
                    type, clazz
                }), e);
            }
            Map propertyMap = new HashMap();
            if(ci.getProperties() != null)
            {
                ComponentInstance.Property p;
                for(Iterator iterator = ci.getProperties().iterator(); iterator.hasNext(); propertyMap.put(p.getName(), p.getValue()))
                    p = (ComponentInstance.Property)iterator.next();

            }
            Iterator iterator1 = ci.getComponentImplementationObj().getProperties().iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                cn.sunline.adp.metadata.model.component.ComponentImplementation.Property pi = (cn.sunline.adp.metadata.model.component.ComponentImplementation.Property)iterator1.next();
                Object value = null;
                String property = (String)propertyMap.get(pi.getName());
                if(StringUtil.isEmpty(property))
                    property = pi.getDefaultValue();
                property = exchangeVal(property);
                if(StringUtil.isNotEmpty(property))
                {
                    try
                    {
                        value = ConvertUtil.convert(property, pi.getTypeObj().getJavaClass());
                    }
                    catch(Exception e)
                    {
                        log.error(EngineConst.ComponentCacher01, e, new Object[] {
                            ci.getComponentImplementation(), pi.getName(), property, pi.getType()
                        });
                    }
                    try
                    {
                        PropertyUtil.createAccessor(ret).setNestedProperty(pi.getName(), value);
                    }
                    catch(Exception e)
                    {
                        log.error(EngineConst.ComponentCacher02, e, new Object[] {
                            clazz, pi.getName(), value
                        });
                    }
                }
            } while(true);
            if(ci.getSingleton())
                cache.put(key, ret);
        } else
        if(log.isDebugEnabled())
            log.debug(EngineConst.ComponentCacher03, new Object[] {
                ci.getAbstractComponentInstance()
            });
        return ret;
    }

    public static Object getInstance(Class clazz, String abstSchemaId, boolean direct)
    {
        ComponentInstance ci = getComponentInstance(abstSchemaId);
        if(direct || ci.getSynchronous())
            return getInstance(clazz, ci);
        else
            return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {
                clazz
            }, new JDKComponentServiceProxy(clazz, abstSchemaId));
    }

    public static Object getInstance(Class componentType, String abstSchemaId)
    {
        return getInstance(componentType, abstSchemaId, false);
    }

    public static ComponentInstance getComponentInstance(String abstSchemaId)
    {
        ComponentInstance compInst = ComponentManager.get().getComponentInstanceByAbst(abstSchemaId);
        if(compInst == null)
            throw new RuntimeException(String.format(EngineConst.ComponentCacher09, new Object[] {
                abstSchemaId
            }));
        AbstractComponentInstance abstInst = ComponentManager.get().getAbstractComponentInstance(abstSchemaId);
        if(abstInst == null)
            throw new RuntimeException(String.format(EngineConst.ComponentCacher10, new Object[] {
                abstSchemaId
            }));
        if(!abstInst.getType().equals(compInst.getComponentType()))
            throw new RuntimeException(String.format(EngineConst.ComponentCacher11, new Object[] {
                abstSchemaId, abstInst.getType(), compInst.getComponentType()
            }));
        else
            return compInst;
    }

    private static String exchangeVal(String propvl)
    {
        if(propvl == null)
            return null;
        propvl = StrParmResolveUtil.evalTest(new StrParmResolveUtil.Runnable() {

            public String run(String key)
            {
                String ret = System.getProperty(key, System.getenv(key));
                if(StringUtil.isEmpty(ret))
                    ret = (new StringBuilder()).append("${").append(key).append("}").toString();
                return ret;
            }

        }
, propvl);
        if(ConfigSecurityUtil.needDecrypt(propvl))
            propvl = ConfigSecurityUtil.decrypt(propvl);
        return propvl;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/component/ComponentCacher);
    private static Map cache = new ConcurrentHashMap();

}
