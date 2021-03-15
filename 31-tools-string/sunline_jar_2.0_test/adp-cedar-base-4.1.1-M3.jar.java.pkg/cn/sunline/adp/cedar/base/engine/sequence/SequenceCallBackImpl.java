// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceCallBackImpl.java

package cn.sunline.adp.cedar.base.engine.sequence;

import cn.sunline.adp.cedar.base.bean.SequenceMessage;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.errors.CorePluginErrorDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable;
import cn.sunline.adp.cedar.base.type.KBaseEnumType;
import cn.sunline.adp.cedar.base.util.CoreUtil;
import cn.sunline.adp.core.exception.AdpDaoDuplicateException;
import cn.sunline.adp.metadata.loader.db.util.DaoUtil;
import cn.sunline.edsp.base.lang.RunnableWithReturn;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.sequence:
//            SequenceCallback

public class SequenceCallBackImpl
    implements SequenceCallback
{
    private static class Sequence
    {

        public synchronized String next()
        {
            if(maxValue >= currentValue + (long)incrementBy)
            {
                currentValue = currentValue + (long)incrementBy;
                return Long.toString(currentValue);
            } else
            {
                cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_define lsdy = getSequenceInfo(sequenceId);
                maxValue = lsdy.getMax_serial_value().longValue();
                currentValue = lsdy.getCurrent_serial_value().longValue();
                incrementBy = lsdy.getStep_value().intValue();
                return Long.toString(currentValue);
            }
        }

        private cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_define getSequenceInfo(final String sequenceId)
        {
            return (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_define)DaoUtil.executeInNewTransation(new RunnableWithReturn() {

                public cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_define execute()
                {
                    cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_define ret = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_defineDao.selectOneWithLock_id(sequenceId, CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), false);
                    if(ret == null)
                    {
                        ret = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_define)CoreUtil.getInstance(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_define);
                        ret.setSerial_code(sequenceId);
                        ret.setCache_size(Integer.valueOf(100));
                        ret.setStep_value(Integer.valueOf(1));
                        ret.setSerial_name(sequenceId);
                        ret.setMin_serial_value(Long.valueOf(0L));
                        ret.setMax_serial_value(Long.valueOf(0x5f5e0ffL));
                        ret.setCurrent_serial_value(Long.valueOf(0L));
                        ret.setSystem_code(CoreUtil.getSystemId());
                        ret.setCorporate_code(CoreUtil.getDefaultTenantId());
                        try
                        {
                            cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_defineDao.insert(ret);
                        }
                        catch(AdpDaoDuplicateException e)
                        {
                            ret = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_defineDao.selectOneWithLock_id(sequenceId, CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), false);
                            if(ret == null)
                                throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E022(e);
                        }
                    }
                    long currentValue = ret.getCurrent_serial_value().longValue();
                    if((long)ret.getCache_size().intValue() + ret.getCurrent_serial_value().longValue() < ret.getMax_serial_value().longValue())
                        ret.setCurrent_serial_value(Long.valueOf((long)ret.getCache_size().intValue() + ret.getCurrent_serial_value().longValue()));
                    else
                        ret.setCurrent_serial_value(ret.getMin_serial_value());
                    int count = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_defineDao.updateOne_id(ret);
                    if(count == 0)
                    {
                        throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E023(sequenceId);
                    } else
                    {
                        ret.setMax_serial_value(ret.getCurrent_serial_value());
                        ret.setCurrent_serial_value(Long.valueOf(currentValue + (long)ret.getStep_value().intValue()));
                        return ret;
                    }
                }

                public volatile Object execute()
                {
                    return execute();
                }

                final String val$sequenceId;
                final Sequence this$0;

                
                {
                    this.this$0 = Sequence.this;
                    sequenceId = s;
                    super();
                }
            }
);
        }

        private String sequenceId;
        private int incrementBy;
        private long maxValue;
        private long currentValue;








        public Sequence(String sequenceId)
        {
            incrementBy = 1;
            maxValue = 0L;
            currentValue = 0L;
            this.sequenceId = sequenceId;
        }
    }


    public SequenceCallBackImpl()
    {
        log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/sequence/SequenceCallBackImpl);
    }

    public SequenceMessage nextval(String type, String sequenceName)
    {
        Sequence sequence = (Sequence)caches.get(sequenceName);
        if(sequence == null)
            sequence = initSequence(sequenceName);
        return new SequenceMessage(null, sequence.next());
    }

    public synchronized Sequence initSequence(String sequenceName)
    {
        Sequence sequence = (Sequence)caches.get(sequenceName);
        if(sequence == null)
        {
            sequence = new Sequence(sequenceName);
            caches.put(sequenceName, sequence);
        }
        return sequence;
    }

    public void init()
    {
        try
        {
            final List list = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_registerDao.selectAll_odb2(CoreUtil.getSvcId(), cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT.hang, CoreUtil.getSubSystemId(), CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), false);
            DaoUtil.executeInNewTransation(new RunnableWithReturn() {

                public Void execute()
                {
                    for(Iterator iterator = list.iterator(); iterator.hasNext();)
                    {
                        cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_register liuszc = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_register)iterator.next();
                        Sequence sequence = (Sequence)caches.get(liuszc.getSerial_name());
                        if(sequence == null)
                        {
                            sequence = new Sequence(liuszc.getSerial_name());
                            caches.put(liuszc.getSerial_name(), sequence);
                        }
                        sequence.currentValue = liuszc.getCurrent_serial_value().longValue();
                        sequence.maxValue = liuszc.getMax_serial_value().longValue();
                        sequence.incrementBy = liuszc.getStep_value().intValue();
                        try
                        {
                            liuszc.setSerial_register_status(cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT.using);
                            cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_registerDao.updateOne_odb1(liuszc);
                        }
                        catch(Exception e)
                        {
                            log.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C001(), new Object[] {
                                liuszc.getSerial_name(), e.getMessage()
                            });
                        }
                    }

                    return null;
                }

                public volatile Object execute()
                {
                    return execute();
                }

                final List val$list;
                final SequenceCallBackImpl this$0;

            
            {
                this.this$0 = SequenceCallBackImpl.this;
                list = list1;
                super();
            }
            }
);
        }
        catch(Exception e)
        {
            log.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C002(), new Object[] {
                e.getMessage()
            });
        }
    }

    public void collection()
    {
        DaoUtil.executeInNewTransation(new RunnableWithReturn() {

            public Void execute()
            {
                Iterator iterator = caches.entrySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    java.util.Map.Entry item = (java.util.Map.Entry)iterator.next();
                    Sequence sequence = (Sequence)item.getValue();
                    if(sequence != null)
                    {
                        cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_register liuszc = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_register)CoreUtil.getInstance(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register);
                        liuszc.setSerial_name(sequence.sequenceId);
                        liuszc.setCurrent_serial_value(Long.valueOf(sequence.currentValue));
                        liuszc.setMax_serial_value(Long.valueOf(sequence.maxValue));
                        liuszc.setStep_value(Integer.valueOf(sequence.incrementBy));
                        liuszc.setService_code(CoreUtil.getSvcId());
                        liuszc.setSerial_register_status(cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT.hang);
                        liuszc.setSystem_code(CoreUtil.getSystemId());
                        liuszc.setCorporate_code(CoreUtil.getDefaultTenantId());
                        liuszc.setSub_system_code(CoreUtil.getSubSystemId());
                        try
                        {
                            int i = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_registerDao.updateOne_odb1(liuszc);
                            if(i == 0)
                                cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_registerDao.insert(liuszc);
                        }
                        catch(Exception e)
                        {
                            log.warn(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C003(), new Object[] {
                                sequence.sequenceId, e.getMessage()
                            });
                        }
                    }
                } while(true);
                return null;
            }

            public volatile Object execute()
            {
                return execute();
            }

            final SequenceCallBackImpl this$0;

            
            {
                this.this$0 = SequenceCallBackImpl.this;
                super();
            }
        }
);
    }

    private final Map caches = new ConcurrentHashMap();
    private SysLog log;


}
