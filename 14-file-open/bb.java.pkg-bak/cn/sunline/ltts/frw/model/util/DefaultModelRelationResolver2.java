// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultModelRelationResolver2.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.frw.model.util.ModelPropertyDescriptor;
import cn.sunline.ltts.base.odb.ModelRelationResolver;
import cn.sunline.ltts.base.util.ClassUtils;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelRelation;
import cn.sunline.ltts.core.util.LangUtil;
import cn.sunline.ltts.frw.model.ModelFactory;
import cu.sunline.ltts.ModelRtConst;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.bind.annotation.XmlTransient;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelUtil, ModelFactoryUtil

public class DefaultModelRelationResolver2
    implements ModelRelationResolver
{
    public static class FieldRelationInfo
    {

        public ModelPropertyDescriptor getField()
        {
            return field;
        }

        public void setField(ModelPropertyDescriptor field)
        {
            this.field = field;
        }

        public ModelPropertyDescriptor getRelationIdField()
        {
            return relationIdField;
        }

        public void setRelationIdField(ModelPropertyDescriptor relationIdField)
        {
            this.relationIdField = relationIdField;
        }

        public boolean getRelation()
        {
            return relation;
        }

        public void setRelation(boolean relation)
        {
            this.relation = relation;
        }

        private ModelPropertyDescriptor field;
        private ModelPropertyDescriptor relationIdField;
        private boolean relation;

        private FieldRelationInfo(ModelPropertyDescriptor field)
        {
            relation = false;
            this.field = field;
        }

    }


    public DefaultModelRelationResolver2()
    {
        fieldRelations = new ConcurrentHashMap();
    }

    public void resolve(Object model, Set resolved)
    {
        if(model == null)
            return;
        if(resolved.contains(model))
            return;
        resolved.add(model);
        Class clazz = ModelUtil.getOriginalClass(model.getClass());
        try
        {
            while(clazz != null && clazz != java/lang/Object) 
            {
                resolveModelRelation(model, clazz, resolved);
                clazz = clazz.getSuperclass();
            }
        }
        catch(Exception e)
        {
            if(e instanceof IllegalArgumentException)
                throw (IllegalArgumentException)e;
            else
                throw new IllegalArgumentException(e);
        }
    }

    private void resolveModelRelation(Object model, Class type, Set resolved)
    {
        List relation = getFieldRelation(type);
        if(relation == null)
            return;
        Iterator i$ = relation.iterator();
label0:
        do
        {
            if(!i$.hasNext())
                break;
            FieldRelationInfo fieldInfo = (FieldRelationInfo)i$.next();
            ModelPropertyDescriptor destField = fieldInfo.getField();
            Class fieldType = destField.getPropertyType();
            Object fieldValue;
            if(fieldInfo.getRelation())
            {
                Object relationId = fieldInfo.getRelationIdField().getProperty(model);
                if(relationId == null || relationId == "")
                    continue;
                if(relationId.getClass().isArray() && fieldType.isArray())
                {
                    fieldValue = Array.newInstance(fieldType.getComponentType(), Array.getLength(relationId));
                    boolean empty = true;
                    for(int i = 0; i < Array.getLength(relationId); i++)
                    {
                        Object sub = ModelFactoryUtil.getModelFactory().getModel(fieldType.getComponentType(), Array.get(relationId, i));
                        if(sub != null)
                            empty = false;
                        Array.set(fieldValue, i, sub);
                    }

                    if(empty)
                        fieldValue = null;
                } else
                {
                    fieldValue = ModelFactoryUtil.getModelFactory().getModel(fieldType, relationId);
                    if(fieldValue == null)
                        log.warn(String.format(ModelRtConst.DefaultModelRelationResolver201, new Object[] {
                            type.getName(), fieldInfo.getField().getProperty(), fieldType, relationId
                        }));
                }
                if(fieldValue != null)
                    destField.setProperty(model, fieldValue);
            } else
            {
                fieldValue = destField.getProperty(model);
            }
            if(fieldValue == null)
                continue;
            if(java/util/Collection.isAssignableFrom(fieldType))
            {
                Collection collenctions = (Collection)fieldValue;
                Iterator i$ = collenctions.iterator();
                do
                {
                    Object obj;
                    do
                    {
                        if(!i$.hasNext())
                            continue label0;
                        obj = i$.next();
                    } while(obj == null);
                    resolve(obj, resolved);
                } while(true);
            }
            if(fieldType.isArray())
            {
                int i = 0;
                while(i < Array.getLength(fieldValue)) 
                {
                    resolve(Array.get(fieldValue, i), resolved);
                    i++;
                }
            } else
            {
                resolve(fieldValue, resolved);
            }
        } while(true);
    }

    private List getFieldRelation(Class type)
    {
        List ret = (List)fieldRelations.get(type);
        if(ret == NULL)
            return null;
        if(ret == null)
        {
            ret = createFieldRelation(type);
            fieldRelations.put(type, ret != null ? ((Object) (ret)) : ((Object) (NULL)));
        }
        return ret;
    }

    private List createFieldRelation(Class clazz)
    {
        List ret = new ArrayList();
        if(clazz.getDeclaredFields() != null)
        {
            Field arr$[] = clazz.getDeclaredFields();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Field field = arr$[i$];
                if(field.isAnnotationPresent(javax/xml/bind/annotation/XmlTransient) || Modifier.isVolatile(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()))
                    continue;
                try
                {
                    if(!needResolve(field.getType()) && (!field.getType().isArray() || !needResolve(field.getType().getComponentType())) && (!java/util/Collection.isAssignableFrom(field.getType()) || !needResolve(ClassUtils.getCollectionMemberType(field))))
                        continue;
                }
                catch(Exception e)
                {
                    throw LangUtil.wrapThrow(e);
                }
                ModelPropertyDescriptor propertyDescriptor = ModelPropertyDescriptor.get(field);
                FieldRelationInfo relationInfo = new FieldRelationInfo(propertyDescriptor);
                if(field.isAnnotationPresent(cn/sunline/ltts/core/api/model/ModelRelation))
                {
                    ModelRelation mr = (ModelRelation)field.getAnnotation(cn/sunline/ltts/core/api/model/ModelRelation);
                    String fieldName = mr.propertyName();
                    ModelPropertyDescriptor relationIdField = ModelPropertyDescriptor.get(clazz, fieldName);
                    if(relationIdField == null)
                        throw new RuntimeException(String.format(ModelRtConst.DefaultModelRelationResolver202, new Object[] {
                            clazz.getName(), propertyDescriptor.getProperty(), fieldName
                        }));
                    relationInfo.setRelation(true);
                    relationInfo.setRelationIdField(relationIdField);
                }
                ret.add(relationInfo);
            }

        }
        if(ret.size() > 0)
            return ret;
        else
            return null;
    }

    public static boolean needResolve(Class c)
    {
        if(c.isPrimitive())
            return false;
        if(c.isEnum())
            return false;
        Class arr$[] = SimpleClass;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Class c1 = arr$[i$];
            if(c1 == c)
                return false;
        }

        if(c.getPackage() == null)
            return false;
        String pkgName = c.getPackage().getName();
        return pkgName != null && !pkgName.startsWith("java.") && !pkgName.startsWith("net.") && !pkgName.startsWith("org.eclipse.");
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/DefaultModelRelationResolver2);
    static final DefaultModelRelationResolver2 instance = new DefaultModelRelationResolver2();
    private Map fieldRelations;
    private static final List NULL = Collections.emptyList();
    private static Class SimpleClass[] = {
        java/lang/Object, java/util/Date, java/sql/Date, java/sql/Timestamp, java/lang/String, java/lang/Boolean, java/lang/Character, java/lang/Byte, java/lang/Short, java/lang/Integer, 
        java/lang/Long, java/lang/Float, java/lang/Double, java/lang/Void
    };

}
