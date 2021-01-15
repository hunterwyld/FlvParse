package com.wanghao.biz.flv;

import java.util.List;

/**
 * @author wanghao
 */
public class FlvBody {
    private List<FlvTag> flvTagList;
    private List<Long> previousTagSizeList;

    public FlvBody(List<FlvTag> flvTagList, List<Long> previousTagSizeList) {
        this.flvTagList = flvTagList;
        this.previousTagSizeList = previousTagSizeList;
    }

    public List<FlvTag> getFlvTagList() {
        return flvTagList;
    }

    public List<Long> getPreviousTagSizeList() {
        return previousTagSizeList;
    }
}
