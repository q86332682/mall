package com.mall.generator.model;

import java.util.Date;

public class Consultation {
    private Integer id;

    private Integer userid;

    private Integer goodsid;

    private String content;

    private Date createtime;

    private String adminname;

    private String adminhead;

    private String admincontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getAdminhead() {
        return adminhead;
    }

    public void setAdminhead(String adminhead) {
        this.adminhead = adminhead == null ? null : adminhead.trim();
    }

    public String getAdmincontent() {
        return admincontent;
    }

    public void setAdmincontent(String admincontent) {
        this.admincontent = admincontent == null ? null : admincontent.trim();
    }
}