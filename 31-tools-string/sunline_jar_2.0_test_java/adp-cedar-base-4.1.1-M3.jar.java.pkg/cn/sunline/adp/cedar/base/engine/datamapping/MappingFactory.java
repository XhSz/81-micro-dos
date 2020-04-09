// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MappingFactory.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.constant.TspEngineConstantDef;
import cn.sunline.adp.cedar.base.engine.BaseProfileValidator;
import cn.sunline.adp.cedar.base.engine.ProcessEventListener;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.util.CommonExpressionUtil;
import cn.sunline.adp.cedar.base.util.EncStringConvertUtil;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.core.bean.accessor.PropertyAccessor;
import cn.sunline.adp.core.bean.accessor.impl.*;
import cn.sunline.adp.core.exception.AdpBusinessException;
import cn.sunline.adp.core.expression.enhance.OgnlType;
import cn.sunline.adp.core.profile.ProfileSwitcher;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.metadata.base.util.PropertyUtil;
import cn.sunline.adp.metadata.model.Element;
import cn.sunline.adp.metadata.model.RestrictionType;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.lang.EncString;
import cn.sunline.edsp.base.util.convert.ConvertUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import cn.sunline.edsp.base.util.test.AssertUtil;
import java.math.BigDecimal;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.datamapping:
//            EngineContext

public class MappingFactory
{
    public static class ComplexTypeListMapping
        implements Mapping
    {

        public void doMapping(PropertyAccessorWithOgnlType src, PropertyAccessor dest)
        {
            List destList;
            OgnlType valueType;
            Object value;
            destList = (List)dest.getNestedProperty(destProp);
            if(destList == null)
            {
                destList = (List)ModelObjectCreatorUtil.getModelObjectCreator().create(listType);
                dest.setNestedProperty(destProp, destList);
            }
            valueType = src.getType() != null ? src.getType().getPropertyType(srcProp) : null;
            value = MappingFactory.getSrcFieldValue(src, srcProp, false, evalOnTop);
            if(!(value instanceof List)) goto _L2; else goto _L1
_L1:
            List values;
            int i;
            values = (List)value;
            i = 0;
_L4:
            Object val;
            if(i >= values.size())
                break; /* Loop/switch isn't completed */
            val = i >= destList.size() ? null : destList.get(i);
            if(val == null)
            {
                val = mapping.createBean();
                destList.add(val);
            }
            EngineContext.getListener().beginDataMappingProcess(String.format(EngineConst.MappingFactory07, new Object[] {
                Integer.valueOf(i)
            }));
            mapping.doMapping(new PropertyAccessorWithOgnlTypeImpl(valueType, PropertyUtil.createAccessor(values.get(i))), PropertyUtil.createAccessor(val));
            EngineContext.getListener().endDataMappingProcess();
            break MISSING_BLOCK_LABEL_251;
            Exception exception;
            exception;
            EngineContext.getListener().endDataMappingProcess();
            throw exception;
            i++;
            if(true) goto _L4; else goto _L3
_L2:
            if(StringUtil.isNotEmpty(value) && !"0".equals(value))
                throw new IllegalArgumentException(String.format(EngineConst.MappingFactory08, new Object[] {
                    srcProp, destProp, value
                }));
_L3:
        }

        public String getSrcProp()
        {
            return srcProp;
        }

        public String getDestProp()
        {
            return destProp;
        }

        private final String srcProp;
        private final String destProp;
        private final Class listType;
        private ComplexTypeMapping mapping;
        private final boolean evalOnTop;

        public ComplexTypeListMapping(String srcProp, String destProp, Class listType, ComplexTypeMapping mapping, boolean evalOnTop)
        {
            this.srcProp = srcProp;
            this.destProp = destProp;
            this.listType = listType;
            this.mapping = mapping;
            this.evalOnTop = evalOnTop;
        }
    }

    public static class ComplexTypeMapping
        implements Mapping
    {

        public ComplexTypeMapping cloneForListMapping()
        {
            return new ComplexTypeMapping(type, null, null, mappings, lazymapping, evalOnTop);
        }

        public Object createBean()
        {
            return ModelObjectCreatorUtil.getModelObjectCreator().create(type.getType());
        }

        private PropertyAccessorWithOgnlType getRealSrcAccessor(PropertyAccessorWithOgnlType src)
        {
            PropertyAccessorWithOgnlType src1 = src;
            if(srcProp != null)
            {
                OgnlType type = src.getType().getPropertyType(srcProp);
                Object value = MappingFactory.getSrcFieldValue(src, srcProp, false, evalOnTop);
                if(value != null)
                {
                    src1 = new PropertyAccessorWithOgnlTypeImpl(type, PropertyUtil.createAccessor(value));
                    src1 = new RootAwarePropertyAccessor(MappingFactory.getRootAccessor(src), src1);
                } else
                {
                    src1 = null;
                }
            }
            return src1;
        }

        public void doMapping(PropertyAccessorWithOgnlType src, PropertyAccessor dest)
        {
            src = getRealSrcAccessor(src);
            if(src == null) goto _L2; else goto _L1
_L1:
            Iterator iterator = mappings.values().iterator();
_L3:
            Mapping m;
            if(!iterator.hasNext())
                break; /* Loop/switch isn't completed */
            m = (Mapping)iterator.next();
            ProfileUtil.start_record("mapping.immediacy");
            try
            {
                if(m instanceof ComplexTypeMapping)
                    ((ComplexTypeMapping)m).__doMapping(src, dest);
                else
                    m.doMapping(src, dest);
            }
            catch(Exception e)
            {
                MappingFactory.log.debug(EngineConst.MappingFactory01, new Object[] {
                    m.getSrcProp(), m.getDestProp(), e.getMessage()
                });
                throw new IllegalArgumentException(e.getMessage(), e);
            }
            ProfileUtil.end_record("mapping.immediacy");
            if(true) goto _L3; else goto _L2
            Exception exception;
            exception;
            ProfileUtil.end_record("mapping.immediacy");
            throw exception;
_L2:
        }

        public boolean isEmpty()
        {
            for(Iterator iterator = mappings.values().iterator(); iterator.hasNext();)
            {
                Mapping m = (Mapping)iterator.next();
                if(m instanceof ComplexTypeMapping)
                {
                    if(!((ComplexTypeMapping)m).isEmpty())
                        return false;
                } else
                {
                    return false;
                }
            }

            return true;
        }

        private void __doMapping(PropertyAccessorWithOgnlType src, PropertyAccessor dest)
        {
            AssertUtil.notNull(destProp);
            Object value = dest.getNestedProperty(destProp);
            if(value == null)
            {
                value = createBean();
                dest.setNestedProperty(destProp, value);
            }
            doMapping(src, PropertyUtil.createAccessor(value));
        }

        public String getSrcProp()
        {
            return srcProp;
        }

        public String getDestProp()
        {
            return destProp;
        }

        private final OgnlType type;
        private final String srcProp;
        private final String destProp;
        private final Map mappings;
        private final boolean lazymapping;
        private final boolean evalOnTop;

        public ComplexTypeMapping(OgnlType type, String srcProp, String destProp, Map mappings, boolean lazymapping, boolean evalOnTop)
        {
            this.type = type;
            this.srcProp = srcProp;
            this.destProp = destProp;
            this.mappings = mappings;
            this.lazymapping = lazymapping;
            this.evalOnTop = evalOnTop;
        }
    }

    public static class SimpleTypeListMapping
        implements Mapping
    {

        public void doMapping(PropertyAccessorWithOgnlType src, PropertyAccessor dest)
        {
            List destList = (List)dest.getNestedProperty(destProp);
            if(destList == null)
            {
                destList = (List)ModelObjectCreatorUtil.getModelObjectCreator().create(type);
                dest.setNestedProperty(destProp, destList);
            }
            Object value = MappingFactory.getSrcFieldValue(src, srcProp, expr, evalOnTop);
            if(value == null)
                value = defaultValue;
            if(validate)
                BaseProfileValidator.checkFieldValidity(destElmt, value);
            destList.addAll(MappingFactory.obj2list(value, elementType));
        }

        public String getSrcProp()
        {
            return srcProp;
        }

        public String getDestProp()
        {
            return destProp;
        }

        private final Element destElmt;
        private final String srcProp;
        private final String destProp;
        private final Object defaultValue;
        private final Class type;
        private final Class elementType;
        private final boolean expr;
        private final boolean evalOnTop;
        private final boolean validate;

        public SimpleTypeListMapping(Element destElmt, String srcProp, String destProp, Object defaultValue, Class type, Class elementType, boolean expr, 
                boolean evalOnTop, boolean validate)
        {
            this.destElmt = destElmt;
            this.srcProp = srcProp;
            this.destProp = destProp;
            this.defaultValue = defaultValue;
            this.type = type;
            this.elementType = elementType;
            this.expr = expr;
            this.evalOnTop = evalOnTop;
            this.validate = validate;
        }
    }

    public static class SimpleTypeMapping
        implements Mapping
    {

        public void doMapping(PropertyAccessorWithOgnlType src, PropertyAccessor dest)
        {
            Object value = MappingFactory.getSrcFieldValue(src, srcProp, expr, evalOnTop);
            if(StringUtil.isBlank(value) && StringUtil.isBlank(dest.getNestedProperty(destProp)) && defaultValue != null)
                value = defaultValue;
            if(value != null && (value instanceof String) && !ProfileSwitcher.get().enablePkgNotTrim)
                value = ((String)value).trim();
            if(validate)
                BaseProfileValidator.checkFieldValidity(destElmt, value);
            if(value != null && type != null)
                try
                {
                    if(cn/sunline/edsp/base/lang/EncString.isAssignableFrom(type))
                        value = EncStringConvertUtil.convert(value, type, destElmt);
                    else
                        value = ConvertUtil.convert(value, type);
                }
                catch(Exception e)
                {
                    throw new AdpBusinessException("0423", String.format(EngineConst.MappingFactory02, new Object[] {
                        destProp, destElmt.getLongname()
                    }), e);
                }
            if(ProfileSwitcher.get().enableDecimalMapping && (value instanceof BigDecimal))
            {
                RestrictionType rt = ModelUtil.getRestrictionType(destElmt.getTypeObj());
                if(rt != null)
                {
                    Integer fd = rt.getFractionDigits();
                    if(fd != null && fd.intValue() != ((BigDecimal)value).scale())
                        if(ProfileSwitcher.get().enableDecimalComplete)
                            try
                            {
                                value = ((BigDecimal)value).setScale(fd.intValue());
                            }
                            catch(ArithmeticException e)
                            {
                                String message = String.format(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C013(), new Object[] {
                                    destElmt.getLongname(), destElmt.getLongname(), destElmt.getId(), ((BigDecimal)value).toPlainString(), rt.getFractionDigits()
                                });
                                throw new AdpBusinessException("0422", message);
                            }
                        else
                        if(fd.intValue() <= ((BigDecimal)value).scale() || !ProfileSwitcher.get().enableSkipErrorDecimalScale)
                        {
                            String message = String.format(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C013(), new Object[] {
                                destElmt.getLongname(), destElmt.getLongname(), destElmt.getId(), ((BigDecimal)value).toPlainString(), rt.getFractionDigits()
                            });
                            throw new AdpBusinessException("0422", message);
                        }
                }
            }
            if(value != null || mappingNullField && dest.getNestedProperty(destProp) == null || ProfileSwitcher.get().forceMappingNullField)
            {
                dest.setNestedProperty(destProp, value);
                EngineContext.getListener().beginDataMappingProcess((new StringBuilder()).append(debugInfo).append("=").append(value).toString());
                EngineContext.getListener().endDataMappingProcess();
            }
        }

        public String getSrcProp()
        {
            return srcProp;
        }

        public String getDestProp()
        {
            return destProp;
        }

        private final Element destElmt;
        private final String debugInfo;
        private final String srcProp;
        private final String destProp;
        private final Object defaultValue;
        private final Class type;
        private final boolean expr;
        private final boolean evalOnTop;
        private final boolean validate;
        private final boolean mappingNullField;

        public SimpleTypeMapping(Element destElmt, String debugInfo, String srcProp, String destProp, Object defaultValue, Class type, boolean expr, 
                boolean evalOnTop, boolean validate, boolean mappingNullField)
        {
            this.destElmt = destElmt;
            this.debugInfo = debugInfo;
            this.srcProp = srcProp;
            this.destProp = destProp;
            this.defaultValue = defaultValue;
            this.type = type;
            this.expr = expr;
            this.evalOnTop = evalOnTop;
            this.validate = validate;
            this.mappingNullField = mappingNullField;
        }
    }

    public static class ConstantMapping
        implements Mapping
    {

        public void doMapping(PropertyAccessorWithOgnlType src, PropertyAccessor dest)
        {
            dest.setNestedProperty(property, value);
            EngineContext.getListener().beginDataMappingProcess((new StringBuilder()).append(debugInfo).append("=").append(value).toString());
            EngineContext.getListener().endDataMappingProcess();
        }

        public String getSrcProp()
        {
            return String.valueOf(value);
        }

        public String getDestProp()
        {
            return property;
        }

        private final String debugInfo;
        private final String property;
        private final Object value;

        public ConstantMapping(String debugInfo, String property, Object value)
        {
            this.debugInfo = debugInfo;
            this.property = property;
            this.value = value;
        }
    }

    public static interface Mapping
    {

        public abstract void doMapping(PropertyAccessorWithOgnlType propertyaccessorwithognltype, PropertyAccessor propertyaccessor);

        public abstract String getSrcProp();

        public abstract String getDestProp();
    }


    public MappingFactory()
    {
    }

    private static Object getSrcFieldValue(PropertyAccessorWithOgnlType src, String srcProp, boolean expr, boolean evalOnTop)
    {
        if(srcProp == null)
            return null;
        if(evalOnTop)
            src = (PropertyAccessorWithOgnlType)getRootAccessor(src);
        Object value = expr ? CommonExpressionUtil.evalExpressionAndReturn(srcProp, src.getType(), src.getWrappedObject()) : src.getNestedProperty(srcProp);
        return value;
    }

    private static PropertyAccessor getRootAccessor(PropertyAccessor src)
    {
        if(src instanceof RootAwarePropertyAccessor)
            return ((RootAwarePropertyAccessor)src).getRoot();
        else
            return src;
    }

    public static List obj2list(Object value, Class type)
    {
        if(value instanceof List)
        {
            List ret = new ArrayList();
            Object o;
            for(Iterator iterator = ((List)value).iterator(); iterator.hasNext(); ret.add(ConvertUtil.convert(o, type)))
                o = iterator.next();

            return ret;
        }
        if(value instanceof String)
        {
            String s = (String)value;
            String ss[] = null;
            if(s.indexOf(',') != -1)
                ss = PropertyUtil.split(s, ',', false);
            else
                ss = PropertyUtil.split(s, ' ', false);
            List ret = new ArrayList();
            String as[] = ss;
            int i = as.length;
            for(int j = 0; j < i; j++)
            {
                String s1 = as[j];
                Object val = type != null ? ConvertUtil.convert(s1, type) : ((Object) (s1));
                ret.add(val);
            }

            return ret;
        }
        if(value != null)
            throw new IllegalArgumentException(String.format(EngineConst.MappingFactory15, new Object[] {
                value
            }));
        else
            return Collections.emptyList();
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/datamapping/MappingFactory);




}
