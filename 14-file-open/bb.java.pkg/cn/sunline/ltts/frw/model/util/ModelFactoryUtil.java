// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelFactoryUtil.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.frw.model.util.ModelPropertyDescriptor;
import cn.sunline.ltts.base.frw.model.util.ModelTreeWalker;
import cn.sunline.ltts.base.util.ClassUtils;
import cn.sunline.ltts.base.util.PropertyUtil;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.*;
import cn.sunline.ltts.core.api.model.dm.ComplexType;
import cn.sunline.ltts.core.api.model.dm.Element;
import cn.sunline.ltts.core.api.util.LttsCoreBeanUtil;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.db.Table;
import cn.sunline.ltts.frw.model.db.TableFinder;
import cn.sunline.ltts.frw.model.dm.RestrictionType;
import cn.sunline.ltts.frw.model.dm.internal.MetaData;
import cu.sunline.ltts.ModelRtConst;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            FileModelFile, XmlConfigManager, ModelUtil, ModelObjectPartnerAware, 
//            ModelObjectPartner, AbstractModelFactory

public class ModelFactoryUtil
{
    public static class DefaultModelFactoryByModel
        implements ModelFactoryByModel
    {

        public TableFinder getModelFactory(Object model)
        {
            return new TableFinder() {

                public Table findTableByName(String tableName)
                {
                    return null;
                }

                final DefaultModelFactoryByModel this$0;

                
                {
                    this$0 = DefaultModelFactoryByModel.this;
                    super();
                }
            }
;
        }

        public Object findModelFromAllModulesById(Class type, String id)
        {
            return null;
        }

        public boolean isFileIDUnique(Class type, String fileId)
        {
            return true;
        }

        public DefaultModelFactoryByModel()
        {
        }
    }

    public static interface ModelFactoryByModel
    {

        public abstract TableFinder getModelFactory(Object obj);

        public abstract boolean isFileIDUnique(Class class1, String s);
    }


    public ModelFactoryUtil()
    {
    }

    public static ModelFactory getModelFactory()
    {
        if(instanceInThread.get() == null)
        {
            if(instanceInGlobal != null)
                return instanceInGlobal;
            else
                throw new IllegalArgumentException(ModelRtConst.ModelFactoryUtil04);
        } else
        {
            return (ModelFactory)instanceInThread.get();
        }
    }

    public static ModelFactory setModelFactory(ModelFactory mf)
    {
        ModelFactory old = (ModelFactory)instanceInThread.get();
        instanceInThread.set(mf);
        return old;
    }

    public static void setGlobalModelFactory(ModelFactory mf)
    {
        instanceInGlobal = mf;
    }

    public static transient ModelFactory setModelFactory(ModelFactory modelFactories[])
    {
        ModelFactory old = (ModelFactory)instanceInThread.get();
        if(modelFactories != null)
            instanceInThread.set(new AbstractModelFactory(modelFactories) {

                final ModelFactory val$modelFactories[];

            
            {
                modelFactories = amodelfactory;
                super();
                getFactories().addAll(Arrays.asList(modelFactories));
            }
            }
);
        return old;
    }

    public static ModelFactoryByModel getModelFactoryByModel()
    {
        return modelFactoryByModel;
    }

    public static void setModelFactoryByModel(ModelFactoryByModel m)
    {
        modelFactoryByModel = m;
    }

    public static Object clone(Object t)
        throws JAXBException, IOException
    {
        if(t == null)
            return null;
        else
            return xcm.clone(t);
    }

    public static ModelObject parse(InputStream is)
    {
        return (ModelObject)xcm.load(is);
    }

    public static ModelObject parse(InputStream is, ModelFile file)
    {
        ModelObject modelobject;
        ModelObject m = (ModelObject)xcm.load(file, is);
        modelobject = m;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new RuntimeException(String.format(ModelRtConst.ModelFactoryUtil05, new Object[] {
                    file.getFullPath()
                }));
            }
        return modelobject;
        Exception e;
        e;
        log.warn((new StringBuilder()).append(file.getFullPath()).append(ModelRtConst.ModelFactoryUtil01).append(e.getMessage()).toString(), e, new Object[0]);
        modelobject = null;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new RuntimeException(String.format(ModelRtConst.ModelFactoryUtil05, new Object[] {
                    file.getFullPath()
                }));
            }
        return modelobject;
        Exception exception;
        exception;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new RuntimeException(String.format(ModelRtConst.ModelFactoryUtil05, new Object[] {
                    file.getFullPath()
                }));
            }
        throw exception;
    }

    public static ModelObject parse(File file)
    {
        return parse(((ModelFile) (new FileModelFile(file))));
    }

    public static ModelObject parse(ModelFile r)
    {
        return parse(r, xcm);
    }

    public static ModelObject parse(ModelFile r, XmlConfigManager xcm)
    {
        return parse(r, xcm, null);
    }

    public static ModelObject parse(ModelFile r, XmlConfigManager xcm, javax.xml.bind.Unmarshaller.Listener listener)
    {
        InputStream is = r.getInputStream();
        ModelObject modelobject;
        ModelObject m = (ModelObject)xcm.load(r, is, listener);
        if(!(m instanceof ModelObject))
            throw new RuntimeException(String.format(ModelRtConst.ModelFactoryUtil06, new Object[] {
                m.getClass().getName()
            }));
        modelobject = m;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new RuntimeException(String.format(ModelRtConst.ModelFactoryUtil05, new Object[] {
                    r.getFullPath()
                }));
            }
        return modelobject;
        Exception e;
        e;
        log.warn((new StringBuilder()).append(r.getFullPath()).append(ModelRtConst.ModelFactoryUtil02).append(e.getMessage()).toString(), e, new Object[0]);
        modelobject = null;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new RuntimeException(String.format(ModelRtConst.ModelFactoryUtil05, new Object[] {
                    r.getFullPath()
                }));
            }
        return modelobject;
        Exception exception;
        exception;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new RuntimeException(String.format(ModelRtConst.ModelFactoryUtil05, new Object[] {
                    r.getFullPath()
                }));
            }
        throw exception;
    }

    /**
     * @deprecated Method resolveModelRelation is deprecated
     */

    public static transient void resolveModelRelation(Object model, ModelFactory scope[])
    {
        ModelFactory old;
        if(model == null)
            return;
        old = (ModelFactory)instanceInThread.get();
        if(scope != null && scope.length > 0)
            instanceInThread.set(new AbstractModelFactory(scope) {

                final ModelFactory val$scope[];

            
            {
                scope = amodelfactory;
                super();
                getFactories().addAll(Arrays.asList(scope));
            }
            }
);
        resolveModelRelation(model, ((Set) (new HashSet())));
        instanceInThread.set(old);
        break MISSING_BLOCK_LABEL_69;
        Exception exception;
        exception;
        instanceInThread.set(old);
        throw exception;
    }

    public static void resolveModelRelation(Object model, Set resolved)
    {
        (new ModelTreeWalker(new cn.sunline.ltts.base.frw.model.util.ModelTreeWalker.ModelVisitor() {

            public boolean accept(Object model, Set data)
            {
                if(data.contains(model))
                {
                    return false;
                } else
                {
                    data.add(model);
                    return true;
                }
            }

            public Object accept(Object model, Class clazz, ModelPropertyDescriptor field, Object fieldValue, Set data)
            {
                if(fieldValue == null && field.isAnnotationPresent(cn/sunline/ltts/core/api/model/ModelRelation))
                {
                    if(model instanceof ModelObjectPartnerAware)
                    {
                        ModelPropertyDescriptor mpd = field;
                        fieldValue = ((ModelObjectPartnerAware)model).getModelObjectPartner().getRelation(mpd);
                    } else
                    {
                        fieldValue = ModelFactoryUtil.resolveModelRelation(model, clazz, field);
                        if(fieldValue != null)
                            field.setProperty(model, fieldValue);
                    }
                    return null;
                } else
                {
                    return fieldValue;
                }
            }

            public volatile Object accept(Object obj, Class class1, ModelPropertyDescriptor modelpropertydescriptor, Object obj1, Object obj2)
            {
                return accept(obj, class1, modelpropertydescriptor, obj1, (Set)obj2);
            }

            public volatile boolean accept(Object obj, Object obj1)
            {
                return accept(obj, (Set)obj1);
            }

        }
)).visit(model, resolved);
    }

    public static Object resolveModelRelation(Object model, Class clazz, ModelPropertyDescriptor field)
    {
        ModelRelation mr = (ModelRelation)field.getAnnotation(cn/sunline/ltts/core/api/model/ModelRelation);
        String fieldName = mr.propertyName();
        ModelPropertyDescriptor f = ModelPropertyDescriptor.get(clazz, fieldName);
        Object relationId = f.getProperty(model);
        if(relationId == null || relationId == "")
            return null;
        Object ret;
        if(relationId.getClass().isArray() && field.getPropertyType().isArray())
        {
            Object subModel = Array.newInstance(field.getPropertyType().getComponentType(), Array.getLength(relationId));
            boolean empty = true;
            for(int i = 0; i < Array.getLength(relationId); i++)
            {
                Object sub = getModelFactory().getModel(field.getPropertyType().getComponentType(), Array.get(relationId, i));
                if(sub != null)
                    empty = false;
                Array.set(subModel, i, sub);
            }

            if(empty)
                subModel = null;
            ret = subModel;
        } else
        {
            ret = getModelFactory().getModel(field.getPropertyType(), relationId);
            if(ret == null)
                log.warn(ModelRtConst.ModelFactoryUtil03, new Object[] {
                    clazz.getName(), field.getProperty(), field.getPropertyType(), relationId
                });
        }
        return ret;
    }

    public static Object getInstance(Class intfClass)
    {
        ComplexType ct = getClassComplexType(intfClass);
        if(ct == null)
            try
            {
                return LttsCoreBeanUtil.getModelObjectCreator().create(ClassUtils.classForName((new StringBuilder()).append(intfClass.getName()).append("Impl").toString()));
            }
            catch(Exception e1)
            {
                throw new RuntimeException(e1);
            }
        try
        {
            return LttsCoreBeanUtil.getModelObjectCreator().create(ct.getJavaClass());
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static ComplexType getClassComplexType(Class clazz)
    {
        if(clazz == null)
            return null;
        String clazzName = clazz.getName();
        ComplexType ret = (ComplexType)complexTypeCache.get(clazzName);
        if(ret != null)
            return ret;
        String fullId = ModelUtil.getFullId(clazz);
        if(fullId != null && fullId.trim().length() != 0)
        {
            ret = (ComplexType)getModelFactory().getModel(cn/sunline/ltts/core/api/model/dm/ComplexType, fullId);
            return ret;
        }
        List typeList = getModelFactory().getModels(cn/sunline/ltts/core/api/model/dm/ComplexType);
        if(typeList == null || typeList.size() == 0)
            return null;
        Iterator i$ = typeList.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            ComplexType typeObj = (ComplexType)i$.next();
            try
            {
                if(clazzName.equals(typeObj.getJavaClass().getName()))
                {
                    complexTypeCache.put(clazzName, typeObj);
                    return typeObj;
                }
            }
            catch(IllegalArgumentException e)
            {
                if(!(e.getCause() instanceof ClassNotFoundException))
                    throw e;
            }
        } while(true);
        return null;
    }

    public static MetaData getMetaData(Class type, String property)
    {
        ComplexType typeObj = getClassComplexType(type);
        if(typeObj == null)
            return null;
        List elements = typeObj.getElements();
        if(elements == null || elements.size() == 0)
            return null;
        Iterator i$ = elements.iterator();
        Element e;
        do
        {
            if(!i$.hasNext())
                break MISSING_BLOCK_LABEL_135;
            e = (Element)i$.next();
        } while(!e.getId().equals(property));
        MetaData md;
        try
        {
            md = _getMetaData(e, new MetaData());
            if(e.getTypeObj() instanceof RestrictionType)
                return _getMetaData(e.getTypeObj(), md);
        }
        catch(Exception e1)
        {
            throw new IllegalArgumentException(e1);
        }
        return md;
        return null;
    }

    private static MetaData _getMetaData(Object model, MetaData md)
        throws IllegalArgumentException, IllegalAccessException
    {
        cn.sunline.ltts.base.util.PropertyUtil.PropertyAccessor pa = PropertyUtil.createAccessor(model);
        Field arr$[] = md.getClass().getDeclaredFields();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Field field = arr$[i$];
            field.setAccessible(true);
            if(field.get(md) == null)
                field.set(md, pa.getNestedProperty(field.getName()));
        }

        return md;
    }

    public static boolean isWeakHashMap = false;
    public static ModelFactoryByModel modelFactoryByModel = new DefaultModelFactoryByModel();
    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/ModelFactoryUtil);
    public static XmlConfigManager xcm = new XmlConfigManager();
    private static Map complexTypeCache = new ConcurrentHashMap();
    private static ModelFactory instanceInGlobal;
    private static final ThreadLocal instanceInThread = new ThreadLocal();

}
