package com.xzsd.pc.driver.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.driver.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 司机控制类
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Resource
    private DriverService driverService;


    /**
     * demo 新增司机
     * @param driver
     * @return AppResponse
     */
    @PostMapping("addDriver")
    public AppResponse addDriver(Driver driver){
        try{
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            driver.setCreateUser(userId);
            return driverService.addDriver(driver);
        } catch (Exception e){
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 司机详情
     * @param driverId
     * @return AppResponse
     */
    @PostMapping("getDriver")
    public AppResponse getDriverById(String driverId){
        try {
            return driverService.getDriverById(driverId);
        } catch (Exception e){
            logger.error("查询司机详情失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改司机信息
     * @param driver
     * @return AppResponse
     */
    @PostMapping("updateDriver")
    public AppResponse updateDriver(Driver driver){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            driver.setUpdateUser(userId);
            return driverService.updateDriver(driver);
        } catch (Exception e){
            logger.error("修改司机信息失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机列表（分页）
     * @param driver
     * @return
     */
    @PostMapping("listDrivers")
    public AppResponse getListDriver(Driver driver){
        try {
            //获取当前登录人的id
            String loginUserId = SecurityUtils.getCurrentUserId();
            driver.setLoginUserId("2020040820282980355");
            return driverService.getListDriver(driver);
        }catch (Exception e){
            logger.error("查询司机列表失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除司机
     * @param driverId
     * @return
     */
    @PostMapping("deleteDriver")
    public AppResponse deleteDriverById(String driverId){
        try {
            //获取用户角色
            String userId = SecurityUtils.getCurrentUserId();
            return driverService.deleteDriverById(driverId, userId);
        }catch (Exception e){
            logger.error("删除司机失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
