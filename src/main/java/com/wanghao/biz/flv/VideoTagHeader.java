package com.wanghao.biz.flv;

/**
 * @author wanghao
 */
public class VideoTagHeader extends Packet {
    // Frame Type
    private byte frameType;

    // CodecID
    private byte codecId;

    // AVCPacketType
    private Short avcPacketType;

    // CompositionTime
    private Integer compositionTime;

    // non-avc
    public VideoTagHeader(byte frameType, byte codecId,
                          int startIdx, int endIdx) {
        super(startIdx, endIdx);
        this.frameType = frameType;
        this.codecId = codecId;
    }

    // avc
    public VideoTagHeader(byte frameType, byte codecId,
                          Short avcPacketType, Integer compositionTime,
                          int startIdx, int endIdx) {
        super(startIdx, endIdx);
        this.frameType = frameType;
        this.codecId = codecId;
        this.avcPacketType = avcPacketType;
        this.compositionTime = compositionTime;
    }

    public byte getFrameType() {
        return frameType;
    }

    public byte getCodecId() {
        return codecId;
    }

    public Short getAvcPacketType() {
        return avcPacketType;
    }

    public Integer getCompositionTime() {
        return compositionTime;
    }

    public boolean isAVCSequenceHeader() {
        return avcPacketType != null && avcPacketType == 0;
    }
    public boolean isAVCNalu() {
        return avcPacketType != null && avcPacketType == 1;
    }

    @Override
    public String getBriefInfo() {
        if (isAVCSequenceHeader()) {
            return "AVC Sequence Header";
        }
        return "";
    }

    @Override
    public Object[][] getDetailInfo() {
        String[][] data = new String[5][2];
        data[0][0] = "VideoTagHeader";data[0][1] = "";
        data[1][0] = "  FrameType";data[1][1] = displayFrameType();
        data[2][0] = "  CodecID";data[2][1] = displayCodecId();
        data[3][0] = "  AVCPacketType";data[3][1] = displayAVCPacketType();
        data[4][0] = "  CompositionTime";data[4][1] = displayCompositionTime();
        return data;
    }

    private String displayFrameType() {
        switch (frameType) {
            case 1: return "key frame(for AVC, a seekable frame)";
            case 2: return "inter frame(for AVC, a non-seekable frame)";
            case 3: return "disposable inter frame(H.263 only)";
            case 4: return "generate key frame(reserved for server use only)";
            case 5: return "video info/command frame";
            default:return "unknown";
        }
    }
    private String displayCodecId() {
        switch (codecId) {
            case 2: return "Sorenson H.263";
            case 3: return "Screen video";
            case 4: return "On2 VP6";
            case 5: return "On2 VP6 with alpha channel";
            case 6: return "Screen video version 2";
            case 7: return "AAC";
            default:return "unknown";
        }
    }
    private String displayAVCPacketType() {
        if (avcPacketType == null) {
            return "";
        }
        if (isAVCSequenceHeader()) {
            return "AVC sequence header";
        }
        if (isAVCNalu()) {
            return "AVC NALU";
        }
        return "AVC end of sequence";
    }
    private String displayCompositionTime() {
        if (compositionTime == null) {
            return "";
        }
        return String.valueOf(compositionTime);
    }
}
