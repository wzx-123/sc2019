package com.xzsd.pc.goodsClassify.dao;

import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsClassifyDao {
    /**
     * 统计商品分类的名称
     * @param goodsClassify
     * @return
     */
    int countGoodsClassifyName(GoodsClassify goodsClassify);
    /**
     * 新增商品分类
     * @param goodsClassify
     * @return
     */
    int addGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 查询分类详情
     * @param classifyId
     * @return
     */
    GoodsClassifyVO getGoodsClassify(@Param("classifyId") String classifyId);

    /**
     * 修改商品分类信息
     * @param goodsClassify
     * @return
     */
    int updateGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 查询商品一二级分类列表信息
     * @return
     */
    List<GoodsClassifyVO> listAllGoodsClassify();

    /**
     * 删除商品分类
     * @param classifyId
     * @param loginId
     * @return
     */
    int deleteGoodsClassify(@Param("classifyId") String classifyId, @Param("loginId") String loginId);

    /**
     * 获取当前分类的父级id
     * @param classifyId
     * @return
     */
    int countParentId(@Param("classifyId") String classifyId);

}
