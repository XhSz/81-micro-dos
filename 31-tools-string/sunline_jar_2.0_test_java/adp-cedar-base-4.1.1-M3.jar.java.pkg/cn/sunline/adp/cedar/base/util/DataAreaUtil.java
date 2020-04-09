// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataAreaUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.metadata.model.*;
import cn.sunline.adp.metadata.model.datainterface.*;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import cn.sunline.edsp.base.util.convert.EnumUtils;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.util:
//            TypeConvertUtil

public class DataAreaUtil
{

    public DataAreaUtil()
    {
    }

    private static String processDefultValue(Map map, DataInterfaceElement element)
    {
        String ret = null;
        Object data = map.get(element.getId());
        try
        {
            if(StringUtil.isNotEmpty(element.getFixedValue()))
            {
                data = TypeConvertUtil.convert(element.getFixedValue(), element.getElementJavaClass());
                map.put(element.getId(), data);
            } else
            if(StringUtil.isEmpty(data) && StringUtil.isNotEmpty(element.getDefaultValue()))
            {
                data = TypeConvertUtil.convert(element.getDefaultValue(), element.getElementJavaClass());
                map.put(element.getId(), data);
            }
        }
        catch(Exception e)
        {
            ret = String.format(EngineConst.DataAreaUtil01, new Object[] {
                element.getId()
            });
            log.error(EngineConst.DataAreaUtil01, e, new Object[] {
                element.getId()
            });
            return ret;
        }
        return ret;
    }

    public static String checkDataValidity(DataArea dataArea, DataInterface config)
    {
        if(config.getInput() == null)
            return null;
        String rtMessage = null;
        Map dataContext = dataArea.getInput();
        Iterator iterator = config.getInput().getElements().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            DataInterfaceElement element = (DataInterfaceElement)iterator.next();
            rtMessage = processDefultValue(dataContext, element);
            if(rtMessage != null)
                break;
            Object data = dataContext.get(element.getId());
            rtMessage = checkDataInterfaceElement(element, data);
        } while(rtMessage == null);
        return rtMessage;
    }

    private static String checkDataInterfaceElement(DataInterfaceElement element, Object data)
    {
        String rtMessage;
label0:
        {
            rtMessage = null;
            if(element instanceof DataInterfaceField)
            {
                if(data == null || (data instanceof String))
                {
                    rtMessage = checkDataInterfaceField((DataInterfaceField)element, (String)data);
                    if(rtMessage != null)
                        rtMessage = (new StringBuilder()).append(element.getId()).append("(").append(element.getLongname()).append(")").append(rtMessage).toString();
                }
                break label0;
            }
            if(!(element instanceof DataInterfaceFields))
                break label0;
            if(element.getMulti() != null && element.getMulti().booleanValue())
            {
                if(data instanceof List)
                {
                    List list = (List)data;
                    int index = 0;
                    Iterator iterator2 = list.iterator();
                    do
                    {
                        if(!iterator2.hasNext())
                            break;
                        Map map = (Map)iterator2.next();
                        Iterator iterator3 = ((DataInterfaceFields)element).getFields().iterator();
                        do
                        {
                            if(!iterator3.hasNext())
                                break;
                            DataInterfaceElement e = (DataInterfaceElement)iterator3.next();
                            rtMessage = processDefultValue(map, e);
                            if(rtMessage != null)
                                break;
                            rtMessage = checkDataInterfaceElement(e, map.get(e.getId()));
                        } while(rtMessage == null);
                        if(rtMessage != null)
                        {
                            rtMessage = (new StringBuilder()).append(element.getId()).append("[").append(index).append("].").append(rtMessage).toString();
                            break;
                        }
                        index++;
                    } while(true);
                }
                break label0;
            }
            if(data instanceof Map)
            {
                Map dataMap = (Map)data;
                Iterator iterator1 = ((DataInterfaceFields)element).getFields().iterator();
                do
                {
                    if(!iterator1.hasNext())
                        break label0;
                    DataInterfaceElement e = (DataInterfaceElement)iterator1.next();
                    rtMessage = processDefultValue(dataMap, e);
                    if(rtMessage != null)
                        break label0;
                    rtMessage = checkDataInterfaceElement(e, dataMap.get(e.getId()));
                } while(rtMessage == null);
                rtMessage = (new StringBuilder()).append(element.getId()).append(".").append(rtMessage).toString();
            } else
            {
                if(element.getRequired() != null && element.getRequired().booleanValue())
                    return (new StringBuilder()).append(element.getId()).append(String.format(EngineConst.DataAreaUtil02, new Object[] {
                        element.getLongname()
                    })).toString();
                Iterator iterator = ((DataInterfaceFields)element).getFields().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    DataInterfaceElement e = (DataInterfaceElement)iterator.next();
                    rtMessage = checkDataInterfaceElement(e, null);
                } while(rtMessage == null);
            }
        }
        return rtMessage;
    }

    private static String checkDataInterfaceField(DataInterfaceField field, String data)
    {
        if(field.getRequired() != null && field.getRequired().booleanValue() && (data == null || data.trim().length() == 0))
            return EngineConst.DataAreaUtil03;
        if(data == null || data.trim().length() == 0)
            return null;
        if((field.getTypeObj() instanceof RestrictionType) || (field.getTypeObj() instanceof SubEnum))
        {
            RestrictionType rt = ModelUtil.getRestrictionType(field.getTypeObj());
            if(rt.getMinLength() != null && data.trim().length() < rt.getMinLength().intValue())
                return String.format(EngineConst.DataAreaUtil04, new Object[] {
                    rt.getMinLength()
                });
            if(rt.getMaxLength() != null && data.trim().length() > rt.getMaxLength().intValue())
                return String.format(EngineConst.DataAreaUtil05, new Object[] {
                    rt.getMaxLength()
                });
            if(rt.getFractionDigits() != null && rt.getFractionDigits().intValue() != 0 && data.indexOf(".") != -1 && data.trim().substring(data.indexOf(".") + 1).trim().length() > rt.getFractionDigits().intValue())
                return String.format(EngineConst.DataAreaUtil06, new Object[] {
                    rt.getFractionDigits()
                });
            if(rt.getEnumerations() != null && rt.getEnumerations().size() != 0)
            {
                Class clazz = field.getTypeObj().getJavaClass();
                if(clazz.isEnum() || !EnumUtils.isInEnum(clazz, data))
                    return String.format(EngineConst.DataAreaUtil07, new Object[] {
                        data, field.getTypeObj().getFullId()
                    });
            }
        }
        return null;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/util/DataAreaUtil);

}
