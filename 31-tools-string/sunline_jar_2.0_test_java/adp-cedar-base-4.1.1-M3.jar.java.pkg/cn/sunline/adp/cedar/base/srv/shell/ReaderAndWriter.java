// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReaderAndWriter.java

package cn.sunline.adp.cedar.base.srv.shell;

import java.io.IOException;
import java.io.Writer;

public interface ReaderAndWriter
{

    public abstract Writer getWriter();

    public abstract String readLine(String s)
        throws IOException;

    public abstract String readLine(String s, char c)
        throws IOException;

    public abstract String readLine()
        throws IOException;

    public abstract void print(String s)
        throws IOException;

    public abstract void println()
        throws IOException;

    public abstract void println(String s)
        throws IOException;

    public abstract void flush()
        throws IOException;
}
