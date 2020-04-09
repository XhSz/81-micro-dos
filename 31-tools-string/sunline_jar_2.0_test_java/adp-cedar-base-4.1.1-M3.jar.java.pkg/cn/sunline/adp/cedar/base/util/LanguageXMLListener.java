// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LanguageXMLListener.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.logging.IXMLListener;
import cn.sunline.adp.metadata.loader.xml.LanguageListener;

public class LanguageXMLListener
    implements IXMLListener
{

    public LanguageXMLListener()
    {
    }

    public void beforeUnmarshal(Object target, Object parent)
    {
        languageListener.beforeUnmarshal(target, parent);
    }

    public void afterUnmarshal(Object target, Object parent)
    {
        languageListener.afterUnmarshal(target, parent);
    }

    private static final LanguageListener languageListener = new LanguageListener();

}
