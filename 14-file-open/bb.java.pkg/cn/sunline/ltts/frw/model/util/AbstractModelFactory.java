// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractModelFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.frw.model.ModelFactory;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ShortcutException, LazyloadableModelFactory, ModelFactoryUtil

public class AbstractModelFactory
    implements ModelFactory, LazyloadableModelFactory
{

    public AbstractModelFactory()
    {
        modelsByTypeAndId = ((Map) (isWeakHashMap ? ((Map) (new WeakHashMap())) : ((Map) (new HashMap()))));
        secondCache = ((Map) (isWeakHashMap ? ((Map) (new WeakHashMap())) : ((Map) (new HashMap()))));
        factories = new ArrayList();
    }

    protected String[] getModelIds(ModelObject m)
    {
        return getModelIds(m.getClass(), m);
    }

    protected String[] getModelIds(Class clazz, ModelObject m)
    {
        List ret = new ArrayList();
        if(clazz.getName().contains("$$EnhancerByCGLIB") || clazz.getName().contains("_$$_javassist"))
            clazz = clazz.getSuperclass();
        ret.add((new StringBuilder()).append(clazz.getName()).append("-").append(m.getId()).toString());
        while(clazz != java/lang/Object) 
        {
            Class arr$[] = clazz.getInterfaces();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Class c = arr$[i$];
                ret.add((new StringBuilder()).append(c.getName()).append("-").append(m.getId()).toString());
            }

            clazz = clazz.getSuperclass();
            ret.add((new StringBuilder()).append(clazz.getName()).append("-").append(m.getId()).toString());
        }
        return (String[])ret.toArray(new String[0]);
    }

    public void putModel(ModelObject m)
    {
        if(m == null)
        {
            return;
        } else
        {
            putModel(m.getClass(), m);
            return;
        }
    }

    public void putModel(Class clazz, ModelObject m)
    {
        if(m == null)
            return;
        String ids[] = getModelIds(clazz, m);
        modelsByTypeAndId.put(ids[0], m);
        for(int i = 1; i < ids.length; i++)
            secondCache.put(ids[i], m);

    }

    protected void removeModel(ModelObject m)
    {
        if(m == null)
            return;
        String ids[] = getModelIds(m);
        modelsByTypeAndId.remove(ids[0]);
        for(int i = 1; i < ids.length; i++)
            secondCache.remove(ids[i]);

    }

    public Object getModel(Class type, Object id)
    {
        Iterator i$;
        if(id == null)
            return null;
        i$ = getFactories().iterator();
_L2:
        ModelFactory mf;
        if(!i$.hasNext())
            break; /* Loop/switch isn't completed */
        mf = (ModelFactory)i$.next();
        Object t = mf.getModel(type, id);
        if(t != null)
            return t;
        continue; /* Loop/switch isn't completed */
        ShortcutException e;
        e;
        break; /* Loop/switch isn't completed */
        if(true) goto _L2; else goto _L1
_L1:
        Object ret;
        Iterator i$;
        String key = (new StringBuilder()).append(type.getName()).append("-").append(id).toString();
        ret = modelsByTypeAndId.get(key);
        if(ret == null)
            ret = secondCache.get(key);
        if(ret != null)
            break MISSING_BLOCK_LABEL_203;
        i$ = modelsByTypeAndId.values().iterator();
_L4:
        Object o;
        do
        {
            if(!i$.hasNext())
                break MISSING_BLOCK_LABEL_203;
            o = i$.next();
        } while(!(o instanceof ModelFactory));
        ret = ((ModelFactory)o).getModel(type, id);
        if(ret != null)
            return ret;
        if(true) goto _L4; else goto _L3
_L3:
        ShortcutException e;
        e;
        return ret;
    }

    public List getModels(Class type)
    {
        Set ret = new HashSet();
        Iterator i$ = modelsByTypeAndId.values().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object o = i$.next();
            if(type.isAssignableFrom(o.getClass()))
                ret.add(o);
            if(o instanceof ModelFactory)
            {
                List list = ((ModelFactory)o).getModels(type);
                if(list != null)
                    ret.addAll(list);
            }
        } while(true);
        i$ = getFactories().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            ModelFactory mf = (ModelFactory)i$.next();
            List list = mf.getModels(type);
            if(list != null)
                ret.addAll(list);
        } while(true);
        return new ArrayList(ret);
    }

    public ModelObject parseModelFile(ModelFile file)
    {
        return ModelFactoryUtil.parse(file);
    }

    protected List getFactories()
    {
        return factories;
    }

    protected void addFactory(ModelFactory mf)
    {
        factories.add(mf);
    }

    protected void addFactories(List mf)
    {
        factories.addAll(mf);
    }

    public static boolean isWeakHashMap = false;
    protected Map modelsByTypeAndId;
    protected Map secondCache;
    private List factories;

}
