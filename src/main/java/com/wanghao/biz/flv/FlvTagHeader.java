package com.wanghao.biz.flv;

/**
 * @author wanghao
 */
public class FlvTagHeader extends Packet {
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

    public FlvTagHeader(int filter, int tagType, int dataSize, int timestamp, int streamId,
                        int startIdx, int endIdx) {
        super(startIdx, endIdx);
        this.filter = filter;
        this.tagType = tagType;
        this.dataSize = dataSize;
        this.timestamp = timestamp;
        this.streamId = streamId;
    }

    public int getFilter() {
        return filter;
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

    @Override
    public String getBriefInfo() {
        return "";
    }

    @Override
    public Object[][] getDetailInfo() {
        return null;
    }
}
