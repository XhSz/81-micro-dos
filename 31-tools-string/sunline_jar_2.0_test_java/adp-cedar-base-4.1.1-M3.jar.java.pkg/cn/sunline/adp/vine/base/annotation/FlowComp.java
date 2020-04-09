// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FlowComp.java

package cn.sunline.adp.vine.base.annotation;

import java.lang.annotation.Annotation;

public interface FlowComp
    extends Annotation
{

    public abstract String name();

    public abstract String category();

    public abstract String iconName();
}
