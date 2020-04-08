// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelFileLoader.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.model.ModelFile;

public interface ModelFileLoader
{

    public abstract ModelFile load(Class class1, Object obj);
}
