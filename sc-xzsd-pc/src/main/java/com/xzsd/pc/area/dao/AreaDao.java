package com.xzsd.pc.area.dao;

import com.xzsd.pc.area.entity.Area;
import com.xzsd.pc.area.entity.AreaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaDao {

    /**
     * 查询省市区
     * @param areaInfo
     * @return
     */
    List<AreaVO> getListArea(Area areaInfo);
}
