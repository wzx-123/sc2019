package com.xzsd.pc.slideshowHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.slideshowHome.entity.SlideInfo;
import com.xzsd.pc.slideshowHome.service.SlideshowHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 增删改查
 * @author wzx
 * @date 2020-3-29
 */
@RestController
@RequestMapping("/slideshowHome")
public class SlideshowHomeController {

    private static final Logger logger = LoggerFactory.getLogger(SlideshowHomeController.class);

    @Resource
    private SlideshowHomeService slideshowHomeService;


    /**
     * 新增轮播图
     * @param slideInfo
     * @return
     * @author wzx
     * @date 2020-3-29
     */
    @PostMapping("addSlideshowHome")
    public AppResponse addSlide(SlideInfo slideInfo){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            slideInfo.setCreateUser(userId);
            return slideshowHomeService.addSlide(slideInfo);
        }catch (Exception e){
            logger.error("新增失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询轮播图列表（分页）
     * @param slideInfo
     * @return
     * @author wzx
     * @date 2020-3-29
     */
    @PostMapping("listSlideshowHome")
    public AppResponse getListSlide(SlideInfo slideInfo){
        try {
            return slideshowHomeService.getListSlide(slideInfo);
        }catch (Exception e){
            logger.error("查询轮播图列表失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改轮播图状态
     * @param slideInfo
     * @return
     * @author wzx
     * @date 2020-3-29
     */
    @PostMapping("updateSlideshowHomeState")
    public AppResponse updateSlideStatus(SlideInfo slideInfo){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            slideInfo.setUpdateUser(userId);
            return slideshowHomeService.updateSlideStatus(slideInfo);
        }catch (Exception e){
            logger.error("修改轮播图状态失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除轮播图
     * @param slideshowId
     * @return
     * @author wzx
     * @date 2020-3-29
     */
    @PostMapping("deleteSlideshowHome")
    public AppResponse deleteSlide(String slideshowId){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            return slideshowHomeService.deleteSlide(slideshowId, userId);
        }catch (Exception e){
            logger.error("删除失败！");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 新增轮播图和热门商品时的商品列表
     * @param goods
     * @return
     * @author wzx
     * @Date 2020-03-31
     */
    @PostMapping("listGoods")
    public AppResponse getSlideAndHotGoods(Goods goods){
        try {
            return slideshowHomeService.getSlideAndHotGoods(goods);
        }catch (Exception e){
            logger.error("查询新增轮播图和热门商品时的商品列表失败");
            System.out.println(e.toString());
            throw e;
        }
    }
}