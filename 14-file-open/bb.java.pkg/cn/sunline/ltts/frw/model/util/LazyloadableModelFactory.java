// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LazyloadableModelFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.frw.model.ModelFactory;

public interface LazyloadableModelFactory
    extends ModelFactory
{

    public abstract void putModel(Class class1, ModelObject modelobject);

    public abstract ModelObject parseModelFile(ModelFile modelfile);
}
