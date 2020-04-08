// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfigurationFacotry.java

package cn.sunline.ltts.frw.model.generator;

import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import freemarker.log.Logger;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurationFacotry
{

    public ConfigurationFacotry()
    {
    }

    public static Configuration getConfiguration(Class clazz)
    {
        Configuration cfg = (Configuration)cfgMap.get(clazz);
        if(cfg != null)
        {
            return cfg;
        } else
        {
            cfg = new Configuration();
            cfg.setClassForTemplateLoading(clazz, (new StringBuilder()).append("/").append(clazz.getPackage().getName().replace('.', '/')).toString());
            cfg.setObjectWrapper(ObjectWrapper.DEFAULT_WRAPPER);
            cfgMap.put(clazz, cfg);
            return cfg;
        }
    }

    private static final SysLog log;
    public static final String ENCODING = "UTF-8";
    private static Map cfgMap = new ConcurrentHashMap();

    static 
    {
        log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/generator/ConfigurationFacotry);
        try
        {
            Logger.selectLoggerLibrary(3);
        }
        catch(ClassNotFoundException e)
        {
            log.error("ConfigurationFacotry ClassNotFoundException error!", e, new Object[0]);
        }
    }
}
