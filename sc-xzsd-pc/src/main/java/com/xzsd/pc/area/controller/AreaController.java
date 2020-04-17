package com.xzsd.pc.area.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.area.entity.Area;
import com.xzsd.pc.area.service.AreaService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/area")
public class AreaController {

    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Resource
    private AreaService areaService;

    /**
     * 查询省市区信息
     * @param areaInfo
     * @return
     */
    @PostMapping("listArea")
    public AppResponse getListArea(Area areaInfo){
        try {
            return areaService.getListArea(areaInfo);
        }catch (Exception e){
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}