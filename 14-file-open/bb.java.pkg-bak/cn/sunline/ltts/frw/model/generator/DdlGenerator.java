// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DdlGenerator.java

package cn.sunline.ltts.frw.model.generator;

import cn.sunline.ltts.base.util.StringUtil;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.dm.ElementType;
import cn.sunline.ltts.frw.model.db.*;
import cn.sunline.ltts.frw.model.dm.*;
import cn.sunline.ltts.frw.model.util.ModelUtil;
import freemarker.template.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.generator:
//            ConfigurationFacotry

public class DdlGenerator
{

    public DdlGenerator()
    {
    }

    public static String generateDDL(Schema schema, DbType dbType)
        throws IOException, TemplateException
    {
        return generateDDL(getTables(schema), dbType);
    }

    public static String generateDDL(List tables, DbType dbType)
        throws IOException, TemplateException
    {
        return generateDDL(tables, dbType, false, false, false, false);
    }

    public static List getTables(Schema s)
    {
        List ret = new ArrayList();
        if(s.getTypes() != null)
        {
            Iterator i$ = s.getTypes().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                ElementType elmt = (ElementType)i$.next();
                if(elmt instanceof Table)
                    ret.add((Table)elmt);
            } while(true);
        }
        return ret;
    }

    public static String generateDDL(List tables, DbType dbType, boolean hasDesc, boolean hasFieldList, boolean keyProcess)
        throws IOException, TemplateException
    {
        return generateDDL(tables, dbType, hasDesc, hasFieldList, keyProcess, false);
    }

    public static String generateDDL(List tables, DbType dbType, boolean hasDesc, boolean hasFieldList, boolean keyProcess, boolean haseCommList)
        throws IOException, TemplateException
    {
        Configuration cfg = ConfigurationFacotry.getConfiguration(cn/sunline/ltts/frw/model/generator/DdlGenerator);
        cfg.setNumberFormat("#");
        Template temp = cfg.getTemplate((new StringBuilder()).append("sql/").append(dbType).append(".ftl").toString(), "UTF-8");
        Map root = new HashMap();
        root.put("tables", tables);
        root.put("help", new DdlGenerator());
        root.put("DbType", dbType);
        root.put("hasDesc", Boolean.valueOf(hasDesc));
        root.put("hasFieldList", Boolean.valueOf(hasFieldList));
        root.put("keyProcess", Boolean.valueOf(keyProcess));
        root.put("haseCommList", Boolean.valueOf(haseCommList));
        java.io.Writer out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

    public static String generateDDL(List tables, DbType dbType, boolean hasDesc, boolean hasFieldList, boolean keyProcess, boolean haseCommList, String username)
        throws IOException, TemplateException
    {
        Configuration cfg = ConfigurationFacotry.getConfiguration(cn/sunline/ltts/frw/model/generator/DdlGenerator);
        cfg.setNumberFormat("#");
        Template temp = cfg.getTemplate((new StringBuilder()).append("sql/").append(dbType).append(".ftl").toString(), "UTF-8");
        Map root = new HashMap();
        root.put("tables", tables);
        root.put("help", new DdlGenerator());
        root.put("DbType", dbType);
        root.put("hasDesc", Boolean.valueOf(hasDesc));
        root.put("hasFieldList", Boolean.valueOf(hasFieldList));
        root.put("username", username);
        root.put("keyProcess", Boolean.valueOf(keyProcess));
        root.put("haseCommList", Boolean.valueOf(haseCommList));
        java.io.Writer out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

    public static String generateIndex(List tables, DbType dbType)
        throws IOException, TemplateException
    {
        Configuration cfg = ConfigurationFacotry.getConfiguration(cn/sunline/ltts/frw/model/generator/DdlGenerator);
        cfg.setNumberFormat("#");
        Template temp = cfg.getTemplate("sql/index.ftl", "UTF-8");
        Map root = new HashMap();
        root.put("tables", tables);
        root.put("help", new DdlGenerator());
        root.put("DbType", dbType);
        java.io.Writer out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

    public static String generateSynonym(List tables, DbType dbType, String username)
        throws IOException, TemplateException
    {
        Configuration cfg = ConfigurationFacotry.getConfiguration(cn/sunline/ltts/frw/model/generator/DdlGenerator);
        cfg.setNumberFormat("#");
        Template temp = cfg.getTemplate("sql/synonym.ftl", "UTF-8");
        Map root = new HashMap();
        root.put("tables", tables);
        root.put("help", new DdlGenerator());
        root.put("DbType", dbType);
        root.put("username", username);
        java.io.Writer out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

    public static String generateSequence(List tables, DbType dbType)
        throws IOException, TemplateException
    {
        Configuration cfg = ConfigurationFacotry.getConfiguration(cn/sunline/ltts/frw/model/generator/DdlGenerator);
        cfg.setNumberFormat("#");
        Template temp = cfg.getTemplate("sql/sequence.ftl", "UTF-8");
        Map root = new HashMap();
        root.put("tables", tables);
        root.put("help", new DdlGenerator());
        root.put("DbType", dbType);
        java.io.Writer out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

    public static String getDbType(ElementType type)
    {
        if(type == null)
            return null;
        if(type instanceof SimpleType)
            return ((SimpleType)type).getId();
        if(type instanceof RestrictionType)
        {
            RestrictionType typeObj = (RestrictionType)type;
            if(typeObj.getBaseTypeObj() instanceof SimpleType)
                return getDbType(((ElementType) ((SimpleType)typeObj.getBaseTypeObj())));
            if(typeObj.getBaseTypeObj() instanceof RestrictionType)
                return getDbType(((ElementType) ((RestrictionType)typeObj.getBaseTypeObj())));
        } else
        if(type instanceof SubEnum)
            if(((SubEnum)type).getOwner() instanceof RestrictionType)
                return getDbType(((ElementType) ((RestrictionType)((SubEnum)type).getOwner())));
            else
                return getDbType(((ElementType) ((RestrictionType)((SubEnum)type).getBaseTypeObj())));
        throw new IllegalArgumentException((new StringBuilder()).append("\u672A\u77E5\u5143\u7D20\u7C7B\u578B\uFF1A").append(type).toString());
    }

    public String getFieldLengthString(Field field, Map userTypeDefaultLength)
    {
        if(field == null)
            return null;
        Integer fieldLength = null;
        try
        {
            fieldLength = field.getFieldLength();
        }
        catch(Exception e)
        {
            log.error(e.getMessage(), e, new Object[0]);
        }
        String ret = null;
        if(isEffect(fieldLength))
        {
            StringBuffer lengthString = new StringBuffer();
            lengthString.append("(");
            lengthString.append(fieldLength);
            Integer fieldFraction = field.getFieldFraction();
            if(isEffect(fieldFraction))
                lengthString.append(",").append(fieldFraction);
            lengthString.append(")");
            ret = lengthString.toString();
        }
        if(ret == null)
            return (String)userTypeDefaultLength.get(field.getType());
        else
            return ret;
    }

    public static boolean isEffect(Integer i)
    {
        return i != null && i.intValue() != 0;
    }

    public static boolean hasDefindIndexAfter(Table table)
    {
        if(table == null || StringUtil.isEmpty(table.getIndex()))
            return false;
        for(Iterator i$ = table.getIndex().iterator(); i$.hasNext();)
        {
            DbIndex index = (DbIndex)i$.next();
            if(index.getType() == cn.sunline.ltts.frw.model.db.DbIndex.Index.unique)
                return true;
        }

        return false;
    }

    public static boolean hasDefindPrimaryKeyAfter(Table table)
    {
        if(table == null || StringUtil.isEmpty(table.getIndex()))
            return false;
        for(Iterator i$ = table.getIndex().iterator(); i$.hasNext();)
        {
            DbIndex index = (DbIndex)i$.next();
            if(index.getType() == cn.sunline.ltts.frw.model.db.DbIndex.Index.primarykey)
                return true;
        }

        return false;
    }

    public static boolean isExists(Object obj)
    {
        return StringUtil.isExists(obj);
    }

    public static boolean hasEnumeration(ElementType type)
    {
        return ModelUtil.hasEnumeration(type);
    }

    public static boolean isEmpty(Object s)
    {
        return StringUtil.isEmpty(s);
    }

    public static List getEnumerationList(ElementType typeObj)
    {
        return ModelUtil.getEnumerationList(typeObj);
    }

    public static String getMysqlDbType(String dbType, String fieldLength)
    {
        if(!"varchar".equals(dbType))
            return dbType;
        if(fieldLength == null || fieldLength.length() == 0 || fieldLength.indexOf("(") == -1 || fieldLength.indexOf(",") != -1)
            return dbType;
        try
        {
            int length = Integer.parseInt(fieldLength.substring(1, fieldLength.length() - 1));
            if(length >= 1000)
                return "text";
        }
        catch(Exception exception) { }
        return dbType;
    }

    public static boolean hasDefinePrimaryKeys(Table table, String keyStr)
    {
        List parentKeys = ModelUtil.getParentKeys(table);
        if(StringUtil.isNotEmpty(keyStr))
        {
            String keys[] = keyStr.split("[\\s,]+");
            if(keys.length > parentKeys.size())
                return true;
        }
        return false;
    }

    public static String getKeys(Table table)
    {
        String defaultIndexFields;
label0:
        {
            defaultIndexFields = null;
            if(table.getIndex() == null)
                break label0;
            Iterator i$ = table.getIndex().iterator();
            DbIndex index;
            do
            {
                if(!i$.hasNext())
                    break label0;
                index = (DbIndex)i$.next();
            } while(index.getType() != cn.sunline.ltts.frw.model.db.DbIndex.Index.primarykey && index.getType() != cn.sunline.ltts.frw.model.db.DbIndex.Index.unique);
            defaultIndexFields = index.getFields();
        }
        if(StringUtil.isEmpty(defaultIndexFields))
            return "";
        StringBuffer sb = new StringBuffer();
        boolean isStart = true;
        String arr$[] = defaultIndexFields.split("[\\s,]+");
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String indexField = arr$[i$];
            if(isStart)
            {
                sb.append(indexField);
                isStart = false;
            } else
            {
                sb.append(", ").append(indexField);
            }
        }

        return sb.toString();
    }

    public static List getPrimaryKey(Table table)
    {
        String defaultIndexFields;
label0:
        {
            defaultIndexFields = null;
            if(table.getIndex() == null)
                break label0;
            Iterator i$ = table.getIndex().iterator();
            DbIndex index;
            do
            {
                if(!i$.hasNext())
                    break label0;
                index = (DbIndex)i$.next();
            } while(index.getType() != cn.sunline.ltts.frw.model.db.DbIndex.Index.primarykey);
            defaultIndexFields = index.getFields();
        }
        List sb = new ArrayList();
        if(StringUtil.isEmpty(defaultIndexFields))
            return sb;
        String arr$[] = defaultIndexFields.split("[\\s,]+");
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String indexField = arr$[i$];
            sb.add(indexField);
        }

        return sb;
    }

    public static List getTableIndex(Table table, boolean hasKeys)
    {
        if(hasKeys)
            return table.getIndex();
        List ret = new ArrayList();
        boolean isFound = false;
        for(Iterator i$ = table.getIndex().iterator(); i$.hasNext();)
        {
            DbIndex index = (DbIndex)i$.next();
            if(!isFound && (index.getType() == cn.sunline.ltts.frw.model.db.DbIndex.Index.primarykey || index.getType() == cn.sunline.ltts.frw.model.db.DbIndex.Index.unique))
                isFound = true;
            else
                ret.add(index);
        }

        return ret;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/generator/DdlGenerator);

}
