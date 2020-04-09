// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoreUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.bean.SequenceMessage;
import cn.sunline.adp.cedar.base.constant.ApiConst;
import cn.sunline.adp.cedar.base.engine.HeaderDataConstants;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineContext;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineRuntimeContext;
import cn.sunline.adp.cedar.base.engine.sequence.SequenceManager;
import cn.sunline.adp.cedar.base.errors.CorePluginErrorDef;
import cn.sunline.adp.cedar.base.logging.*;
import cn.sunline.adp.cedar.base.tmp.LocalTmpTableCaches;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.core.cache.TransactionCache;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.core.util.JsonUtil;
import cn.sunline.adp.core.util.SystemContext;
import cn.sunline.adp.metadata.base.engine.EngineResourceManager;
import cn.sunline.adp.metadata.base.odb.OdbFactory;
import cn.sunline.adp.metadata.base.odb.OdbManager;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import cn.sunline.adp.metadata.loader.util.MetaData;
import cn.sunline.adp.metadata.loader.util.ModelFactoryUtil;
import cn.sunline.adp.metadata.model.*;
import cn.sunline.adp.metadata.model.annotation.ConfigType;
import cn.sunline.adp.metadata.model.annotation.Index;
import cn.sunline.adp.metadata.model.database.Field;
import cn.sunline.adp.metadata.model.database.Table;
import cn.sunline.adp.metadata.model.util.ModelUtil;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.util:
//            SystemParams, ComponentUtil, CommUtil

public class CoreUtil
{
    public static class EncryptInfo
    {

        public String getFieldName()
        {
            return field.getId();
        }

        public String getEncryptType()
        {
            return encryptType;
        }

        public String[] getEncryptFactor()
        {
            return encryptFactor;
        }

        public Object[] getEncryptFactorValue(Object bean)
        {
            if(bean == null || encryptFactor == null || encryptFactor.length == 0)
                return null;
            Map modelMap = CommUtil.toMap(bean);
            Object ret[] = new Object[encryptFactor.length];
            for(int i = 0; i < encryptFactor.length; i++)
                ret[i] = modelMap.get(encryptFactor[i]);

            return ret;
        }

        private Field field;
        private String encryptType;
        private String encryptFactor[];

        private EncryptInfo(Field field)
        {
            this.field = field;
            encryptType = field.getEncryptType();
            encryptFactor = field.getEncryptFactor();
        }

    }

    public static class BeanInfo
    {

        public List getEncryptInfo()
        {
            if(encryptInfo == emptyEncryptInfo)
                return emptyEncryptInfo;
            if(encryptInfo == null)
                encryptInfo = _getEncryptInfo();
            return encryptInfo;
        }

        private synchronized List _getEncryptInfo()
        {
            if(encryptInfo != null && encryptInfo == emptyEncryptInfo)
                return encryptInfo;
            ComplexType ct = OdbFactory.getComplexType(clazz);
            if(ct == null || !(ct instanceof Table))
                return encryptInfo = emptyEncryptInfo;
            Table table = (Table)ct;
            List temp = new ArrayList();
            Iterator iterator = table.getFields().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Field f = (Field)iterator.next();
                if(f.getEncryptType() != null && f.getEncryptType().length() != 0)
                    temp.add(new EncryptInfo(f));
            } while(true);
            if(temp.size() == 0)
                return encryptInfo = emptyEncryptInfo;
            else
                return encryptInfo = temp;
        }

        public String getCategory()
        {
            return category;
        }

        private String fullId;
        private String category;
        private Class clazz;
        private List encryptInfo;
        private List emptyEncryptInfo;

        private BeanInfo(Class clazz)
        {
            emptyEncryptInfo = new ArrayList();
            ConfigType ct = ModelUtil.getConfigType(clazz);
            if(ct == null)
            {
                throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E056(clazz.getName());
            } else
            {
                fullId = ct.value();
                category = ct.category();
                this.clazz = clazz;
                return;
            }
        }

    }


    public CoreUtil()
    {
    }

    public static String getDcnNo()
    {
        return SystemParams.get().getDcnNo();
    }

    public static String getSubSystemId()
    {
        return SystemParams.get().getSubSystemId();
    }

    public static String getSystemId()
    {
        return SystemParams.get().getSystemId();
    }

    /**
     * @deprecated Method getDefaultFarendma is deprecated
     */

    public static String getDefaultFarendma()
    {
        return SystemParams.get().getTenantId();
    }

    public static String getDefaultTenantId()
    {
        return SystemParams.get().getTenantId();
    }

    public static boolean isDistributedSystem()
    {
        return SystemParams.get().isDistributedSystem();
    }

    public static Object getTrxRspEnvs()
    {
        return EngineContext.getEngineRuntimeContext().getTrxRspEnvs();
    }

    public static Object getTrxRunEnvs()
    {
        return EngineContext.getEngineRuntimeContext().getTrxRunEnvs();
    }

    public static cn.sunline.adp.cedar.base.logging.LogConfigManager.SystemType getCurrentSystemType()
    {
        return LogConfigManager.get().getCurrentSystemType();
    }

    /**
     * @deprecated Method getMetaData is deprecated
     */

    private static MetaData getMetaData(Class type, String property)
    {
        return ModelFactoryUtil.getMetaData(type, property);
    }

    public static Object getInstance(Class intfClass)
    {
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.start_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        Object obj;
        ConfigType ct = (ConfigType)intfClass.getAnnotation(cn/sunline/adp/metadata/model/annotation/ConfigType);
        if(ct == null)
            break MISSING_BLOCK_LABEL_123;
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.SERVICE == ct.type())
            throw new RuntimeException(cn.sunline.adp.cedar.base.constant.ApiConst.AConst.CoreUtil01());
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.JAVA_BEAN != ct.type())
            break MISSING_BLOCK_LABEL_123;
        obj = ModelObjectCreatorUtil.getModelObjectCreator().create(intfClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        return obj;
        obj = ModelObjectCreatorUtil.getModelObjectCreator().create(intfClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        return obj;
        Exception exception;
        exception;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        throw exception;
    }

    public static Object getInstance(Class intfClass, String serviceImplId)
    {
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.start_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        Object obj;
        ConfigType ct = (ConfigType)intfClass.getAnnotation(cn/sunline/adp/metadata/model/annotation/ConfigType);
        if(ct == null)
            break MISSING_BLOCK_LABEL_147;
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.SERVICE == ct.type())
            throw new RuntimeException(cn.sunline.adp.cedar.base.constant.ApiConst.AConst.CoreUtil01());
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.COMPONENT != ct.type())
            break MISSING_BLOCK_LABEL_147;
        obj = ComponentUtil.getInstance(intfClass, serviceImplId);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        return obj;
        obj = ComponentUtil.getInstance(intfClass, serviceImplId);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        return obj;
        Exception exception;
        exception;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        throw exception;
    }

    public static String serialize(Object entity)
    {
        return JsonUtil.formatEntity(entity);
    }

    public static Object deserialize(String s, Class type)
    {
        return JsonUtil.parseEntity(s, type);
    }

    public static List getTableFieldArray(Class clazz)
    {
        ComplexType ct = OdbFactory.getComplexType(clazz);
        if(ct == null || !(ct instanceof Table))
            return null;
        List ret = new ArrayList();
        Field field;
        for(Iterator iterator = ((Table)ct).getTrueField().iterator(); iterator.hasNext(); ret.add(field.getId()))
            field = (Field)iterator.next();

        return ret;
    }

    public static String getTableName(Class clazz)
    {
        ComplexType ct = OdbFactory.getComplexType(clazz);
        if(ct == null || !(ct instanceof Table))
            throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E055(clazz.getName());
        else
            return ((Table)ct).getName();
    }

    /**
     * @deprecated Method getElements is deprecated
     */

    public static List getElements(Class clazz, String tableId)
    {
        ComplexType c = (ComplexType)ModelFactoryUtil.getModelFactory().getModel(clazz, tableId);
        return c.getAllElements();
    }

    public static BeanInfo getBeanInfo(Class clazz)
    {
        if(clazz == null)
            return null;
        BeanInfo ret = (BeanInfo)beanInfoCache.get(clazz);
        if(ret == null)
        {
            beanInfoCache.put(clazz, new BeanInfo(clazz));
            ret = (BeanInfo)beanInfoCache.get(clazz);
        }
        return ret;
    }

    /**
     * @deprecated Method getTableName is deprecated
     */

    public static String getTableName(String fullId)
    {
        Table t = (Table)ModelFactoryUtil.getModelFactory().getModel(cn/sunline/adp/metadata/model/database/Table, fullId);
        if(t == null)
            return null;
        else
            return t.getName();
    }

    /**
     * @deprecated Method clearCache is deprecated
     */

    public static void clearCache()
    {
        LocalTmpTableCaches.clearAllTmpTable();
        TransactionCache.clear();
    }

    public static void clearCache(boolean before)
    {
        EdspCoreBeanUtil.getEngineResourceManager().clearTxnCache(before);
    }

    public static String getHostName()
    {
        return SystemContext.getHostName();
    }

    public static String getIp()
    {
        return SystemContext.getIp();
    }

    public static String getVmid()
    {
        return SystemContext.getVmid();
    }

    public static String getAppName()
    {
        return SystemContext.getAppName();
    }

    public static String getMainShardingId()
    {
        return (String)EngineContext.getTxnShardingInfoMap(HeaderDataConstants.F_MainShardingId);
    }

    public static String getCurrentShardingId()
    {
        return (String)EngineContext.getTxnShardingInfoMap(HeaderDataConstants.F_CurrentShardingId);
    }

    public static String setCurrentShardingId(String currentShardingId)
    {
        String oldShardingId = getCurrentShardingId();
        EngineContext.setTxnShardingInfoMap(HeaderDataConstants.F_CurrentShardingId, currentShardingId);
        return oldShardingId;
    }

    public static void setMainShardingId(String mainShardingId)
    {
        EngineContext.setTxnShardingInfoMap(HeaderDataConstants.F_MainShardingId, mainShardingId);
    }

    public static String getSvcId()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(SystemContext.getIp()).append("#");
        sb.append(SystemContext.getUserName()).append("#");
        sb.append(SystemContext.getVmid());
        return sb.toString();
    }

    public static Class getDefaultUniqueIndex(String tableName)
    {
        Table table = (Table)OdbFactory.get().getOdbManager(cn/sunline/adp/metadata/model/database/Table).selectByIndex(cn/sunline/adp/metadata/model/database/Table$TableName, new Object[] {
            tableName
        });
        OdbIndex index = table.getFirstUniqueOdbIndex();
        if(index == null)
            return null;
        else
            return index.getJavaClass();
    }

    public static String nextValue(String sequenceName)
    {
        return SequenceManager.nextval(null, sequenceName).getNextValue();
    }

    public static SequenceMessage nextValue(String resetType, String sequenceName)
    {
        return SequenceManager.nextval(resetType, sequenceName);
    }

    public static void rollback()
    {
    }

    private static final SysLog syslog = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/util/CoreUtil);
    private static Map beanInfoCache = new ConcurrentHashMap();

}
