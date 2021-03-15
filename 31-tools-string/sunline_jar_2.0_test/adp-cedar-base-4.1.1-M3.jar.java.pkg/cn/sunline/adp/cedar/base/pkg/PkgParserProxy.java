// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PkgParserProxy.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.metadata.model.datainterface.DataInterface;
import cn.sunline.edsp.base.lang.RunnableWithReturn;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.io.UnsupportedEncodingException;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgParser, PkgMode, PkgFactory

public class PkgParserProxy
    implements PkgParser
{

    public PkgParserProxy(PkgParser delegator)
    {
        this.delegator = delegator;
    }

    public DataArea parse(final String data, final DataInterface dataInterface, final PkgMode mode)
    {
        return (DataArea)ProfileUtil.doProfile("pack.parse", new RunnableWithReturn() {

            public DataArea execute()
            {
                try
                {
                    return delegator.parse(data, dataInterface, mode);
                }
                catch(Exception e)
                {
                    throw ExceptionUtil.wrapThrow(BaseConst.PkgParser01, e, new String[] {
                        data, PkgFactory.get().getDefaultPkgencoding()
                    });
                }
            }

            public volatile Object execute()
            {
                return execute();
            }

            final String val$data;
            final DataInterface val$dataInterface;
            final PkgMode val$mode;
            final PkgParserProxy this$0;

            
            {
                this.this$0 = PkgParserProxy.this;
                data = s;
                dataInterface = datainterface;
                mode = pkgmode;
                super();
            }
        }
);
    }

    public DataArea parse(final byte bytes[], final String encoding, final DataInterface dataInterface, final PkgMode mode)
    {
        return (DataArea)ProfileUtil.doProfile("pack.parse", new RunnableWithReturn() {

            public DataArea execute()
            {
                try
                {
                    return delegator.parse(bytes, encoding, dataInterface, mode);
                }
                catch(Exception e)
                {
                    String msg = null;
                    try
                    {
                        msg = new String(bytes, encoding);
                    }
                    catch(UnsupportedEncodingException e1)
                    {
                        PkgParserProxy.log.error(e1);
                    }
                    throw ExceptionUtil.wrapThrow(BaseConst.PkgParser01, e, new String[] {
                        msg, encoding
                    });
                }
            }

            public volatile Object execute()
            {
                return execute();
            }

            final byte val$bytes[];
            final String val$encoding;
            final DataInterface val$dataInterface;
            final PkgMode val$mode;
            final PkgParserProxy this$0;

            
            {
                this.this$0 = PkgParserProxy.this;
                bytes = abyte0;
                encoding = s;
                dataInterface = datainterface;
                mode = pkgmode;
                super();
            }
        }
);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/pkg/PkgParserProxy);
    private final PkgParser delegator;



}
