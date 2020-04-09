// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EngineRuntimeContext.java

package cn.sunline.adp.cedar.base.engine.datamapping;

import cn.sunline.adp.cedar.base.engine.EnvDataHelper;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.bean.ModelObjectCreatorUtil;
import cn.sunline.adp.metadata.loader.util.ModelFactoryUtil;
import cn.sunline.adp.metadata.model.ComplexType;
import cn.sunline.adp.metadata.model.ModelFactory;
import cn.sunline.adp.metadata.model.datainterface.*;
import java.util.HashMap;
import java.util.Map;

public class EngineRuntimeContext
{

    public EngineRuntimeContext(String txnCode, String runEnvMetadataId, String resEnvMetadataId)
    {
        innerServiceCode = txnCode;
        innerServiceImplCode = txnCode;
        this.runEnvMetadataId = runEnvMetadataId;
        this.resEnvMetadataId = resEnvMetadataId;
    }

    public EngineRuntimeContext(String innerServiceCode, String innerServiceImplCode, String runEnvMetadataId, String resEnvMetadataId)
    {
        this.innerServiceCode = innerServiceCode;
        this.innerServiceImplCode = innerServiceImplCode;
        this.runEnvMetadataId = runEnvMetadataId;
        this.resEnvMetadataId = resEnvMetadataId;
    }

    public String getInnerServiceCode()
    {
        return innerServiceCode;
    }

    public String getInnerServiceImplCode()
    {
        return innerServiceImplCode;
    }

    public Object getTrxRunEnvs()
    {
        if(trxRunEnvs_ == null)
            trxRunEnvs_ = createTrxRspEnvs(runEnvMetadataId);
        return trxRunEnvs_;
    }

    public Object getTrxRspEnvs()
    {
        if(trxRspEnvs_ == null)
            trxRspEnvs_ = createTrxRspEnvs(resEnvMetadataId);
        return trxRspEnvs_;
    }

    public Object getTrxInput()
    {
        if(trxInput == null)
            if(dataInterface == null)
            {
                trxInput = new HashMap();
            } else
            {
                Input input = dataInterface.getInput();
                if(input.isInterfacePackMode())
                    trxInput = ModelObjectCreatorUtil.getModelObjectCreator().create(input.getJavaClass());
                else
                    trxInput = new HashMap();
            }
        return trxInput;
    }

    public Object getTrxProperty()
    {
        if(trxProperty == null)
            if(dataInterface == null)
            {
                trxProperty = new HashMap();
            } else
            {
                Property property = dataInterface.getProperty();
                if(property.isInterfacePackMode())
                    trxProperty = ModelObjectCreatorUtil.getModelObjectCreator().create(property.getJavaClass());
                else
                    trxProperty = new HashMap();
            }
        return trxProperty;
    }

    public Object getTrxOutput()
    {
        if(trxOutput == null)
            if(dataInterface == null)
            {
                trxOutput = new HashMap();
            } else
            {
                Output output = dataInterface.getOutput();
                if(output.isInterfacePackMode())
                    trxOutput = ModelObjectCreatorUtil.getModelObjectCreator().create(output.getJavaClass());
                else
                    trxOutput = new HashMap();
            }
        return trxOutput;
    }

    public EnvDataHelper getTrxRunEnvsHelper()
    {
        Object runEnvs = getTrxRunEnvs();
        return new EnvDataHelper(runEnvs);
    }

    public DataArea getRunDataArea()
    {
        DataArea ret = DataArea.buildWithEmpty();
        ret.setCommReq((Map)getTrxRunEnvs());
        ret.setInput((Map)getTrxInput());
        ret.setOutput((Map)getTrxOutput());
        ret.setProperty((Map)getTrxProperty());
        return ret;
    }

    private Object createTrxRspEnvs(String metadataId)
    {
        ComplexType ct = (ComplexType)ModelFactoryUtil.getModelFactory().getModel(cn/sunline/adp/metadata/model/ComplexType, metadataId);
        if(ct == null)
            return new HashMap();
        else
            return ModelObjectCreatorUtil.getModelObjectCreator().create(ct.getJavaClass());
    }

    public void setDataInterface(DataInterface dataInterface)
    {
        this.dataInterface = dataInterface;
    }

    public EngineRuntimeContext clone(String innerServiceCode, String innerServiceImplCode)
    {
        EngineRuntimeContext ret = new EngineRuntimeContext(innerServiceCode, innerServiceImplCode, runEnvMetadataId, resEnvMetadataId);
        ret.trxRunEnvs_ = trxRunEnvs_;
        ret.trxRspEnvs_ = trxRspEnvs_;
        return ret;
    }

    private final String innerServiceCode;
    private final String innerServiceImplCode;
    private DataInterface dataInterface;
    private final String runEnvMetadataId;
    private final String resEnvMetadataId;
    private Object trxRunEnvs_;
    private Object trxRspEnvs_;
    private Object trxInput;
    private Object trxOutput;
    private Object trxProperty;
}
