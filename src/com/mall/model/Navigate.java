package com.mall.model;

public class Navigate {
    private Byte id;

    private String name;

    private String url;

    private Byte parentid;

    private Byte sort;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getParentid() {
        return parentid;
    }

    public void setParentid(Byte parentid) {
        this.parentid = parentid;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }
}