package com.wanghao.biz.flv;

/**
 * @author wanghao
 */
public class ScriptTagData extends Packet {
    public ScriptTagData(int startIdx, int endIdx) {
        super(startIdx, endIdx);
    }

    @Override
    public String getBriefInfo() {
        return "";
    }

    @Override
    public Object[][] getDetailInfo() {
        String[][] data = new String[1][2];
        data[0][0] = "ScriptTagData";data[0][1] = "";
        return data;
    }
}
