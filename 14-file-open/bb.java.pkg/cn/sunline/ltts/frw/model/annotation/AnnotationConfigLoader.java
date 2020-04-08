// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnnotationConfigLoader.java

package cn.sunline.ltts.frw.model.annotation;

import cn.sunline.ltts.core.api.model.ModelObject;
import java.lang.annotation.Annotation;

public interface AnnotationConfigLoader
{

    public abstract ModelObject load(Class class1, Annotation annotation);
}
