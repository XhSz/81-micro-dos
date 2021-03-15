// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PkgParser.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.metadata.model.datainterface.DataInterface;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgMode

public interface PkgParser
{

    public abstract DataArea parse(String s, DataInterface datainterface, PkgMode pkgmode);

    public abstract DataArea parse(byte abyte0[], String s, DataInterface datainterface, PkgMode pkgmode);
}
