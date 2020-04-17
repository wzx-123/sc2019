package com.xzsd.pc.slideshowHome.entity;

import java.util.Date;

public class SlideshowHome {
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 图片编号
     */
    private String slideshowId;
    /**
     *排序
     */
    private int slideshowNum;
    /**
     *图片路径
     */
    private String slideshowPath;
    /**
     *有效期开始时间
     */
    private Date startTime;
    /**
     *有效期结束时间
     */
    private Date endTime;
    /**
     *商品编号
     */
    private String goodsId;
    /**
     *0表示图片已禁用，1表示启用
     */
    private int slideshowStateId;
    /**
     * 作废标记 0否1是
     */
    private int isDelete;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新者
     */
    private String updateUser;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getSlideshowId() {
        return slideshowId;
    }

    public void setSlideshowId(String slideshowId) {
        this.slideshowId = slideshowId;
    }

    public int getSlideshowNum() {
        return slideshowNum;
    }

    public void setSlideshowNum(int slideshowNum) {
        this.slideshowNum = slideshowNum;
    }

    public String getSlideshowPath() {
        return slideshowPath;
    }

    public void setSlideshowPath(String slideshowPath) {
        this.slideshowPath = slideshowPath;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getSlideshowStateId() {
        return slideshowStateId;
    }

    public void setSlideshowStateId(int slideshowStateId) {
        this.slideshowStateId = slideshowStateId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 版本号
     */
    private String version;



}
