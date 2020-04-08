// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelConfigUtil.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.odb.*;
import cn.sunline.ltts.base.odb.impl.GlobalOdbManager;
import cn.sunline.ltts.base.odb.impl.LazyloadOdbManager;
import cn.sunline.ltts.base.util.ConcurrentHashSet;
import cn.sunline.ltts.core.api.dao.EntityDao;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.frw.model.DefaultModelObjectCreator;
import cn.sunline.ltts.frw.model.dm.ComplexTypeSearch;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelRelationResolverFactory, ModelConfig

public class ModelConfigUtil
{
    public static class Loader
    {

        private boolean isExitOnError()
        {
            return exitOnError;
        }

        public void load(final TaskQueue queue, final boolean lazyload)
        {
            if(odbm == null)
            {
                return;
            } else
            {
                final boolean listenerMode = isIndexOnCreation ? loader.registerLoadListener(new EntityLoadListener() {

                    public void afterLoad(Object entity)
                    {
                        OdbManager odbm = Loader.this.odbm.getFactory().getOdbManager(entity.getClass());
                        if(odbm != null)
                            odbm.insertOnlyEntity(entity);
                    }

                    final Loader this$0;

                
                {
                    this$0 = Loader.this;
                    super();
                }
                }
) : false;
                queue.addTask(new Runnable() {

                    public void run()
                    {
                        DefaultModelObjectCreator.LazyLoad.set(Boolean.valueOf(lazyload));
                        List list = loader.loadAll();
                        DefaultModelObjectCreator.LazyLoad.set(Boolean.valueOf(false));
                        break MISSING_BLOCK_LABEL_52;
                        Exception exception;
                        exception;
                        DefaultModelObjectCreator.LazyLoad.set(Boolean.valueOf(false));
                        throw exception;
                        queue.addCount(list.size());
                        if(!listenerMode)
                        {
                            final Object t;
                            for(Iterator i$ = list.iterator(); i$.hasNext(); queue.addTask(new Runnable() {

                    public void run()
                    {
                        try
                        {
                            odbm.insert(t);
                        }
                        catch(Exception e)
                        {
                            String message = String.format(ModelRtConst.ModelConfigUtil07, new Object[] {
                                t, loader.getType(), e.getMessage()
                            });
                            if(isExitOnError())
                                throw new IllegalArgumentException(message, e);
                            ModelConfigUtil.BOOT_LOGGER.warn(message, e, new Object[0]);
                        }
                    }

                    final Object val$t;
                    final _cls2 this$1;

                        
                        {
                            this$1 = _cls2.this;
                            t = obj;
                            super();
                        }
                }
))
                                t = i$.next();

                        }
                        return;
                    }

                    final boolean val$lazyload;
                    final TaskQueue val$queue;
                    final boolean val$listenerMode;
                    final Loader this$0;

                
                {
                    this$0 = Loader.this;
                    lazyload = flag;
                    queue = taskqueue;
                    listenerMode = flag1;
                    super();
                }
                }
);
                return;
            }
        }

        public void resolve(TaskQueue queue)
        {
            if(odbm == null)
                return;
            List list = odbm.selectAll();
            queue.addCount(list.size());
            final Object t;
            for(Iterator i$ = list.iterator(); i$.hasNext(); queue.addTask(new Runnable() {

        public void run()
        {
            if(ModelConfigUtil.resolvedSet.contains(t))
                return;
            try
            {
                ModelConfigUtil.resolvedSet.add(t);
                ModelRelationResolverFactory.getModelRelationResolver().resolve(t, new HashSet());
            }
            catch(Exception e)
            {
                String message = (new StringBuilder()).append(String.format(ModelRtConst.ModelConfigUtil08, new Object[] {
                    t
                })).append(e.getMessage()).toString();
                if(isExitOnError())
                    throw new IllegalArgumentException(message, e);
                ModelConfigUtil.BOOT_LOGGER.warn(message, e, new Object[0]);
            }
            return;
        }

        final Object val$t;
        final Loader this$0;

                
                {
                    this$0 = Loader.this;
                    t = obj;
                    super();
                }
    }
))
                t = i$.next();

        }

        public OdbManager odbm;
        public EntityLoader loader;
        public boolean exitOnError;
        public boolean isIndexOnCreation;


        public Loader(OdbManager odbm, EntityLoader loader, boolean exitOnError, boolean isIndexOnCreation)
        {
            this.odbm = odbm;
            this.loader = loader;
            this.exitOnError = exitOnError;
            this.isIndexOnCreation = isIndexOnCreation;
        }
    }

    public static class SequentialTaskQueue extends TaskQueueSupport
    {

        public void addTask(Runnable task)
        {
            index++;
            task.run();
            if(index % 100 == 0 && ModelConfigUtil.BOOT_LOGGER.isDebugEnabled())
                ModelConfigUtil.BOOT_LOGGER.debug((new StringBuilder()).append("Process: ").append(index).append("/").append(getCount()).toString());
        }

        int index;

        public SequentialTaskQueue()
        {
            index = 0;
        }
    }

    public static abstract class TaskQueueSupport
        implements TaskQueue
    {

        public int addCount(int count)
        {
            this.count += count;
            return this.count;
        }

        public int getCount()
        {
            return count;
        }

        private int count;

        public TaskQueueSupport()
        {
            count = 0;
        }
    }

    public static interface TaskQueue
    {

        public abstract void addTask(Runnable runnable);

        public abstract int addCount(int i);

        public abstract int getCount();
    }

    public static class LoaderHelper
    {

        public EntityLoader loader;
        public EntityDao dao;
        public ChangeChecker changeChecker;

        public LoaderHelper()
        {
        }
    }


    public ModelConfigUtil()
    {
    }

    public static void clearResolvedSet()
    {
        resolvedSet.clear();
    }

    public static void load(ModelConfig.ModelLoader config, TaskQueue queue)
    {
        if(!config.isLazyloadSetted())
        {
            for(Iterator i$ = config.loaders.iterator(); i$.hasNext();)
            {
                Loader loader = (Loader)i$.next();
                try
                {
                    loader.load(queue, config.isLazyloadSetted());
                }
                catch(Exception e)
                {
                    String message = String.format(ModelRtConst.ModelConfigUtil05, new Object[] {
                        config.getId(), config.getLongname(), e.getMessage()
                    });
                    if(config.isExitOnError())
                        throw new IllegalArgumentException(message, e);
                    BOOT_LOGGER.warn(message, e, new Object[0]);
                }
            }

        }
    }

    public static void resolve(ModelConfig.ModelLoader config, TaskQueue queue)
    {
        if(!config.isLazyloadSetted())
        {
            for(Iterator i$ = config.loaders.iterator(); i$.hasNext();)
            {
                Loader loader = (Loader)i$.next();
                try
                {
                    loader.resolve(queue);
                }
                catch(Exception e)
                {
                    String message = String.format(ModelRtConst.ModelConfigUtil06, new Object[] {
                        config.getId(), config.getLongname(), e.getMessage()
                    });
                    if(config.isExitOnError())
                        throw new IllegalArgumentException(message, e);
                    BOOT_LOGGER.warn(message, e, new Object[0]);
                }
            }

        }
    }

    protected static OdbManager createOdbManager(ModelConfig.ModelLoader modelLoader, ComplexTypeSearch ctype, Class type, EntityLoader entityLoader, ChangeChecker changeChecker, boolean reload2)
    {
        if(!modelLoader.isCacheSetted())
            return null;
        IndexParser ip;
        if(ctype != null)
            ip = OdbFactory.get().getIndexParser(ctype);
        else
            ip = OdbFactory.get().getIndexParser(type);
        if(ip == null)
            throw new IllegalArgumentException(String.format(ModelRtConst.ModelConfigUtil09, new Object[] {
                type.getName()
            }));
        OdbManager odbm;
        if(OdbFactory.get().hasOdbManager(ip.getType()))
            odbm = OdbFactory.get().getOdbManager(ip.getType());
        else
            odbm = new GlobalOdbManager(OdbFactory.get(), ip, reload2);
        if(modelLoader.isLazyloadSetted() || modelLoader.isReloadSetted())
            odbm = new LazyloadOdbManager(type, odbm, entityLoader, changeChecker, ModelRelationResolverFactory.getModelRelationResolver(), modelLoader.isLazyloadSetted(), modelLoader.isReloadSetted());
        OdbFactory.get().addOdbManager(odbm);
        return odbm;
    }

    private static final SysLog BOOT_LOGGER = SysLogUtil.getBootLogger();
    private static volatile Set resolvedSet = new ConcurrentHashSet();



}
