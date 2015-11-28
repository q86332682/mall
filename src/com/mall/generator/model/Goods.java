package com.mall.generator.model;

import java.util.Date;

public class Goods {
    private Byte id;

    private String name;

    private String img;

    private Byte categoryid;

    private Float marketprice;

    private Float sellprice;

    private Byte issell;

    private Byte ishotsell;

    private Byte isadvertdown;

    private Byte isrecommend;

    private Byte sort;

    private Date createtime;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Byte getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Byte categoryid) {
        this.categoryid = categoryid;
    }

    public Float getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(Float marketprice) {
        this.marketprice = marketprice;
    }

    public Float getSellprice() {
        return sellprice;
    }

    public void setSellprice(Float sellprice) {
        this.sellprice = sellprice;
    }

    public Byte getIssell() {
        return issell;
    }

    public void setIssell(Byte issell) {
        this.issell = issell;
    }

    public Byte getIshotsell() {
        return ishotsell;
    }

    public void setIshotsell(Byte ishotsell) {
        this.ishotsell = ishotsell;
    }

    public Byte getIsadvertdown() {
        return isadvertdown;
    }

    public void setIsadvertdown(Byte isadvertdown) {
        this.isadvertdown = isadvertdown;
    }

    public Byte getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(Byte isrecommend) {
        this.isrecommend = isrecommend;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}