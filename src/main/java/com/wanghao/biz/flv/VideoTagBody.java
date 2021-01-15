package com.wanghao.biz.flv;

/**
 * @author wanghao
 */
public class VideoTagBody extends Packet {
    public VideoTagBody(int startIdx, int endIdx) {
        super(startIdx, endIdx);
    }

    @Override
    public String getBriefInfo() {
        return "";
    }

    @Override
    public Object[][] getDetailInfo() {
        String[][] data = new String[1][2];
        data[0][0] = "VideoTagBody";data[0][1] = "";
        return data;
    }
}
