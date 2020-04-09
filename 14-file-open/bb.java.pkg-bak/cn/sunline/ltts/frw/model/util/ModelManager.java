// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelManager.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.frw.model.DefaultModelObjectCreator;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.ClassModelFactory;
import cn.sunline.ltts.frw.model.dm.SimpleTypeModelFactory;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;
import java.util.concurrent.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelConfig, ModelFactoryUtil, ModelConfigUtil, ModelIntroductUtil, 
//            AbstractModelFactory, OdbModelFactory, ModelFilesLoader

public class ModelManager
{
    private static class ConcurrentTaskQueue extends ModelConfigUtil.TaskQueueSupport
    {

        public void addTask(Runnable task)
        {
            e.submit(task);
            ModelManager.BOOT_LOG.debug((new StringBuilder()).append("Task: ").append(e.getCompletedTaskCount()).append("/").append(e.getTaskCount()).toString());
        }

        private final ThreadPoolExecutor e;

        public ConcurrentTaskQueue(ThreadPoolExecutor e)
        {
            this.e = e;
        }
    }


    public ModelManager()
    {
    }

    public static ModelManager get()
    {
        return instance;
    }

    public static ModelManager reset()
    {
        instance = new ModelManager();
        return get();
    }

    public void init(ModelConfig config)
    {
        this.config = config;
        factory = new AbstractModelFactory() {

            final ModelManager this$0;

            
            {
                this$0 = ModelManager.this;
                super();
                getFactories().addAll(Arrays.asList(new ModelFactory[] {
                    ClassModelFactory.INSTANCE, SimpleTypeModelFactory.INSTANCE, new OdbModelFactory(null, new ModelFilesLoader[0])
                }));
            }
        }
;
        ModelFactoryUtil.setModelFactory(factory);
        ModelFactoryUtil.setGlobalModelFactory(factory);
        if(config.getGroups() == null) goto _L2; else goto _L1
_L1:
        Iterator i$;
        List groups = sort(config.getGroups());
        i$ = groups.iterator();
_L3:
        ModelConfig.Group group;
        long start;
        boolean lazyResolve;
        if(!i$.hasNext())
            break; /* Loop/switch isn't completed */
        group = (ModelConfig.Group)i$.next();
        for(Iterator i$ = group.getLoaders().iterator(); i$.hasNext();)
        {
            ModelConfig.ModelLoader loader = (ModelConfig.ModelLoader)i$.next();
            try
            {
                loader.init();
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException(String.format(ModelRtConst.ModelManager11, new Object[] {
                    group.getLongname(), loader.getLongname()
                }), e);
            }
        }

        start = System.currentTimeMillis();
        if(BOOT_LOG.isInfoEnabled())
            BOOT_LOG.info(ModelRtConst.ModelManager01, new Object[] {
                group.getLongname()
            });
        lazyResolve = group.getLazyResolve() != null && group.getLazyResolve().booleanValue();
        DefaultModelObjectCreator.LazyResolve.set(Boolean.valueOf(lazyResolve));
        int count = doLoadOrResolve(group, true, null);
        DefaultModelObjectCreator.LazyResolve.set(Boolean.valueOf(false));
        break MISSING_BLOCK_LABEL_272;
        Exception exception;
        exception;
        DefaultModelObjectCreator.LazyResolve.set(Boolean.valueOf(false));
        throw exception;
        if(BOOT_LOG.isInfoEnabled())
            BOOT_LOG.info(ModelRtConst.ModelManager02, new Object[] {
                Double.valueOf((double)(System.currentTimeMillis() - start) / 1000D), Integer.valueOf(count), group.getLongname()
            });
        if(lazyResolve)
        {
            if(BOOT_LOG.isInfoEnabled())
                BOOT_LOG.info(ModelRtConst.ModelManager03, new Object[] {
                    group.getLongname()
                });
        } else
        {
            start = System.currentTimeMillis();
            if(BOOT_LOG.isInfoEnabled())
                BOOT_LOG.info(ModelRtConst.ModelManager04, new Object[] {
                    group.getLongname()
                });
            Set resolve = new HashSet();
            count = doLoadOrResolve(group, false, resolve);
            if(BOOT_LOG.isInfoEnabled())
                BOOT_LOG.info(ModelRtConst.ModelManager05, new Object[] {
                    Double.valueOf((double)(System.currentTimeMillis() - start) / 1000D), Integer.valueOf(count), group.getLongname()
                });
        }
        if(true) goto _L3; else goto _L2
_L2:
        ModelConfigUtil.clearResolvedSet();
        ModelIntroductUtil.introductProcessor(factory);
        return;
    }

    private List sort(List groups)
    {
        List ret = new ArrayList();
        ret.addAll(groups);
        Collections.sort(ret, new Comparator() {

            public int compare(ModelConfig.Group o1, ModelConfig.Group o2)
            {
                if(o1.getOrder() > o2.getOrder())
                    return 1;
                return o1.getOrder() >= o2.getOrder() ? 0 : -1;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((ModelConfig.Group)obj, (ModelConfig.Group)obj1);
            }

            final ModelManager this$0;

            
            {
                this$0 = ModelManager.this;
                super();
            }
        }
);
        return ret;
    }

    public ModelConfig getNewModelConfig()
    {
        return config;
    }

    private ThreadPoolExecutor createThreadPool(ModelConfig.Group group)
    {
        ThreadPoolExecutor e = (ThreadPoolExecutor)Executors.newFixedThreadPool(group.getLoadThreads().intValue());
        e.setKeepAliveTime(1L, TimeUnit.SECONDS);
        e.allowCoreThreadTimeOut(true);
        return e;
    }

    private int doLoadOrResolve(ModelConfig.Group group, boolean load, Set resolve)
    {
        Integer threads = load ? group.getLoadThreads() : group.getResolveThreads();
        if(threads == null || threads.intValue() <= 1)
        {
            int count = 0;
            for(Iterator i$ = group.getLoaders().iterator(); i$.hasNext();)
            {
                ModelConfig.ModelLoader loader = (ModelConfig.ModelLoader)i$.next();
                ModelConfigUtil.TaskQueue queue = new ModelConfigUtil.SequentialTaskQueue();
                long start = System.currentTimeMillis();
                if(BOOT_LOG.isInfoEnabled())
                    BOOT_LOG.info(ModelRtConst.ModelManager06, new Object[] {
                        load ? ModelRtConst.ModelManager09 : ModelRtConst.ModelManager10, loader.getId(), loader.getLongname()
                    });
                if(load)
                    ModelConfigUtil.load(loader, queue);
                else
                    ModelConfigUtil.resolve(loader, queue);
                if(BOOT_LOG.isInfoEnabled())
                    BOOT_LOG.info(ModelRtConst.ModelManager07, new Object[] {
                        load ? ModelRtConst.ModelManager09 : ModelRtConst.ModelManager10, Double.valueOf((double)(System.currentTimeMillis() - start) / 1000D), Integer.valueOf(queue.getCount()), loader.getId(), loader.getLongname()
                    });
                count += queue.getCount();
            }

            return count;
        }
        ThreadPoolExecutor e = createThreadPool(group);
        ModelConfigUtil.TaskQueue queue = new ConcurrentTaskQueue(e);
        for(Iterator i$ = group.getLoaders().iterator(); i$.hasNext();)
        {
            ModelConfig.ModelLoader loader = (ModelConfig.ModelLoader)i$.next();
            if(load)
                ModelConfigUtil.load(loader, queue);
            else
                ModelConfigUtil.resolve(loader, queue);
        }

        do
        {
            if(e.getActiveCount() <= 0)
                break;
            if(BOOT_LOG.isInfoEnabled())
                BOOT_LOG.info(ModelRtConst.ModelManager08, new Object[] {
                    load ? ModelRtConst.ModelManager09 : ModelRtConst.ModelManager10, Long.valueOf(e.getCompletedTaskCount()), Long.valueOf(e.getTaskCount())
                });
            try
            {
                Thread.sleep(1000L);
                continue;
            }
            catch(InterruptedException e1) { }
            break;
        } while(true);
        e.shutdown();
        return queue.getCount();
    }

    private static final SysLog BOOT_LOG = SysLogUtil.getBootLogger();
    private static ModelManager instance = new ModelManager();
    private ModelConfig config;
    private ModelFactory factory;


}
