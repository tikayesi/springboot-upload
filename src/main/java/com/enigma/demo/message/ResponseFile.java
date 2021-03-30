package com.enigma.demo.message;

import java.util.Date;

public class ResponseFile {
    private String name;
    private String productName;
    private String url;
    private String type;
    private Date date;
    private Integer status;
    private long size;

    public ResponseFile(String name, String productName, String url, Date date, String type,Integer status, long size) {
        this.name = name;
        this.productName = productName;
        this.url = url;
        this.type = type;
        this.date = date;
        this.status = status;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
