// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransExecutorRemoteImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.executor;

import cn.sunline.adp.cedar.base.component.ComponentCacher;
import cn.sunline.adp.cedar.base.engine.HeaderDataConstants;
import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.component.TransExecutorCompImpl;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import java.util.Map;

public class TransExecutorRemoteImpl extends cn.sunline.adp.cedar.busi.sdk.component.TransExecutorCompImpl.TransExecutorRemote
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.TransExecutor
{

    public TransExecutorRemoteImpl()
    {
    }

    public Map call(Map inputData)
        throws EdspServiceException
    {
        cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Pack pack = (cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Pack)ComponentCacher.getInstance(cn/sunline/adp/cedar/busi/sdk/component/BaseComp$Pack, getPkgAbstId());
        if(pack == null)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E028(getPkgAbstId());
        String in = pack.format(inputData);
        cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Appc appc = (cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Appc)ComponentCacher.getInstance(cn/sunline/adp/cedar/busi/sdk/component/BaseComp$Appc, getAppcAbstId());
        if(appc == null)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E028(getPkgAbstId());
        String out = appc.call(in);
        Map ret;
        try
        {
            ret = pack.parse(out);
        }
        catch(Exception e)
        {
            DataArea dataContext = DataArea.buildWithEmpty();
            dataContext.getSystem().put(HeaderDataConstants.NAME_ERORTX, String.format(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C020(), new Object[] {
                out
            }));
            dataContext.getSystem().put(HeaderDataConstants.NAME_ERORCD, "0099");
            ret = dataContext.getData();
        }
        return ret;
    }
}
