package com.wanghao.biz.flv;

/**
 * @author wanghao
 */
public class AudioTagHeader extends Packet {
    // SoundFormat
    private byte soundFormat;

    // SoundRate
    private byte soundRate;

    // SoundSize
    private byte soundSize;

    // SoundType
    private byte soundType;

    // AACPacketType
    private Short aacPacketType;

    // non-aac
    public AudioTagHeader(byte soundFormat, byte soundRate, byte soundSize, byte soundType,
                          int startIdx, int endIdx) {
        super(startIdx, endIdx);
        this.soundFormat = soundFormat;
        this.soundRate = soundRate;
        this.soundSize = soundSize;
        this.soundType = soundType;
    }

    // aac
    public AudioTagHeader(byte soundFormat, byte soundRate, byte soundSize, byte soundType,
                          short aacPacketType,
                          int startIdx, int endIdx) {
        super(startIdx, endIdx);
        this.soundFormat = soundFormat;
        this.soundRate = soundRate;
        this.soundSize = soundSize;
        this.soundType = soundType;
        this.aacPacketType = aacPacketType;
    }

    public byte getSoundFormat() {
        return soundFormat;
    }

    public byte getSoundRate() {
        return soundRate;
    }

    public byte getSoundSize() {
        return soundSize;
    }

    public byte getSoundType() {
        return soundType;
    }

    public Short getAacPacketType() {
        return aacPacketType;
    }

    public boolean isAACSequenceHeader() {
        return aacPacketType != null && aacPacketType == 0;
    }

    @Override
    public String getBriefInfo() {
        if (isAACSequenceHeader()) {
            return "AAC Sequence Header";
        }

        return "";
    }

    @Override
    public Object[][] getDetailInfo() {
        String[][] data = new String[6][2];
        data[0][0] = "AudioTagHeader";data[0][1] = "";
        data[1][0] = "  SoundFormat";data[1][1] = displaySoundFormat();
        data[2][0] = "  SoundRate";data[2][1] = displaySoundRate();
        data[3][0] = "  SoundSize";data[3][1] = displaySoundSize();
        data[4][0] = "  SoundType";data[4][1] = displaySoundType();
        data[5][0] = "  AACPacketType";data[5][1] = displayAACPacketType();
        return data;
    }

    private String displaySoundFormat() {
        switch (soundFormat) {
            case 0: return "Linear PCM, platform endian";
            case 1: return "ADPCM";
            case 2: return "MP3";
            case 3: return "Linear PCM, little endian";
            case 4: return "Nellymoser 16kHz mono";
            case 5: return "Nellymoser 8kHz mono";
            case 6: return "Nellymoser";
            case 7: return "G.711 A-law logarithmic PCM";
            case 8: return "G.711 mu-law logarithmic PCM";
            case 9: return "reserved";
            case 10:return "AAC";
            case 11:return "Speex";
            case 14:return "MP3 8 kHz";
            case 15:return "Device-specific sound";
            default:return "unknown";
        }
    }
    private String displaySoundRate() {
        switch (soundRate) {
            case 0: return "5.5 kHz";
            case 1: return "11 kHz";
            case 2: return "22 kHz";
            case 3: return "44 kHz";
            default:return "unknown";
        }
    }
    private String displaySoundSize() {
        switch (soundSize) {
            case 0: return "8-bit samples";
            case 1: return "16-bit samples";
            default:return "unknown";
        }
    }
    private String displaySoundType() {
        switch (soundType) {
            case 0: return "Mono sound";
            case 1: return "Stereo sound";
            default:return "unknown";
        }
    }
    private String displayAACPacketType() {
        if (aacPacketType == null) {
            return "";
        }
        if (isAACSequenceHeader()) {
            return "AAC sequence header";
        }
        return "AAC raw";
    }
}
