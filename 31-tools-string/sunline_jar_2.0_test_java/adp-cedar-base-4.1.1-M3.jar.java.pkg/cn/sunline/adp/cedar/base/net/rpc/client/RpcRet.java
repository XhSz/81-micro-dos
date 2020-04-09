// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcRet.java

package cn.sunline.adp.cedar.base.net.rpc.client;


// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.client:
//            RpcFuture, RpcStatusFuture

public class RpcRet
{

    public RpcRet(String reqid)
    {
        realRet = new RpcFuture(reqid);
        statusRet = new RpcStatusFuture(reqid);
    }

    public RpcFuture getRealRet()
    {
        return realRet;
    }

    public RpcStatusFuture getStatusRet()
    {
        return statusRet;
    }

    public void cancel()
    {
        statusRet.cancel(true);
        realRet.cancel(true);
    }

    RpcFuture realRet;
    RpcStatusFuture statusRet;
}
