// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BranchAttachment.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed.bean;

import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import cn.sunline.adp.cedar.base.engine.transaction.ServiceTransactionMode;
import cn.sunline.adp.core.util.JsonUtil;
import java.util.HashMap;
import java.util.Map;

public class BranchAttachment extends MapListDataContext
{

    public BranchAttachment()
    {
    }

    public BranchAttachment(Map data)
    {
        super(data);
    }

    public Map getInputHead()
    {
        Object inputHead = get("input_head");
        if(inputHead instanceof String)
            inputHead = JsonUtil.parse((String)inputHead);
        return (Map)inputHead;
    }

    public void setInputHead(Map inputHead)
    {
        put("input_head", inputHead);
    }

    public Map getInputBody()
    {
        Object inputBody = get("input_body");
        if(inputBody instanceof String)
            inputBody = JsonUtil.parse((String)inputBody);
        return (Map)inputBody;
    }

    public void setInputBody(Map inputBody)
    {
        put("input_body", inputBody);
    }

    public String getTargetDCN()
    {
        return getString("targetDCNKey");
    }

    public void setTargetDCN(String targetDCN)
    {
        setString("targetDCNKey", targetDCN);
    }

    public ServiceTransactionMode getServiceTransactionMode()
    {
        Object serviceTranMode = get("serviceTransactionModeKey");
        if(serviceTranMode instanceof String)
            serviceTranMode = ServiceTransactionMode.get((String)serviceTranMode);
        return (ServiceTransactionMode)serviceTranMode;
    }

    public void setServiceTransactionMode(ServiceTransactionMode serviceTransactionMode)
    {
        put("serviceTransactionModeKey", serviceTransactionMode);
    }

    public String getConfirmInvokeId()
    {
        return getString("confirmInvokeIdKey");
    }

    public void setConfirmInvokeId(String confirmInvokeId)
    {
        setString("confirmInvokeIdKey", confirmInvokeId);
    }

    public String getCancelInvokeId()
    {
        return getString("cancelInvokeIdKey");
    }

    public void setCancelInvokeId(String cancelInvokeId)
    {
        setString("cancelInvokeIdKey", cancelInvokeId);
    }

    public Map getExclusiveInputData()
    {
        Map map = new HashMap();
        map.put("targetDCNKey", getTargetDCN());
        map.put("serviceTransactionModeKey", getServiceTransactionMode());
        map.put("confirmInvokeIdKey", getConfirmInvokeId());
        map.put("cancelInvokeIdKey", getCancelInvokeId());
        return map;
    }

    private static final String INPUT_HEAD_KEY = "input_head";
    private static final String INPUT_BODY_KEY = "input_body";
    private static final String TARGET_DCN_KEY = "targetDCNKey";
    private static final String SVC_TRAN_MODE_KEY = "serviceTransactionModeKey";
    private static final String CONFIRM_INVOKE_ID_KEY = "confirmInvokeIdKey";
    private static final String CANCEL_INVOKE_ID_KEY = "cancelInvokeIdKey";
}
