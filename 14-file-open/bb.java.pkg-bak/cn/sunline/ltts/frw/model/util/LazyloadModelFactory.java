// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LazyloadModelFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.frw.model.ModelFactory;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            LazyloadableModelFactory, ModelFilesLoader, ModelFactoryUtil

public class LazyloadModelFactory
    implements ModelFactory
{
    private class ModelObjectWithModelFile
        implements ModelObject
    {

        public String getId()
        {
            return mo.getId();
        }

        public ModelObject reload()
        {
            if(lastModified != mf.lastModified())
            {
                lastModified = mf.lastModified();
                mo = cache.parseModelFile(mf);
                ModelFactoryUtil.resolveModelRelation(mo, new ModelFactory[0]);
                if(LazyloadModelFactory.log.isDebugEnabled())
                    LazyloadModelFactory.log.debug((new StringBuilder()).append("lazy load file:").append(mf).toString());
            }
            return mo;
        }

        private static final long serialVersionUID = 0xd7b8e6970c733eacL;
        private ModelObject mo;
        private ModelFile mf;
        private long lastModified;
        final LazyloadModelFactory this$0;



        private ModelObjectWithModelFile()
        {
            this$0 = LazyloadModelFactory.this;
            super();
            lastModified = -1L;
        }

    }


    public transient LazyloadModelFactory(LazyloadableModelFactory backup, Boolean lazyload, Boolean autoreload, ModelFilesLoader loaders[])
    {
        files = new LinkedHashMap();
        cache = backup;
        this.autoreload = autoreload;
        if(loaders != null)
        {
            ModelFilesLoader arr$[] = loaders;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                ModelFilesLoader loader = arr$[i$];
                ModelFile arr$[] = loader.load();
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    ModelFile r = arr$[i$];
                    files.put(r.getFileName(), r);
                    if(lazyload == null || !lazyload.booleanValue())
                        loadAndPut(r);
                }

            }

        }
    }

    public Object getModel(Class type, Object id)
    {
        Object ret = cache.getModel(type, id);
        if(ret == null)
            ret = loadModel(type, id);
        else
        if(ret instanceof ModelObjectWithModelFile)
            ret = getModelObjectReload(ret);
        return ret;
    }

    public List getModels(Class type)
    {
        List ret = cache.getModels(type);
        List list = new ArrayList();
        for(Iterator i$ = ret.iterator(); i$.hasNext();)
        {
            Object t = i$.next();
            if(t instanceof ModelObjectWithModelFile)
                list.add(getModelObjectReload(t));
            else
                list.add(t);
        }

        return list;
    }

    private Object getModelObjectReload(Object t)
    {
        if(autoreload != null && autoreload.booleanValue())
            return ((ModelObjectWithModelFile)t).reload();
        else
            return ((ModelObjectWithModelFile)t).mo;
    }

    private Object loadModel(Class type, Object id)
    {
label0:
        {
            if(id == null)
                break label0;
            Iterator i$ = files.values().iterator();
            String _id;
            ModelObject mo;
            do
            {
                ModelFile mf;
                do
                {
                    if(!i$.hasNext())
                        break label0;
                    mf = (ModelFile)i$.next();
                } while(!match(mf.getFileName(), id));
                _id = String.valueOf(id).replaceAll("\\.", "_");
                mo = loadAndPut(mf).mo;
            } while(!type.isInstance(mo) || !mo.getId().equals(_id));
            return mo;
        }
        return null;
    }

    private boolean match(String filename, Object id)
    {
        String sid = String.valueOf(id);
        if(filename.startsWith(sid))
            return true;
        String ids[] = sid.split("\\.");
        return filename.startsWith(ids[0]);
    }

    private ModelObjectWithModelFile loadAndPut(ModelFile mf)
    {
        ModelObjectWithModelFile ret = new ModelObjectWithModelFile();
        ret.mf = mf;
        ret.reload();
        cache.putModel(ret.mo.getClass(), ret);
        return ret;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/LazyloadModelFactory);
    private LazyloadableModelFactory cache;
    private Boolean autoreload;
    private Map files;



}
