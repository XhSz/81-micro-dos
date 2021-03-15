// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBPassSecurity.java

package cn.sunline.adp.cedar.base.security;


public interface DBPassSecurity
{

    public abstract String encrypt(String s);

    public abstract String decrypt(String s);
}
