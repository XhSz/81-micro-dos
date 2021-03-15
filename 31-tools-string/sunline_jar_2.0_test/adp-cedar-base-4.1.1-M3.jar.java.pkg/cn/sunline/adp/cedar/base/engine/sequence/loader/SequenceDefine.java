// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceDefine.java

package cn.sunline.adp.cedar.base.engine.sequence.loader;


// Referenced classes of package cn.sunline.adp.cedar.base.engine.sequence.loader:
//            SequenceBase

public class SequenceDefine extends SequenceBase
{

    public SequenceDefine()
    {
    }

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public String getCorporationId()
    {
        return corporationId;
    }

    public void setCorporationId(String corporationId)
    {
        this.corporationId = corporationId;
    }

    public String getResetMode()
    {
        return resetMode;
    }

    public void setResetMode(String resetMode)
    {
        this.resetMode = resetMode;
    }

    private String systemId;
    private String corporationId;
    private String resetMode;
}
