// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEngineResourceManager.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.engine.datamapping.EngineContext;
import cn.sunline.adp.cedar.base.logging.*;
import cn.sunline.adp.cedar.base.tmp.LocalTmpTableCaches;
import cn.sunline.adp.core.cache.TransactionCache;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.core.reloader.ConfigReloaderFactory;
import cn.sunline.adp.dao.base.DaoContext;
import cn.sunline.adp.metadata.base.engine.EngineResourceManager;

public class DefaultEngineResourceManager
    implements EngineResourceManager
{

    public DefaultEngineResourceManager()
    {
    }

    public void clearTxnCache(boolean before)
    {
        TransactionCache.clear();
        DaoContext.get().clearContext();
        ProfileUtil.cleanup(before);
        LocalTmpTableCaches.clearAllTmpTable();
    }

    public void clearThreadCache(boolean before)
    {
        clearTxnCache(before);
        ProfileUtil.cleanup(true, before);
        Log4j2Util.clearAllThreadContext();
        if(!before)
            ConfigReloaderFactory.allCloseSession();
        EngineContext.clear();
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/DefaultEngineResourceManager);

}
