// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DcpBaseReturnCode.java

package cn.sunline.adp.vine.base.exception;


// Referenced classes of package cn.sunline.adp.vine.base.exception:
//            ReturnCode

public class DcpBaseReturnCode extends ReturnCode
{

    protected DcpBaseReturnCode(String returnCode, String orginMsg)
    {
        super(returnCode, orginMsg);
    }

    private static final String PREFIX = "ADBA";
    public static final DcpBaseReturnCode Ognl_InvalidExpression = new DcpBaseReturnCode("ADBA0001", "Invalid expression!");
    public static final DcpBaseReturnCode Ognl_Eval = new DcpBaseReturnCode("ADBA0002", "The evaluation of expression failed!");
    public static final DcpBaseReturnCode Ognl_SetValue = new DcpBaseReturnCode("ADBA0003", "The assignment of expression failed!");
    public static final DcpBaseReturnCode Type_UnsupportedPrimitiveType = new DcpBaseReturnCode("ADBA0004", "Unsupported native type!");
    public static final DcpBaseReturnCode Type_NotFound = new DcpBaseReturnCode("ADBA0005", "Can't find the specified type!");
    public static final DcpBaseReturnCode Ognl_EvalStr_UnclosedExp = new DcpBaseReturnCode("ADBA0006", "Incomplete expression structure error!");
    public static final DcpBaseReturnCode Character_encoding_notsupported = new DcpBaseReturnCode("ADBA0007", "Unsupported character set encoding type!");
    public static final DcpBaseReturnCode Date_Format_Wrong = new DcpBaseReturnCode("ADBA0008", "Date format error!");
    public static final DcpBaseReturnCode File_Name_IsNot_Empty = new DcpBaseReturnCode("ADBA0009", "File name couldn't be empty!");
    public static final DcpBaseReturnCode File_IsNot_Exists = new DcpBaseReturnCode("ADBA0010", "File doesn't exist!");
    public static final DcpBaseReturnCode File_Type_IsNot_Match = new DcpBaseReturnCode("ADBA0011", "File type doesn't match!");
    public static final DcpBaseReturnCode File_Copy_Failure = new DcpBaseReturnCode("ADBA0012", "Copying file failed!");
    public static final DcpBaseReturnCode File_Read_Failure = new DcpBaseReturnCode("ADBA0013", "Reading file failed!");
    public static final DcpBaseReturnCode File_Write_Failure = new DcpBaseReturnCode("ADBA0014", "Writing file failed!");
    public static final DcpBaseReturnCode Parameter_Format_Wrong = new DcpBaseReturnCode("ADBA0015", "Parameter format error!");

}
