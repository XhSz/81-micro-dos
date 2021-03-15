// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceDefineLoader.java

package cn.sunline.adp.cedar.base.engine.sequence.loader;

import cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable;
import cn.sunline.adp.cedar.base.util.CoreUtil;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.sequence.loader:
//            SequenceDefine

public class SequenceDefineLoader
{

    public SequenceDefineLoader()
    {
    }

    public static List loadAll()
    {
        String systemCode = CoreUtil.getSystemId();
        String tenantId = CoreUtil.getDefaultTenantId();
        List dbSeqBeanList = cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.Tsp_sequence_generate_defineDao.selectAll_tsp_seq_genrate_odb2(systemCode, tenantId, false);
        if(dbSeqBeanList == null || dbSeqBeanList.isEmpty())
            return null;
        List sequenceDefineList = new ArrayList();
        cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_generate_define dbBean;
        for(Iterator iterator = dbSeqBeanList.iterator(); iterator.hasNext(); sequenceDefineList.add(toSequenceDefine(dbBean)))
            dbBean = (cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_generate_define)iterator.next();

        return sequenceDefineList;
    }

    private static SequenceDefine toSequenceDefine(cn.sunline.adp.cedar.base.tables.KSysSequenceDefineTable.tsp_sequence_generate_define dbBean)
    {
        SequenceDefine bean = new SequenceDefine();
        bean.setSystemId(dbBean.getSystem_code());
        bean.setCorporationId(dbBean.getCorporate_code());
        bean.setSequenceType(dbBean.getSerial_type());
        bean.setMaxSequenceValue(dbBean.getMax_serial_value().longValue());
        bean.setMinSequenceValue(dbBean.getMin_serial_value().longValue());
        bean.setCacheSize(dbBean.getCache_size().intValue());
        bean.setStepValue(dbBean.getStep_value().intValue());
        bean.setResetMode(dbBean.getReset_mode());
        return bean;
    }
}
