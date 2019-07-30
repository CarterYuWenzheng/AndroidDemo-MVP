package com.carter.javaAndroid.core.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.carter.javaAndroid.core.db.bean.HistoryBean;

import java.util.List;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM historyBean")
    List<HistoryBean> getAll();

    @Query("DELETE FROM historyBean")
    void deleteAll();

    @Query("DELETE FROM historyBean WHERE ID = :id")
    void deleteById(Long id);

    @Insert
    void insert(HistoryBean historyBean);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<HistoryBean> historyBeans);
}
