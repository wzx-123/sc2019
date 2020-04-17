package com.xzsd.pc.goods.dao;


import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.goods.entity.GoodsVO;
import com.xzsd.pc.goods.entity.GoodsVTO;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品管理接口类
 * @Author wzx
 * @Date 2020-03-26
 */
@Mapper
public interface GoodsDao {

    /**
     * 统计是否出现重复的书号个数
     * @param isbn
     * @return
     */
    int countBookNumber(@Param("isbn") String isbn);

    /**
     * 新增商品
     * @param goods
     * @return
     */
    int addGoods(Goods goods);

    /**
     * 查询商品详情
     * @param goodsId
     * @return
     */
    GoodsVTO getGoodsInfoById(@Param("goodsId") String goodsId);

    /**
     * 查询商品一二级分类列表
     * @param classifyId
     * @return
     */
    List<GoodsClassifyVO> getListGoodsCategory(@Param("classifyId") String classifyId);

    /**
     * 修改商品的信息
     * @param goods
     * @return
     */
    int updateGoodsInfo(Goods goods);

    /**
     * 查询商品列表（分页）
     * @param goods
     * @return
     */
    List<GoodsVO> getListGoods(Goods goods);

    /**
     * 修改商品状态
     * @param goodsInfoList
     * @return
     */
    int updateGoodsStatus(@Param("goodsInfoList") List<Goods> goodsInfoList);

    /**
     * 删除商品
     * @param listGoodsId
     * @param loginId
     * @return
     */
    int deleteGoods(@Param("listGoodsId") List<String> listGoodsId, @Param("loginId") String loginId);

    /**
     * 查询删除的商品是否已经被轮播图或热门商品使用
     * @param listGoodsId
     * @return
     */
    List<String> querySlideAndHotGoods(@Param("listGoodsId") List<String> listGoodsId);
}