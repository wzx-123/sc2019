package com.xzsd.pc.hotGoods.entity;

public class HotGoodsShowNumber {
    /**
     * 展示数量
     */
    private int hotGoodsShowNum;
    /**
     * 版本号
     */
    private String version;

    public int getHotGoodsShowNum() {
        return hotGoodsShowNum;
    }

    public void setHotGoodsShowNum(int hotGoodsShowNum) {
        this.hotGoodsShowNum = hotGoodsShowNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
}
