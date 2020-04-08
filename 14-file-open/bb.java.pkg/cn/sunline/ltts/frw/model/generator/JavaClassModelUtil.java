// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaClassModelUtil.java

package cn.sunline.ltts.frw.model.generator;

import cn.sunline.common.util.StringUtil;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.frw.model.db.Table;
import cn.sunline.ltts.frw.model.dm.Schema;
import cn.sunline.ltts.frw.model.report.Report;

public class JavaClassModelUtil
{
    public static class JavaClassModel
    {

        public String getPackage()
        {
            return packag;
        }

        public void setPackage(String packag)
        {
            this.packag = packag;
        }

        public String getClassName()
        {
            return className;
        }

        public void setClassName(String className)
        {
            this.className = className;
        }

        public String getJavaContent()
        {
            return javaContent;
        }

        public void setJavaContent(String javaContent)
        {
            this.javaContent = javaContent;
        }

        private String packag;
        private String className;
        private String javaContent;

        public JavaClassModel(String packag, String className)
        {
            this.packag = StringUtil.isEmpty(packag) ? "cn.sunline.ltts" : packag;
            this.className = className;
            if(StringUtil.isEmpty(this.className))
                return;
            if(this.className.length() > this.packag.length() && this.className.indexOf(this.packag) != -1)
                this.className = this.className.substring(this.packag.length() + 1);
        }
    }


    public JavaClassModelUtil()
    {
    }

    public static JavaClassModel getJavaClassModel(ModelObject model)
    {
        if(model == null)
            return null;
        JavaClassModel ret = null;
        if(model instanceof Report)
        {
            Report si = (Report)model;
            ret = new JavaClassModel(si.getPackag(), (new StringBuilder()).append(si.getId()).append("ReportProcessor").toString());
        } else
        if(model instanceof Table)
        {
            Table table = (Table)model;
            if(table.getParam() == null || !table.getParam().booleanValue())
                return null;
            ret = new JavaClassModel(((Schema)table.getOwner()).getPackage(), (new StringBuilder()).append(table.getId()).append("ParamProcessor").toString());
        }
        return ret;
    }
}
