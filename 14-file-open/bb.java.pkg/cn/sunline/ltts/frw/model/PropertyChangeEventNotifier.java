// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertyChangeEventNotifier.java

package cn.sunline.ltts.frw.model;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEventNotifier
{

    public abstract void addPropertyChangeListener(PropertyChangeListener propertychangelistener);

    public abstract void removePropertyChangeListener(PropertyChangeListener propertychangelistener);

    public abstract PropertyChangeListener[] getPropertyChangeListeners();

    public abstract void addPropertyChangeListener(String s, PropertyChangeListener propertychangelistener);

    public abstract void removePropertyChangeListener(String s, PropertyChangeListener propertychangelistener);

    public abstract PropertyChangeListener[] getPropertyChangeListeners(String s);
}
