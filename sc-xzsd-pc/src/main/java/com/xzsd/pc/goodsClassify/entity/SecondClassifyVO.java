package com.xzsd.pc.goodsClassify.entity;

public class SecondClassifyVO {
    /**
     * 分类id
     */
    private String classifyId;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 等级
     */
    private int classifyLevel;
    /**
     * 父级id
     */
    private String classifyParent;
    /**
     * 备注
     */
    private String classifyComment;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public int getClassifyLevel() {
        return classifyLevel;
    }

    public void setClassifyLevel(int classifyLevel) {
        this.classifyLevel = classifyLevel;
    }

    public String getClassifyParent() {
        return classifyParent;
    }

    public void setClassifyParent(String classifyParent) {
        this.classifyParent = classifyParent;
    }

    public String getClassifyComment() {
        return classifyComment;
    }

    public void setClassifyComment(String classifyComment) {
        this.classifyComment = classifyComment;
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
