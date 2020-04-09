// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnicodeReader.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.metadata.base.util.CommUtil_;
import java.io.*;

public class UnicodeReader extends Reader
{

    public UnicodeReader(InputStream in, String defaultEnc)
    {
        internalIn2 = null;
        internalIn = new PushbackInputStream(in, 4);
        this.defaultEnc = CommUtil_.isNull(defaultEnc) ? defaultEnc : defaultEnc.toUpperCase();
    }

    public String getDefaultEncoding()
    {
        return defaultEnc;
    }

    public String getEncoding()
    {
        if(internalIn2 == null)
            return null;
        else
            return internalIn2.getEncoding();
    }

    protected void init()
        throws IOException
    {
        if(internalIn2 != null)
            return;
        byte bom[] = new byte[4];
        int n = internalIn.read(bom, 0, bom.length);
        String encoding;
        int unread;
        if(bom[0] == 0 && bom[1] == 0 && bom[2] == -2 && bom[3] == -1)
        {
            encoding = "UTF-32BE";
            unread = n - 4;
        } else
        if(bom[0] == -1 && bom[1] == -2 && bom[2] == 0 && bom[3] == 0)
        {
            encoding = "UTF-32LE";
            unread = n - 4;
        } else
        if(bom[0] == -17 && bom[1] == -69 && bom[2] == -65)
        {
            encoding = "UTF-8";
            unread = n - 3;
        } else
        if(bom[0] == -2 && bom[1] == -1)
        {
            encoding = "UTF-16BE";
            unread = n - 2;
        } else
        if(bom[0] == -1 && bom[1] == -2)
        {
            encoding = "UTF-16LE";
            unread = n - 2;
        } else
        {
            encoding = defaultEnc;
            unread = n;
        }
        if(unread > 0)
            internalIn.unread(bom, n - unread, unread);
        if(encoding == null)
            internalIn2 = new InputStreamReader(internalIn);
        else
            internalIn2 = new InputStreamReader(internalIn, encoding);
    }

    public void close()
        throws IOException
    {
        init();
        internalIn2.close();
    }

    public int read(char cbuf[], int off, int len)
        throws IOException
    {
        init();
        return internalIn2.read(cbuf, off, len);
    }

    PushbackInputStream internalIn;
    InputStreamReader internalIn2;
    String defaultEnc;
    private static final int BOM_SIZE = 4;
}
