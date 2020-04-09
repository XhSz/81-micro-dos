// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlEntityOdbEntityDao.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.common.util.StringUtil;
import cn.sunline.ltts.base.dao.EntityDaoSupport;
import cn.sunline.ltts.base.odb.*;
import cn.sunline.ltts.core.api.exception.LTTSDaoDuplicateException;
import cn.sunline.ltts.core.api.exception.LTTSDaoException;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.core.api.model.ModelFileAware;
import cn.sunline.ltts.frw.model.annotation.Index;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;
import javax.xml.bind.Unmarshaller;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelObjectPartner, ModelFilesLoader, ModelFactoryUtil, XmlConfigManager, 
//            XmlEntityLoader

public class XmlEntityOdbEntityDao extends EntityDaoSupport
    implements EntityLoader, ChangeChecker
{

    public XmlEntityOdbEntityDao(Class type, XmlConfigManager xmlConfigManager, ModelFilesLoader loader, boolean exitOnError)
    {
        this.type = type;
        this.xmlConfigManager = xmlConfigManager;
        this.loader = loader;
        this.exitOnError = exitOnError;
    }

    public Class getType()
    {
        return type;
    }

    public boolean supportChangeCheckOnRequest()
    {
        return true;
    }

    public List reload(Object en)
    {
        if(en instanceof ModelObjectPartner)
        {
            ModelFile r = (ModelFile)((ModelObjectPartner)en).getData().get(cn.sunline.ltts.frw.model.util.XmlEntityLoader.PartnerKey.ModelFile);
            if(r == null)
            {
                return null;
            } else
            {
                List ret = new ArrayList();
                ret.add(parseModelFile(r));
                return ret;
            }
        } else
        {
            return null;
        }
    }

    public transient List loadByKey(Object key[])
    {
        List ret = new ArrayList();
        ModelFile arr$[] = loader.load();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            ModelFile mf = arr$[i$];
            if(match(mf.getFileName(), key[0]))
                ret.add(parseModelFile(mf));
        }

        return ret;
    }

    public transient List loadByIndex(Class index, Object args[])
    {
        throw new UnsupportedOperationException();
    }

    public List loadAll()
    {
        List ret = new ArrayList();
        ModelFile arr$[] = loader.load();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$;)
        {
            ModelFile r = arr$[i$];
            try
            {
                ret.add(parseModelFile(r));
                continue;
            }
            catch(Exception e)
            {
                String message = (new StringBuilder()).append("\u52A0\u8F7D\u914D\u7F6E\u6587\u4EF6[").append(r.getFullPath()).append("]\u9519\u8BEF").append(e.getMessage()).toString();
                if(exitOnError)
                    throw new IllegalArgumentException(message, e);
                bootLog.warn(ModelRtConst.XmlEntityOdbEntityDao01, new Object[] {
                    r.getFullPath(), e.getMessage()
                });
                i$++;
            }
        }

        return ret;
    }

    public boolean isChanged(Object en)
    {
        if(en == null || !(en instanceof ModelObjectPartner))
            return false;
        ModelFile r = (ModelFile)((ModelObjectPartner)en).getData().get(cn.sunline.ltts.frw.model.util.XmlEntityLoader.PartnerKey.ModelFile);
        if(r == null)
            return false;
        long lastModified = r.lastModified();
        Long preModified = (Long)((ModelObjectPartner)en).getData().get(XmlEntityLoader.PartnerKey.LastModified);
        if(preModified == null)
            return false;
        return lastModified > 0L && preModified.longValue() > 0L && preModified.longValue() + 1000L < lastModified;
    }

    public void registerReloadEventListener(cn.sunline.ltts.base.odb.ChangeChecker.ReloadEventListener reloadeventlistener)
    {
    }

    public boolean registerLoadListener(EntityLoadListener listener)
    {
        listeners.add(listener);
        return true;
    }

    protected Object parseModelFile(ModelFile file)
    {
        if(bootLog.isDebugEnabled())
            bootLog.debug((new StringBuilder()).append("load file:").append(file).toString());
        Object mo = ModelFactoryUtil.parse(file, xmlConfigManager, new javax.xml.bind.Unmarshaller.Listener() {

            public void afterUnmarshal(Object target, Object parent)
            {
                EntityLoadListener listener;
                for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.afterLoad(target))
                    listener = (EntityLoadListener)i$.next();

            }

            final XmlEntityOdbEntityDao this$0;

            
            {
                this$0 = XmlEntityOdbEntityDao.this;
                super();
            }
        }
);
        if(mo instanceof ModelObjectPartner)
        {
            Map partner = ((ModelObjectPartner)mo).getData();
            partner.put(cn.sunline.ltts.frw.model.util.XmlEntityLoader.PartnerKey.ModelFile, file);
            partner.put(XmlEntityLoader.PartnerKey.LastModified, Long.valueOf(file.lastModified()));
        }
        return mo;
    }

    protected boolean match(String filename, Object id)
    {
        String sid = String.valueOf(id);
        if(filename.startsWith((new StringBuilder()).append(sid).append(".").toString()))
            return true;
        String ids[] = sid.split("\\.");
        return filename.startsWith((new StringBuilder()).append(ids[0]).append(".").toString());
    }

    public int insert(Object entity)
        throws LTTSDaoException, LTTSDaoDuplicateException
    {
        if(entity == null)
            return 0;
        if(OdbFactory.get().getOdbManager(type).selectByKey(new Object[] {
    entity
}) != null)
        {
            throw new LTTSDaoDuplicateException(ModelRtConst.XmlEntityOdbEntityDao03);
        } else
        {
            save(entity);
            return 1;
        }
    }

    public int[] insertBatch(List entity)
        throws LTTSDaoException, LTTSDaoDuplicateException
    {
        if(StringUtil.isEmpty(entity))
            return (new int[] {
                0
            });
        int ret[] = new int[entity.size()];
        for(int i = 0; i < entity.size(); i++)
            ret[i] = insert(entity.get(i));

        return ret;
    }

    public int update(Object entity)
        throws LTTSDaoException, LTTSDaoDuplicateException
    {
        if(entity == null)
            return 0;
        if(!(entity instanceof ModelObjectPartner))
            return 0;
        ModelFile mf = (ModelFile)((ModelObjectPartner)entity).getData().get(cn.sunline.ltts.frw.model.util.XmlEntityLoader.PartnerKey.ModelFile);
        if(mf == null)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.XmlEntityOdbEntityDao04, new Object[] {
                entity.getClass().getName()
            }));
        } else
        {
            save(entity, mf);
            return 1;
        }
    }

    public int[] updateBatch(List entity)
        throws LTTSDaoException, LTTSDaoDuplicateException
    {
        if(entity == null)
            return (new int[] {
                0
            });
        int ret[] = new int[entity.size()];
        for(int i = 0; i < entity.size(); i++)
            ret[i] = update(entity.get(i));

        return ret;
    }

    public int delete(Object entity)
        throws LTTSDaoException, LTTSDaoDuplicateException
    {
        if(entity == null)
            return 0;
        if(!(entity instanceof ModelObjectPartner))
            return 0;
        ModelFile mf = (ModelFile)((ModelObjectPartner)entity).getData().get(cn.sunline.ltts.frw.model.util.XmlEntityLoader.PartnerKey.ModelFile);
        if(mf == null)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.XmlEntityOdbEntityDao04, new Object[] {
                entity.getClass().getName()
            }));
        } else
        {
            save(null, mf);
            return 1;
        }
    }

    public int deleteByKey(Object key)
        throws LTTSDaoException, LTTSDaoDuplicateException
    {
        if(key == null)
            return 0;
        Object entity = OdbFactory.get().getOdbManager(type).selectByKey(new Object[] {
            key
        });
        if(!(entity instanceof ModelObjectPartner))
            return 0;
        ModelFile mf = (ModelFile)((ModelObjectPartner)entity).getData().get(cn.sunline.ltts.frw.model.util.XmlEntityLoader.PartnerKey.ModelFile);
        if(mf == null)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.XmlEntityOdbEntityDao04, new Object[] {
                entity.getClass().getName()
            }));
        } else
        {
            save(null, mf);
            return 1;
        }
    }

    private void save(Object entity, ModelFile mf)
    {
        if(mf.getOutputStream() == null)
        {
            throw new IllegalArgumentException(String.format(ModelRtConst.XmlEntityOdbEntityDao05, new Object[] {
                entity.getClass().getName()
            }));
        } else
        {
            java.io.OutputStream os = mf.getOutputStream();
            bootLog.debug(ModelRtConst.XmlEntityOdbEntityDao02, new Object[] {
                mf.getFullPath()
            });
            xmlConfigManager.save(entity, os);
            return;
        }
    }

    private void save(Object entity)
    {
        if(entity instanceof ModelFileAware)
            save(entity, ((ModelFileAware)entity).getModelFile());
        else
            throw new IllegalArgumentException(String.format(ModelRtConst.XmlEntityOdbEntityDao06, new Object[] {
                entity.getClass().getName()
            }));
    }

    public List selectAll()
    {
        return loadAll();
    }

    private static final SysLog bootLog = SysLogUtil.getBootLogger();
    private final Class type;
    private final XmlConfigManager xmlConfigManager;
    private final ModelFilesLoader loader;
    private final List listeners = new ArrayList();
    private final boolean exitOnError;


}
