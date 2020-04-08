// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelObjectLocator.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.model.ModelFile;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Locator;

public class ModelObjectLocator
{

    public ModelObjectLocator()
    {
        endLocatorByField = new HashMap();
    }

    public Locator getFieldLocator(String field)
    {
        return (Locator)endLocatorByField.get(field);
    }

    public ModelFile getFile()
    {
        return file;
    }

    public Locator getStartElementEndLocator()
    {
        return startElementEndLocator;
    }

    public Locator getEndElementEndLocator()
    {
        return endElementEndLocator;
    }

    public Map getEndLocatorByField()
    {
        return endLocatorByField;
    }

    public void setFile(ModelFile file)
    {
        this.file = file;
    }

    public void setStartElementEndLocator(Locator startElementEndLocator)
    {
        this.startElementEndLocator = startElementEndLocator;
    }

    public void setEndElementEndLocator(Locator endElementEndLocator)
    {
        this.endElementEndLocator = endElementEndLocator;
    }

    private ModelFile file;
    private Locator startElementEndLocator;
    private Locator endElementEndLocator;
    private Map endLocatorByField;
}
