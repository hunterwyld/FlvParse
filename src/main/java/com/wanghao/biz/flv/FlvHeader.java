package com.wanghao.biz.flv;

import static com.wanghao.biz.util.Constant.DELIMETER;

/**
 * @author wanghao
 */
public class FlvHeader extends Packet {
    // Signature
    private String signature;

    // Version
    private int version;

    // TypeFlagsAudio
    private boolean audioPresent;

    // TypeFlagsVideo
    private boolean videoPresent;

    // DataOffset
    private long headerLength;

    public FlvHeader(String signature, int version, boolean audioPresent, boolean videoPresent, long headerLength,
                     int startIdx, int endIdx) {
        super(startIdx, endIdx);
        this.signature = signature;
        this.version = version;
        this.audioPresent = audioPresent;
        this.videoPresent = videoPresent;
        this.headerLength = headerLength;
    }

    public String getSignature() {
        return signature;
    }

    public int getVersion() {
        return version;
    }

    public boolean isAudioPresent() {
        return audioPresent;
    }

    public boolean isVideoPresent() {
        return videoPresent;
    }

    public long getHeaderLength() {
        return headerLength;
    }

    @Override
    public String getBriefInfo() {
        return signature + DELIMETER
                + "Version:"+version + DELIMETER
                + (audioPresent ? "Audio"+DELIMETER : "")
                + (videoPresent ? "Video"+DELIMETER : "")
                + headerLength;
    }

    @Override
    public Object[][] getDetailInfo() {
        String[][] data = new String[5][2];
        data[0][0] = "Signature";data[0][1] = signature;
        data[1][0] = "Version";data[1][1] = String.valueOf(version);
        data[2][0] = "TypeFlagsAudio";data[2][1] = audioPresent ? "1" : "0";
        data[3][0] = "TypeFlagsVideo";data[3][1] = videoPresent ? "1" : "0";
        data[4][0] = "DataOffset";data[4][1] = String.valueOf(headerLength);
        return data;
    }
}
