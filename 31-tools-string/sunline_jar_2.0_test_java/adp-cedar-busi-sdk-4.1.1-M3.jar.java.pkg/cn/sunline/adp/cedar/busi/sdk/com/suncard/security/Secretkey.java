// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Secretkey.java

package cn.sunline.adp.cedar.busi.sdk.com.suncard.security;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.com.suncard.security.util.EchoUtil;
import java.io.*;
import java.net.BindException;
import java.net.Socket;
import java.util.Map;
import javax.crypto.SecretKey;

public class Secretkey
{

    public Secretkey()
    {
    }

    public static byte[] send_recive(String ip, int port, byte input[])
        throws Throwable
    {
        return send_recive(ip, port, input, true);
    }

    private static byte[] send_recive(String ip, int port, byte input[], boolean retry)
        throws Throwable
    {
        Socket socket;
        InputStream in;
        OutputStream out;
        socket = null;
        in = null;
        out = null;
        ByteArrayOutputStream bos = null;
        byte abyte1[];
        socket = new Socket(ip, port);
        socket.setSoTimeout(5000);
        socket.setTcpNoDelay(true);
        out = new BufferedOutputStream(socket.getOutputStream());
        out.write(header(input));
        out.write(input);
        out.flush();
        in = socket.getInputStream();
        byte head[] = new byte[2];
        in.read(head);
        int len = Integer.valueOf(EchoUtil.hex2bcd(head), 16).intValue();
        int temp = -1;
        int sum = 0;
        byte buffer[] = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        do
        {
            if((temp = in.read(buffer)) == -1)
                break;
            sum += temp;
            bos.write(buffer, 0, temp);
        } while(sum < len);
        log.info(new String(bos.toByteArray(), "GB18030"));
        byte ret[] = new byte[len - 2];
        System.arraycopy(bos.toByteArray(), 2, ret, 0, len - 2);
        abyte1 = ret;
        try
        {
            if(out != null)
            {
                out.close();
                out = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey out.close() error!", ex, new Object[0]);
        }
        try
        {
            if(in != null)
            {
                in.close();
                in = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey in.close() error!", ex, new Object[0]);
        }
        try
        {
            if(socket != null)
            {
                socket.close();
                socket = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey socket.close() error!", ex, new Object[0]);
        }
        return abyte1;
        BindException ex;
        ex;
        byte abyte0[];
        if(!retry)
            break MISSING_BLOCK_LABEL_444;
        abyte0 = send_recive(ip, port, input, false);
        try
        {
            if(out != null)
            {
                out.close();
                out = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey out.close() error!", ex, new Object[0]);
        }
        try
        {
            if(in != null)
            {
                in.close();
                in = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey in.close() error!", ex, new Object[0]);
        }
        try
        {
            if(socket != null)
            {
                socket.close();
                socket = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey socket.close() error!", ex, new Object[0]);
        }
        return abyte0;
        throw ex;
        Exception exception;
        exception;
        try
        {
            if(out != null)
            {
                out.close();
                out = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey out.close() error!", ex, new Object[0]);
        }
        try
        {
            if(in != null)
            {
                in.close();
                in = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey in.close() error!", ex, new Object[0]);
        }
        try
        {
            if(socket != null)
            {
                socket.close();
                socket = null;
            }
        }
        catch(Exception ex)
        {
            log.error("Secretkey socket.close() error!", ex, new Object[0]);
        }
        throw exception;
    }

    public static byte[] header(byte input[])
    {
        if(input.length == 0 || input == null)
        {
            return (new byte[] {
                0, 2
            });
        } else
        {
            char c1 = (char)((input.length + 2) / 256);
            char c2 = (char)((input.length + 2) % 256);
            log.info((new StringBuilder()).append("HSM input:").append(EchoUtil.num2hex(c1)).append(EchoUtil.num2hex(c2)).append("5441").append(",").append(new String(input)).toString());
            return EchoUtil.bcd2hex((new StringBuilder()).append(EchoUtil.num2hex(c1)).append(EchoUtil.num2hex(c2)).append("5441").toString());
        }
    }

    public static String arqc(String ip, int port, String cardno, String tag_5f34, String tag_9f26, String tag_9f02, String tag_9f03, String tag_9f1a, 
            String tag_95, String tag_5f2a, String tag_9a, String tag_9c, String tag_9f37, String tag_82, String tag_9f36, 
            String tag_9f10)
    {
        StringBuffer sb;
        if(!EchoUtil.v_LengthAndNotNull(cardno, true, 19) || !EchoUtil.v_LengthAndNotNull(tag_5f34, false, 2) || !EchoUtil.v_LengthAndNotNull(tag_9f26, false, 16) || !EchoUtil.v_LengthAndNotNull(tag_9f02, false, 12) || !EchoUtil.v_LengthAndNotNull(tag_9f03, false, 12) || !EchoUtil.v_LengthAndNotNull(tag_9f1a, false, 4) || !EchoUtil.v_LengthAndNotNull(tag_95, false, 10) || !EchoUtil.v_LengthAndNotNull(tag_5f2a, false, 4) || !EchoUtil.v_LengthAndNotNull(tag_9a, false, 6) || !EchoUtil.v_LengthAndNotNull(tag_9c, false, 2) || !EchoUtil.v_LengthAndNotNull(tag_9f37, false, 8) || !EchoUtil.v_LengthAndNotNull(tag_82, false, 4) || !EchoUtil.v_LengthAndNotNull(tag_9f36, false, 4) || !EchoUtil.v_LengthAndNotNull(tag_9f10, true, 64))
            break MISSING_BLOCK_LABEL_525;
        StringBuffer data = new StringBuffer();
        data.append(EchoUtil.lpad(tag_9f02, "0", 12));
        data.append(EchoUtil.lpad(tag_9f03, "0", 12));
        data.append(EchoUtil.lpad(tag_9f1a, "0", 4));
        data.append(EchoUtil.lpad(tag_95, "0", 10));
        data.append(EchoUtil.lpad(tag_5f2a, "0", 4));
        data.append(EchoUtil.lpad(tag_9a, "0", 6));
        data.append(EchoUtil.lpad(tag_9c, "0", 2));
        data.append(EchoUtil.lpad(tag_9f37, "0", 8));
        data.append(EchoUtil.lpad(tag_82, "0", 4));
        data.append(EchoUtil.lpad(tag_9f36, "0", 4));
        data.append(tag_9f10.substring(6, 14));
        sb = new StringBuffer();
        sb.append("5251");
        sb.append("007");
        sb.append(EchoUtil.keyouFormat("001", (new StringBuilder()).append("pboc20.").append(cardno.substring(0, 6)).append(".mdk-ac").toString()));
        sb.append(EchoUtil.keyouFormat("410", "1"));
        sb.append(EchoUtil.keyouFormat("041", EchoUtil.formatPan(cardno, tag_5f34)));
        sb.append(EchoUtil.keyouFormat("303", tag_9f36.toUpperCase()));
        sb.append(EchoUtil.keyouFormat("304", data.toString().toUpperCase()));
        sb.append(EchoUtil.keyouFormat("313", tag_9f26.toUpperCase()));
        sb.append(EchoUtil.keyouFormat("214", "0"));
        byte bt[] = send_recive(ip, port, sb.toString().getBytes("GB18030"));
        if(respCheck(bt))
            break MISSING_BLOCK_LABEL_517;
        if(bt == null || bt.length <= 4)
            return "09other exception";
        try
        {
            return "01verify exception";
        }
        catch(Throwable e)
        {
            return "09unknown exception";
        }
        return "00";
        return "02data input exception";
    }

    public static String arpc(String ip, int port, String cardno, String tag_5f34, String tag_9f26, String tag_9f36, String arcnum)
    {
        if(!EchoUtil.v_LengthAndNotNull(cardno, true, 19) || !EchoUtil.v_LengthAndNotNull(tag_5f34, false, 2) || !EchoUtil.v_LengthAndNotNull(tag_9f26, false, 16) || !EchoUtil.v_LengthAndNotNull(tag_9f36, false, 4) || !EchoUtil.v_LengthAndNotNull(arcnum, false, 2))
            break MISSING_BLOCK_LABEL_299;
        byte bt[];
        StringBuffer sb = new StringBuffer();
        sb.append("5291");
        sb.append("007");
        sb.append(EchoUtil.keyouFormat("001", (new StringBuilder()).append("pboc20.").append(cardno.substring(0, 6)).append(".mdk-ac").toString()));
        sb.append(EchoUtil.keyouFormat("410", "1"));
        sb.append(EchoUtil.keyouFormat("041", EchoUtil.formatPan(cardno, tag_5f34)));
        sb.append(EchoUtil.keyouFormat("303", tag_9f36.toUpperCase()));
        sb.append(EchoUtil.keyouFormat("313", tag_9f26.toUpperCase()));
        sb.append(EchoUtil.keyouFormat("315", arcnum));
        sb.append(EchoUtil.keyouFormat("214", "0"));
        bt = send_recive(ip, port, sb.toString().getBytes("GB18030"));
        if(respCheck(bt))
            break MISSING_BLOCK_LABEL_246;
        if(bt == null || bt.length <= 4)
            return "09other exception";
        Map ret;
        try
        {
            return "09other exception";
        }
        catch(Throwable e)
        {
            return "09unknown exception";
        }
        ret = EchoUtil.keyouExtract(bt);
        return (new StringBuilder()).append("00").append(new String(EchoUtil.bcd2hex((String)ret.get("314")))).toString();
        return "02data input exception";
    }

    public static String isscmac(String ip, int port, String cardno, String tag_5f34, String tag_9f36, String tag_9f26, String apdu_head, String apdu_data)
    {
        StringBuffer sb;
        if(!EchoUtil.v_LengthAndNotNull(cardno, true, 19) || !EchoUtil.v_LengthAndNotNull(tag_5f34, false, 2) || !EchoUtil.v_LengthAndNotNull(tag_9f36, false, 4) || !EchoUtil.v_LengthAndNotNull(tag_9f26, false, 16) || !EchoUtil.v_LengthAndNotNull(apdu_head, true, 10) || !EchoUtil.v_LengthAndNotNull(apdu_data, true, 255))
            break MISSING_BLOCK_LABEL_330;
        StringBuffer data = new StringBuffer();
        data.append(apdu_head);
        data.append(tag_9f36);
        data.append(tag_9f26);
        data.append(apdu_data);
        sb = new StringBuffer();
        sb.append("5281");
        sb.append("005");
        sb.append(EchoUtil.keyouFormat("001", (new StringBuilder()).append("pboc20.").append(cardno.substring(0, 6)).append(".mdk-mac").toString()));
        sb.append(EchoUtil.keyouFormat("410", "1"));
        sb.append(EchoUtil.keyouFormat("426", EchoUtil.formatPan(cardno, tag_5f34)));
        sb.append(EchoUtil.keyouFormat("303", tag_9f36.toUpperCase()));
        sb.append(EchoUtil.keyouFormat("081", data.toString().toUpperCase()));
        byte bt[];
        bt = send_recive(ip, port, sb.toString().getBytes("GB18030"));
        if(respCheck(bt))
            break MISSING_BLOCK_LABEL_277;
        if(bt == null || bt.length <= 4)
            return "09other exception";
        Map ret;
        try
        {
            return "09other exception";
        }
        catch(Throwable e)
        {
            return "09unknown exception";
        }
        ret = EchoUtil.keyouExtract(bt);
        return (new StringBuilder()).append("00").append(new String(EchoUtil.bcd2hex((String)ret.get("022")))).toString();
        return "02data input exception";
    }

    public static String isscenc(String ip, int port, String cardno, String tag_5f34, String tag_9f36, String opertp, String newpin, String oldpin)
    {
        StringBuffer sb;
        if(!EchoUtil.v_LengthAndNotNull(cardno, true, 19) || !EchoUtil.v_LengthAndNotNull(tag_5f34, false, 2) || !EchoUtil.v_LengthAndNotNull(tag_9f36, false, 4) || !EchoUtil.v_LengthAndNotNull(opertp, false, 2) || !EchoUtil.v_LengthAndNotNull(newpin, true, 12) || !EchoUtil.v_LengthAndNotNull(oldpin, true, 12))
            break MISSING_BLOCK_LABEL_304;
        StringBuffer data = new StringBuffer();
        data.append("12345678");
        sb = new StringBuffer();
        sb.append("5271");
        sb.append("006");
        sb.append(EchoUtil.keyouFormat("001", (new StringBuilder()).append("pboc20.").append(cardno.substring(0, 6)).append(".mdk-enc").toString()));
        sb.append(EchoUtil.keyouFormat("410", "1"));
        sb.append(EchoUtil.keyouFormat("214", "1"));
        sb.append(EchoUtil.keyouFormat("041", EchoUtil.formatPan(cardno, tag_5f34)));
        sb.append(EchoUtil.keyouFormat("303", tag_9f36.toUpperCase()));
        sb.append(EchoUtil.keyouFormat("081", data.toString().toUpperCase()));
        byte bt[];
        bt = send_recive(ip, port, sb.toString().getBytes("GB18030"));
        if(respCheck(bt))
            break MISSING_BLOCK_LABEL_264;
        if(bt == null || bt.length <= 4)
            return "09other exception";
        Map ret;
        try
        {
            return "09other exception";
        }
        catch(Throwable e)
        {
            return "09unknown exception";
        }
        ret = EchoUtil.keyouExtract(bt);
        return (new StringBuilder()).append("00").append(ret.get("082")).toString();
        return "02data input exception";
    }

    public static String cvvauth(String ip, int port, String cvvkey, String cardno, String cardsq, String inefdt, String servcd, String cvvnum)
    {
        StringBuffer sb;
        if(!EchoUtil.v_LengthAndNotNull(cvvkey, false, 4) || !EchoUtil.v_LengthAndNotNull(cardno, true, 19) || !EchoUtil.v_LengthAndNotNull(cardsq, false, 2) || !EchoUtil.v_LengthAndNotNull(inefdt, false, 4) || !EchoUtil.v_LengthAndNotNull(servcd, true, 3) || !EchoUtil.v_LengthAndNotNull(cvvnum, true, 3))
            break MISSING_BLOCK_LABEL_252;
        sb = new StringBuffer();
        sb.append("5021");
        sb.append("005");
        sb.append(EchoUtil.keyouFormat("071", cvvnum));
        sb.append(EchoUtil.keyouFormat("072", inefdt));
        sb.append(EchoUtil.keyouFormat("073", servcd));
        sb.append(EchoUtil.keyouFormat("041", (new StringBuilder()).append(cardno).append(cardsq).toString()));
        sb.append(EchoUtil.keyouFormat("001", (new StringBuilder()).append("pboc20.").append(cardno.substring(0, 6)).append(".").append(cvvkey.toLowerCase()).toString()));
        byte bt[] = send_recive(ip, port, sb.toString().getBytes("GB18030"));
        if(respCheck(bt))
            break MISSING_BLOCK_LABEL_244;
        if(bt == null || bt.length <= 4)
            return "09other exception";
        try
        {
            return "01verify exception";
        }
        catch(Throwable e)
        {
            return "09unknown exception";
        }
        return "00";
        return "02data input exception";
    }

    private static boolean respCheck(byte input[])
    {
        if(input == null || input.length < 10)
            return false;
        return Integer.parseInt(new String(input, 4, 6)) >= 0;
    }

    private static final SysLog log = SysLogUtil.getSysLog(javax/crypto/SecretKey);
    private static final int BUFFER_LENGTH = 1024;
    private static final int timeout = 5000;
    public static final String SUCCESS = "00";
    public static final String VERIFY_FAIL = "01";
    public static final String DATA_INPUT_ERROR = "02";
    public static final String UNKNOW_SERVER_EXCEPTION = "03";
    public static final String SERVER_TIMEOUT_EXCEPTION = "04";
    public static final String CLIENT_BIND_EXCEPTION = "05";
    public static final String IO_EXCEPTION = "06";
    public static final String SOCKET_EXCEPTION = "07";
    public static final String OTHER_EXCEPTION = "09";

}
