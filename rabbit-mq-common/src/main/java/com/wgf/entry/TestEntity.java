package com.wgf.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by acer on 2019/1/8.
 */
public class TestEntity implements Serializable{

    public TestEntity(){}

    public TestEntity(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    private String name;

    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
