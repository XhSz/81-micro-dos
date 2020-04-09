// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelConfig.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.dao.*;
import cn.sunline.ltts.base.expression.ExpressionEvaluatorFactory;
import cn.sunline.ltts.base.odb.OdbFactory;
import cn.sunline.ltts.base.odb.OdbManager;
import cn.sunline.ltts.core.api.dao.EntityDao;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.OwnerAware;
import cn.sunline.ltts.core.api.model.dm.ComplexType;
import cn.sunline.ltts.core.api.util.ModelFullId;
import cn.sunline.ltts.core.api.util.ModelParent;
import cn.sunline.ltts.frw.model.annotation.Index;
import cn.sunline.ltts.frw.model.dm.ComplexTypeSearch;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelUtil, ModelConfigUtil, ModelClassManager

public class ModelConfig
    implements ModelFullId
{
    private static class DBModelLoaderEntityDaoCreator
        implements EntityDaoCreator
    {

        public boolean isSupport(Class type)
        {
            ComplexType ctype = OdbFactory.getComplexType(type);
            return ctype != null && ModelUtil.hasEntityDaoLoader(ctype) && isSupport(ctype);
        }

        public boolean isSupport(ComplexType ctype)
        {
            Set excludes = loader.getExcludeSet();
            if(excludes.contains(ctype.getFullId()))
                return false;
            if(loader.getTag() != null)
            {
                String arr$[] = loader.getTag();
                int len$ = arr$.length;
label0:
                for(int i$ = 0; i$ < len$; i$++)
                {
                    String tag = arr$[i$];
                    if(tag.equalsIgnoreCase(ctype.getFullId()))
                        return true;
                    if(ctype.getTags() == null)
                        continue;
                    String arr$[] = ctype.getTags();
                    int len$ = arr$.length;
                    int i$ = 0;
                    do
                    {
                        if(i$ >= len$)
                            continue label0;
                        String store = arr$[i$];
                        if(excludes.contains(store))
                            return false;
                        if(tag.equalsIgnoreCase(store))
                            return true;
                        i$++;
                    } while(true);
                }

            }
            return false;
        }

        public EntityDao createEntityDao(Class type)
        {
            ComplexTypeSearch ctype = OdbFactory.getComplexType(type);
            return createEntityDao(ctype);
        }

        public EntityDao createEntityDao(ComplexTypeSearch ctype)
        {
            return createEntityDao(ctype, ctype.getJavaClass());
        }

        private EntityDao createEntityDao(ComplexTypeSearch ctype, Class type)
        {
            ModelConfigUtil.LoaderHelper dao = loader.createHelper(ctype, type);
            OdbManager odbm = ModelConfigUtil.createOdbManager(loader, ctype, type, dao.loader, dao.changeChecker, true);
            return ((EntityDao) (odbm == null ? dao.dao : new OdbEntityDao(dao.dao, odbm)));
        }

        private final DBModelLoader loader;

        public DBModelLoaderEntityDaoCreator(DBModelLoader loader)
        {
            this.loader = loader;
        }
    }

    public static abstract class DBModelLoader extends ModelLoader
    {

        protected ModelConfigUtil.Loader createEntityLoader(ComplexTypeSearch ctype, Class type)
        {
            if(DefaultEntityDaoFactory.get().hasEntityDao(type))
            {
                log.error(ModelRtConst.ModelConfig01, new Object[] {
                    ctype.getFullId()
                });
                return null;
            } else
            {
                ModelConfigUtil.LoaderHelper dao = createHelper(ctype, type);
                OdbManager odbm = ModelConfigUtil.createOdbManager(this, ctype, type, dao.loader, dao.changeChecker, true);
                dao.dao = ((EntityDao) (odbm == null ? dao.dao : ((EntityDao) (new OdbEntityDao(dao.dao, odbm)))));
                dao.dao = DefaultEntityDaoFactory.get().addEntityDao(dao.dao);
                return new ModelConfigUtil.Loader(odbm, dao.loader, isExitOnError(), isIndexOnCreation());
            }
        }

        protected abstract ModelConfigUtil.LoaderHelper createHelper(ComplexType complextype, Class class1);

        protected void addLoader(ModelConfigUtil.Loader loader)
        {
            loaders.add(loader);
        }

        public void init()
        {
            if(!isLazyloadSetted())
            {
                String arr$[] = tag;
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    String store = arr$[i$];
                    if(!getExcludeSet().contains(store))
                    {
                        List list = OdbFactory.get().getOdbManager(cn/sunline/ltts/frw/model/dm/ComplexTypeSearch).selectListByIndex(cn/sunline/ltts/core/api/model/dm/ComplexType$TagsIndex, new Object[] {
                            store
                        });
                        initLoaders(store, list);
                        list = OdbFactory.get().getOdbManager(cn/sunline/ltts/frw/model/dm/ComplexTypeSearch).selectListByIndex(cn/sunline/ltts/frw/model/annotation/Index$PrimaryKey, new Object[] {
                            store
                        });
                        initLoaders(store, list);
                    }
                }

            } else
            {
                DefaultEntityDaoFactory.get().addEntityDaoCreator(new DBModelLoaderEntityDaoCreator(this));
            }
        }

        private void initLoaders(String tags, List list)
        {
            Set excludes = getExcludeSet();
            if(list != null)
            {
                Iterator i$ = list.iterator();
label0:
                do
                {
                    if(!i$.hasNext())
                        break;
                    ComplexTypeSearch ct = (ComplexTypeSearch)i$.next();
                    if(!ModelUtil.hasEntityDaoLoader(ct) || excludes.contains(ct.getFullId()))
                        continue;
                    if(ct.getTags() != null)
                    {
                        String arr$[] = ct.getTags();
                        int len$ = arr$.length;
                        for(int i$ = 0; i$ < len$; i$++)
                        {
                            String tag = arr$[i$];
                            if(excludes.contains(tag))
                                continue label0;
                        }

                    }
                    try
                    {
                        ModelConfigUtil.Loader loader = createEntityLoader(ct, ct.getJavaClass());
                        if(loader == null)
                            log.warn(ModelRtConst.ModelConfig02, new Object[] {
                                ct.getFullId(), getId()
                            });
                        else
                            loaders.add(loader);
                    }
                    catch(Exception e)
                    {
                        String message = String.format(ModelRtConst.ModelConfig04, new Object[] {
                            ct.getFullId(), tags, getId(), e.getMessage()
                        });
                        if(isExitOnError())
                            throw new IllegalArgumentException(message, e);
                        log.error(ModelRtConst.ModelConfig03, e, new Object[] {
                            ct.getFullId(), tags, getId(), e.getMessage()
                        });
                    }
                } while(true);
            }
        }

        public String[] getTag()
        {
            return tag;
        }

        public void setTag(String tag[])
        {
            this.tag = tag;
        }

        public String[] getExcludes()
        {
            return excludes;
        }

        public void setExcludes(String excludes[])
        {
            this.excludes = excludes;
        }

        private Set getExcludeSet()
        {
            if(excludeSet != null)
                return excludeSet;
            excludeSet = new HashSet();
            if(getExcludes() != null)
                excludeSet.addAll(Arrays.asList(getExcludes()));
            return excludeSet;
        }

        private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/ModelConfig$DBModelLoader);
        private String tag[];
        private String excludes[];
        private Set excludeSet;



        public DBModelLoader()
        {
        }
    }

    public static abstract class TypedModelLoader extends ModelLoader
    {

        public void init()
        {
            ModelConfigUtil.Loader loader = createLoader();
            loaders.add(loader);
        }

        private ModelConfigUtil.Loader createLoader()
        {
            ModelConfigUtil.LoaderHelper dao = createHelper();
            OdbManager odbm = ModelConfigUtil.createOdbManager(this, null, ModelClassManager.getModelClass(modelType), dao.loader, dao.changeChecker, false);
            dao.dao = ((EntityDao) (odbm == null ? dao.dao : ((EntityDao) (new OdbEntityDao(dao.dao, odbm)))));
            dao.dao = DefaultEntityDaoFactory.get().addEntityDao(dao.dao);
            return new ModelConfigUtil.Loader(odbm, dao.loader, isExitOnError(), isIndexOnCreation());
        }

        protected abstract ModelConfigUtil.LoaderHelper createHelper();

        protected Class getType()
        {
            return ModelClassManager.getModelClass(getModelType());
        }

        public String getModelType()
        {
            return modelType;
        }

        public void setModelType(String modelType)
        {
            this.modelType = modelType;
        }

        private String modelType;

        public TypedModelLoader()
        {
        }
    }

    public static abstract class ModelLoader extends BaseAttribute
        implements OwnerAware
    {

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public Group getOwner()
        {
            return owner;
        }

        public void setOwner(Group owner)
        {
            this.owner = owner;
        }

        public String getFullId()
        {
            return (new StringBuilder()).append(((Group)parent).getFullId()).append(".").append(getId()).toString();
        }

        public abstract void init();

        protected boolean isCacheSetted()
        {
            if(getCache() != null && !getCache().booleanValue())
                return false;
            return owner == null || owner.getCache() == null || owner.getCache().booleanValue();
        }

        protected boolean isIndexOnCreation()
        {
            if(getIndexOnCreation() != null && getIndexOnCreation().booleanValue())
                return true;
            return owner != null && owner.getIndexOnCreation() != null && owner.getIndexOnCreation().booleanValue();
        }

        protected boolean isExitOnError()
        {
            if(getExitOnError() == null || getExitOnError().booleanValue())
                return true;
            return owner != null && owner.getExitOnError() == null || owner.getExitOnError().booleanValue();
        }

        protected boolean isLazyloadSetted()
        {
            if(getLazyload() != null && getLazyload().booleanValue())
                return true;
            return owner != null && owner.getLazyload() != null && owner.getLazyload().booleanValue();
        }

        protected boolean isReloadSetted()
        {
            if(getReload() != null && getReload().booleanValue())
                return true;
            if(getReload2() != null && getReload2().booleanValue())
                return true;
            if(owner != null && owner.getReload() != null && owner.getReload().booleanValue())
                return true;
            return owner != null && owner.getReload2() != null && owner.getReload2().booleanValue();
        }

        public volatile void setOwner(Object obj)
        {
            setOwner((Group)obj);
        }

        public volatile Object getOwner()
        {
            return getOwner();
        }

        private Group owner;
        private String id;
        List loaders;

        public ModelLoader()
        {
            loaders = new ArrayList();
        }
    }

    public static class Group extends BaseAttribute
    {

        public List getLoaders()
        {
            return loaders;
        }

        public void setLoaders(List loaders)
        {
            this.loaders = loaders;
        }

        public Integer getLoadThreads()
        {
            return loadThreads;
        }

        public void setLoadThreads(Integer loadThreads)
        {
            this.loadThreads = loadThreads;
        }

        public Integer getResolveThreads()
        {
            return resolveThreads;
        }

        public void setResolveThreads(Integer resolveThreads)
        {
            this.resolveThreads = resolveThreads;
        }

        public Boolean getLazyResolve()
        {
            return lazyResolve;
        }

        public void setLazyResolve(Boolean lazyResolve)
        {
            this.lazyResolve = lazyResolve;
        }

        public int getOrder()
        {
            return order;
        }

        public void setOrder(int order)
        {
            this.order = order;
        }

        public String getFullId()
        {
            return parent != null ? (new StringBuilder()).append(((ModelConfig)parent).getFullId()).append(".").append(getOrder()).toString() : (new StringBuilder()).append("").append(getOrder()).toString();
        }

        private List loaders;
        private Integer loadThreads;
        private Integer resolveThreads;
        private Boolean lazyResolve;
        private int order;

        public Group()
        {
            order = 0;
        }
    }

    public static abstract class BaseAttribute
        implements ModelParent, ModelFullId
    {

        public String getLongname()
        {
            return longname;
        }

        public void setLongname(String longname)
        {
            this.longname = longname;
        }

        public Boolean getLazyload()
        {
            return lazyload;
        }

        public void setLazyload(Boolean lazyload)
        {
            this.lazyload = lazyload;
        }

        public Boolean getReload()
        {
            return reload;
        }

        public void setReload(Boolean autoreload)
        {
            reload = autoreload;
        }

        public Boolean getReload2()
        {
            return reload2;
        }

        public void setReload2(Boolean autoreload2)
        {
            reload2 = autoreload2;
        }

        public Boolean getCache()
        {
            return cache;
        }

        public void setCache(Boolean cache)
        {
            this.cache = cache;
        }

        public Boolean getIndexOnCreation()
        {
            return indexOnCreation;
        }

        public void setIndexOnCreation(Boolean indexOnCreation)
        {
            this.indexOnCreation = indexOnCreation;
        }

        public Boolean getExitOnError()
        {
            return exitOnError;
        }

        public void setExitOnError(Boolean exitOnError)
        {
            this.exitOnError = exitOnError;
        }

        public ModelFullId getParent()
        {
            return parent;
        }

        public void setParent(ModelFullId parent)
        {
            this.parent = parent;
        }

        protected ModelFullId parent;
        private String longname;
        private Boolean lazyload;
        private Boolean reload;
        private Boolean reload2;
        private Boolean cache;
        private Boolean indexOnCreation;
        private Boolean exitOnError;

        public BaseAttribute()
        {
        }
    }

    public static class ParamReloadThreadConfig
    {

        public Long getReloadInterval()
        {
            return reloadInterval;
        }

        public void setReloadInterval(Long reloadInterval)
        {
            this.reloadInterval = reloadInterval;
        }

        public Long getReloadDelay()
        {
            return reloadDelay;
        }

        public void setReloadDelay(Long reloadDelay)
        {
            this.reloadDelay = reloadDelay;
        }

        public String getThreadId()
        {
            return threadId;
        }

        public void setThreadId(String threadId)
        {
            this.threadId = threadId;
        }

        public String getThreadName()
        {
            return threadName;
        }

        public void setThreadName(String threadName)
        {
            this.threadName = threadName;
        }

        private String threadId;
        private String threadName;
        private Long reloadInterval;
        private Long reloadDelay;

        public ParamReloadThreadConfig()
        {
            threadId = "paramReloader";
            threadName = "param-reloader";
        }
    }


    public ModelConfig()
    {
        defaultExprType = cn.sunline.ltts.base.expression.ExpressionEvaluatorFactory.ExpressionType.ognl;
    }

    public boolean isEnablePrehot()
    {
        return enablePrehot;
    }

    public void setEnablePrehot(boolean enablePrehot)
    {
        this.enablePrehot = enablePrehot;
    }

    public List getGroups()
    {
        return groups;
    }

    public void setGroups(List groups)
    {
        this.groups = groups;
    }

    public cn.sunline.ltts.base.expression.ExpressionEvaluatorFactory.ExpressionType getDefaultExprType()
    {
        return defaultExprType;
    }

    public void setDefaultExprType(cn.sunline.ltts.base.expression.ExpressionEvaluatorFactory.ExpressionType defaultExpressionType)
    {
        defaultExprType = defaultExpressionType;
    }

    public void setParamReloadThreadConfig(ParamReloadThreadConfig paramReloadThreadConfig)
    {
        this.paramReloadThreadConfig = paramReloadThreadConfig;
    }

    public ParamReloadThreadConfig getParamReloadThreadConfig()
    {
        return paramReloadThreadConfig;
    }

    public String getFullId()
    {
        return getDefaultExprType().name();
    }

    private cn.sunline.ltts.base.expression.ExpressionEvaluatorFactory.ExpressionType defaultExprType;
    private List groups;
    private ParamReloadThreadConfig paramReloadThreadConfig;
    private boolean enablePrehot;
}
