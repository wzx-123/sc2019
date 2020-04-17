package com.xzsd.pc.hotGoods.dao;

import com.xzsd.pc.hotGoods.entity.HotGoods;
import com.xzsd.pc.hotGoods.entity.HotGoodsShowNumber;
import com.xzsd.pc.hotGoods.entity.HotGoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 热门商品
 */
@Mapper
public interface HotGoodsDao {

    /**
     * 统计排序是否出现重复的序号
     * @param hotGoods
     * @return
     */
    int countSort(HotGoods hotGoods);

    /**
     * 统计该商品是否已经被使用
     * @param hotGoods
     * @return
     */
    int countGoodsIsUse(HotGoods hotGoods);

    /**
     * 新增热门商品
     * @param hotGoods
     * @return
     */
    int addHotGoods(HotGoods hotGoods);

    /**
     * 查询热门商品详情
     * @param hotGoodsId
     * @return
     */
    HotGoodsVO getHotGoodsById(@Param("hotGoodsId") String hotGoodsId);

    /**
     * 修改热门位商品
     * @param hotGoods
     * @return
     */
    int updateHotGoods(HotGoods hotGoods);

    /**
     * 查询热门商品列表（分页）
     * @param hotGoods
     * @return
     */
    List<HotGoodsVO> getListHotGoods(HotGoods hotGoods);

    /**
     * 查询热门位商品展示数量
     * @return
     */
    HotGoodsShowNumber getHotGoodsShowNumber();

    /**
     * 修改热门商品展示数量
     * @param hotGoodsShowNumber
     * @return
     */
    int modifyShowNumber(HotGoodsShowNumber hotGoodsShowNumber);

    /**
     * 删除热门位商品
     * @param listHotId
     * @param loginId
     * @return
     */
    int deleteHotGoods(@Param("listHotId") List<String> listHotId, @Param("loginId") String loginId);
}
