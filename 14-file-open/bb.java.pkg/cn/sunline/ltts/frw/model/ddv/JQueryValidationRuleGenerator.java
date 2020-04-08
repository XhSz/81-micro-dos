// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JQueryValidationRuleGenerator.java

package cn.sunline.ltts.frw.model.ddv;

import cn.sunline.ltts.core.api.model.dm.ComplexType;
import cn.sunline.ltts.core.api.model.dm.Element;
import cn.sunline.ltts.frw.model.dm.RestrictionType;
import cn.sunline.ltts.frw.model.util.ModelUtil;
import freemarker.template.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class JQueryValidationRuleGenerator
{

    public JQueryValidationRuleGenerator()
    {
    }

    public static String generate(ComplexType config)
        throws IOException, TemplateException
    {
        Configuration cfg = new Configuration();
        cfg.setNumberFormat("#");
        cfg.setClassForTemplateLoading(cn/sunline/ltts/frw/model/ddv/JQueryValidationRuleGenerator, (new StringBuilder()).append("/").append(cn/sunline/ltts/frw/model/ddv/JQueryValidationRuleGenerator.getPackage().getName().replace('.', '/')).toString());
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Template temp = cfg.getTemplate("jqueryValidationRule.ftl");
        Map root = new HashMap();
        root.put("config", config);
        root.put("help", new JQueryValidationRuleGenerator());
        java.io.Writer out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

    public static RestrictionType getRestrictionType(Element element)
    {
        return ModelUtil.getRestrictionType(element.getTypeObj());
    }
}
