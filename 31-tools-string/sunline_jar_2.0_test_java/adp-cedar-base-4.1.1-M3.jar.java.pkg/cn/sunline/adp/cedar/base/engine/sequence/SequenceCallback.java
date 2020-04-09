// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceCallback.java

package cn.sunline.adp.cedar.base.engine.sequence;

import cn.sunline.adp.cedar.base.bean.SequenceMessage;

public interface SequenceCallback
{

    public abstract void init();

    public abstract SequenceMessage nextval(String s, String s1);

    public abstract void collection();
}
