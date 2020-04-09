// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SysUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.adp.cedar.base.StandardHeaderData;
import cn.sunline.adp.cedar.base.engine.RequestData;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineContext;
import cn.sunline.adp.cedar.base.engine.datamapping.EngineRuntimeContext;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineTransactionMode;
import cn.sunline.adp.cedar.base.engine.transaction.OnlineTransactionUtil;
import cn.sunline.adp.cedar.base.logging.LogConfigManager;
import cn.sunline.adp.cedar.base.util.*;
import cn.sunline.adp.cedar.busi.sdk.biz.global.model.TranInfo;
import cn.sunline.adp.cedar.engine.online.OnlineEngineTemplate;
import cn.sunline.adp.cedar.engine.online.config.OnlineEngineConfigManager;
import cn.sunline.adp.cedar.flowtran.model.TransactionConf;
import cn.sunline.adp.cedar.net.tcp.socket.ChannelContext;
import cn.sunline.adp.cedar.server.batch.util.BatchUtil;
import cn.sunline.adp.cedar.server.online.util.OnlineServerContextUtil;
import cn.sunline.adp.cedar.service.util.ServiceInstanceUtils;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.exception.AdpBusinessException;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.dao.base.conn.DBConnectionManager;
import cn.sunline.adp.metadata.base.util.CommUtil_;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import cn.sunline.adp.metadata.loader.remote.parmreload.DbParamVersionInfo;
import cn.sunline.adp.metadata.loader.remote.parmreload.OdbChangeListenerManager;
import cn.sunline.adp.metadata.model.ModelFactory;
import cn.sunline.adp.metadata.model.annotation.ConfigType;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.util.List;
import java.util.Map;

public class SysUtil extends CoreUtil
{

    public SysUtil()
    {
    }

    public static String getScid()
    {
        return ChannelContext.getChannelId();
    }

    public static String getDbEncoding()
    {
        if(DB_ENCODEING != null)
        {
            return DB_ENCODEING;
        } else
        {
            DB_ENCODEING = EdspCoreBeanUtil.getDBConnectionManager().getDatabaseEncoding();
            return DB_ENCODEING;
        }
    }

    public static String getChannelId()
    {
        return StringUtil.isNotEmpty(ChannelContext.getChannelId()) ? ChannelContext.getChannelId() : "default";
    }

    public static DbParamVersionInfo updateMainParamVersion()
    {
        return OdbChangeListenerManager.get().updateMainParamVersion();
    }

    public static Object getRemoteInstance(Class intfClass)
    {
        return ServiceInstanceUtils.getRouteInstance(intfClass);
    }

    public static Object getInstance(Class intfClass)
    {
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.start_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        ConfigType ct;
        Object obj;
        ct = (ConfigType)intfClass.getAnnotation(cn/sunline/adp/metadata/model/annotation/ConfigType);
        if(ct == null)
            break MISSING_BLOCK_LABEL_150;
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.SERVICE != ct.type())
            break MISSING_BLOCK_LABEL_95;
        obj = ServiceInstanceUtils.getLocalInstance(intfClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        return obj;
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.JAVA_BEAN != ct.type())
            break MISSING_BLOCK_LABEL_150;
        obj = EdspCoreBeanUtil.getModelObjectCreator().create(intfClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        return obj;
        obj = ServiceInstanceUtils.getLocalInstance(intfClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        return obj;
        Exception e;
        e;
        Object obj1 = EdspCoreBeanUtil.getModelObjectCreator().create(intfClass);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).toString());
        return obj1;
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
        ConfigType ct;
        Object obj;
        ct = (ConfigType)intfClass.getAnnotation(cn/sunline/adp/metadata/model/annotation/ConfigType);
        if(ct == null)
            break MISSING_BLOCK_LABEL_189;
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.SERVICE != ct.type())
            break MISSING_BLOCK_LABEL_124;
        obj = ServiceInstanceUtils.getLocalInstance(intfClass, serviceImplId);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        return obj;
        if(cn.sunline.adp.metadata.model.annotation.ConfigType.Type.COMPONENT != ct.type())
            break MISSING_BLOCK_LABEL_189;
        obj = ComponentUtil.getInstance(intfClass, serviceImplId);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        return obj;
        obj = ServiceInstanceUtils.getLocalInstance(intfClass, serviceImplId);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        return obj;
        Exception e;
        e;
        Object obj1 = ComponentUtil.getInstance(intfClass, serviceImplId);
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        return obj1;
        Exception exception;
        exception;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("SysUtil.getInstance_").append(intfClass.getSimpleName()).append("by Id[").append(serviceImplId).append("]").toString());
        throw exception;
    }

    public static Map callFlowTran(String prcscd, Map input)
        throws AdpBusinessException, Exception
    {
        return callFlowTranWithAllReturn(prcscd, input).getOutput();
    }

    public static Map callFlowTran(String prcscd, DataArea dataArea)
        throws AdpBusinessException, Exception
    {
        return callFlowTranWithAllReturn(prcscd, dataArea).getOutput();
    }

    public static DataArea callFlowTranWithAllReturn(String prcscd, Map input)
        throws AdpBusinessException, Exception
    {
        Map commReq = CommUtil_.toMap(EngineContext.getEngineRuntimeContext().getTrxRunEnvs());
        Map system = CommUtil_.toMap(EngineContext.getRequestData().getRequestHeader());
        DataArea dataArea = DataArea.buildWithInput(input);
        dataArea.setCommReq(commReq);
        dataArea.setSystem(system);
        return callFlowTranWithAllReturn(prcscd, dataArea);
    }

    public static DataArea callFlowTranWithAllReturn(String prcscd, DataArea dataArea)
        throws AdpBusinessException, Exception
    {
        return BatchUtil.callFlowTranWithAllReturn(prcscd, dataArea);
    }

    public static Map callFlowTran(String flowTranId, Map input, Map header, OnlineTransactionMode transationMode)
        throws AdpBusinessException, Exception
    {
        return BatchUtil.callFlowTranWithReturn(flowTranId, input, header, transationMode);
    }

    public static DataArea callFlowTran(String flowTranId, DataArea dataArea, OnlineTransactionMode transationMode)
        throws AdpBusinessException, Exception
    {
        return BatchUtil.callFlowTranWithReturn(flowTranId, dataArea, transationMode);
    }

    public static Map callFlowTranWithTransaction(String flowTranId, Map input, Map header)
        throws AdpBusinessException, Exception
    {
        Map map = BatchUtil.callFlowTranWithReturn(flowTranId, input, header, OnlineTransactionMode.DISTRIBUTED);
        EdspCoreBeanUtil.getDBConnectionManager().beginTransation();
        return map;
        Exception exception;
        exception;
        EdspCoreBeanUtil.getDBConnectionManager().beginTransation();
        throw exception;
    }

    public static DataArea callFlowTranWithTransaction(String flowTranId, DataArea dataArea)
        throws AdpBusinessException, Exception
    {
        DataArea dataarea = BatchUtil.callFlowTranWithReturn(flowTranId, dataArea, OnlineTransactionMode.DISTRIBUTED);
        EdspCoreBeanUtil.getDBConnectionManager().beginTransation();
        return dataarea;
        Exception exception;
        exception;
        EdspCoreBeanUtil.getDBConnectionManager().beginTransation();
        throw exception;
    }

    public static Object getInstanceByBind(Class intfClass, String bindId)
    {
        return ServiceInstanceUtils.getRouteInstance(intfClass, bindId);
    }

    public static Object getInstanceByCopy(Object t)
    {
        if(t == null)
            return null;
        else
            return EdspCoreBeanUtil.getModelObjectCreator().cloneModel(t);
    }

    public static Object getInstanceProxyByBind(Class intfClass, String bindId)
    {
        return ServiceInstanceUtils.getRouteInstance(intfClass, bindId);
    }

    public static Object getInstanceByDefaultBind(Class intfClass)
    {
        return ServiceInstanceUtils.getRouteInstance(intfClass);
    }

    public static String serialize(Object entity)
    {
        return CoreUtil.serialize(entity);
    }

    public static Object getTrxRunEnvs()
    {
        return CoreUtil.getTrxRunEnvs();
    }

    public static String nextValue(String sequenceName)
    {
        return CoreUtil.nextValue(sequenceName);
    }

    public static Object deserialize(String s, Class type)
    {
        return CoreUtil.deserialize(s, type);
    }

    public static cn.sunline.adp.cedar.base.logging.LogConfigManager.SystemType getCurrentSystemType()
    {
        return CoreUtil.getCurrentSystemType();
    }

    /**
     * @deprecated Method getCurrentDataArea is deprecated
     */

    public static DataArea getCurrentDataArea()
    {
        return EngineContext.getEngineRuntimeContext().getRunDataArea();
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

    public static TranInfo getTranInfo(String prcscd)
    {
        TransactionConf config = (TransactionConf)OnlineEngineConfigManager.get().getModelFactory().getModel(cn/sunline/adp/cedar/flowtran/model/TransactionConf, prcscd);
        if(config == null)
            return null;
        else
            return new TranInfo(config);
    }

    public static boolean isReadOnly()
    {
        Boolean o = (Boolean)EngineContext.getTxnTempObj("_SYSTEM_READ_ONLY_KEY");
        if(o == null)
            return false;
        else
            return o.booleanValue();
    }

    public static Throwable getTranException()
    {
        return EngineContext.isEmpty() ? null : EngineContext.getTranException();
    }

    public static void rollbackGlobal(String globalTranSeqNo)
    {
        OnlineTransactionUtil.rollbackGlobal(globalTranSeqNo);
    }

    public static List getGlobalFailList(String subSystemCode, String dcnNo, int rowNum)
    {
        return OnlineTransactionUtil.queryGlobalFailList(subSystemCode, dcnNo, rowNum);
    }

    public static void rollbackTransactionNotFinalStatus(String globalTranSeq)
    {
        OnlineTransactionUtil.rollbackTransactionNotFinalStatus(globalTranSeq);
    }

    public static cn.sunline.adp.cedar.base.StandardHeaderData.Request getRequestHead()
    {
        return new cn.sunline.adp.cedar.base.StandardHeaderData.Request(OnlineServerContextUtil.getRequestHead());
    }

    public static final cn.sunline.adp.cedar.base.StandardHeaderData.Request getServiceRequestHead()
    {
        return new cn.sunline.adp.cedar.base.StandardHeaderData.Request(OnlineServerContextUtil.getServiceRequestHead());
    }

    public static final String getInnerServiceCode()
    {
        return EngineContext.getEngineRuntimeContext().getInnerServiceCode();
    }

    public static final String getInnerServiceImplCode()
    {
        return EngineContext.getEngineRuntimeContext().getInnerServiceImplCode();
    }

    private static String DB_ENCODEING = "UTF-8";

}
