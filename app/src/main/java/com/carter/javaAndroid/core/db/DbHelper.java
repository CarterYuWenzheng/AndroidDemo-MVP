package com.carter.javaAndroid.core.db;

import com.carter.javaAndroid.core.db.bean.HistoryBean;

import java.util.List;

public interface DbHelper {

    List<HistoryBean> addHistoryData(String data);

    void clearAllHistoryData();

    void deleteHistoryDataById(Long id);

    List<HistoryBean> loadAllHistoryData();
}
