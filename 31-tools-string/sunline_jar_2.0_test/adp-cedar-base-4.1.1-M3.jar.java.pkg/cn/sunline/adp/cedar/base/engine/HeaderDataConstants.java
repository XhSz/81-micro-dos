// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeaderDataConstants.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.constant.ConstantValueManager;

public class HeaderDataConstants
{

    public HeaderDataConstants()
    {
    }

    public static final String NAME_PRCSCD = ConstantValueManager.get().getValue("tranCode", "prcscd");
    public static final String NAME_USERID = ConstantValueManager.get().getValue("userId", "jiaoyigy");
    public static final String NAME_PCKGSQ = ConstantValueManager.get().getValue("packageSequence", "pckgsq");
    public static final String NAME_TRANDT = ConstantValueManager.get().getValue("tranDate", "jiaoyirq");
    public static final String NAME_ERORCD = ConstantValueManager.get().getValue("errorCode", "erorcd");
    public static final String NAME_RET_STATUS = ConstantValueManager.get().getValue("tranStatus", "status");
    public static final String NAME_ERORTX = ConstantValueManager.get().getValue("errorText", "erortx");
    public static final String NAME_RECONNECT = ConstantValueManager.get().getValue("reconnectTime", "reconnect");
    public static final String NAME_GLOBAL_PARM_VERSION = ConstantValueManager.get().getValue("globalParmVersion", "quanjcsbb");
    public static final String NAME_MIRCOSERVICE_PARM_VERSION = ConstantValueManager.get().getValue("mircoserviceParmVersion", "wfwcsbb");
    public static final String NAME_TENANT_ID = ConstantValueManager.get().getValue("tenantId", "farendma");
    public static final String NAME_MESSAGE = ConstantValueManager.get().getValue("message", "message");
    public static final String NAME_TRAN_SEQUENCE = ConstantValueManager.get().getValue("tranSequence", "jiaoylsh");
    public static final String NAME_LANGUAGE = ConstantValueManager.get().getValue("languageContry", "country");
    /**
     * @deprecated Field NAME_DEBUG is deprecated
     */
    public static final String NAME_DEBUG = ConstantValueManager.get().getValue("debug", "_debug");
    /**
     * @deprecated Field NAME_TRANCMD is deprecated
     */
    public static final String NAME_TRANCMD = ConstantValueManager.get().getValue("transactionCommand", "_trancmd");
    /**
     * @deprecated Field NAME_TRANGLOBALID is deprecated
     */
    public static final String NAME_TRANGLOBALID = ConstantValueManager.get().getValue("transactionGlobalId", "_tranglobalid");
    /**
     * @deprecated Field NAME_TRANBRANCHID is deprecated
     */
    public static final String NAME_TRANBRANCHID = ConstantValueManager.get().getValue("transactionBranchId", "_tranbranchid");
    /**
     * @deprecated Field NAME_DEFAULTDS is deprecated
     */
    public static final String NAME_DEFAULTDS = ConstantValueManager.get().getValue("transactionDefaultDataSource", "_trands");
    public static final String BUSI_SEQ_NO = ConstantValueManager.get().getValue("busiSeqNo", "busiseqno");
    public static final String CALL_SEQ_NO = ConstantValueManager.get().getValue("callSeqNo", "callseqno");
    public static final String CONSUMER_CALL_SEQ_NO = ConstantValueManager.get().getValue("consumerCallSeqNo", "consumercallseqno");
    public static final String SERVICE_SEQ_NO = ConstantValueManager.get().getValue("serviceSeqNo", "serviceseqno");
    public static final String ORIG_SYS_ID = ConstantValueManager.get().getValue("origSysId", "origsysid");
    public static final String ORIG_SRV_ID = ConstantValueManager.get().getValue("origSrvId", "origsrvid");
    public static final String CONSUMER_SYS_ID = ConstantValueManager.get().getValue("consumerSysId", "consumersysid");
    public static final String CONSUMER_SRV_ID = ConstantValueManager.get().getValue("consumerSrvId", "consumersrvid");
    public static final String CONSUMER_DCN = ConstantValueManager.get().getValue("consumerSvrId", "consumersvrid");
    public static final String CONSUMER_SYS_VERSION = ConstantValueManager.get().getValue("consumerSysVersion", "consumersysversion");
    public static final String TRAN_TIMESTAMP = ConstantValueManager.get().getValue("tranTimestamp", "trantimestamp");
    public static final String REVERSAL_SEQ_NO = ConstantValueManager.get().getValue("reversalSeqNo", "reversalseqno");
    public static final String USER_LANG = NAME_LANGUAGE;
    public static final String GLOBAL_TRAN_SEQ_NO = ConstantValueManager.get().getValue("globalTranSeqNo", "globaltranseqno");
    public static final String BUSI_SRV_ID = ConstantValueManager.get().getValue("busiSrvId", "busisrvid");
    public static final String TXN_CD = NAME_PRCSCD;
    public static final String SERVICE_CODE = ConstantValueManager.get().getValue("serviceCode", "servicecode");
    public static final String SERVICE_GROUP_CODE = ConstantValueManager.get().getValue("serviceGroupCode", "servicegroupcode");
    public static final String SERVICE_VERSION = ConstantValueManager.get().getValue("serviceVersion", "serviceversion");
    public static final String RET_ERROR_STACK = ConstantValueManager.get().getValue("returnErrorStack", "errorstack");
    public static final String DB_ROLLBACK = ConstantValueManager.get().getValue("dbRollback", "dbrollback");
    public static final String IN_SERVICE_MARK = ConstantValueManager.get().getValue("inServiceMark", "inservicemark");
    public static final String RESPONSE_CODE = NAME_ERORCD;
    public static final String RESPONSE_MSG = NAME_ERORTX;
    public static final String RET_Status = NAME_RET_STATUS;
    public static final String RESPONSE_TYPE = ConstantValueManager.get().getValue("responseType", "responsetype");
    public static final String PROVIDER_DCN = ConstantValueManager.get().getValue("providerDcn", "providerdcn");
    public static final String DTX_STATUS = ConstantValueManager.get().getValue("dtxStatus", "dtxStatus");
    public static final String DTX_ERRORTX = ConstantValueManager.get().getValue("dtxErrortx", "dtxErrortx");
    public static final String DTX_ERRORCD = ConstantValueManager.get().getValue("dtxErrorcd", "dtxErrorcd");
    public static final String F_MainShardingId = ConstantValueManager.get().getValue("mainShardingId", "mainShardingId");
    public static final String F_CurrentShardingId = ConstantValueManager.get().getValue("currentShardingId", "currentShardingId");

}
