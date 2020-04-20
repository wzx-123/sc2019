package com.xzsd.pc.goodsClassify.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.goodsClassify.entity.ClassifyList;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goodsClassify.dao.GoodsClassifyDao;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类实现类
 * @Author wzx
 * @Date 2020-03-29
 */

@Service
public class GoodsClassifyService {

    @Resource
    private GoodsClassifyDao goodsClassifyDao;

    /**
     * 新增商品分类
     * @param goodsClassify
     * @param loginId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsClassify(GoodsClassify goodsClassify, String loginId){

        //校验是否存在相同的分类名
        int count = goodsClassifyDao.countGoodsClassifyName(goodsClassify);
        if(count != 0){
            return AppResponse.bizError("存在相同的分类名，请重新输入！");
        }
        goodsClassify.setClassifyId(StringUtil.getCommonCode(2));
        goodsClassify.setIsDelete(0);
        goodsClassify.setCreateUser(loginId);
        //判断是否插入成功
        int classifyNum = goodsClassifyDao.addGoodsClassify(goodsClassify);
        if(classifyNum == 0){
            return AppResponse.bizError("新增失败！");
        }
        return AppResponse.success("新增成功！");
    }


    /**
     * 查询商品分类详情
     * @param classifyId
     * @return
     */
    public AppResponse getGoodsClassify(String classifyId){
        GoodsClassifyVO goodsClassify = goodsClassifyDao.getGoodsClassify(classifyId);
        if(goodsClassify == null){
            return AppResponse.bizError("查询分类详情失败！");
        }
        return AppResponse.success("查询分类详情成功！", goodsClassify);
    }

    /**
     * 修改商品分类信息
     * @param goodsClassify
     * @param loginId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsClassify(GoodsClassify goodsClassify, String loginId){
        GoodsClassifyVO category = goodsClassifyDao.getGoodsClassify(goodsClassify.getClassifyId());
        //判断当前的分类名称是否存在相同的，只有修改后存在相同的分类名才会提示重新输入
        if(category.getClassifyName().equals(goodsClassify.getClassifyName()) == false){
            int count = goodsClassifyDao.countGoodsClassifyName(goodsClassify);
            if(count != 0) {
                return AppResponse.bizError("存在相同的分类名，请重新输入！");
            }
        }
        goodsClassify.setUpdateUser(loginId);
        int classifyNum = goodsClassifyDao.updateGoodsClassify(goodsClassify);
        if(classifyNum == 0){
            return AppResponse.bizError("修改失败！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 查询商品分类列表
     * @return
     */
    public AppResponse listAllGoodsClassify(){
        List<GoodsClassifyVO> listGoodsClassify = goodsClassifyDao.listAllGoodsClassify();
        ClassifyList classifyList = new ClassifyList();
        classifyList.setOneClassifyList(listGoodsClassify);
        return AppResponse.success("查询成功！", classifyList);
    }

    /**
     * 删除商品分类
     * @param classifyId
     * @param loginId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoodsClassify(String classifyId, String loginId){
        //查询是否存在二级分类
        int num = goodsClassifyDao.countParentId(classifyId);
        if(num != 0){
            return AppResponse.bizError("存在二级分类，不能删除！");
        }
        int count = goodsClassifyDao.deleteGoodsClassify(classifyId, loginId);
        if(count == 0){
            return AppResponse.bizError("删除失败");
        }
        return AppResponse.success("删除成功");
    }
}