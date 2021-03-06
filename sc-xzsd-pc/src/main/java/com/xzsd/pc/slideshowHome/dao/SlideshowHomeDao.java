package com.xzsd.pc.slideshowHome.dao;

import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.slideshowHome.entity.SlideAndHotGoods;
import com.xzsd.pc.slideshowHome.entity.SlideInfo;
import com.xzsd.pc.slideshowHome.entity.SlideVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**@Description SlideDao
 * @author wzx
 * @data 2020-3-29
 */
@Mapper
public interface SlideshowHomeDao {
    /**
     * 统计sort出现的次数
     * @param slideInfo
     * @return
     */
    int countSort(SlideInfo slideInfo);

    /**
     * 新增轮播图
     * @param slideInfo
     * @return
     */
    int addSlide(SlideInfo slideInfo);

    /**
     * 查询轮播图列表
     * @param slideInfo
     * @return
     */
    List<SlideVO> getListSlide(SlideInfo slideInfo);

    /**
     * 修改轮播图状态
     * @param slideInfoList
     * @return
     */
    int updateSlideStatus(@Param("slideInfoList") List<SlideInfo> slideInfoList);

    /**
     * 删除轮播图
     * @param listSlideId
     * @param loginId
     * @return
     */
    int deleteSlide(@Param("listSlideId") List<String> listSlideId, @Param("loginId") String loginId);

    /**
     * 新增轮播图和热门商品时的商品列表
     * @param goods
     * @return
     */
    List<SlideAndHotGoods> getSlideAndHotGoods(Goods goods);
}