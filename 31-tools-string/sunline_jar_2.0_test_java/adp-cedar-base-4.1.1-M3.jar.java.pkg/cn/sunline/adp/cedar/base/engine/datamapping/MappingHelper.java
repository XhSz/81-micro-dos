// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MappingHelper.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.util.CommonExpressionUtil;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.core.expression.type.OgnlTypeFactory;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.metadata.model.*;
import cn.sunline.adp.metadata.model.datainterface.DataMapping;
import cn.sunline.adp.metadata.model.datainterface.DataMappingDetail;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.util.convert.ConvertUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.datamapping:
//            DataMappingUtil, MappingFactory

public class MappingHelper
{
    public static class MappingKey
    {

        public int hashCode()
        {
            ProfileUtil.start_record("dataMapping.hashCode");
            int i;
            int prime = 31;
            int result = 1;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            result = 31 * result + (destElmts != null ? destElmts.hashCode() : 0);
            result = 31 * result + (lazymapping ? 1231 : '\u04D5');
            result = 31 * result + (validate ? 1231 : '\u04D5');
            result = 31 * result + (mappingNullField ? 1231 : '\u04D5');
            result = 31 * result + (mapping != null ? mapping.hashCode() : 0);
            result = 31 * result + (srcElmts != null ? srcElmts.hashCode() : 0);
            i = result;
            ProfileUtil.end_record("dataMapping.hashCode");
            return i;
            Exception exception;
            exception;
            ProfileUtil.end_record("dataMapping.hashCode");
            throw exception;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            MappingKey other = (MappingKey)obj;
            if(type != other.type)
                return false;
            if(destElmts != other.destElmts)
                return false;
            if(lazymapping != other.lazymapping)
                return false;
            if(validate != other.validate)
                return false;
            if(mappingNullField != other.mappingNullField)
                return false;
            if(mapping != other.mapping)
                return false;
            return srcElmts == other.srcElmts;
        }

        public final Class type;
        public final List srcElmts;
        public final List destElmts;
        public final DataMapping mapping;
        public final boolean lazymapping;
        public final boolean validate;
        public final boolean mappingNullField;

        public MappingKey(Class type, List srcElmts, List destElmts, DataMapping mapping, boolean lazymapping, boolean validate, boolean mappingNullField)
        {
            this.type = type;
            this.srcElmts = srcElmts;
            this.destElmts = destElmts;
            this.mapping = mapping;
            this.lazymapping = lazymapping;
            this.validate = validate;
            this.mappingNullField = mappingNullField;
        }
    }


    public MappingHelper(MappingKey key, Element elmt, List srcType, String destProp, boolean mappingByInterface, String srcFullPath, String destFullPath)
    {
        this.key = key;
        this.elmt = elmt;
        this.destProp = destProp;
        this.destFullPath = destFullPath;
        multi = elmt.getMulti() != null && elmt.getMulti().booleanValue();
        destField = StringUtil.isEmpty(destProp) ? elmt.getId() : (new StringBuilder()).append(destProp).append(".").append(elmt.getId()).toString();
        DataMappingDetail mappingDetail = (DataMappingDetail)key.mapping.getDestMapping2().get(destField);
        hasMapping = mappingDetail != null && mappingDetail.getSrc() != null;
        evalOnTop = hasMapping && (mappingDetail.getMappingOnTop() == null || mappingDetail.getMappingOnTop().booleanValue());
        if(hasMapping)
        {
            srcField = mappingDetail.getSrc();
            this.srcFullPath = srcField;
            expr = DataMappingUtil.isExpression(srcField);
            srcElement = expr ? null : getElement(list2map(evalOnTop ? key.srcElmts : srcType), srcField);
            if(!expr && srcElement == null)
                log.warn(EngineConst.MappingHelper01, new Object[] {
                    srcField
                });
        } else
        {
            this.srcFullPath = srcFullPath;
            expr = false;
            if(mappingByInterface)
            {
                srcElement = getElement(list2map(srcType), elmt.getId());
                if(srcElement != null)
                    srcField = elmt.getId();
                else
                    srcField = null;
            } else
            {
                srcElement = null;
                srcField = null;
            }
        }
        isSrcMulti = srcElement != null && srcElement.getMulti() != null && srcElement.getMulti().booleanValue();
        srcComplexType = srcElement == null ? null : ModelUtil.getComplexType(srcElement.getTypeObj());
        needMapping = needMapping(mappingByInterface);
    }

    public String getDebugInfo()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(srcFullPath);
        sb.append("->");
        sb.append(destFullPath);
        return sb.toString().replaceAll("\"", "\\\\\"");
    }

    public boolean isMappingElementsByInterface()
    {
        if(srcElement == null)
            return false;
        Boolean ret = null;
        if(hasMapping)
            ret = ((DataMappingDetail)key.mapping.getDestMapping2().get(destField)).getMappingByInterface();
        if(ret == null)
            ret = key.mapping.isMappingByInterfaceDefault();
        return ret.booleanValue();
    }

    public boolean isComplexType()
    {
        return ModelUtil.getComplexType(elmt.getTypeObj()) != null;
    }

    public ComplexType getComplexType()
    {
        return ModelUtil.getComplexType(elmt.getTypeObj());
    }

    public Class getJavaClass(boolean listType)
    {
        if(listType)
            return elmt.getElementJavaClass();
        ElementType elmtType = elmt.getTypeObj();
        if(elmtType == null)
            throw new RuntimeException(String.format(EngineConst.MappingHelper02, new Object[] {
                elmt.getId()
            }));
        else
            return elmtType.getJavaClass();
    }

    public Object getDefaultValue()
    {
        return evalExpr(elmt.getDefaultValue());
    }

    public Object getFixedValue()
    {
        return evalExpr(elmt.getFixedValue());
    }

    private Object evalExpr(String expr)
    {
        if(expr == null)
            return null;
        Object value = CommonExpressionUtil.evalExpressionAndReturn(expr, OgnlTypeFactory.getNull(), empty);
        if(multi)
        {
            List values = (List)ModelObjectCreatorUtil.getModelObjectCreator().create(getJavaClass(true));
            values.addAll(MappingFactory.obj2list(value, getJavaClass(false)));
            return values;
        } else
        {
            return ConvertUtil.convert(value, getJavaClass(false));
        }
    }

    private boolean hasAnyPrefixMapping()
    {
        if(key.mapping.getDetails() == null)
            return false;
        for(Iterator iterator = key.mapping.getDetails().iterator(); iterator.hasNext();)
        {
            DataMappingDetail dest = (DataMappingDetail)iterator.next();
            if(dest.getDest().startsWith((new StringBuilder()).append(destField).append(".").toString()))
                return true;
        }

        return false;
    }

    private boolean needMapping(boolean mappingByInterface)
    {
        boolean needMapping = hasMapping || mappingByInterface;
        if(!expr && srcElement == null)
            needMapping = false;
        if(isComplexType())
        {
            if(expr)
                throw new IllegalArgumentException(String.format(EngineConst.MappingHelper03, new Object[] {
                    srcField
                }));
            if(srcElement != null)
            {
                ComplexType srcct = ModelUtil.getComplexType(srcElement.getTypeObj());
                if(srcct == null)
                    throw new IllegalArgumentException(String.format(EngineConst.MappingHelper04, new Object[] {
                        srcField, destField
                    }));
            }
            if(!needMapping && key.mapping != null)
                needMapping = hasAnyPrefixMapping();
        } else
        if(!needMapping && StringUtil.isNotEmpty(elmt.getDefaultValue()))
            needMapping = true;
        return needMapping;
    }

    private static Element getElement(Map src, String path)
    {
        int index = path.indexOf(".");
        if(index == -1)
            return (Element)src.get(path);
        Element e = (Element)src.get(path.substring(0, index));
        if(e != null)
        {
            ComplexType elmtType = ModelUtil.getComplexType(e.getTypeObj());
            if(elmtType == null)
                return null;
            else
                return getElement(list2map(elmtType.getAllElements()), path.substring(index + 1));
        } else
        {
            return null;
        }
    }

    public static Map list2map(List list)
    {
        Map ret = new LinkedHashMap();
        if(list != null)
        {
            Element e;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); ret.put(e.getId(), e))
                e = (Element)iterator.next();

        }
        return ret;
    }

    private static SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/datamapping/MappingHelper);
    private static Map empty = new HashMap();
    public final MappingKey key;
    public final Element elmt;
    private final String destProp;
    public final boolean multi;
    public final String destField;
    public final String srcField;
    public final boolean hasMapping;
    public final boolean evalOnTop;
    public final boolean expr;
    public final boolean needMapping;
    public final Element srcElement;
    public final ComplexType srcComplexType;
    public final boolean isSrcMulti;
    public final String srcFullPath;
    public final String destFullPath;

}
