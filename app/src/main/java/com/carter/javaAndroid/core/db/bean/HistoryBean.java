package com.carter.javaAndroid.core.db.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class HistoryBean {

    @PrimaryKey
    private Long id;

    private long date;

    private String data;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public String getData() {
        return data;
    }
}
