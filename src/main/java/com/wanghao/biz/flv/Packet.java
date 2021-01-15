package com.wanghao.biz.flv;

import com.wanghao.biz.err.BusinessException;
import io.netty.buffer.ByteBuf;

import static com.wanghao.biz.util.Utils.*;

/**
 * @author wanghao
 */
public abstract class Packet {

    // start index of byte array
    private int start;

    // end index of byte array
    private int end;

    public Packet(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // packet length
    public int getLength() {
        return end - start + 1;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public abstract String getBriefInfo();

    public abstract Object[][] getDetailInfo();

    public Object[][] getPrintable(ByteBuf buf) {
        if (buf == null) {
            throw new BusinessException("inner error");
        }
        byte[] array = buf.array();
        int len = this.getLength();
        if (array == null || array.length < len || array.length <= this.getEnd()) {
            throw new BusinessException("inner error");
        }

        // 总行数
        int n = (int) Math.ceil(len / 16D);
        String[][] data = new String[n][3];

        // 前n-1行
        for (int row = 0; row < n-1; row++) {
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            for (int i = this.getStart()+16*row; i <= this.getStart()+16*(row+1); i++) {
                sb2.append(getHexByte(array[i])).append(" ");
                sb3.append(getPrintableChar((char) array[i]));
            }
            data[row][0] = getHexRow(row);
            data[row][1] = sb2.toString();
            data[row][2] = sb3.toString();
        }
        // 最后一行
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (int i = this.getStart()+16*(n-1); i <= this.getEnd(); i++) {
            sb2.append(getHexByte(array[i])).append(" ");
            sb3.append(getPrintableChar((char) array[i]));
        }
        data[n-1][0] = getHexRow(n-1);
        data[n-1][1] = sb2.toString();
        data[n-1][2] = sb3.toString();

        return data;
    }
}
