package com.enigma.demo.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String productName;

    private String name;

    private String type;

    private Date date;

    private Integer status;

    @Lob
    private byte[] data;

    public FileDB() {
    }

    public FileDB(String id, String name, String productName, String type, Date date, Integer status, byte[] data) {
        this.id = id;
        this.name = name;
        this.productName = productName;
        this.type = type;
        this.date = date;
        this.status = status;
        this.data = data;
    }

    public FileDB(String name, String productName, String type, Date date, Integer status, byte[] data) {
        this.name = name;
        this.productName = productName;
        this.type = type;
        this.date = date;
        this.status = status;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
