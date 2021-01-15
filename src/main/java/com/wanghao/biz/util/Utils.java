package com.wanghao.biz.util;

import com.wanghao.biz.err.BusinessException;
import com.wanghao.biz.flv.Packet;
import io.netty.buffer.ByteBuf;

/**
 * @author wanghao
 */
public class Utils {

    public static char getPrintableChar(char c) {
        if (c >= '\u0020' && c < '\u007f') {
            return c;
        }
        return '.';
    }

    public static String getHexRow(int row) {
        String str = Integer.toHexString(row * 16);
        if (str.length() >= 4) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4-str.length(); i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }

    public static String getHexByte(byte b) {
        int a = b & 0xFF;
        String str = Integer.toHexString(a);
        if (str.length() >= 2) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2-str.length(); i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }

}
