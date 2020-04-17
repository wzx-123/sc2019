package com.xzsd.pc.goods.entity;

import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVO;

import java.util.List;

/**
 * 分类list
 */
public class GoodsClassifyList {
    public List<GoodsClassifyVO> getGoodsClassifyList() {
        return goodsClassifyList;
    }

    public void setGoodsClassifyList(List<GoodsClassifyVO> goodsClassifyList) {
        this.goodsClassifyList = goodsClassifyList;
    }

    private List<GoodsClassifyVO> goodsClassifyList;
}
