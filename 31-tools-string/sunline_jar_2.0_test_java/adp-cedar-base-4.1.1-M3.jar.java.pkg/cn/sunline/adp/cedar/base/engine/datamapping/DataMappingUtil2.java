// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataMappingUtil2.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.engine.*;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.core.bean.accessor.impl.PropertyAccessorWithOgnlTypeImpl;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.metadata.base.expression.type.MetadataOgnlTypeFactory;
import cn.sunline.adp.metadata.base.util.PropertyUtil;
import cn.sunline.adp.metadata.model.ComplexType;
import cn.sunline.adp.metadata.model.Element;
import cn.sunline.adp.metadata.model.datainterface.DataMapping;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.datamapping:
//            MappingHelper, EngineContext, MappingFactory

public class DataMappingUtil2
{

    public DataMappingUtil2()
    {
    }

    public static Object dataMapping(List srcElmts, List destElmts, DataMapping mapping, Class type, Object src, Object dest, boolean lazymapping, boolean validate, 
            boolean mappingNullField)
    {
        EngineContext.getListener().beginDataMappingProcess(EngineConst.DataMappingUtil202);
        ProfileUtil.start_record("dataMapping.doMapping2");
        Object obj;
        MappingFactory.ComplexTypeMapping map = getMapping(type, srcElmts, destElmts, mapping, lazymapping, validate, mappingNullField);
        cn.sunline.adp.core.expression.enhance.OgnlType ot = MetadataOgnlTypeFactory.get(ModelUtil.getOriginalClass(src.getClass()), srcElmts);
        cn.sunline.adp.core.bean.accessor.PropertyAccessor pa = PropertyUtil.createAccessor(src);
        map.doMapping(new PropertyAccessorWithOgnlTypeImpl(ot, pa), PropertyUtil.createAccessor(dest));
        obj = dest;
        ProfileUtil.end_record("dataMapping.doMapping2");
        EngineContext.getListener().endDataMappingProcess();
        return obj;
        Exception exception;
        exception;
        ProfileUtil.end_record("dataMapping.doMapping2");
        EngineContext.getListener().endDataMappingProcess();
        throw exception;
    }

    public static MappingFactory.ComplexTypeMapping getMapping(Class type, List fromElmts, List toElmts, DataMapping mapping, boolean lazymapping, boolean validate, boolean mappingNullField)
    {
        if(mapping == null)
            mapping = DataMapping.Default;
        MappingHelper.MappingKey key = new MappingHelper.MappingKey(type, fromElmts, toElmts, mapping, lazymapping, validate, mappingNullField);
        MappingFactory.ComplexTypeMapping ret = (MappingFactory.ComplexTypeMapping)mappings.get(key);
        if(ret == null)
        {
            ret = createComplexTypeMapping(key, fromElmts, null, null, null, null, null, mapping != null ? mapping.isMappingByInterfaceDefault().booleanValue() : false);
            mappings.put(key, ret);
        }
        return ret;
    }

    private static MappingFactory.ComplexTypeMapping createComplexTypeMapping(MappingHelper.MappingKey key, List srcType, String srcProp, String destProp, String srcFullPath, String destFullPath, String id, boolean mappingByInterface)
    {
        Map mappings = new LinkedHashMap();
        Iterator iterator = key.destElmts.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Element elmt = (Element)iterator.next();
            MappingHelper helper = new MappingHelper(key, elmt, srcType, destProp, mappingByInterface, srcFullPath != null ? (new StringBuilder()).append(srcFullPath).append(".").append(elmt.getId()).toString() : elmt.getId(), destFullPath != null ? (new StringBuilder()).append(destFullPath).append(".").append(elmt.getId()).toString() : elmt.getId());
            MappingFactory.Mapping m = helper.isComplexType() ? createComplexTypeMapping(helper, elmt) : createSimpleTypeMapping(helper, elmt);
            if(m != null)
                mappings.put(elmt.getId(), m);
        } while(true);
        return new MappingFactory.ComplexTypeMapping(MetadataOgnlTypeFactory.get(key.type, key.destElmts), srcProp, id, mappings, key.lazymapping, false);
    }

    private static MappingFactory.Mapping createSimpleTypeMapping(MappingHelper helper, Element elmt)
    {
        if(StringUtil.isNotEmpty(elmt.getFixedValue()))
            return new MappingFactory.ConstantMapping(helper.destFullPath, elmt.getId(), helper.getFixedValue());
        if(helper.needMapping)
        {
            if(helper.multi)
                return new MappingFactory.SimpleTypeListMapping(helper.elmt, helper.srcField, elmt.getId(), helper.getDefaultValue(), helper.getJavaClass(true), helper.getJavaClass(false), helper.expr, helper.evalOnTop, helper.key.validate);
            else
                return new MappingFactory.SimpleTypeMapping(helper.elmt, helper.getDebugInfo(), helper.srcField, elmt.getId(), helper.getDefaultValue(), helper.getJavaClass(false), helper.expr, helper.evalOnTop, helper.key.validate, helper.key.mappingNullField);
        } else
        {
            return null;
        }
    }

    private static MappingFactory.Mapping createComplexTypeMapping(MappingHelper helper, Element elmt)
    {
        if(helper.needMapping)
        {
            if(helper.getComplexType().getAllElements() == null)
                throw new IllegalArgumentException(String.format(EngineConst.DataMappingUtil203, new Object[] {
                    helper.getComplexType().getFullId()
                }));
            MappingHelper.MappingKey key = new MappingHelper.MappingKey(helper.getJavaClass(false), helper.key.srcElmts, helper.getComplexType().getAllElements(), helper.key.mapping, helper.key.lazymapping, helper.key.validate, helper.key.mappingNullField);
            MappingFactory.ComplexTypeMapping ctm = createComplexTypeMapping(key, helper.srcComplexType != null ? helper.srcComplexType.getAllElements() : null, helper.srcElement == null ? null : helper.srcField, helper.destField, helper.srcFullPath, helper.destFullPath, elmt.getId(), helper.isMappingElementsByInterface());
            if(helper.multi)
                if(helper.srcElement != null && !helper.isSrcMulti)
                    throw new IllegalArgumentException(String.format(EngineConst.DataMappingUtil204, new Object[] {
                        helper.srcField, helper.destField
                    }));
                else
                    return new MappingFactory.ComplexTypeListMapping(helper.srcField, elmt.getId(), helper.getJavaClass(true), ctm.cloneForListMapping(), helper.evalOnTop);
            if(helper.srcElement != null && helper.isSrcMulti)
                throw new IllegalArgumentException(String.format(EngineConst.DataMappingUtil205, new Object[] {
                    helper.srcField, helper.destField
                }));
            else
                return ctm;
        } else
        {
            return null;
        }
    }

    public static void request2response(RequestData request, ResponseData response)
    {
        response.getBody().getOutput().putAll(request.getBody().getOutput());
        response.getBody().getPrinter().putAll(request.getBody().getPrinter());
    }

    public static final Map mappings = new ConcurrentHashMap();

}
