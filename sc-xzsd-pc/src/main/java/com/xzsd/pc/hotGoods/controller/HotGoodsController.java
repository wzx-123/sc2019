package com.xzsd.pc.hotGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.hotGoods.entity.HotGoods;
import com.xzsd.pc.hotGoods.entity.HotGoodsShowNumber;
import com.xzsd.pc.hotGoods.service.HotGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hotGoods")
public class HotGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(HotGoodsController.class);

    @Resource
    private HotGoodsService hotGoodsService;

    /**
     * 新增热门商品
     */
    @PostMapping("addHotGoods")
    public AppResponse addHotGoods(HotGoods hotGoodsInfo){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setCreateUser(userId);
            return hotGoodsService.addHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("新增热门商品失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品详情
     */
    @PostMapping("getHotGoods")
    public AppResponse getHotGoodsById(String hotGoodsId){
        try {
            return hotGoodsService.getHotGoodsById(hotGoodsId);
        }catch (Exception e){
            logger.error("查询热门商品详情失败");
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * 修改热门商品信息
     */
    @PostMapping("updateHotGoods")
    public AppResponse updateHotGoods(HotGoods hotGoodsInfo){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setUpdateUser(userId);
            return hotGoodsService.updateHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("修改热门商品信息失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品列表（分页）
     */
    @PostMapping("listHotGoods")
    public AppResponse getListHotGoods(HotGoods hotGoodsInfo){
        try {
            return hotGoodsService.getListHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("查询热门商品列表失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品展示数量
     */
    @PostMapping("getHotGoodsShowNum")
    public AppResponse getHotGoodsShowNumber(){
        try {
            return hotGoodsService.getHotGoodsShowNumber();
        }catch (Exception e){
            logger.error("查询热门商品展示数量成功");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改热门位商品展示数量
     */
    @PostMapping("updateHotGoodsShowNum")
    public AppResponse modifyShowNumber(HotGoodsShowNumber hotGoodsShowNumber){
        try {
            String loginUserId = SecurityUtils.getCurrentUserId();
            hotGoodsShowNumber.setUpdateUser(loginUserId);
            return hotGoodsService.modifyShowNumber(hotGoodsShowNumber);
        }catch (Exception e){
            logger.error("修改热门位商品展示数量失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除热门位商品
     */
    @PostMapping("deleteHotGoods")
    public AppResponse deleteHotGoods(String hotGoodsId){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            return hotGoodsService.deleteHotGoods(hotGoodsId, userId);
        }catch (Exception e){
            logger.error("删除热门位商品失败");
            System.out.println(e.toString());
            throw e;
        }
    }
}
