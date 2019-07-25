package com.oocl.packagebooking.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Parcel {
    @Id
    @GeneratedValue
    private long id;

    private String customName;

    private long phoneNumner;

    private int status;

//    @DatetimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Parcel() {
    }

    public Parcel(String customName, long phoneNumner, int status, Date createTime) {
        this.customName = customName;
        this.phoneNumner = phoneNumner;
        this.status = status;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public long getPhoneNumner() {
        return phoneNumner;
    }

    public void setPhoneNumner(long phoneNumner) {
        this.phoneNumner = phoneNumner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

