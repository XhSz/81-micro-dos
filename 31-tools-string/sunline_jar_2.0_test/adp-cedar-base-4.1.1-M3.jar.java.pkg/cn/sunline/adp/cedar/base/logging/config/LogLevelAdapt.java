// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogLevelAdapt.java

package cn.sunline.adp.cedar.base.logging.config;

import cn.sunline.adp.cedar.base.constant.TspBaseConstantDef;
import java.util.*;
import org.apache.logging.log4j.Level;

public class LogLevelAdapt
{

    public LogLevelAdapt(String levelDefine, String levelArray[])
    {
        this.levelDefine = levelDefine;
        Set levelSet = new HashSet();
        if(levelArray != null && levelArray.length > 0)
        {
            String as[] = levelArray;
            int i = as.length;
            for(int j = 0; j < i; j++)
            {
                String level = as[j];
                String trueName = (String)nameMappings.get(level);
                if(trueName != null)
                    level = trueName;
                Level l = Level.getLevel(level.toUpperCase());
                if(l == null)
                    throw new RuntimeException(String.format(cn.sunline.adp.cedar.base.constant.TspBaseConstantDef.TBConst.C011(), new Object[] {
                        levelDefine, level
                    }));
                levelSet.add(l);
            }

        }
        levels = Collections.unmodifiableSet(levelSet);
    }

    public String getLevelDefine()
    {
        return levelDefine;
    }

    public Set getLevels()
    {
        return levels;
    }

    public boolean match(Level level)
    {
        return levels.contains(level);
    }

    private static final Map nameMappings;
    private final String levelDefine;
    private final Set levels;

    static 
    {
        nameMappings = new HashMap();
        nameMappings.put("sqlInfo", "SQLIF");
        nameMappings.put("service", "SVC");
        nameMappings.put("sql", "SQL");
        nameMappings.put("profile", "PROF");
        nameMappings.put("dataArea", "DAREA");
        nameMappings.put("method", "METHD");
        nameMappings.put("techMethod", "TECH");
        nameMappings.put("parm", "PARM");
    }
}
