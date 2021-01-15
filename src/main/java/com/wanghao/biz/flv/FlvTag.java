package com.wanghao.biz.flv;

import static com.wanghao.biz.util.Constant.DELIMETER;

/**
 * @author wanghao
 */
public class FlvTag extends Packet {
    // Filter
    private int filter;

    // TagType
    private int tagType;

    // DataSize
    private int dataSize;

    // Timestamp + TimestampExtended
    private int timestamp;

    // StreamId
    private int streamId;

    // AudioTagHeader, if TagType == 8
    private AudioTagHeader audioTagHeader;

    // VideoTagHeader, if TagType == 9
    private VideoTagHeader videoTagHeader;

    // EncryptionTagHeader, if Filter == 1
    // FilterParams, if Filter == 1

    // AudioTagBody, if TagType == 8
    private AudioTagBody audioTagBody;

    // VideoTagBody, if TagType == 9
    private VideoTagBody videoTagBody;

    // ScriptTagData, if TagType == 18
    private ScriptTagData scriptTagData;

    /** audio */
    public FlvTag(int tagType, int dataSize, int timestamp, int streamId,
                  AudioTagHeader audioTagHeader, AudioTagBody audioTagBody,
                  int startIdx, int endIdx) {
        super(startIdx, endIdx);
        setCommonFields(tagType, dataSize, timestamp, streamId);
        this.filter = 0;
        this.audioTagHeader = audioTagHeader;
        this.audioTagBody = audioTagBody;
    }

    /** video */
    public FlvTag(int tagType, int dataSize, int timestamp, int streamId,
                  VideoTagHeader videoTagHeader, VideoTagBody videoTagBody,
                  int startIdx, int endIdx) {
        super(startIdx, endIdx);
        setCommonFields(tagType, dataSize, timestamp, streamId);
        this.filter = 0;
        this.videoTagHeader = videoTagHeader;
        this.videoTagBody = videoTagBody;
    }

    /** script */
    public FlvTag(int tagType, int dataSize, int timestamp, int streamId,
                  ScriptTagData scriptTagData,
                  int startIdx, int endIdx) {
        super(startIdx, endIdx);
        setCommonFields(tagType, dataSize, timestamp, streamId);
        this.filter = 0;
        this.scriptTagData = scriptTagData;
    }

    /** encrypted audio */

    /** encrypted video */

    /** encrypted script */


    private void setCommonFields(int tagType, int dataSize, int timestamp, int streamId) {
        this.tagType = tagType;
        this.dataSize = dataSize;
        this.timestamp = timestamp;
        this.streamId = streamId;
    }

    public boolean isEncrypted() {
        return filter == 1;
    }

    public int getTagType() {
        return tagType;
    }

    public int getDataSize() {
        return dataSize;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getStreamId() {
        return streamId;
    }

    public AudioTagHeader getAudioTagHeader() {
        return audioTagHeader;
    }

    public VideoTagHeader getVideoTagHeader() {
        return videoTagHeader;
    }

    public AudioTagBody getAudioTagBody() {
        return audioTagBody;
    }

    public VideoTagBody getVideoTagBody() {
        return videoTagBody;
    }

    public ScriptTagData getScriptTagData() {
        return scriptTagData;
    }

    public static String getTagTypeStr(int tagType) {
        if (tagType == 8) {
            return "Audio";
        }
        if (tagType == 9) {
            return "Video";
        }
        if (tagType == 18) {
            return "Script";
        }
        return null;
    }

    @Override
    public String getBriefInfo() {
        StringBuilder sb = new StringBuilder();
        if (isEncrypted()) {
            sb.append("encrypted").append(DELIMETER);
        }
        sb.append("Size:").append(dataSize).append(DELIMETER);
        sb.append("Time:").append(timestamp).append(DELIMETER);
        switch (tagType) {
            case 8:
                sb.append(audioTagHeader.getBriefInfo()).append(DELIMETER).append(audioTagBody.getBriefInfo());
                break;
            case 9:
                sb.append(videoTagHeader.getBriefInfo()).append(DELIMETER).append(videoTagBody.getBriefInfo());
                break;
            case 18:
                sb.append(scriptTagData.getBriefInfo());
                break;
            default:
                break;
        }
        String ret = sb.toString();
        while (ret.endsWith(DELIMETER)) {
            ret = ret.substring(0, ret.length()-1);
        }
        return ret;
    }

    @Override
    public Object[][] getDetailInfo() {
        Object[][] tagHeaderData = null;
        Object[][] tagBodyData = null;
        switch (tagType) {
            //todo: TreeTableView
            case 8:
                tagHeaderData = audioTagHeader.getDetailInfo();
                tagBodyData = audioTagBody.getDetailInfo();
                break;
            case 9:
                tagHeaderData = videoTagHeader.getDetailInfo();
                tagBodyData = videoTagBody.getDetailInfo();
                break;
            case 18:
                tagBodyData = scriptTagData.getDetailInfo();
                break;
            default:
                break;
        }
        int tagHeaderRows = 0;
        int tagBodyRows = 0;
        if (tagHeaderData != null) {
            tagHeaderRows = tagHeaderData.length;
        }
        if (tagBodyData != null) {
            tagBodyRows = tagBodyData.length;
        }

        String[][] data = new String[tagHeaderRows+tagBodyRows+6][2];
        data[0][0] = "TagHeader";data[0][1] = "";
        data[1][0] = "  Filter";data[1][1] = String.valueOf(filter);
        data[2][0] = "  TagType";data[2][1] = String.valueOf(tagType);
        data[3][0] = "  DataSize";data[3][1] = String.valueOf(dataSize);
        data[4][0] = "  Timestamp";data[4][1] = String.valueOf(timestamp);
        data[5][0] = "  StreamId";data[5][1] = String.valueOf(streamId);
        if (tagHeaderData != null) {
            for (int i = 6; i < 6+tagHeaderRows; i++) {
                data[i][0] = (String) tagHeaderData[i-6][0];
                data[i][1] = (String) tagHeaderData[i-6][1];
            }
        }
        if (tagBodyData != null) {
            for (int i = 6+tagHeaderRows; i < 6+tagHeaderRows+tagBodyRows; i++) {
                data[i][0] = (String) tagBodyData[i-6-tagHeaderRows][0];
                data[i][1] = (String) tagBodyData[i-6-tagHeaderRows][1];
            }
        }

        return data;

    }
}
