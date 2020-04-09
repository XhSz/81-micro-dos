// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExcelUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.constant.ApiConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.edsp.base.lang.EncString;
import cn.sunline.edsp.base.lang.Options;
import cn.sunline.edsp.base.lang.Range;
import cn.sunline.edsp.base.lang.options.DefaultOptions;
import cn.sunline.edsp.base.lang.options.StringOptions;
import cn.sunline.edsp.base.lang.range.DefaultRange;
import cn.sunline.edsp.base.util.convert.ConvertUtil;
import cn.sunline.edsp.base.util.convert.EnumUtils;
import cn.sunline.edsp.base.util.lang.StringUtil;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil
{

    public ExcelUtil()
    {
    }

    public static File getFile(String fileName, String filePath)
    {
        File file = new File(filePath);
        if(!file.exists())
            file.mkdirs();
        if(!fileName.endsWith(".xls"))
            fileName = (new StringBuilder()).append(fileName).append(".xls").toString();
        return new File(file, fileName);
    }

    public static void writeExcelFile(String filePath, String file, Map datas)
    {
        if(datas == null)
            return;
        WritableWorkbook workbook = getWritableWorkbook(getFile(file, filePath));
        if(workbook == null)
            return;
        int index = 0;
        for(Iterator iterator = datas.keySet().iterator(); iterator.hasNext();)
        {
            String key = (String)iterator.next();
            WritableSheet wSheet = workbook.createSheet(key, index);
            String headNames[] = {
                cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil01(), cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil02(), cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil03(), cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil04(), cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil05(), cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil06(), cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil07(), cn.sunline.adp.cedar.base.constant.ApiConst.AConst.ExcelUtil08()
            };
            for(int i = 0; i < headNames.length; i++)
                try
                {
                    wSheet.addCell(new Label(i, 0, headNames[i]));
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e);
                }

            List data = (List)datas.get(key);
            for(int rowIndex = 0; rowIndex < data.size(); rowIndex++)
            {
                for(int cellIndex = 0; cellIndex < headNames.length; cellIndex++)
                    try
                    {
                        String str = (String)((Map)data.get(rowIndex)).get(headNames[cellIndex]);
                        wSheet.addCell(new Label(cellIndex, rowIndex + 1, str));
                    }
                    catch(Exception e)
                    {
                        throw new RuntimeException(e);
                    }

            }

            index++;
        }

        try
        {
            workbook.write();
            workbook.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static WritableWorkbook getWritableWorkbook(File file)
    {
        try
        {
            return Workbook.createWorkbook(file);
        }
        catch(IOException e)
        {
            log.error(String.format(BaseConst.ExcelUtil01, new Object[] {
                file.getAbsolutePath()
            }), e, new Object[0]);
        }
        return null;
    }

    public static Map convertExcel2Beans(File file, List beans)
    {
        Map ret = new HashMap();
        try
        {
            Workbook book = Workbook.getWorkbook(new FileInputStream(file));
            Sheet sheets[] = book.getSheets();
            if(sheets.length != beans.size())
                throw new RuntimeException(BaseConst.ExcelUtil02);
            for(int s = 0; s < sheets.length; s++)
            {
                List data = new ArrayList();
                Cell fieldNames[] = sheets[s].getRow(0);
                for(int i = 1; i < sheets[s].getRows(); i++)
                {
                    Object originObj = ((Class)beans.get(s)).newInstance();
                    Cell fieldValue[] = sheets[s].getRow(i);
                    for(int j = 0; j < fieldValue.length; j++)
                    {
                        PropertyDescriptor pd = new PropertyDescriptor(((Class)beans.get(s)).getDeclaredFields()[j].getName(), (Class)beans.get(s));
                        Method m = pd.getWriteMethod();
                        m.invoke(originObj, new Object[] {
                            convert(fieldValue[j].getContents(), ((Class)beans.get(s)).getDeclaredFields()[j].getType())
                        });
                    }

                    data.add(originObj);
                }

                ret.put(sheets[s].getName(), data);
            }

        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        return ret;
    }

    public static Object convert(Object value, Class type)
        throws IllegalArgumentException
    {
        if(type == null)
            throw new IllegalArgumentException("type is null.");
        if(value == null)
            return null;
        if(type.isAssignableFrom(value.getClass()))
            return value;
        ProfileUtil.start_record("bean.convert");
        Object obj = __convert(value, type);
        ProfileUtil.end_record("bean.convert");
        return obj;
        Exception exception;
        exception;
        ProfileUtil.end_record("bean.convert");
        throw exception;
    }

    private static Object __convert(Object value, Class type)
        throws IllegalArgumentException
    {
        if(!type.isAssignableFrom(value.getClass()) && StringUtil.isEmpty(value))
            return null;
        if(!value.getClass().isEnum() && type.isEnum())
            return EnumUtils.toEnum(type, value);
        if(value.getClass().isEnum() && !type.isEnum())
        {
            Object obj;
            try
            {
                obj = EnumUtils.getEnumValue(value);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException(e);
            }
            return convert(obj, type);
        }
        if((cn/sunline/edsp/base/lang/Range.isAssignableFrom(type) || cn/sunline/edsp/base/lang/Options.isAssignableFrom(type)) && (value instanceof String))
            return convertCollection((String)value, type);
        if(type.isArray() && (value instanceof String))
            return toArray((String)value, type.getComponentType());
        if(java/util/List.isAssignableFrom(type) && value.getClass().isArray())
        {
            String array[] = (String[])(String[])value;
            DefaultOptions so = new StringOptions();
            so.addAll(Arrays.asList(array));
            return so;
        }
        if(value.getClass().isEnum() && type.isEnum() && !value.getClass().isAssignableFrom(type))
            throw new IllegalArgumentException(String.format(BaseConst.ExcelUtil03, new Object[] {
                value.toString(), value.getClass().getName(), type.getName()
            }));
        if(java/lang/String.equals(type))
            return value.toString();
        if(cn/sunline/edsp/base/lang/EncString.equals(type))
            return new EncString(value.toString());
        if(java/math/BigDecimal.equals(type))
            if(value instanceof BigDecimal)
                return value;
            else
                return new BigDecimal(value.toString());
        if(java/lang/Boolean.equals(type) || Boolean.TYPE.equals(type))
            return toBoolean(value);
        if(java/lang/Byte.equals(type) || Byte.TYPE.equals(type))
            return ConvertUtil.toByte(value);
        if(java/util/Calendar.equals(type))
            return ConvertUtil.toCalendar(value, "yyyy-MM-dd");
        if(java/awt/Color.equals(type))
            return ConvertUtil.toColor(value);
        if(java/util/Date.equals(type))
            return ConvertUtil.toDate(value);
        if(java/lang/Double.equals(type))
            return ConvertUtil.toDouble(value);
        if(java/lang/Float.equals(type) || Float.TYPE.equals(type))
            return ConvertUtil.toFloat(value);
        if(java/lang/Integer.equals(type) || Integer.TYPE.equals(type))
            return ConvertUtil.toInteger(value);
        if(java/util/Locale.equals(type))
            return ConvertUtil.toLocale(value);
        if(java/lang/Long.equals(type) || Long.TYPE.equals(type))
            return ConvertUtil.toLong(value);
        if(java/lang/Short.equals(type) || Short.TYPE.equals(type))
            return ConvertUtil.toShort(value);
        if(java/sql/Date.equals(type))
            return ConvertUtil.toSqlDate(value);
        if(java/sql/Time.equals(type))
            return ConvertUtil.toSqlTime(value);
        if(java/sql/Timestamp.equals(type))
            return ConvertUtil.toTimestamp(value);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Can't convert value '").append(value).append("' to ").append(type).toString());
    }

    public static Boolean toBoolean(Object value)
    {
        if(value == null)
            return null;
        if(value instanceof Boolean)
            return (Boolean)value;
        if(value instanceof String)
            return !"false".equalsIgnoreCase((String)value) && !"0".equalsIgnoreCase((String)value) ? Boolean.TRUE : Boolean.FALSE;
        else
            return null;
    }

    public static Object toArray(String s, Class type)
    {
        List ss = splitString(s);
        Object ret = Array.newInstance(type, ss.size());
        for(int i = 0; i < ss.size(); i++)
            Array.set(ret, i, convert(ss.get(i), type));

        return ret;
    }

    private static List splitString(String s)
    {
        List ret = StringUtil.split(s);
        if(ret == null)
            return Collections.emptyList();
        else
            return ret;
    }

    public static Object convertCollection(String s, Class type)
    {
        if(s == null || s.trim().length() == 0)
            return null;
        if(cn/sunline/edsp/base/lang/Range.isAssignableFrom(type))
        {
            List ss = splitString(s);
            DefaultRange ret = (DefaultRange)ModelObjectCreatorUtil.getModelObjectCreator().create(type);
            Class genericType = getGenericType(type);
            if(ss.size() == 1)
                ret.setMin(convert(ss.get(0), genericType));
            else
            if(ss.size() > 1)
            {
                ret.setMin(convert(ss.get(0), genericType));
                ret.setMax(convert(ss.get(1), genericType));
            } else
            {
                throw new IllegalArgumentException(String.format(BaseConst.ExcelUtil04, new Object[] {
                    s
                }));
            }
            return ret;
        }
        if(cn/sunline/edsp/base/lang/Options.isAssignableFrom(type))
        {
            DefaultOptions ret = (DefaultOptions)ModelObjectCreatorUtil.getModelObjectCreator().create(type);
            Class genericType = getGenericType(type);
            List list = new ArrayList();
            String s1;
            for(Iterator iterator = splitString(s).iterator(); iterator.hasNext(); list.add(convert(s1, genericType)))
                s1 = (String)iterator.next();

            ret.setValues(list);
            return ret;
        } else
        {
            return ConvertUtil.convert(s, type);
        }
    }

    private static Class getGenericType(Class type)
    {
        Class genericType = ReflectionUtil.getGenericClass(type);
        if(genericType == null)
            throw new IllegalArgumentException(String.format(BaseConst.ExcelUtil05, new Object[] {
                type
            }));
        else
            return genericType;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/util/ExcelUtil);
    public static final String fileSuffix = ".xls";

}
