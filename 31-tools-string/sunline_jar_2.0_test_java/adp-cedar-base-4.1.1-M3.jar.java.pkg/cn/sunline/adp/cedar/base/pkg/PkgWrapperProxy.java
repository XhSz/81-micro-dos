// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PkgWrapperProxy.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.metadata.model.datainterface.DataInterface;
import cn.sunline.edsp.base.lang.RunnableWithReturn;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgWrapper, PkgMode

public class PkgWrapperProxy
    implements PkgWrapper
{

    public PkgWrapperProxy(PkgWrapper delegator)
    {
        this.delegator = delegator;
    }

    public String format(final DataArea response, final DataInterface dataInterface, final PkgMode mode)
    {
        return (String)ProfileUtil.doProfile("pack.format", new RunnableWithReturn() {

            public String execute()
            {
                return delegator.format(response, dataInterface, mode);
            }

            public volatile Object execute()
            {
                return execute();
            }

            final DataArea val$response;
            final DataInterface val$dataInterface;
            final PkgMode val$mode;
            final PkgWrapperProxy this$0;

            
            {
                this.this$0 = PkgWrapperProxy.this;
                response = dataarea;
                dataInterface = datainterface;
                mode = pkgmode;
                super();
            }
        }
);
    }

    public byte[] format(final DataArea response, final String encoding, final DataInterface dataInterface, final PkgMode mode)
    {
        return (byte[])ProfileUtil.doProfile("pack.format", new RunnableWithReturn() {

            public byte[] execute()
            {
                return delegator.format(response, encoding, dataInterface, mode);
            }

            public volatile Object execute()
            {
                return execute();
            }

            final DataArea val$response;
            final String val$encoding;
            final DataInterface val$dataInterface;
            final PkgMode val$mode;
            final PkgWrapperProxy this$0;

            
            {
                this.this$0 = PkgWrapperProxy.this;
                response = dataarea;
                encoding = s;
                dataInterface = datainterface;
                mode = pkgmode;
                super();
            }
        }
);
    }

    private final PkgWrapper delegator;

}
