package com.xzsd.pc.area.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.area.dao.AreaDao;
import com.xzsd.pc.area.entity.Area;
import com.xzsd.pc.area.entity.AreaList;
import com.xzsd.pc.area.entity.AreaVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaService {

    @Resource
    private AreaDao areaDao;

    /**
     * 查询省市区信息
     * @param area
     * @return
     */
    public AppResponse getListArea(Area area){
        List<AreaVO> listArea = areaDao.getListArea(area);
        if(listArea.size() == 0){
            return AppResponse.bizError("查询失败！");
        }
        //封装成接口文档需要的名称
        AreaList areaList = new AreaList();
        areaList.setAreaList(listArea);
        return AppResponse.success("查询成功！", areaList);
    }
}
