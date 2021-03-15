// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceCallBackResetImpl.java

package cn.sunline.adp.cedar.base.engine.sequence;

import cn.sunline.adp.cedar.base.bean.SequenceMessage;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import cn.sunline.adp.cedar.base.engine.sequence.loader.SequenceBase;
import cn.sunline.adp.cedar.base.engine.sequence.loader.SequenceDefine;
import cn.sunline.adp.cedar.base.engine.sequence.loader.SequenceDefineLoader;
import cn.sunline.adp.cedar.base.errors.CorePluginErrorDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable;
import cn.sunline.adp.cedar.base.type.KBaseEnumType;
import cn.sunline.adp.cedar.base.util.CoreUtil;
import cn.sunline.adp.core.exception.AdpDaoDuplicateException;
import cn.sunline.adp.metadata.loader.db.util.DaoUtil;
import cn.sunline.edsp.base.lang.RunnableWithReturn;
import cn.sunline.edsp.base.util.collection.CollectionUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.sequence:
//            SequenceCallback

public class SequenceCallBackResetImpl
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
                SequenceBase sequenceBase = getSequenceInfo(sequenceId);
                incrementBy = sequenceBase.getStepValue();
                maxValue = sequenceBase.getMaxSequenceValue();
                currentValue = sequenceBase.getCurrentSequenceValue();
                return Long.toString(currentValue);
            }
        }

        private SequenceBase getSequenceInfo(final String sequenceId)
        {
            final SequenceBase sequenceBase = new SequenceBase();
            return (SequenceBase)DaoUtil.executeInNewTransation(new RunnableWithReturn() {

                public SequenceBase execute()
                {
                    SequenceDefine sequenceDefine;
                    if(sequenceId.lastIndexOf("-") == -1)
                        sequenceDefine = (SequenceDefine)SequenceCallBackResetImpl.sequenceDefinesCache.get(sequenceId);
                    else
                        sequenceDefine = (SequenceDefine)SequenceCallBackResetImpl.sequenceDefinesCache.get(sequenceId.substring(0, sequenceId.lastIndexOf("-")));
                    cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_detail ret = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_detailDao.selectOneWithLock_tsp_seq_detail_odb(CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), sequenceId, false);
                    if(ret == null)
                    {
                        ret = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_detail)CoreUtil.getInstance(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail);
                        ret.setCurrent_serial_value(Long.valueOf(0L));
                        ret.setSystem_code(CoreUtil.getSystemId());
                        ret.setCorporate_code(CoreUtil.getDefaultTenantId());
                        ret.setSerial_name(sequenceId);
                        ret.setUpdate_time(new Timestamp(System.currentTimeMillis()));
                        try
                        {
                            cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_detailDao.insert(ret);
                        }
                        catch(AdpDaoDuplicateException e)
                        {
                            ret = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_detailDao.selectOneWithLock_tsp_seq_detail_odb(CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), sequenceId, false);
                            if(ret == null)
                                throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E022(e);
                        }
                    }
                    if((long)sequenceDefine.getCacheSize() + ret.getCurrent_serial_value().longValue() < sequenceDefine.getMaxSequenceValue())
                    {
                        sequenceBase.setCurrentSequenceValue(ret.getCurrent_serial_value().longValue() + (long)sequenceDefine.getStepValue());
                        ret.setCurrent_serial_value(Long.valueOf(ret.getCurrent_serial_value().longValue() + (long)sequenceDefine.getCacheSize()));
                    } else
                    {
                        sequenceBase.setCurrentSequenceValue(sequenceDefine.getMinSequenceValue());
                        ret.setCurrent_serial_value(Long.valueOf(sequenceDefine.getMinSequenceValue()));
                    }
                    int count = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_detailDao.updateOne_tsp_seq_detail_odb(ret);
                    if(count == 0)
                    {
                        throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E023(sequenceId);
                    } else
                    {
                        sequenceBase.setMaxSequenceValue(ret.getCurrent_serial_value().longValue());
                        sequenceBase.setStepValue(sequenceDefine.getStepValue());
                        return sequenceBase;
                    }
                }

                public volatile Object execute()
                {
                    return execute();
                }

                final String val$sequenceId;
                final SequenceBase val$sequenceBase;
                final Sequence this$0;

                
                {
                    this.this$0 = Sequence.this;
                    sequenceId = s;
                    sequenceBase = sequencebase;
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


    public SequenceCallBackResetImpl()
    {
        log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/engine/sequence/SequenceCallBackResetImpl);
    }

    public SequenceMessage nextval(String resetType, String sequenceType)
    {
        String sequenceName = sequenceType;
        SequenceDefine sequenceDefine = (SequenceDefine)sequenceDefinesCache.get(sequenceType);
        if(sequenceDefine == null)
            initSequenceDefine(sequenceType);
        if(!StringUtil.isEmpty(resetType))
            sequenceName = (new StringBuilder()).append(sequenceType).append("-").append(resetType).toString();
        Sequence sequence = (Sequence)caches.get(sequenceName);
        if(sequence == null)
            sequence = initSequence(sequenceName);
        return new SequenceMessage(resetType, sequence.next());
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
        List defineList = SequenceDefineLoader.loadAll();
        if(CollectionUtil.isNotEmpty(defineList))
        {
            SequenceDefine sequenceDefine;
            for(Iterator iterator = defineList.iterator(); iterator.hasNext(); sequenceDefinesCache.put(sequenceDefine.getSequenceType(), sequenceDefine))
                sequenceDefine = (SequenceDefine)iterator.next();

        }
        try
        {
            final List list = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_recycleDao.selectAll_tsp_seq_recycle_odb2(CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), CoreUtil.getSubSystemId(), CoreUtil.getSvcId(), cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT.hang, false);
            DaoUtil.executeInNewTransation(new RunnableWithReturn() {

                public Void execute()
                {
                    for(Iterator iterator1 = list.iterator(); iterator1.hasNext();)
                    {
                        cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_recycle liuszc = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_recycle)iterator1.next();
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
                            cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_recycleDao.updateOne_tsp_seq_recycle_odb1(liuszc);
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
                final SequenceCallBackResetImpl this$0;

            
            {
                this.this$0 = SequenceCallBackResetImpl.this;
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
                        cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_recycle liuszc = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_recycle)CoreUtil.getInstance(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle);
                        liuszc.setSerial_name(sequence.sequenceId);
                        liuszc.setCurrent_serial_value(Long.valueOf(sequence.currentValue));
                        liuszc.setMax_serial_value(Long.valueOf(sequence.maxValue));
                        liuszc.setStep_value(Integer.valueOf(sequence.incrementBy));
                        liuszc.setInstance_code(CoreUtil.getSvcId());
                        liuszc.setSerial_register_status(cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT.hang);
                        liuszc.setSystem_code(CoreUtil.getSystemId());
                        liuszc.setCorporate_code(CoreUtil.getDefaultTenantId());
                        liuszc.setSub_system_code(CoreUtil.getSubSystemId());
                        try
                        {
                            int i = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_recycleDao.updateOne_tsp_seq_recycle_odb1(liuszc);
                            if(i == 0)
                                cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_recycleDao.insert(liuszc);
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

            final SequenceCallBackResetImpl this$0;

            
            {
                this.this$0 = SequenceCallBackResetImpl.this;
                super();
            }
        }
);
    }

    private synchronized void initSequenceDefine(final String sequenceType)
    {
        DaoUtil.executeInNewTransation(new RunnableWithReturn() {

            public Void execute()
            {
                cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_generate_define dbSequenceDefine = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_generate_defineDao.selectOneWithLock_tsp_seq_generate_odb(CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), sequenceType, false);
                if(dbSequenceDefine == null)
                {
                    dbSequenceDefine = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_generate_define)CoreUtil.getInstance(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define);
                    dbSequenceDefine.setSystem_code(CoreUtil.getSystemId());
                    dbSequenceDefine.setCorporate_code(CoreUtil.getDefaultTenantId());
                    dbSequenceDefine.setSerial_type(sequenceType);
                    dbSequenceDefine.setCache_size(Integer.valueOf(100));
                    dbSequenceDefine.setMin_serial_value(Long.valueOf(0L));
                    dbSequenceDefine.setMax_serial_value(Long.valueOf(0x5f5e0ffL));
                    dbSequenceDefine.setStep_value(Integer.valueOf(1));
                    dbSequenceDefine.setReset_mode("");
                    try
                    {
                        cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_generate_defineDao.insert(dbSequenceDefine);
                    }
                    catch(AdpDaoDuplicateException e)
                    {
                        dbSequenceDefine = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_generate_defineDao.selectOneWithLock_tsp_seq_generate_odb(CoreUtil.getSystemId(), CoreUtil.getDefaultTenantId(), sequenceType, false);
                        if(dbSequenceDefine == null)
                            throw cn.sunline.adp.cedar.base.errors.CorePluginErrorDef.SP_CP.E022(e);
                    }
                }
                cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_generate_defineDao.updateOne_tsp_seq_generate_odb(dbSequenceDefine);
                return null;
            }

            public volatile Object execute()
            {
                return execute();
            }

            final String val$sequenceType;
            final SequenceCallBackResetImpl this$0;

            
            {
                this.this$0 = SequenceCallBackResetImpl.this;
                sequenceType = s;
                super();
            }
        }
);
        SequenceDefine sequenceDefine = new SequenceDefine();
        sequenceDefine.setSystemId(CoreUtil.getSystemId());
        sequenceDefine.setCorporationId(CoreUtil.getDefaultTenantId());
        sequenceDefine.setSequenceType(sequenceType);
        sequenceDefine.setCacheSize(100);
        sequenceDefine.setStepValue(1);
        sequenceDefine.setMaxSequenceValue(0x5f5e0ffL);
        sequenceDefine.setMinSequenceValue(0L);
        sequenceDefine.setResetMode("");
        sequenceDefinesCache.put(sequenceType, sequenceDefine);
    }

    private final Map caches = new ConcurrentHashMap();
    private SysLog log;
    private static Map sequenceDefinesCache = new ConcurrentHashMap();
    private static final String HYPHEN = "-";




}
