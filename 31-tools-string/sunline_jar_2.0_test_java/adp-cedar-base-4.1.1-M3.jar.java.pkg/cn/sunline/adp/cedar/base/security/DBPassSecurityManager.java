// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBPassSecurityManager.java

package cn.sunline.adp.cedar.base.security;


// Referenced classes of package cn.sunline.adp.cedar.base.security:
//            DBPassSecurity

public interface DBPassSecurityManager
{

    public abstract void init(String s);

    public abstract DBPassSecurity getSecurity();
}
