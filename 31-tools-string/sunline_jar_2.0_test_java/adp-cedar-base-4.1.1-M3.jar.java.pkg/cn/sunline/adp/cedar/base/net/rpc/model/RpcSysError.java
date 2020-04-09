// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcSysError.java

package cn.sunline.adp.cedar.base.net.rpc.model;

import cn.sunline.adp.cedar.base.constant.CommonNetConstantDef;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.model:
//            RpcResponse, RpcRequest, SysHeader

public class RpcSysError
{

    private RpcSysError(String errorCode, String errorMsg)
    {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static RpcSysError create(String errorCode, String errorMsg)
    {
        return new RpcSysError(errorCode, errorMsg);
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public RpcResponse toReponse(RpcRequest req)
    {
        RpcResponse res = new RpcResponse();
        res.setRqId(req.getSysHeader().getReqId());
        res.setContent(getErrorMsg());
        res.setRetCode(getErrorCode());
        res.setRetMsg(getErrorMsg());
        res.setStatus("error");
        return res;
    }

    public static final RpcSysError serverBusy = new RpcSysError("001", cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C026());
    public static final RpcSysError serverNotRet = new RpcSysError("002", cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C027());
    public static final RpcSysError serverUnknowException = new RpcSysError("003", cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C028());
    public static final RpcSysError serverClientVersionNotSame = new RpcSysError("004", cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C029());
    private String errorCode;
    private String errorMsg;

}
