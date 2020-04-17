package com.xzsd.pc.store.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.store.entity.Store;
import com.xzsd.pc.store.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/store")
public class StoreController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;


    /**
     * 新增门店信息
     */
    @PostMapping("addStore")
    public AppResponse addStore(Store store){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            store.setCreateUser(userId);
            return storeService.addStore(store);
        }catch (Exception e){
            logger.error("门店新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店详情
     */
    @PostMapping("getStore")
    public AppResponse getStoreInfoById(String storeId){
        try {
            return storeService.getStoreInfoById(storeId);
        }catch (Exception e){
            logger.error("查询门店详情失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改门店信息
     */
    @PostMapping("updateStore")
    public AppResponse updateStore(Store store){
        try {
            //获取用户的id
            String userId = SecurityUtils.getCurrentUserId();
            store.setUpdateUser(userId);
            return storeService.updateStore(store);
        }catch (Exception e){
            logger.error("修改门店信息失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店信息列表（分页）
     */
    @PostMapping("listStores")
    public AppResponse getListStore(Store store){
        try {
            //获取当前登录者的id
            String loginUserId = SecurityUtils.getCurrentUserId();
            store.setUserId(loginUserId);
            return storeService.getListStore(store);
        }catch (Exception e){
            logger.error("查询门店信息列表失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除门店
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStoreById(String storeId){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            return storeService.deleteStoreById(storeId, userId);
        }catch (Exception e){
            logger.error("删除门店成功", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
