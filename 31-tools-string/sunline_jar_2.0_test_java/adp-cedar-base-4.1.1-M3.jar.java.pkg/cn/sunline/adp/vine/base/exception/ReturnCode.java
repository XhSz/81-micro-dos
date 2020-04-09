// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReturnCode.java

package cn.sunline.adp.vine.base.exception;

import cn.sunline.adp.vine.base.util.code.ReturnCodeUtil;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            IReturnCodeMessage

public class ReturnCode
    implements IReturnCodeMessage
{

    protected ReturnCode(String returnCode, String orginMsg)
    {
        retCode = returnCode;
        this.orginMsg = orginMsg;
        retMsgList.add(this);
    }

    public String getCode()
    {
        return retCode;
    }

    public String getMessage()
    {
        String msg = ReturnCodeUtil.getReturnCodeMessage(retCode);
        return msg != null ? msg : orginMsg;
    }

    private String retCode;
    private String orginMsg;
    private static List retMsgList = new ArrayList();
    public static final ReturnCode SUCCESS = new ReturnCode("00000000", "Success");
    public static final ReturnCode EXCEPTION = new ReturnCode("AAAA0001", "Fail");

}
