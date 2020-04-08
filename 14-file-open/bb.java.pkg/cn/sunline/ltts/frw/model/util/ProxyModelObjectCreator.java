// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProxyModelObjectCreator.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.frw.model.util.ModelPropertyDescriptor;
import cn.sunline.ltts.base.odb.OdbFactory;
import cn.sunline.ltts.base.util.*;
import cn.sunline.ltts.core.api.expression.ExpressionEvaluatorApi;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.*;
import cn.sunline.ltts.core.api.model.dm.*;
import cn.sunline.ltts.core.api.model.dm.internal.DefaultEnum;
import cn.sunline.ltts.core.api.util.*;
import cn.sunline.ltts.frw.model.DefaultModelObjectCreator;
import cn.sunline.ltts.frw.model.datainterface.DataInterfaceField;
import cn.sunline.ltts.frw.model.dm.*;
import cu.sunline.ltts.ModelRtConst;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.proxy.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelObjectPartner, XmlConfigManager, ModelUtil, ModelFactoryUtil

public class ProxyModelObjectCreator extends DefaultModelObjectCreator
    implements ModelObjectCreator
{
    private static class ProxyMap
        implements Map
    {

        private Map getDiffMap()
        {
            if(diff == null)
                diff = new LinkedHashMap((int)((float)parent.size() / 0.75F + 1.0F));
            return diff;
        }

        public int size()
        {
            if(fullData != null)
                return fullData.size();
            else
                return parent.size();
        }

        public boolean isEmpty()
        {
            return false;
        }

        public boolean containsKey(Object key)
        {
            if(fullData != null)
                return fullData.containsKey(key);
            else
                return parent.containsKey(key);
        }

        /**
         * @deprecated Method containsValue is deprecated
         */

        public boolean containsValue(Object value)
        {
            throw new UnsupportedOperationException(ModelRtConst.ProxyModelObjectCreator05);
        }

        public Object get(Object key)
        {
            if(fullData != null)
                return fullData.get(key);
            if(diff != null && diff.containsKey(key))
                return diff.get(key);
            else
                return parent.get(key);
        }

        public Object put(Object key, Object value)
        {
            if(fullData != null)
                return fullData.put(key, value);
            else
                return getDiffMap().put(key, value);
        }

        public Object remove(Object key)
        {
            throw new UnsupportedOperationException(ModelRtConst.ProxyModelObjectCreator05);
        }

        public void putAll(Map m)
        {
            if(fullData != null)
                fullData.putAll(m);
            else
                getDiffMap().putAll(m);
        }

        public void clear()
        {
            throw new UnsupportedOperationException(ModelRtConst.ProxyModelObjectCreator05);
        }

        public Set keySet()
        {
            if(fullData != null)
                return fullData.keySet();
            else
                return parent.keySet();
        }

        /**
         * @deprecated Method values is deprecated
         */

        public Collection values()
        {
            throw new UnsupportedOperationException(ModelRtConst.ProxyModelObjectCreator05);
        }

        /**
         * @deprecated Method entrySet is deprecated
         */

        public Set entrySet()
        {
            if(fullData == null)
                initFullData();
            return fullData.entrySet();
        }

        public String toString()
        {
            if(fullData == null)
            {
                if(diff == null)
                    return StringUtil.mapToString(parent);
                else
                    return StringUtil.mapToString(diff, parent);
            } else
            {
                return StringUtil.mapToString(fullData);
            }
        }

        private synchronized void initFullData()
        {
            if(fullData == null)
            {
                Map d = new LinkedHashMap((int)((float)parent.size() / 0.75F + 1.0F));
                d.putAll(parent);
                if(diff != null)
                    d.putAll(diff);
                diff = null;
                parent = null;
                fullData = d;
            }
        }

        private Map diff;
        private Map parent;
        private Map fullData;

        private ProxyMap(Map parent, boolean fullCopy)
        {
            fullData = null;
            if(fullCopy)
            {
                fullData = new LinkedHashMap((int)((float)parent.size() / 0.75F + 1.0F));
                fullData.putAll(parent);
                diff = null;
                this.parent = null;
            } else
            {
                diff = null;
                this.parent = parent;
            }
        }

    }

    public static interface ReadAndWriteControllor
    {

        public abstract boolean __isReadonly__();

        public abstract void __setReadonly__();
    }

    private static final class ProxyModelObjectInterceptor
        implements MethodInterceptor
    {

        public Object intercept(Object obj, Method method, Object args[], MethodProxy proxy)
            throws Throwable
        {
            if(!inited && introduceMap)
            {
                Map prototype = ProxyModelObjectCreator.getPrototype(proxyClass);
                if(prototype != null)
                    map = new ProxyMap(prototype, false);
                else
                    map = new LinkedHashMap();
                inited = true;
            }
            String mname = method.getName();
            int mname_len = mname.length();
            int mlen = method.getParameterTypes().length;
            if(method.getDeclaringClass() == cn/sunline/ltts/core/api/model/ModelObjectCreator$ModelClassAware)
                return proxyClass;
            if(introducePartner && method.getDeclaringClass() == cn/sunline/ltts/frw/model/util/ModelObjectPartner && "getData".equals(mname))
                return partner;
            if(introduceMap)
                if(mlen == 1)
                {
                    if(mname_len == 3 && "get".equals(mname))
                        return map.get(args[0]);
                    if(mname_len == 6 && "equals".equals(mname))
                        return Boolean.valueOf(obj == args[0]);
                    if(mname_len == 11 && "containsKey".equals(mname))
                        return Boolean.valueOf(map.containsKey(args[0]));
                    if(mname_len == 6 && "putAll".equals(mname))
                    {
                        map.putAll((Map)args[0]);
                        return null;
                    }
                    if(mname_len > 3 && method.getReturnType() == Void.TYPE && mname.startsWith("set"))
                    {
                        String property = ProxyModelObjectCreator.getPropertyName(method);
                        if(property != null)
                        {
                            setProperty(obj, property, args[0]);
                            return null;
                        }
                    }
                } else
                if(mlen == 2)
                {
                    if(mname_len == 3 && "put".equals(mname))
                    {
                        map.put((String)args[0], args[1]);
                        return null;
                    }
                } else
                if(mlen == 0)
                {
                    if(mname_len == 8 && "toString".equals(mname))
                        return (new StringBuilder()).append(proxyClass.getSimpleName()).append((map instanceof ProxyMap) ? map.toString() : StringUtil.mapToString(map)).toString();
                    if(mname_len == 7 && "isEmpty".equals(mname))
                        return Boolean.valueOf(false);
                    if(mname_len == 8 && "hashCode".equals(mname))
                        return Integer.valueOf(hash.hashCode());
                    if(mname_len > 3 && method.getReturnType() != Void.TYPE && (mname.startsWith("get") || mname.startsWith("is")))
                    {
                        mname.startsWith("get");
                    } else
                    {
                        Method m = MapMethodCache.getMethod(mname, method.getParameterTypes());
                        if(m != null)
                            return m.invoke(map, args);
                    }
                }
            if(method.getReturnType() != Void.TYPE && (args == null || args.length == 0))
            {
                String property = ProxyModelObjectCreator.getPropertyName(method);
                if(property != null)
                {
                    Object ret = null;
                    if(!introduceMap)
                        ret = proxy.invokeSuper(obj, args);
                    else
                        ret = map.get(property);
                    if(ret != null)
                        return ret;
                    if(lazyresolve)
                    {
                        ModelPropertyDescriptor field = ModelPropertyDescriptor.get(method, true);
                        if(field.getField() != null && field.isAnnotationPresent(cn/sunline/ltts/core/api/model/ModelRelation))
                        {
                            Object rel = ModelFactoryUtil.resolveModelRelation(obj, proxyClass, field);
                            field.setProperty(obj, rel);
                            return rel;
                        }
                    }
                    if(!ClassUtils.isSimpleClass(method.getReturnType()) && !cn/sunline/ltts/core/api/model/dm/internal/DefaultEnum.isAssignableFrom(method.getReturnType()) && (ModelUtil.isMapBeanType(method.getReturnType()) || method.getReturnType() == cn/sunline/ltts/core/api/model/dm/Options))
                    {
                        ret = LttsCoreBeanUtil.getModelObjectCreator().create(method.getReturnType());
                        setProperty(obj, property, ret);
                        return ret;
                    } else
                    {
                        return ret;
                    }
                }
            }
            if(mname_len == 15 && "__setReadonly__".equals(mname))
            {
                readOnly = true;
                map = Collections.unmodifiableMap(map);
                return null;
            }
            if(mname_len == 14 && "__isReadonly__".equals(mname))
                return Boolean.valueOf(readOnly);
            try
            {
                return proxy.invokeSuper(obj, args);
            }
            catch(Exception e)
            {
                throw new RuntimeException(String.format(ModelRtConst.ProxyModelObjectCreator04, new Object[] {
                    proxyClass.getSimpleName(), method.getName()
                }), e);
            }
        }

        private void setProperty(Object obj, String property, Object value)
        {
            if(introduceMap)
            {
                map.put(property, value);
            } else
            {
                ModelPropertyDescriptor pd = ModelPropertyDescriptor.get(proxyClass, property);
                pd.setProperty(obj, value);
            }
        }

        private final Class proxyClass;
        private final Map partner;
        private Map map;
        private final boolean lazyresolve;
        private final boolean introducePartner;
        private final boolean introduceMap;
        private final Object hash;
        private boolean inited;
        private boolean readOnly;

        private ProxyModelObjectInterceptor(Class proxyClass, Map map, boolean introduceMap, boolean fullCopy)
        {
            hash = new Object();
            inited = false;
            readOnly = false;
            this.map = new ProxyMap(map, fullCopy);
            partner = null;
            this.proxyClass = proxyClass;
            lazyresolve = false;
            introducePartner = false;
            this.introduceMap = introduceMap;
            inited = true;
        }

        private ProxyModelObjectInterceptor(Class proxyClass, boolean lazyresolve, boolean introducePartner, boolean introduceMap)
        {
            hash = new Object();
            inited = false;
            readOnly = false;
            partner = introducePartner ? ((Map) (new HashMap())) : null;
            this.proxyClass = proxyClass;
            this.lazyresolve = lazyresolve;
            this.introducePartner = introducePartner;
            this.introduceMap = introduceMap;
        }


    }

    private static class MapMethodCache
    {

        public static transient Method getMethod(String name, Class parameterTypes[])
        {
            return instance._getMethod(name, parameterTypes);
        }

        private transient Method _getMethod(String key, Class parameterTypes[])
        {
            Object ret = cache.get(key);
            if(ret == null)
                try
                {
                    ret = java/util/Map.getMethod(key, parameterTypes);
                    if(ret == null)
                        cache.put(key, ProxyModelObjectCreator._NULL);
                    else
                        cache.put(key, ret);
                }
                catch(NoSuchMethodException ignore)
                {
                    cache.put(key, ProxyModelObjectCreator._NULL);
                }
            else
            if(ret == ProxyModelObjectCreator._NULL)
                return null;
            if(ret instanceof Method)
                return (Method)ret;
            else
                return null;
        }

        private static final MapMethodCache instance = new MapMethodCache();
        private static final Map cache = new ConcurrentHashMap();


        private MapMethodCache()
        {
        }
    }


    public ProxyModelObjectCreator()
    {
    }

    public Object create(Class t)
    {
        Class t1 = super.resolveInterface(t);
        if(t1 != t)
            return super.create(t);
        if((((Boolean)LazyLoad.get()).booleanValue() || ((Boolean)LazyResolve.get()).booleanValue()) && XmlConfigManager.isCreatingModelObject())
            return createProxyByCglib(t, ((Boolean)LazyLoad.get()).booleanValue(), ((Boolean)LazyResolve.get()).booleanValue());
        if(t.isInterface())
            return createProxyByCglib(t, false, false);
        else
            return super.create(t);
    }

    public Object createPageModel(Class t)
    {
        Class t1 = super.resolveInterface(t);
        if(t1 != t)
            return super.create(t);
        else
            return createProxyByCglib(t, true, ((Boolean)LazyResolve.get()).booleanValue());
    }

    public void setModelReadonly(Object t)
    {
        if(t == null)
            throw new RuntimeException(ModelRtConst.ProxyModelObjectCreator02);
        if(!ModelUtil.isProxyClass(t.getClass()))
            return;
        if(t instanceof ReadAndWriteControllor)
            ((ReadAndWriteControllor)t).__setReadonly__();
        else
            throw new UnsupportedOperationException(String.format(ModelRtConst.ProxyModelObjectCreator03, new Object[] {
                t.getClass().getName()
            }));
    }

    public boolean isModelReadOnly(Object t)
    {
        if(t == null)
            throw new RuntimeException(ModelRtConst.ProxyModelObjectCreator02);
        if(!ModelUtil.isProxyClass(t.getClass()))
            return true;
        if(t instanceof ReadAndWriteControllor)
            return ((ReadAndWriteControllor)t).__isReadonly__();
        else
            throw new UnsupportedOperationException(String.format(ModelRtConst.ProxyModelObjectCreator03, new Object[] {
                t.getClass().getName()
            }));
    }

    public Object cloneModel(Object t)
    {
        return cloneModel(t, false);
    }

    public Object cloneModel(Object t, boolean fullCopy)
    {
        Class targetClass;
        if(t == null || !(t instanceof Map))
            return super.cloneModel(t);
        targetClass = ModelUtil.getOriginalClass(t.getClass());
        if(!ModelUtil.isProxyClass(t.getClass()) || !targetClass.isInterface())
            return super.cloneModel(t);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.start_record((new StringBuilder()).append("ModelCreator.cloneModel").append(targetClass.getSimpleName()).toString());
        Object obj;
        if(ProfileSwitcher.get().modelCreateUseClassCache)
            break MISSING_BLOCK_LABEL_176;
        Enhancer enhancer = new Enhancer();
        enhancer.setUseCache(true);
        enhancer.setInterfaces(t.getClass().getInterfaces());
        enhancer.setCallback(new ProxyModelObjectInterceptor(targetClass, (Map)t, true, fullCopy));
        obj = enhancer.create();
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("ModelCreator.cloneModel").append(targetClass.getSimpleName()).toString());
        return obj;
        Class genClass = getGenClass(targetClass, false);
        Enhancer.registerCallbacks(genClass, new Callback[] {
            new ProxyModelObjectInterceptor(targetClass, (Map)t, true, fullCopy)
        });
        obj = ReflectUtils.newInstance(genClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("ModelCreator.cloneModel").append(targetClass.getSimpleName()).toString());
        return obj;
        Exception exception;
        exception;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("ModelCreator.cloneModel").append(targetClass.getSimpleName()).toString());
        throw exception;
    }

    public Object createProxyByCglib(Class targetClass, boolean introducePartner, boolean lazyresolve)
    {
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.start_record((new StringBuilder()).append("ModelCreator.createProxyByCglib").append(targetClass.getSimpleName()).toString());
        Object obj1;
        if(ProfileSwitcher.get().modelCreateUseClassCache)
            break MISSING_BLOCK_LABEL_221;
        Enhancer enhancer = new Enhancer();
        enhancer.setUseCache(true);
        List superInterfaces = new ArrayList();
        if(targetClass.isInterface())
        {
            superInterfaces.add(targetClass);
            superInterfaces.add(java/util/Map);
        }
        superInterfaces.add(cn/sunline/ltts/core/api/model/ModelObjectCreator$ModelClassAware);
        if(introducePartner)
            superInterfaces.add(cn/sunline/ltts/frw/model/util/ModelObjectPartner);
        if(!targetClass.isInterface())
            enhancer.setSuperclass(targetClass);
        superInterfaces.add(cn/sunline/ltts/frw/model/util/ProxyModelObjectCreator$ReadAndWriteControllor);
        enhancer.setInterfaces((Class[])superInterfaces.toArray(new Class[0]));
        enhancer.setCallback(new ProxyModelObjectInterceptor(targetClass, lazyresolve, introducePartner, targetClass.isInterface()));
        obj1 = enhancer.create();
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("ModelCreator.createProxyByCglib").append(targetClass.getSimpleName()).toString());
        return obj1;
        Object obj;
        Class genClass = getGenClass(targetClass, introducePartner);
        Enhancer.registerCallbacks(genClass, new Callback[] {
            new ProxyModelObjectInterceptor(targetClass, lazyresolve, introducePartner, targetClass.isInterface())
        });
        obj = ReflectUtils.newInstance(genClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("ModelCreator.createProxyByCglib").append(targetClass.getSimpleName()).toString());
        return obj;
        Exception exception;
        exception;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("ModelCreator.createProxyByCglib").append(targetClass.getSimpleName()).toString());
        throw exception;
    }

    private Class getGenClass(Class targetClass, boolean introducePartner)
    {
        Class gen = (Class)genClazzCache.get(targetClass);
        if(gen == null)
            synchronized(targetClass)
            {
                gen = (Class)genClazzCache.get(targetClass);
                if(gen == null)
                {
                    Enhancer enhancer = new Enhancer();
                    enhancer.setUseCache(true);
                    List superInterfaces = new ArrayList();
                    if(targetClass.isInterface())
                    {
                        superInterfaces.add(targetClass);
                        superInterfaces.add(java/util/Map);
                    }
                    superInterfaces.add(cn/sunline/ltts/core/api/model/ModelObjectCreator$ModelClassAware);
                    if(introducePartner)
                        superInterfaces.add(cn/sunline/ltts/frw/model/util/ModelObjectPartner);
                    if(!targetClass.isInterface())
                        enhancer.setSuperclass(targetClass);
                    superInterfaces.add(cn/sunline/ltts/frw/model/util/ProxyModelObjectCreator$ReadAndWriteControllor);
                    enhancer.setInterfaces((Class[])superInterfaces.toArray(new Class[0]));
                    enhancer.setCallbackType(cn/sunline/ltts/frw/model/util/ProxyModelObjectCreator$ProxyModelObjectInterceptor);
                    gen = enhancer.createClass();
                    genClazzCache.put(targetClass, gen);
                }
            }
        return gen;
    }

    private static Map getPrototype(Class type)
    {
        Map ret = (Map)prototypes.get(type);
        if(ret == NULL)
            return null;
        if(ret != null)
            return ret;
        ComplexType ctype = OdbFactory.getComplexType(type);
        if(ctype != null)
            ret = getPrototype(ctype);
        prototypes.put(type, ret != null ? ((Object) (ret)) : ((Object) (NULL)));
        return ret;
    }

    private static Map getPrototype(ComplexType ctype)
    {
        Object root = ModelUtil.getRoot(ctype);
        if((root instanceof Schema) && ((Schema)root).getClassgen() == cn.sunline.ltts.frw.model.dm.Schema.Classgen.metadata)
            return null;
        List elements = ctype.getAllElements();
        Map ret = new LinkedHashMap((int)((float)elements.size() / 0.75F + 1.0F));
        Element e;
        Object initValue;
        for(Iterator i$ = elements.iterator(); i$.hasNext(); ret.put(e.getId(), initValue))
        {
            e = (Element)i$.next();
            initValue = null;
            if(!(e instanceof DataInterfaceField))
                initValue = getValue(e);
            if(initValue != null || e.getMulti() != null && e.getMulti().booleanValue() || !ProfileSwitcher.get().initModelObjectField)
                continue;
            if(ModelUtil.hasEnumeration(e.getTypeObj()))
            {
                initValue = getValue(e);
                continue;
            }
            SimpleType st = ModelUtil.getSimpleType(e.getTypeObj());
            if(st == null)
                continue;
            if(st.getJavaClass() == java/lang/String)
            {
                initValue = "";
                continue;
            }
            if(st.getJavaClass() == java/lang/Integer)
            {
                initValue = Integer.valueOf(0);
                continue;
            }
            if(st.getJavaClass() == java/lang/Long)
            {
                initValue = Long.valueOf(0L);
                continue;
            }
            if(st.getJavaClass() == java/math/BigDecimal)
                initValue = new BigDecimal("0.00");
        }

        return ret;
    }

    private static Object evalExpr(Element e, String expr)
    {
        if(expr == null)
            return null;
        ElementType eType = e.getTypeObj();
        if(eType == null)
            throw new RuntimeException((new StringBuilder()).append("\u76EE\u6807\u5B57\u6BB5").append(e.getId()).append("\u7C7B\u578B\u975E\u6CD5\uFF01").toString());
        else
            return LttsCoreBeanUtil.getExpressionEvaluator().findValue(expr, java/lang/Object, null, null);
    }

    private static String getPropertyName(Method method)
    {
        String ret = null;
        if(!Modifier.isFinal(method.getModifiers()))
        {
            String property = method.getName();
            Object val = propCache.get(property);
            if(val == null)
            {
                if(property.startsWith("is"))
                    ret = uncapitalFirst(property.substring(2));
                else
                if(property.startsWith("get") || property.startsWith("set"))
                    ret = uncapitalFirst(property.substring(3));
                else
                    ret = property;
                propCache.put(property, ret);
            } else
            {
                ret = (String)val;
            }
        }
        return ret;
    }

    private static String uncapitalFirst(String s)
    {
        return (new StringBuilder()).append(String.valueOf(s.charAt(0)).toLowerCase()).append(s.substring(1)).toString();
    }

    private static Object getValue(Element e)
    {
        String value = null;
        if(e == null || e.getTypeObj() == null)
            return null;
        if(e.getFixedValue() != null && e.getFixedValue().trim().length() != 0)
            value = e.getFixedValue();
        else
        if(e.getDefaultValue() != null && e.getDefaultValue().trim().length() != 0)
            value = e.getDefaultValue();
        else
            return null;
        return getValue(e, e.getTypeObj(), value);
    }

    private static Object getValue(Element e, ElementType typeObj, String value)
    {
        if(ModelUtil.hasEnumeration(typeObj))
        {
            Class retType = e.getElementJavaClass();
            try
            {
                Object ret = value;
                try
                {
                    ret = evalExpr(e, value);
                }
                catch(Exception exception) { }
                return EnumUtils.toEnum(retType, ret);
            }
            catch(Exception e1)
            {
                log.warn(ModelRtConst.ProxyModelObjectCreator01, e1, new Object[] {
                    getFullId(e), retType.getName(), value
                });
            }
            return null;
        }
        if((typeObj instanceof RestrictionType) && (((RestrictionType)typeObj).getBaseTypeObj() instanceof SimpleType))
            return getValue(e, ((ElementType) ((SimpleType)((RestrictionType)typeObj).getBaseTypeObj())), value);
        if(typeObj instanceof SimpleType)
        {
            SimpleType type = (SimpleType)typeObj;
            if(type.getJavaClass().isAssignableFrom(java/lang/String))
                if("''".equals(value))
                    return "";
                else
                    return value;
            if(type.getJavaClass().isAssignableFrom(java/lang/Integer))
                return new Integer(value);
            if(type.getJavaClass().isAssignableFrom(java/lang/Long))
                return new Long(value);
            if(type.getJavaClass().isAssignableFrom(java/math/BigDecimal))
            {
                BigDecimal ret = new BigDecimal(value);
                Integer scale = ModelUtil.getFractionDigits(e);
                if(scale != null)
                    ret = ret.setScale(scale.intValue());
                return ret;
            }
        }
        return null;
    }

    private static String getFullId(ModelObject m)
    {
        String ret = m.getId();
        if(m instanceof OwnerAware)
        {
            OwnerAware o = (OwnerAware)m;
            if(o.getOwner() instanceof ModelObject)
                ret = (new StringBuilder()).append(ret).append(".").append(getFullId((ModelObject)o.getOwner())).toString();
        }
        return ret;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/ProxyModelObjectCreator);
    private static final Object _NULL = new Object();
    private static Map genClazzCache = new ConcurrentHashMap();
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private static Map NULL = new HashMap();
    private static Map prototypes = new ConcurrentHashMap();
    private static final Map propCache = new ConcurrentHashMap();




}
