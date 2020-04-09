// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnnotationEntityOdbEntityDao.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.dao.EntityDaoSupport;
import cn.sunline.ltts.base.odb.EntityLoadListener;
import cn.sunline.ltts.base.odb.EntityLoader;
import cn.sunline.ltts.base.util.ClassUtils;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.core.api.model.dm.ElementType;
import cn.sunline.ltts.frw.model.annotation.Index;
import cn.sunline.ltts.frw.model.dm.Schema;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            AnnotationConfigManager

public class AnnotationEntityOdbEntityDao extends EntityDaoSupport
    implements EntityLoader
{

    public AnnotationEntityOdbEntityDao(Class type, String classes[])
    {
        this.type = type;
        this.classes = classes;
    }

    public Class getType()
    {
        return type;
    }

    public List reload(Object en)
    {
        return null;
    }

    public transient List loadByKey(Object key[])
    {
        return loadAll();
    }

    public transient List loadByIndex(Class index, Object args[])
    {
        return loadAll();
    }

    public List loadAll()
    {
        List ret;
        Map schemaMap;
        Set loadedClass;
        String arr$[];
        int len$;
        int i$;
        ret = new ArrayList();
        schemaMap = new HashMap();
        loadedClass = new HashSet();
        arr$ = classes;
        len$ = arr$.length;
        i$ = 0;
_L3:
        if(i$ >= len$) goto _L2; else goto _L1
_L1:
        String className = arr$[i$];
        Class clss;
        Class innerClasses[];
        Class arr$[];
        int len$;
        int i$;
        Class innerClass;
        try
        {
            clss = ClassUtils.classForName(className);
            if(loadedClass.contains(clss))
                continue; /* Loop/switch isn't completed */
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationEntityOdbEntityDao01, new Object[] {
                className, e.getMessage()
            }), e);
        }
        loadedClass.add(clss);
        if(log.isDebugEnabled())
            log.debug((new StringBuilder()).append("load file:").append(className).toString());
        load(schemaMap, ret, clss);
        innerClasses = clss.getDeclaredClasses();
        if(innerClasses == null || innerClasses.length == 0)
            continue; /* Loop/switch isn't completed */
        arr$ = innerClasses;
        len$ = arr$.length;
        for(i$ = 0; i$ < len$; i$++)
        {
            innerClass = arr$[i$];
            if(!loadedClass.contains(clss))
            {
                loadedClass.add(clss);
                load(schemaMap, ret, innerClass);
            }
        }

        i$++;
          goto _L3
_L2:
        return ret;
    }

    public boolean registerLoadListener(EntityLoadListener listener)
    {
        listeners.add(listener);
        return false;
    }

    private Schema getSchema(Map schemaMap, List ret, Class clss)
    {
        String schemaId = AnnotationConfigManager.get().getSchemaId(clss);
        Schema schema = null;
        if(schemaMap.containsKey(schemaId))
        {
            schema = (Schema)schemaMap.get(schemaId);
        } else
        {
            schema = AnnotationConfigManager.get().getSchema(clss);
            schemaMap.put(schemaId, schema);
            ret.add(schema);
        }
        return schema;
    }

    private void load(Map schemaMap, List ret, Class clss)
    {
        ModelObject model = AnnotationConfigManager.get().load(clss);
        if(model instanceof ElementType)
        {
            Schema schema = getSchema(schemaMap, ret, clss);
            if(schema != null)
            {
                ((ElementType)model).setOwner(schema);
                schema.getTypes().add((ElementType)model);
            }
        } else
        if(model instanceof Schema)
        {
            Schema schema = (Schema)model;
            if(schemaMap.containsKey(schema.getId()))
                return;
            schemaMap.put(schema.getId(), schema);
            ret.add(model);
        } else
        {
            ret.add(model);
        }
    }

    public List selectAll()
    {
        return loadAll();
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/AnnotationEntityOdbEntityDao);
    private final Class type;
    private final String classes[];
    private final List listeners = new ArrayList();

}
