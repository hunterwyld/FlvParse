package com.wanghao.biz;

import com.wanghao.biz.err.BusinessException;
import com.wanghao.biz.flv.*;
import com.wanghao.biz.util.FileReader;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 */
public class FlvParser {
    private static final Logger logger = LoggerFactory.getLogger(FlvParser.class);

    public static FlvHeader flvHeader;
    public static FlvBody flvBody;
    public static ByteBuf byteBuf;

    public static void main(String[] args) throws Exception {
        parse("/Users/wanghao/Documents/测试用的音视频/video.flv");
        FlvTag tag = flvBody.getFlvTagList().get(1);
        Object[][] data = tag.getPrintable(byteBuf);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i][0] + "  " + data[i][1] + "  " + data[i][2]);
            System.out.println();
        }
    }

    public static void parse(String flvPath) throws Exception {
        //flvPath = "/Users/wanghao/Documents/测试用的音视频/video.flv";

        FileReader fileReader = new FileReader(flvPath);
        byteBuf = fileReader.read();

        logger.info("file size: {} bytes", byteBuf.readableBytes());
        // parse flv
        flvHeader = parseFlvHeader(byteBuf);
        if (flvHeader == null) {
            throw new BusinessException("flv file invalid");
        }
        flvBody = parseFlvBody(byteBuf);
        if (flvBody == null) {
            throw new BusinessException("flv file invalid");
        }
    }

    private static FlvBody parseFlvBody(ByteBuf byteBuf) {
        // PreviousTagSize0, always 0
        byteBuf.readUnsignedInt();

        List<FlvTag> flvTagList = new ArrayList<>();
        List<Long> previousTagSizeList = new ArrayList<>();

        while (byteBuf.isReadable()) {
            // FlvTagN
            FlvTag tag = parseFlvTag(byteBuf);
            if (tag == null) {
                return null;
            }
            flvTagList.add(tag);

            // PreviousTagSizeN
            previousTagSizeList.add(byteBuf.readUnsignedInt());
        }
        return new FlvBody(flvTagList, previousTagSizeList);
    }

    private static FlvTag parseFlvTag(ByteBuf byteBuf) {
        int startIdx = byteBuf.readerIndex();

        boolean isEncrypted;
        int tagType;
        short firstByte = byteBuf.readUnsignedByte();
        switch (firstByte) {
            case 0x08:
                isEncrypted = false;
                tagType = 8;
                break;
            case 0x09:
                isEncrypted = false;
                tagType = 9;
                break;
            case 0x12:
                isEncrypted = false;
                tagType = 18;
                break;
            case 0x28:
                isEncrypted = true;
                tagType = 8;
                break;
            case 0x29:
                isEncrypted = true;
                tagType = 9;
                break;
            case 0x32:
                isEncrypted = true;
                tagType = 18;
                break;
            default:
                logger.error("flv tag invalid: first byte");
                return null;
        }

        int dataSize = byteBuf.readUnsignedMedium();
        int timestamp = byteBuf.readInt();
        int streamId = byteBuf.readUnsignedMedium();
        AudioTagHeader audioTagHeader = null;
        VideoTagHeader videoTagHeader = null;

        int size = 0;
        if (tagType == 8) {
            audioTagHeader = parseAudioTagHeader(byteBuf);
            size += audioTagHeader.getLength();
        }
        if (tagType == 9) {
            videoTagHeader = parseVideoTagHeader(byteBuf);
            size += videoTagHeader.getLength();
        }
        if (isEncrypted) {
            throw new BusinessException("encrypted flv is not supported");
        }

        int bodySize = dataSize - size;
        if (tagType == 8) {
            AudioTagBody audioTagBody = parseAudioTagBody(byteBuf, bodySize);
            int endIdx = byteBuf.readerIndex() - 1;
            return new FlvTag(tagType, dataSize, timestamp, streamId, audioTagHeader, audioTagBody, startIdx, endIdx);
        }
        if (tagType == 9) {
            VideoTagBody videoTagBody = parseVideoTagBody(byteBuf, bodySize);
            int endIdx = byteBuf.readerIndex() - 1;
            return new FlvTag(tagType, dataSize, timestamp, streamId, videoTagHeader, videoTagBody, startIdx, endIdx);
        }
        // tagType = 18
        ScriptTagData scriptTagData = parseScriptTagData(byteBuf, bodySize);
        int endIdx = byteBuf.readerIndex() - 1;
        return new FlvTag(tagType, dataSize, timestamp, streamId, scriptTagData, startIdx, endIdx);
    }

    private static ScriptTagData parseScriptTagData(ByteBuf byteBuf, int bodySize) {
        int startIdx = byteBuf.readerIndex();
        byteBuf.skipBytes(bodySize);
        int endIdx = byteBuf.readerIndex() - 1;
        return new ScriptTagData(startIdx, endIdx);
    }

    private static VideoTagBody parseVideoTagBody(ByteBuf byteBuf, int bodySize) {
        int startIdx = byteBuf.readerIndex();
        byteBuf.skipBytes(bodySize);
        int endIdx = byteBuf.readerIndex() - 1;
        return new VideoTagBody(startIdx, endIdx);
    }

    private static AudioTagBody parseAudioTagBody(ByteBuf byteBuf, int bodySize) {
        int startIdx = byteBuf.readerIndex();
        byteBuf.skipBytes(bodySize);
        int endIdx = byteBuf.readerIndex() - 1;
        return new AudioTagBody(startIdx, endIdx);
    }

    private static VideoTagHeader parseVideoTagHeader(ByteBuf byteBuf) {
        int startIdx = byteBuf.readerIndex();

        short firstByte = byteBuf.readUnsignedByte();
        byte frameType = (byte) (firstByte >> 4);
        byte codecId = (byte) (firstByte & 0x0f);
        boolean isAVC = codecId == 7;
        if (isAVC) {
            short avcPacketType = byteBuf.readUnsignedByte();
            int compositionTime = byteBuf.readMedium();
            int endIdx = byteBuf.readerIndex() - 1;
            return new VideoTagHeader(frameType, codecId, avcPacketType, compositionTime, startIdx, endIdx);
        }

        int endIdx = byteBuf.readerIndex() - 1;
        return new VideoTagHeader(frameType, codecId, startIdx, endIdx);
    }

    private static AudioTagHeader parseAudioTagHeader(ByteBuf byteBuf) {
        int startIdx = byteBuf.readerIndex();

        short firstByte = byteBuf.readUnsignedByte();
        byte soundFormat = (byte) (firstByte >> 4);
        byte soundRate = (byte) ((firstByte >> 2) & 0x03);
        byte soundSize = (byte) ((firstByte >> 1) & 0x01);
        byte soundType = (byte) (firstByte & 0x01);

        boolean isAAC = soundFormat == 10;
        if (isAAC) {
            short aacPacketType = byteBuf.readUnsignedByte();
            int endIdx = byteBuf.readerIndex() - 1;
            return new AudioTagHeader(soundFormat, soundRate, soundSize, soundType, aacPacketType, startIdx, endIdx);
        }

        int endIdx = byteBuf.readerIndex() - 1;
        return new AudioTagHeader(soundFormat, soundRate, soundSize, soundType, startIdx, endIdx);
    }

    private static FlvHeader parseFlvHeader(ByteBuf byteBuf) {
        int startIdx = byteBuf.readerIndex();

        short f = byteBuf.readUnsignedByte();
        short l = byteBuf.readUnsignedByte();
        short v = byteBuf.readUnsignedByte();
        if (f != 0x46 || l != 0x4C || v != 0x56) {
            logger.error("flv header invalid: signature");
            return null;
        }
        int version = byteBuf.readUnsignedByte();
        boolean audioPresent;
        boolean videoPresent;
        short typeFlags = byteBuf.readUnsignedByte();
        switch (typeFlags){
            case 0:
                audioPresent = false;
                videoPresent = false;
                break;
            case 1:
                audioPresent = false;
                videoPresent = true;
                break;
            case 4:
                audioPresent = true;
                videoPresent = false;
                break;
            case 5:
                audioPresent = true;
                videoPresent = true;
                break;
            default:
                logger.error("flv header invalid: typeFlags");
                return null;
        }
        long headerLength = byteBuf.readUnsignedInt();

        int endIdx = byteBuf.readerIndex() - 1;
        return new FlvHeader("FLV", version, audioPresent, videoPresent, headerLength, startIdx, endIdx);
    }
}
