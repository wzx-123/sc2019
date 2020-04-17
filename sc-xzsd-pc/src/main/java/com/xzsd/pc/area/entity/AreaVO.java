package com.xzsd.pc.area.entity;

/**
 * @Description 实体类
 * @Author wzx
 * @Date 2020-03-24
 */
public class AreaVO {
    /**
     * 区域id
     */
    private String areaId;
    /**
     * 地区名称
     */
    private String areaName;
    /**
     * 父级id
     */
    private String parentArea;
    /**
     * 等级
     */
    private int parentLevelId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public int getParentLevelId() {
        return parentLevelId;
    }

    public void setParentLevelId(int parentLevelId) {
        this.parentLevelId = parentLevelId;
    }
}
