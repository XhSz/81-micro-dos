// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SchemaAnnotationConfigLoader.java

package cn.sunline.ltts.frw.model.annotation;

import cn.sunline.common.util.StringUtil;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.core.api.model.dm.ComplexType;
import cn.sunline.ltts.core.api.model.dm.internal.DefaultEnum;
import cn.sunline.ltts.frw.model.dm.*;
import cn.sunline.ltts.frw.model.dm.internal.DefaultComplexType;
import cn.sunline.ltts.frw.model.util.*;
import cu.sunline.ltts.ModelRtConst;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.annotation:
//            ComplexType, Enumeration, Schema, Element, 
//            ConfigType, AnnotationConfigLoader

public class SchemaAnnotationConfigLoader
    implements AnnotationConfigLoader
{

    public SchemaAnnotationConfigLoader()
    {
    }

    public ModelObject load(Class c, Annotation annotation)
    {
        if(annotation instanceof cn.sunline.ltts.frw.model.annotation.ComplexType)
            return load(c, (cn.sunline.ltts.frw.model.annotation.ComplexType)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/ComplexType));
        if(annotation instanceof cn.sunline.ltts.frw.model.annotation.Enumeration)
            return load(c, (cn.sunline.ltts.frw.model.annotation.Enumeration)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Enumeration));
        if(annotation instanceof cn.sunline.ltts.frw.model.annotation.Schema)
            return load(c, (cn.sunline.ltts.frw.model.annotation.Schema)c.getAnnotation(cn/sunline/ltts/frw/model/annotation/Schema));
        else
            return null;
    }

    private ComplexType load(Class c, cn.sunline.ltts.frw.model.annotation.ComplexType complexTypeAnnotation)
    {
        DefaultComplexType ct = new DefaultComplexType();
        ct.setClazz(c.getName());
        try
        {
            AnnotationConfigManager.get().copyProperties(complexTypeAnnotation, ct, new String[] {
                "owner"
            });
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.SchemaAnnotationConfigLoader01, new Object[] {
                e.getMessage()
            }), e);
        }
        if(StringUtil.isEmpty(ct.getId()))
            ct.setId(c.getSimpleName());
        Set classSet = new HashSet();
        classSet.add(c);
        List extensions = getExtensions(c, classSet);
        classSet.clear();
        if(ModelUtil.isCollectionNotEmpty(extensions))
            ct.setExtension((String[])extensions.toArray(new String[0]));
        ct.setElements(AnnotationConfigManager.get().creatElements(ct, c));
        return ct;
    }

    private RestrictionType load(Class c, cn.sunline.ltts.frw.model.annotation.Enumeration enumAnnotation)
    {
        RestrictionType rt = new RestrictionType();
        try
        {
            AnnotationConfigManager.get().copyProperties(enumAnnotation, rt, new String[] {
                "owner"
            });
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.SchemaAnnotationConfigLoader01, new Object[] {
                e.getMessage()
            }), e);
        }
        if(StringUtil.isEmpty(rt.getId()))
            rt.setId(c.getSimpleName());
        rt.setClazz(c.getName());
        rt.setEnumerations(getEnumerationList(c));
        try
        {
            rt.setBaseType(AnnotationConfigManager.get().getElementType(AnnotationConfigManager.get().getEnumBaseTypeClass(c), rt));
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.SchemaAnnotationConfigLoader02, new Object[] {
                e.getMessage()
            }), e);
        }
        return rt;
    }

    private Schema load(Class c, cn.sunline.ltts.frw.model.annotation.Schema schemaAnnotation)
    {
        if(schemaAnnotation == null)
            return null;
        Schema schema = new Schema();
        schema.setId(AnnotationConfigManager.get().getSchemaId(c));
        schema.setLongname(schemaAnnotation.longname());
        schema.setPackage(c.getPackage().getName());
        schema.setModelFile(new InputStreamModelFile(null, (new StringBuilder()).append("class:").append(c.getName()).toString()));
        if(schemaAnnotation.types() == null || schemaAnnotation.types().length == 0)
            return schema;
        cn.sunline.ltts.frw.model.annotation.RestrictionType arr$[] = schemaAnnotation.types();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            cn.sunline.ltts.frw.model.annotation.RestrictionType rtAnnotation = arr$[i$];
            RestrictionType rt = AnnotationConfigManager.get().createRestrictionType(rtAnnotation);
            rt.setOwner(schema);
            schema.getTypes().add(rt);
        }

        return schema;
    }

    private List getEnumerationList(Class enumClass)
    {
        Object enumElements[] = enumClass.getEnumConstants();
        if(enumElements == null || enumElements.length == 0)
            throw new IllegalArgumentException(String.format(ModelRtConst.SchemaAnnotationConfigLoader03, new Object[] {
                enumClass.getName()
            }));
        List ret = new ArrayList();
        Field arr$[] = enumClass.getFields();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Field element = arr$[i$];
            if(element.isEnumConstant())
                ret.add(getEnumeration(element));
        }

        return ret;
    }

    private Enumeration getEnumeration(Field f)
    {
        Element ee = (Element)f.getAnnotation(cn/sunline/ltts/frw/model/annotation/Element);
        if(ee != null)
        {
            Enumeration e = new Enumeration();
            e.setId(StringUtil.isEmpty(ee.id()) ? f.getName() : ee.id());
            e.setLongname(StringUtil.isEmpty(ee.longname()) ? f.getName() : ee.longname());
            e.setValue(f.getName());
            return e;
        }
        Object value;
        try
        {
            value = f.get(null);
        }
        catch(Exception e1)
        {
            throw new IllegalArgumentException(e1);
        }
        if(value instanceof DefaultEnum)
        {
            Enumeration e = new Enumeration();
            DefaultEnum de = (DefaultEnum)value;
            e.setId(de.getId());
            e.setLongname(de.getLongName());
            e.setValue(de.getValue().toString());
            return e;
        } else
        {
            Enumeration e = new Enumeration();
            e.setId(f.getName());
            e.setLongname(f.getName());
            e.setValue(f.getName());
            return e;
        }
    }

    private List getExtensions(Class clazz, Set set)
    {
        Class superIntfs[] = clazz.getInterfaces();
        if(superIntfs == null || superIntfs.length == 0)
            return null;
        List extensions = new ArrayList();
        Class arr$[] = superIntfs;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Class superIntf = arr$[i$];
            if(set.contains(superIntf) || superIntf.getAnnotation(cn/sunline/ltts/frw/model/annotation/ComplexType) == null && superIntf.getAnnotation(cn/sunline/ltts/frw/model/annotation/ConfigType) == null)
                continue;
            set.add(superIntf);
            extensions.add(AnnotationConfigManager.get().getComplexTypeFullId(superIntf));
            List superExtensions = getExtensions(superIntf, set);
            if(ModelUtil.isCollectionNotEmpty(superExtensions))
                extensions.addAll(superExtensions);
        }

        return extensions;
    }

    public static final SchemaAnnotationConfigLoader instance = new SchemaAnnotationConfigLoader();

}
