package com.carter.javaAndroid.core.db;

import com.carter.javaAndroid.core.db.bean.HistoryBean;

import java.util.List;

import javax.inject.Inject;

public class DbHelperImpl implements DbHelper {

    private static final int HISTORY_LIST_SIZE = 10;

    private List<HistoryBean> historyBeanList;
    private String data;
    private HistoryBean historyBean;

    @Inject
    DbHelperImpl(){}

    @Override
    public List<HistoryBean> addHistoryData(String data) {
        return null;
    }

    @Override
    public void clearAllHistoryData() {

    }

    @Override
    public void deleteHistoryDataById(Long id) {

    }

    @Override
    public List<HistoryBean> loadAllHistoryData() {
        return null;
    }
}
