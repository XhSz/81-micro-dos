// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PkgWrapper.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.metadata.model.datainterface.DataInterface;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgMode

public interface PkgWrapper
{

    public abstract String format(DataArea dataarea, DataInterface datainterface, PkgMode pkgmode);

    public abstract byte[] format(DataArea dataarea, String s, DataInterface datainterface, PkgMode pkgmode);
}
