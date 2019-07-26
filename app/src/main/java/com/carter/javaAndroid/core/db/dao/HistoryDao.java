package com.carter.javaAndroid.core.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.carter.javaAndroid.core.db.bean.HistoryBean;

import java.util.List;

@Dao
public interface HistoryDao {

    @Query("")
    List<HistoryBean> getAllHistory();


}
