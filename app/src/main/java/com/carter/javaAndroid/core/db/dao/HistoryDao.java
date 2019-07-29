package com.carter.javaAndroid.core.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.carter.javaAndroid.core.db.bean.HistoryBean;

import java.util.List;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM HistoryBean")
    List<HistoryBean> getAllHistory();

    @Query("")
    void clearAllHistoryData();

    @Delete
    void deleteHistoryById(Long id);

    @Query("")
    List<HistoryBean> addHistoryData(String datas);
}
