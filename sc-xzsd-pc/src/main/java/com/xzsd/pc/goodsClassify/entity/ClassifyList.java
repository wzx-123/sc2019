package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

public class ClassifyList {
    public List<GoodsClassifyVO> getOneClassifyList() {
        return oneClassifyList;
    }

    public void setOneClassifyList(List<GoodsClassifyVO> oneClassifyList) {
        this.oneClassifyList = oneClassifyList;
    }

    private List<GoodsClassifyVO> oneClassifyList;
}
