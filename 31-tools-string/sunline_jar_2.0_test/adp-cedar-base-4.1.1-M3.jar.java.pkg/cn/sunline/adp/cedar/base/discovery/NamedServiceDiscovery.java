// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedServiceDiscovery.java

package cn.sunline.adp.cedar.base.discovery;

import java.util.Collection;

public interface NamedServiceDiscovery
{

    /**
     * @deprecated Method queryForInActiveInstances is deprecated
     */

    public abstract Collection queryForInActiveInstances();

    public abstract Collection queryForActiveInstances();

    public abstract boolean isInstanceActive(String s, Long long1);

    public abstract void heartbeat();

    public abstract void start();

    public abstract void close();

    public abstract String getName();
}
