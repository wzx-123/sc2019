package com.xzsd.pc.goodsClassify.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import com.xzsd.pc.goodsClassify.service.GoodsClassifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品分类管理
 * @Author wzx
 * @Date 2020-03-29
 */
@RestController
@RequestMapping("/goodsClassify")
public class GoodsClassifyController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsClassifyController.class);

    @Resource
    private GoodsClassifyService classifyService;


    /**
     * 新增商品分类
     * @param goodsClassify
     * @return
     */
    @PostMapping("addGoodsClassify")
    public AppResponse addGoodsClassify(GoodsClassify goodsClassify){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return classifyService.addGoodsClassify(goodsClassify, userId);
        }catch (Exception e){
            logger.error("新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品分类详情
     * @param classifyId
     * @return
     */
    @PostMapping("getGoodsClassify")
    public AppResponse getGoodsClassify(String classifyId){
        try {
            return classifyService.getGoodsClassify(classifyId);
        }catch (Exception e){
            logger.error("查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品分类信息
     * @param goodsClassify
     * @return
     */
    @PostMapping("updateGoodsClassify")
    public AppResponse updateGoodsClassify(GoodsClassify goodsClassify){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return classifyService.updateGoodsClassify(goodsClassify, userId);
        }catch (Exception e){
            logger.error("修改成功", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品分类列表
     * @return
     */
    @PostMapping("listAllGoodsClassify")
    public AppResponse listAllGoodsClassify(){
        try {
            return classifyService.listAllGoodsClassify();
        }catch (Exception e){
            logger.error("查询成功", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品分类
     * @param classifyId
     * @return
     */
    @PostMapping("deleteGoodsClassify")
    public AppResponse deleteGoodsClassify(String classifyId) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            return classifyService.deleteGoodsClassify(classifyId, userId);
        } catch (Exception e) {
            logger.error("删除失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
