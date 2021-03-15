// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KSysSequenceDefineTable.java

package cn.sunline.adp.cedar.base.tables;

import cn.sunline.adp.cedar.base.type.KBaseEnumType;
import cn.sunline.adp.core.exception.*;
import cn.sunline.adp.core.lang.IString;
import cn.sunline.adp.metadata.loader.db.util.DaoUtil;
import cn.sunline.adp.metadata.model.annotation.ConfigType;
import cn.sunline.adp.metadata.model.annotation.Index;
import cn.sunline.edsp.base.lang.Params;
import java.sql.Timestamp;
import java.util.List;

public interface KSysSequenceDefineTable
{
    public static class Tsp_sequence_recycleDao
    {

        public static int insert(tsp_sequence_recycle entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insert(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e3);
            }
        }

        public static int insertWithReturnKey(tsp_sequence_recycle entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insertWithReturnKey(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e3);
            }
        }

        public static tsp_sequence_recycle selectOne_tsp_seq_recycle_odb1(String system_code, String sub_system_code, String corporate_code, String instance_code, String serial_name, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            Params parms = (new Params()).add("system_code", system_code).add("sub_system_code", sub_system_code).add("corporate_code", corporate_code).add("instance_code", instance_code).add("serial_name", serial_name);
            try
            {
                return (tsp_sequence_recycle)DaoUtil.selectOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle$tsp_seq_recycle_odb1, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C018, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e4);
            }
        }

        public static int updateOne_tsp_seq_recycle_odb1(tsp_sequence_recycle entity)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            try
            {
                return DaoUtil.updateOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle$tsp_seq_recycle_odb1, entity);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C009, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C019, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C007, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C003, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e4);
            }
        }

        public static List selectAll_tsp_seq_recycle_odb2(String system_code, String sub_system_code, String corporate_code, String instance_code, cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT serial_register_status, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException
        {
            Params parms = (new Params()).add("system_code", system_code).add("sub_system_code", sub_system_code).add("corporate_code", corporate_code).add("instance_code", instance_code).add("serial_register_status", serial_register_status);
            try
            {
                return DaoUtil.selectAllByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_recycle$tsp_seq_recycle_odb2, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e1);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_recycle_table_name)), e4);
            }
        }

        private static final IString tsp_sequence_recycle_table_name = new IString("KSysSequenceDefineTable.tsp_sequence_recycle.longname", "\u6D41\u6C34\u56DE\u6536\u8868");


        public Tsp_sequence_recycleDao()
        {
        }
    }

    public static interface tsp_sequence_recycle
    {
        public static class tsp_seq_recycle_odb2
            implements cn.sunline.adp.metadata.model.annotation.Index.Name
        {

            public tsp_seq_recycle_odb2()
            {
            }
        }

        public static class tsp_seq_recycle_odb1 extends cn.sunline.adp.metadata.model.annotation.Index.PrimaryKey
        {

            public tsp_seq_recycle_odb1()
            {
            }
        }


        public abstract String getSystem_code();

        public abstract void setSystem_code(String s);

        public abstract String getSub_system_code();

        public abstract void setSub_system_code(String s);

        public abstract String getCorporate_code();

        public abstract void setCorporate_code(String s);

        public abstract String getInstance_code();

        public abstract void setInstance_code(String s);

        public abstract String getSerial_name();

        public abstract void setSerial_name(String s);

        public abstract Integer getStep_value();

        public abstract void setStep_value(Integer integer);

        public abstract Long getCurrent_serial_value();

        public abstract void setCurrent_serial_value(Long long1);

        public abstract Long getMax_serial_value();

        public abstract void setMax_serial_value(Long long1);

        public abstract cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT getSerial_register_status();

        public abstract void setSerial_register_status(cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT e_liuszczt);

        public abstract String toString();
    }

    public static class Tsp_sequence_detailDao
    {

        public static int insert(tsp_sequence_detail entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insert(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e3);
            }
        }

        public static int insertWithReturnKey(tsp_sequence_detail entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insertWithReturnKey(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e3);
            }
        }

        public static tsp_sequence_detail selectOne_tsp_seq_detail_odb(String system_code, String corporate_code, String serial_name, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            Params parms = (new Params()).add("system_code", system_code).add("corporate_code", corporate_code).add("serial_name", serial_name);
            try
            {
                return (tsp_sequence_detail)DaoUtil.selectOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail$tsp_seq_detail_odb, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C018, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e4);
            }
        }

        public static int updateOne_tsp_seq_detail_odb(tsp_sequence_detail entity)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            try
            {
                return DaoUtil.updateOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail$tsp_seq_detail_odb, entity);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C009, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C019, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C007, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C003, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e4);
            }
        }

        public static tsp_sequence_detail selectOneWithLock_tsp_seq_detail_odb(String system_code, String corporate_code, String serial_name, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            Params parms = (new Params()).add("system_code", system_code).add("corporate_code", corporate_code).add("serial_name", serial_name);
            try
            {
                return (tsp_sequence_detail)DaoUtil.selectOneWithLockByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_detail$tsp_seq_detail_odb, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C018, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_detail_table_name)), e4);
            }
        }

        private static final IString tsp_sequence_detail_table_name = new IString("KSysSequenceDefineTable.tsp_sequence_detail.longname", "\u6D41\u6C34\u8BB0\u5F55\u8868");


        public Tsp_sequence_detailDao()
        {
        }
    }

    public static interface tsp_sequence_detail
    {
        public static class tsp_seq_detail_odb extends cn.sunline.adp.metadata.model.annotation.Index.PrimaryKey
        {

            public tsp_seq_detail_odb()
            {
            }
        }


        public abstract String getSystem_code();

        public abstract void setSystem_code(String s);

        public abstract String getCorporate_code();

        public abstract void setCorporate_code(String s);

        public abstract String getSerial_name();

        public abstract void setSerial_name(String s);

        public abstract Long getCurrent_serial_value();

        public abstract void setCurrent_serial_value(Long long1);

        public abstract Timestamp getUpdate_time();

        public abstract void setUpdate_time(Timestamp timestamp);

        public abstract String toString();
    }

    public static class Tsp_sequence_generate_defineDao
    {

        public static int insert(tsp_sequence_generate_define entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insert(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e3);
            }
        }

        public static int insertWithReturnKey(tsp_sequence_generate_define entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insertWithReturnKey(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e3);
            }
        }

        public static tsp_sequence_generate_define selectOne_tsp_seq_generate_odb(String system_code, String corporate_code, String serial_type, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            Params parms = (new Params()).add("system_code", system_code).add("corporate_code", corporate_code).add("serial_type", serial_type);
            try
            {
                return (tsp_sequence_generate_define)DaoUtil.selectOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define$tsp_seq_generate_odb, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C018, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e4);
            }
        }

        public static int updateOne_tsp_seq_generate_odb(tsp_sequence_generate_define entity)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            try
            {
                return DaoUtil.updateOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define$tsp_seq_generate_odb, entity);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C009, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C019, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C007, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C003, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e4);
            }
        }

        public static tsp_sequence_generate_define selectOneWithLock_tsp_seq_generate_odb(String system_code, String corporate_code, String serial_type, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            Params parms = (new Params()).add("system_code", system_code).add("corporate_code", corporate_code).add("serial_type", serial_type);
            try
            {
                return (tsp_sequence_generate_define)DaoUtil.selectOneWithLockByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define$tsp_seq_generate_odb, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C018, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e4);
            }
        }

        public static List selectAll_tsp_seq_genrate_odb2(String system_code, String corporate_code, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException
        {
            Params parms = (new Params()).add("system_code", system_code).add("corporate_code", corporate_code);
            try
            {
                return DaoUtil.selectAllByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_generate_define$tsp_seq_genrate_odb2, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e1);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_generate_define_table_name)), e4);
            }
        }

        private static final IString tsp_sequence_generate_define_table_name = new IString("KSysSequenceDefineTable.tsp_sequence_generate_define.longname", "\u6D41\u6C34\u751F\u6210\u5B9A\u4E49\u8868");


        public Tsp_sequence_generate_defineDao()
        {
        }
    }

    public static interface tsp_sequence_generate_define
    {
        public static class tsp_seq_genrate_odb2
            implements cn.sunline.adp.metadata.model.annotation.Index.Name
        {

            public tsp_seq_genrate_odb2()
            {
            }
        }

        public static class tsp_seq_generate_odb extends cn.sunline.adp.metadata.model.annotation.Index.PrimaryKey
        {

            public tsp_seq_generate_odb()
            {
            }
        }


        public abstract String getSystem_code();

        public abstract void setSystem_code(String s);

        public abstract String getCorporate_code();

        public abstract void setCorporate_code(String s);

        public abstract String getSerial_type();

        public abstract void setSerial_type(String s);

        public abstract Long getMax_serial_value();

        public abstract void setMax_serial_value(Long long1);

        public abstract Long getMin_serial_value();

        public abstract void setMin_serial_value(Long long1);

        public abstract Integer getCache_size();

        public abstract void setCache_size(Integer integer);

        public abstract Integer getStep_value();

        public abstract void setStep_value(Integer integer);

        public abstract String getReset_mode();

        public abstract void setReset_mode(String s);

        public abstract String toString();
    }

    public static class Tsp_sequence_registerDao
    {

        public static int insert(tsp_sequence_register entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insert(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_register_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_register_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_register_table_name)), e3);
            }
        }

        public static int insertWithReturnKey(tsp_sequence_register entity)
            throws AdpDaoException, AdpDaoDuplicateException, AdpDaoLockException
        {
            try
            {
                return DaoUtil.insertWithReturnKey(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register, entity);
            }
            catch(AdpDaoDuplicateException e1)
            {
                throw new AdpDaoDuplicateException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C011, (new Params()).add("tableName", tsp_sequence_register_table_name)), e1);
            }
            catch(AdpDaoLockException e2)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C005, (new Params()).add("tableName", tsp_sequence_register_table_name)), e2);
            }
            catch(AdpDaoException e3)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C001, (new Params()).add("tableName", tsp_sequence_register_table_name)), e3);
            }
        }

        public static tsp_sequence_register selectOne_odb1(String serial_name, String service_code, String sub_system_code, String system_code, String corporate_code, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            Params parms = (new Params()).add("serial_name", serial_name).add("service_code", service_code).add("sub_system_code", sub_system_code).add("system_code", system_code).add("corporate_code", corporate_code);
            try
            {
                return (tsp_sequence_register)DaoUtil.selectOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register$odb1, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_register_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C018, (new Params()).add("tableName", tsp_sequence_register_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C008, (new Params()).add("tableName", tsp_sequence_register_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C004, (new Params()).add("tableName", tsp_sequence_register_table_name)), e4);
            }
        }

        public static int updateOne_odb1(tsp_sequence_register entity)
            throws AdpDaoNoDataFoundException, AdpDaoLockException, AdpDaoTooManyRowsException
        {
            try
            {
                return DaoUtil.updateOneByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register$odb1, entity);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C009, (new Params()).add("tableName", tsp_sequence_register_table_name)), e1);
            }
            catch(AdpDaoTooManyRowsException e2)
            {
                throw new AdpDaoTooManyRowsException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C019, (new Params()).add("tableName", tsp_sequence_register_table_name)), e2);
            }
            catch(AdpDaoLockException e3)
            {
                throw new AdpDaoLockException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C007, (new Params()).add("tableName", tsp_sequence_register_table_name)), e3);
            }
            catch(AdpDaoException e4)
            {
                throw new AdpDaoException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C003, (new Params()).add("tableName", tsp_sequence_register_table_name)), e4);
            }
        }

        public static List selectAll_odb2(String service_code, cn.sunline.adp.cedar.base.type.KBaseEnumType.E_LIUSZCZT serial_register_status, String sub_system_code, String system_code, String corporate_code, boolean nullException)
            throws AdpDaoNoDataFoundException, AdpDaoLockException
        {
            Params parms = (new Params()).add("service_code", service_code).add("serial_register_status", serial_register_status).add("sub_system_code", sub_system_code).add("system_code", system_code).add("corporate_code", corporate_code);
            try
            {
                return DaoUtil.selectAllByIndex(cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register, cn/sunline/adp/cedar/base/tables/KSysSequenceDefineTable$tsp_sequence_register$odb2, nullException, parms);
            }
            catch(AdpDaoNoDataFoundException e1)
            {
                throw new AdpDaoNoDataFoundException(new IString(cn.sunline.adp.core.exception.InternationalConstantDef.SPC_IN.C010, (new Params()).add("tableName", tsp_sequence_register_table_name)), e1);
            }