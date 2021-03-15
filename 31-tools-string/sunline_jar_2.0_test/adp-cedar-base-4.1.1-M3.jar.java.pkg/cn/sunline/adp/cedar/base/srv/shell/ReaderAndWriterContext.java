// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReaderAndWriterContext.java

package cn.sunline.adp.cedar.base.srv.shell;


// Referenced classes of package cn.sunline.adp.cedar.base.srv.shell:
//            ReaderAndWriter

public class ReaderAndWriterContext
{

    public ReaderAndWriterContext()
    {
    }

    public static ReaderAndWriter get()
    {
        return (ReaderAndWriter)instance.get();
    }

    public static void set(ReaderAndWriter rw)
    {
        instance.set(rw);
    }

    private static ThreadLocal instance = new ThreadLocal();

}
