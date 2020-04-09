// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteUtils.java

package cn.sunline.adp.vine.base.util.lang;

import cn.sunline.edsp.base.util.lang.StringUtil;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteUtils
{

    public ByteUtils()
    {
    }

    public static byte[] short2Bytes(short f)
    {
        return long2Bytes(f, longRealLen(f));
    }

    public static byte[] int2Bytes(int f)
    {
        return long2Bytes(f, longRealLen(f));
    }

    public static byte[] long2Bytes(long f)
    {
        return long2Bytes(f, longRealLen(f));
    }

    public static byte[] long2Bytes(long f, int length)
    {
        byte ret[] = new byte[length];
        Arrays.fill(ret, (byte)0);
        int len = longRealLen(f);
        for(int i = 0; i < len; i++)
            ret[(length - len) + i] = (byte)(int)(f >> (len - 1 - i) * 8 & 255L);

        return ret;
    }

    public static int longRealLen(long f)
    {
        long s = f;
        int i;
        for(i = 0; s > 0L; i++)
            s >>= 8;

        return i;
    }

    public static short bytes2Short(byte bs[])
    {
        return (short)(int)bytes2Long(bs);
    }

    public static int bytes2Int(byte bs[])
    {
        return (int)bytes2Long(bs);
    }

    public static long bytes2Long(byte bs[])
    {
        long ret = 0L;
        int length = bs.length;
        for(int i = 0; i < length; i++)
            ret += (long)(bs[i] & 0xff) << (length - 1 - i) * 8;

        return ret;
    }

    public static byte[] char2Bytes(char ch)
    {
        return String.valueOf(ch).getBytes();
    }

    public static char bytes2Char(byte bs[])
    {
        return (new String(bs)).charAt(0);
    }

    public static byte[] str2Bytes(String str, String encoding)
    {
        try
        {
            return str.getBytes(encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            return str.getBytes();
        }
    }

    public static String bytes2Str(byte bytes[], String encoding)
    {
        try
        {
            return new String(bytes, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            return new String(bytes);
        }
    }

    public static String toBinaryStr(byte bs[])
    {
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        for(int i = 0; i < bs.length; i++)
        {
            tmp = bs[i] & 0xff;
            sb.append(StringUtil.fillChar(Integer.toBinaryString(tmp), '0', 8, true));
        }

        int end = -1;
        int i = 0;
        do
        {
            if(i >= sb.length())
                break;
            if(sb.charAt(i) != '0')
            {
                end = i;
                break;
            }
            i++;
        } while(true);
        if(end != -1)
            sb.delete(0, end);
        return sb.toString();
    }

    public static String toHexStr(byte bs[])
    {
        return toHexStr(bs, false);
    }

    public static String toHexStr(byte bs[], boolean sep)
    {
        StringBuilder sb = new StringBuilder(bs.length * (sep ? 3 : 2));
        for(int i = 0; i < bs.length; i++)
        {
            byte byte0 = bs[i];
            sb.append(hexDigits[byte0 >>> 4 & 0xf]);
            sb.append(hexDigits[byte0 & 0xf]);
            if(sep)
                sb.append(' ');
        }

        return sb.toString();
    }

    public static String toHexAsciiStr(byte bs[])
    {
        StringBuilder ret = new StringBuilder(new String(bs));
        ret.append(" (0x").append(toHexStr(bs, true).trim()).append(')');
        return ret.toString();
    }

    public static byte[] hexStr2Bytes(String hex)
    {
        int i = 0;
        byte data[] = new byte[hex.length() / 2];
        for(int n = 0; n < hex.length(); n += 2)
        {
            String temp = hex.substring(n, n + 2);
            int tt = Integer.valueOf(temp, 16).intValue();
            data[i] = (byte)tt;
            i++;
        }

        return data;
    }

    public static String dumphex(byte bytes[])
    {
        int bsLen = bytes.length;
        String head = "-Location- -0--1--2--3--4--5--6--7--8--9--A--B--C--D--E--F--0--1--2--3--4--5--6--7--8--9--A--B--C--D--E--F- ---ASCII Code---\n";
        StringBuilder ret = new StringBuilder(head.length() + bsLen * 3);
        ret.append(head);
        for(int i = 0; i < bsLen; i += 32)
            dumphexFor(ret, i, bsLen, bytes);

        return ret.toString();
    }

    private static void dumphexFor(StringBuilder ret, int i, int bsLen, byte bytes[])
    {
        ret.append(lpadding(Integer.toHexString(i), 4, "0")).append('(');
        ret.append(lpadding((new StringBuilder()).append("").append(i).toString(), 4, "0")).append(") ");
        for(int j = 0; j < 32; j++)
        {
            String hex = i + j < bsLen ? Integer.toHexString(bytes[i + j] & 0xff) : "..";
            if(hex.length() < 2)
                ret.append("0");
            ret.append(hex).append(' ');
        }

        ret.append(' ');
        for(int j = 0; j < 32; j++)
            dumphexForTwo(i, j, bsLen, bytes, ret);

        ret.append('\n');
    }

    private static void dumphexForTwo(int i, int j, int bsLen, byte bytes[], StringBuilder ret)
    {
        if(i + j >= bsLen)
            ret.append('.');
        else
        if(bytes[i + j] < 20 && bytes[i + j] >= 0)
            ret.append('*');
        else
        if(bytes[i + j] > 0)
            ret.append((char)bytes[i + j]);
        else
        if(bsLen > i + j + 1)
        {
            String s = new String(bytes, i + j, 2);
            ret.append(s);
            j++;
        } else
        {
            ret.append((char)bytes[i + j]);
        }
    }

    private static String lpadding(String s, int n, String padding)
    {
        StringBuilder strbuf = new StringBuilder();
        for(int i = 0; i < n - s.length(); i++)
            strbuf.append(padding);

        strbuf.append(s);
        return strbuf.toString();
    }

    public static byte[] fillByte(byte rs[], byte ch, int num, boolean left)
    {
        int rsLen = rs.length;
        byte ret[] = new byte[Math.abs(num)];
        Arrays.fill(ret, ch);
        if(left)
        {
            if(num >= rsLen)
                System.arraycopy(rs, 0, ret, num - rsLen, rsLen);
            else
                System.arraycopy(rs, 0, ret, 0, ret.length);
        } else
        if(num >= rsLen)
            System.arraycopy(rs, 0, ret, 0, rsLen);
        else
            System.arraycopy(rs, 0, ret, 0, ret.length);
        return ret;
    }

    public static byte[] removeFillByte(byte rs[], byte ch, boolean left)
    {
        if(left)
            return removeFillByteLeft(rs, ch);
        else
            return removeFillByteOther(rs, ch);
    }

    public static byte[] removeFillByteOther(byte rs[], byte ch)
    {
        if(rs[rs.length - 1] != ch)
            return rs;
        int idx = -1;
        int i = rs.length - 1;
        do
        {
            if(i < 0)
                break;
            if(rs[i] != ch)
            {
                idx = i;
                break;
            }
            i--;
        } while(true);
        byte ret[] = new byte[idx + 1];
        System.arraycopy(rs, 0, ret, 0, ret.length);
        return ret;
    }

    public static byte[] removeFillByteLeft(byte rs[], byte ch)
    {
        if(rs[0] != ch)
            return rs;
        int idx = rs.length;
        int i = 0;
        do
        {
            if(i >= rs.length)
                break;
            if(rs[i] != ch)
            {
                idx = i;
                break;
            }
            i++;
        } while(true);
        byte ret[] = new byte[rs.length - idx];
        System.arraycopy(rs, idx, ret, 0, ret.length);
        return ret;
    }

    public static byte[] getRemainBytes(ByteBuffer bb)
    {
        if(!bb.hasRemaining())
        {
            throw new BufferUnderflowException();
        } else
        {
            byte data[] = bb.array();
            byte remain[] = new byte[bb.remaining()];
            System.arraycopy(data, bb.position(), remain, 0, remain.length);
            return remain;
        }
    }

    public static byte[] getUsedBytes(ByteBuffer bb)
    {
        byte data[] = bb.array();
        byte remain[] = new byte[bb.position()];
        System.arraycopy(data, 0, remain, 0, remain.length);
        return remain;
    }

    public static void checkBounds(int off, int len, int size)
        throws IndexOutOfBoundsException
    {
        if((off | len | off + len | size - (off + len)) < 0)
            throw new IndexOutOfBoundsException();
        else
            return;
    }

    public static Byte[] wraps(byte bs[])
    {
        Byte ret[] = new Byte[bs.length];
        for(int i = 0; i < bs.length; i++)
            ret[i] = Byte.valueOf(bs[i]);

        return ret;
    }

    public static byte[] unwraps(Byte bs[])
    {
        byte ret[] = new byte[bs.length];
        for(int i = 0; i < bs.length; i++)
            ret[i] = bs[i].byteValue();

        return ret;
    }

    public static byte[] reverse(byte bs[])
    {
        int len = bs.length;
        byte ret[] = new byte[len];
        for(int i = 0; i < len; i++)
            ret[len - 1 - i] = bs[i];

        return ret;
    }

    public static byte[] copyOf(byte original[], int newLength)
    {
        byte copy[] = new byte[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    public static int indexOf(byte source[], int fromIndex, byte dst[])
    {
        if(fromIndex >= source.length)
            return dst.length != 0 ? -1 : source.length;
        if(fromIndex < 0)
            fromIndex = 0;
        if(dst.length == 0)
            return fromIndex;
        byte first = dst[0];
        int max = source.length - dst.length;
        for(int i = fromIndex; i <= max; i++)
            indexOfFor(source, i, first, max, dst);

        return -1;
    }

    public static int indexOfFor(byte source[], int i, byte first, int max, byte dst[])
    {
        if(source[i] != first)
            while(++i <= max && source[i] != first) ;
        if(i <= max)
        {
            int j = i + 1;
            int end = (j + dst.length) - 1;
            for(int k = 1; j < end && source[j] == dst[k]; k++)
                j++;

            if(j == end)
                return i;
        }
        return max;
    }

    public static String bytes2MockStr(byte bs[])
    {
        int length = bs.length;
        StringBuilder sb = new StringBuilder(length + 2);
        int byteToInt = 0;
        int visible = 0;
        int invisible = 0;
        for(int k = 0; k < length; k++)
        {
            int num = bytes2MockStrFor(byteToInt, length, k, visible, invisible, bs, sb);
            if(num != 1);
        }

        return sb.toString();
    }

    public static int bytes2MockStrFor(int byteToInt, int length, int k, int visible, int invisible, byte bs[], StringBuilder sb)
    {
        int num = 0;
        int byteInt = bs[k] & 0xff;
        if(byteInt >= 129 && byteInt <= 254)
            num = bytes2MockStrForifOne(byteInt, length, k, visible, invisible, bs, sb, num);
        if(byteInt == 9 || byteInt == 10 || byteInt == 13 || byteInt >= 32 && byteInt <= 126)
            bytes2MockStrForSign(byteInt, length, k, visible, invisible, bs, sb);
        else
            bytes2MockStrForNotSign(byteInt, length, k, visible, invisible, bs, sb);
        if(k == length - 1)
            sb.append("]");
        return num;
    }

    public static void bytes2MockStrForNotSign(int byteToInt, int length, int k, int visible, int invisible, byte bs[], StringBuilder sb)
    {
        if(invisible == 0 && visible == 0)
            sb.append("[0X");
        else
        if(invisible == 0 && visible > 0)
            sb.append("][0X");
        String t = Integer.toHexString(byteToInt);
        if(t.length() == 1)
            t = (new StringBuilder()).append("0").append(t).toString();
        sb.append(t.toUpperCase());
        invisible++;
        visible = 0;
    }

    public static void bytes2MockStrForSign(int byteToInt, int length, int k, int visible, int invisible, byte bs[], StringBuilder sb)
    {
        if(visible == 0 && invisible == 0)
            sb.append("[");
        else
        if(visible == 0 && invisible > 0)
            sb.append("][");
        sb.append((char)byteToInt);
        visible++;
        invisible = 0;
    }

    public static int bytes2MockStrForifOne(int byteToInt, int length, int k, int visible, int invisible, byte bs[], StringBuilder sb, int num)
    {
        if(k + 1 < length)
        {
            int byteToInt2 = bs[k + 1] & 0xff;
            if(byteToInt2 >= 64 && byteToInt2 <= 254)
            {
                if(visible == 0 && invisible == 0)
                    sb.append("[");
                else
                if(visible == 0 && invisible > 0)
                    sb.append("][");
                sb.append(new String(new byte[] {
                    bs[k], bs[k + 1]
                }));
                k++;
                visible++;
                invisible = 0;
                if(k == length - 1)
                    sb.append("]");
                num = 1;
            }
        }
        return num;
    }

    protected static final char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };

}
