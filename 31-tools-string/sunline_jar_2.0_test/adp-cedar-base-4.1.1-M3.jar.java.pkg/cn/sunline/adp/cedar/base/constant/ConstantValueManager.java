// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConstantValueManager.java

package cn.sunline.adp.cedar.base.constant;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConstantValueManager
{

    private ConstantValueManager()
    {
        String fileName = System.getProperty("constant_mappings_extension_file", "constant_mappings_extension.properties");
        InputStream is = cn/sunline/adp/cedar/base/constant/ConstantValueManager.getResourceAsStream((new StringBuilder()).append("/").append(fileName).toString());
        if(is == null)
        {
            constantValueMappings = null;
            logger.warn("can't find constant value mappings file: [%s]", new Object[] {
                fileName
            });
        } else
        {
            constantValueMappings = new Properties();
            try
            {
                constantValueMappings.load(is);
            }
            catch(IOException ignore)
            {
                logger.error("Error to load custom constant value mappings config!", ignore, new Object[0]);
            }
        }
    }

    public static ConstantValueManager get()
    {
        return instance;
    }

    public String getValue(String key, String defaultValue)
    {
        if(constantValueMappings == null)
            return defaultValue;
        else
            return constantValueMappings.getProperty(key, defaultValue);
    }

    private static final SysLog logger = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/constant/ConstantValueManager);
    private final Properties constantValueMappings;
    private static final ConstantValueManager instance = new ConstantValueManager();
    private static final String CONSTANT_VALUE_MAPPINGS_CONF = "constant_mappings_extension.properties";

}
