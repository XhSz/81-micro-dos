// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnnotationConfigManager.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.common.util.StringUtil;
import cn.sunline.ltts.base.frw.model.util.ModelPropertyDescriptor;
import cn.sunline.ltts.base.util.ClassUtils;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.core.api.model.ModelObjectCreator;
import cn.sunline.ltts.core.api.model.dm.internal.DefaultEnum;
import cn.sunline.ltts.core.api.util.LttsCoreBeanUtil;
import cn.sunline.ltts.frw.model.annotation.AnnotationConfigLoader;
import cn.sunline.ltts.frw.model.annotation.ComplexType;
import cn.sunline.ltts.frw.model.annotation.ConfigType;
import cn.sunline.ltts.frw.model.annotation.Element;
import cn.sunline.ltts.frw.model.annotation.Enumeration;
import cn.sunline.ltts.frw.model.annotation.Schema;
import cn.sunline.ltts.frw.model.annotation.SchemaAnnotationConfigLoader;
import cn.sunline.ltts.frw.model.datainterface.DataInterfaceElements;
import cn.sunline.ltts.frw.model.datainterface.DataInterfaceField;
import cn.sunline.ltts.frw.model.datainterface.DataInterfaceFields;
import cn.sunline.ltts.frw.model.dm.ElementEx;
import cn.sunline.ltts.frw.model.dm.RestrictionType;
import cn.sunline.ltts.frw.model.dm.SimpleType;
import cn.sunline.ltts.frw.model.dm.internal.DefaultElement;
import cu.sunline.ltts.ModelRtConst;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelUtil

public class AnnotationConfigManager
{
    public static class ElementAnnotationInfo
    {

        public cn.sunline.ltts.frw.model.datainterface.DataInterfaceElements.Type getCurrentInterface()
        {
            return currentInterface;
        }

        public void setCurrentInterface(cn.sunline.ltts.frw.model.datainterface.DataInterfaceElements.Type currentInterface)
        {
            this.currentInterface = currentInterface;
        }

        public Element getElementAnnotation()
        {
            return elementAnnotation;
        }

        public void setElementAnnotation(Element elementAnnotation)
        {
            this.elementAnnotation = elementAnnotation;
        }

        public Annotation getInterfaceAnnotation()
        {
            return interfaceAnnotation;
        }

        public void setInterfaceAnnotation(Annotation interfaceAnnotation)
        {
            this.interfaceAnnotation = interfaceAnnotation;
        }

        private cn.sunline.ltts.frw.model.datainterface.DataInterfaceElements.Type currentInterface;
        private Element elementAnnotation;
        private Annotation interfaceAnnotation;

        public ElementAnnotationInfo(cn.sunline.ltts.frw.model.datainterface.DataInterfaceElements.Type currentInterface)
        {
            this.currentInterface = currentInterface;
        }

        public ElementAnnotationInfo()
        {
        }
    }

    public static final class ElementImpl extends Enum
    {

        public static ElementImpl[] values()
        {
            return (ElementImpl[])$VALUES.clone();
        }

        public static ElementImpl valueOf(String name)
        {
            return (ElementImpl)Enum.valueOf(cn/sunline/ltts/frw/model/util/AnnotationConfigManager$ElementImpl, name);
        }

        public String getId()
        {
            return id;
        }

        public Class getCls()
        {
            return cls;
        }

        public static final ElementImpl defaultElement;
        public static final ElementImpl fild;
        public static final ElementImpl dataInterfaceField;
        public static final ElementImpl dataInterfaceFields;
        private String id;
        private Class cls;
        private static final ElementImpl $VALUES[];

        static 
        {
            defaultElement = new ElementImpl("defaultElement", 0, "default", cn/sunline/ltts/frw/model/dm/internal/DefaultElement);
            fild = new ElementImpl("fild", 1, "field", java/lang/reflect/Field);
            dataInterfaceField = new ElementImpl("dataInterfaceField", 2, "dataInterfaceField", cn/sunline/ltts/frw/model/datainterface/DataInterfaceField);
            dataInterfaceFields = new ElementImpl("dataInterfaceFields", 3, "dataInterfaceFields", cn/sunline/ltts/frw/model/datainterface/DataInterfaceFields);
            $VALUES = (new ElementImpl[] {
                defaultElement, fild, dataInterfaceField, dataInterfaceFields
            });
        }

        private ElementImpl(String s, int i, String id, Class cls)
        {
            super(s, i);
            this.id = id;
            this.cls = cls;
        }
    }


    public AnnotationConfigManager()
    {
    }

    public static AnnotationConfigManager get()
    {
        return instance;
    }

    public static void register(Class annotationType, AnnotationConfigLoader loader)
    {
        loaders.put(annotationType, loader);
    }

    public static void register(String modelType, Class annotationClass[])
    {
        annotationClasses.put(modelType, annotationClass);
    }

    public Class[] getAnnotationClasses(String modelType)
    {
        return (Class[])annotationClasses.get(modelType);
    }

    public ModelObject load(Class c)
    {
        if(c == null)
            return null;
        Annotation arr$[] = c.getAnnotations();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Annotation an = arr$[i$];
            AnnotationConfigLoader loader = (AnnotationConfigLoader)loaders.get(an);
            if(loader != null)
                return loader.load(c, an);
        }

        return null;
    }

    public cn.sunline.ltts.frw.model.dm.Schema getSchema(Class c)
    {
        if(c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Schema) != null)
            return (cn.sunline.ltts.frw.model.dm.Schema)((AnnotationConfigLoader)loaders.get(cn/sunline/ltts/frw/model/annotation/Schema)).load(c, c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Schema));
        if(c.getAnnotation(cn/sunline/ltts/frw/model/annotation/ComplexType) != null)
            return getSchema(((ComplexType)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/ComplexType)).owner());
        else
            return null;
    }

    public String getSchemaId(Class c)
    {
        if(c == null)
            throw new NullPointerException(ModelRtConst.AnnotationConfigManager01);
        if(c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Schema) != null)
            return _getSchemaId(c);
        if(c.getAnnotation(cn/sunline/ltts/frw/model/annotation/ComplexType) != null)
            return _getSchemaId(((ComplexType)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/ComplexType)).owner());
        if(c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Enumeration) != null)
            return _getSchemaId(((Enumeration)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Enumeration)).owner());
        else
            return null;
    }

    private String _getSchemaId(Class c)
    {
        Schema schemaAnnotation = (Schema)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Schema);
        if(schemaAnnotation == null)
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager02, new Object[] {
                c.getCanonicalName(), cn/sunline/ltts/frw/model/annotation/Schema.getName()
            }));
        if(StringUtil.isNotEmpty(schemaAnnotation.id()))
            return schemaAnnotation.id();
        else
            return c.getSimpleName();
    }

    public RestrictionType createRestrictionType(cn.sunline.ltts.frw.model.annotation.RestrictionType rtAnnotation)
    {
        RestrictionType rt = new RestrictionType();
        try
        {
            copyProperties(rtAnnotation, rt, null);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager03, new Object[] {
                e.getMessage()
            }), e);
        }
        return rt;
    }

    public Class getEnumBaseTypeClass(Class clazz)
    {
        if(clazz.isEnum())
            return java/lang/String;
        if(!cn/sunline/ltts/core/api/model/dm/internal/DefaultEnum.isAssignableFrom(clazz))
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager04, new Object[] {
                clazz.getCanonicalName()
            }));
        Object enumElements[] = clazz.getEnumConstants();
        if(enumElements == null || enumElements.length == 0)
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager05, new Object[] {
                clazz.getName()
            }));
        else
            return ((DefaultEnum)enumElements[0]).getValue().getClass();
    }

    public String getComplexTypeFullId(Class c)
    {
        String ret = null;
        ConfigType configType = (ConfigType)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/ConfigType);
        if(configType != null)
        {
            if(StringUtil.isEmpty(configType.value()))
                throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager06, new Object[] {
                    c.getCanonicalName()
                }));
            ret = configType.value();
        } else
        {
            ComplexType complexTypeAnnotation = (ComplexType)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/ComplexType);
            if(complexTypeAnnotation == null)
                throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager07, new Object[] {
                    c.getCanonicalName()
                }));
            String typeId = complexTypeAnnotation.id();
            if(StringUtil.isEmpty(typeId))
                typeId = c.getSimpleName();
            String schemaId;
            try
            {
                schemaId = getSchemaId(complexTypeAnnotation.owner());
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager08, new Object[] {
                    c.getName(), complexTypeAnnotation.owner().getName()
                }), e);
            }
            ret = (new StringBuilder()).append(schemaId).append(".").append(typeId).toString();
        }
        return ret;
    }

    public void copyProperties(Annotation src, Object desc, String exclusiveFields[])
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Class annotationType = src.annotationType();
        Method methods[] = annotationType.getDeclaredMethods();
        if(methods == null || methods.length == 0)
            return;
        Method arr$[] = methods;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Method method = arr$[i$];
            String propertyName = method.getName();
            if(!isInArray(propertyName, exclusiveFields))
            {
                ModelPropertyDescriptor mpd = ModelPropertyDescriptor.get(desc.getClass(), propertyName);
                mpd.setProperty(desc, method.invoke(src, new Object[0]));
            }
        }

    }

    private boolean isInArray(String src, String exclusiveFields[])
    {
        if(exclusiveFields == null)
            return false;
        String arr$[] = exclusiveFields;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String str = arr$[i$];
            if(src.equals(str))
                return true;
        }

        return false;
    }

    public List creatElements(cn.sunline.ltts.core.api.model.dm.ComplexType owner, Class c)
    {
        List elements = new ArrayList();
        Set elementSet = new HashSet();
        Field fields[] = c.getDeclaredFields();
        if(fields != null && fields.length != 0)
        {
            Field arr$[] = fields;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Field field = arr$[i$];
                Element elementAnnotation = (Element)field.getAnnotation(cn/sunline/ltts/frw/model/annotation/Element);
                if(elementAnnotation == null)
                    continue;
                elementSet.add(field.getName());
                Class elementType = field.getType();
                Class genericClass = null;
                if(java/util/Collection.isAssignableFrom(elementType))
                    genericClass = ClassUtils.getCollectionMemberType(field);
                ElementEx element = creatElement(field.getName(), elementAnnotation, ElementImpl.defaultElement);
                setElementType(element, field.getType(), field, genericClass, elementAnnotation);
                element.setProperty(field.getName());
                element.setOwner(owner);
                elements.add(element);
            }

        }
        Method methods[] = c.getDeclaredMethods();
        if(methods != null && methods.length != 0)
        {
            Method arr$[] = methods;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Method method = arr$[i$];
                ModelPropertyDescriptor mpd = ModelPropertyDescriptor.get(method);
                if(mpd == null)
                    continue;
                Element elementAnnotation = (Element)method.getAnnotation(cn/sunline/ltts/frw/model/annotation/Element);
                if(elementAnnotation == null)
                    continue;
                String propertyName = mpd.getProperty();
                if(elementSet.contains(propertyName))
                    throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager09, new Object[] {
                        propertyName
                    }));
                Class elementType = method.getReturnType();
                Class genericClass = null;
                if(java/util/Collection.isAssignableFrom(elementType))
                    genericClass = ClassUtils.getCollectionMemberType(method);
                ElementEx element = creatElement(mpd.getProperty(), elementAnnotation, ElementImpl.defaultElement);
                setElementType(element, method.getReturnType(), method, genericClass, elementAnnotation);
                element.setProperty(mpd.getProperty());
                element.setOwner(owner);
                elements.add(element);
            }

        }
        return elements;
    }

    public ElementEx creatElement(String propertyName, Element elementAnnotation, ElementImpl implMode)
    {
        ElementEx element;
        try
        {
            element = (ElementEx)LttsCoreBeanUtil.getModelObjectCreator().create(implMode.getCls());
        }
        catch(Exception e1)
        {
            throw new IllegalArgumentException(ModelRtConst.AnnotationConfigManager10);
        }
        if(elementAnnotation != null)
            try
            {
                copyProperties(elementAnnotation, element, new String[] {
                    "restriction"
                });
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager11, new Object[] {
                    e.getMessage()
                }), e);
            }
        if(StringUtil.isEmpty(element.getId()))
            element.setId(propertyName);
        return element;
    }

    public void setElementType(ElementEx element, Class elementType, Member elementMember, Class genericClass, Element elementAnnotation)
    {
        if(StringUtil.isNotEmpty(element.getType()))
            return;
        if(elementType.isArray())
        {
            element.setMulti(Boolean.valueOf(true));
            element.setArray(Boolean.valueOf(true));
            elementType = elementType.getComponentType();
            if(elementType.isArray())
                throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager12, new Object[] {
                    elementMember
                }));
        } else
        if(java/util/Collection.isAssignableFrom(elementType))
        {
            element.setMulti(Boolean.valueOf(true));
            elementType = genericClass;
            if(elementType == null)
                throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager13, new Object[] {
                    element.getId(), elementMember
                }));
        }
        try
        {
            if(elementAnnotation != null && elementAnnotation.restriction() != null && !"__NULL__".equals(elementAnnotation.restriction().id()))
            {
                RestrictionType rt = createRestrictionType(elementAnnotation.restriction());
                element.setTypeObj(rt);
                rt.setOwner(element);
                if(StringUtil.isEmpty(rt.getBaseType()))
                    rt.setBaseType(getElementType(elementType, rt));
                return;
            }
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager14, new Object[] {
                element.getId(), e.getMessage()
            }), e);
        }
        element.setType(getElementType(elementType, element));
    }

    public String getElementType(Class elementType, ModelObject owner)
    {
        if(java/util/Map == elementType || elementType.isPrimitive() || ClassUtils.isSimpleClass(elementType))
        {
            SimpleType st = ModelUtil.getSimpleTypeById(ModelUtil.uncapitalFirst(elementType.getSimpleName()));
            if(st == null)
                throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager15, new Object[] {
                    elementType.getSimpleName()
                }));
            else
                return st.getId();
        }
        if(cn/sunline/ltts/core/api/model/dm/internal/DefaultEnum.isAssignableFrom(elementType) || elementType.isEnum())
        {
            if(elementType.getAnnotation(cn/sunline/ltts/frw/model/annotation/Enumeration) != null)
            {
                return getEnumerationTypeFullId(elementType);
            } else
            {
                RestrictionType rt = getEnumRestritionType(elementType);
                rt.setOwner(owner);
                return rt.getId();
            }
        } else
        {
            return getComplexTypeFullId(elementType);
        }
    }

    private String getEnumerationTypeFullId(Class c)
    {
        Enumeration enumAnnotation = (Enumeration)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Enumeration);
        if(enumAnnotation == null)
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationConfigManager16, new Object[] {
                c.getCanonicalName()
            }));
        String typeId = enumAnnotation.id();
        if(StringUtil.isEmpty(typeId))
            typeId = c.getSimpleName();
        return (new StringBuilder()).append(getSchemaId(c)).append(".").append(typeId).toString();
    }

    private RestrictionType getEnumRestritionType(Class elementType)
    {
        RestrictionType rt = new RestrictionType();
        rt.setEnumerations(new ArrayList());
        rt.setBaseType(getElementType(getEnumBaseTypeClass(elementType), rt));
        rt.setClazz(elementType.getCanonicalName());
        if(cn/sunline/ltts/core/api/model/dm/internal/DefaultEnum.isAssignableFrom(elementType))
        {
            Object arr$[] = elementType.getEnumConstants();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Object em = arr$[i$];
                DefaultEnum de = (DefaultEnum)em;
                cn.sunline.ltts.frw.model.dm.Enumeration et = new cn.sunline.ltts.frw.model.dm.Enumeration();
                et.setId(de.getId());
                et.setLongname(de.getLongName());
                et.setValue(String.valueOf(de.getValue()));
                rt.getEnumerations().add(et);
            }

        } else
        if(elementType.isEnum())
        {
            Object arr$[] = elementType.getEnumConstants();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Object em = arr$[i$];
                cn.sunline.ltts.frw.model.dm.Enumeration et = new cn.sunline.ltts.frw.model.dm.Enumeration();
                et.setId(String.valueOf(em));
                et.setValue(String.valueOf(String.valueOf(em)));
                rt.getEnumerations().add(et);
            }

        }
        return rt;
    }

    private static AnnotationConfigManager instance = new AnnotationConfigManager();
    private static final Map loaders = new ConcurrentHashMap();
    private static final Map annotationClasses = new ConcurrentHashMap();

    static 
    {
        register(cn/sunline/ltts/frw/model/annotation/ComplexType, SchemaAnnotationConfigLoader.instance);
        register(cn/sunline/ltts/frw/model/annotation/Enumeration, SchemaAnnotationConfigLoader.instance);
        register(cn/sunline/ltts/frw/model/annotation/Schema, SchemaAnnotationConfigLoader.instance);
    }
}
