package com.carter.javaAndroid.core.db;

import com.carter.javaAndroid.Application.MyApplication;
import com.carter.javaAndroid.core.db.bean.HistoryBean;
import com.carter.javaAndroid.core.db.dao.HistoryDao;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

public class DbHelperImpl implements DbHelper {

    private static final int HISTORY_LIST_SIZE = 10;

    private List<HistoryBean> historyBeanList;
    private String data;
    private HistoryBean historyBean;
    private MyDatabase myDatabase;
    private HistoryDao mHistoryDao;

    @Inject
    DbHelperImpl(){initDatabase();}

    private void initDatabase() {
        myDatabase = MyDatabase.getInstance(MyApplication.getContext());
        mHistoryDao = myDatabase.getHistoryDao();
    }

    @Override
    public List<HistoryBean> addHistoryData(String data) {
        this.data = data;
        getHistoryDataList();
        createHistoryData();
        if (historyDataForward()){
            return historyBeanList;
        }
        if (historyBeanList.size() < HISTORY_LIST_SIZE) {
            mHistoryDao.insert(historyBean);
        } else {
            historyBeanList.remove(0);
            historyBeanList.add(historyBean);
            mHistoryDao.deleteAll();
            mHistoryDao.insertList(historyBeanList);
        }
        return historyBeanList;
    }

    @Override
    public void clearAllHistoryData() {
        mHistoryDao.deleteAll();
    }

    @Override
    public void deleteHistoryDataById(Long id) {
        mHistoryDao.deleteById(id);
    }

    @Override
    public List<HistoryBean> loadAllHistoryData() {
        return myDatabase.getHistoryDao().getAll();
    }

    private void getHistoryDataList(){
        historyBeanList = mHistoryDao.getAll();
    }

    /**
     * 历史数据前移
     *
     * @return 返回true表示查询的数据已存在，只需将其前移到第一项历史记录，否则需要增加新的历史记录
     */
    private boolean historyDataForward() {
        //重复搜索时进行历史记录前移
        Iterator<HistoryBean> iterator = historyBeanList.iterator();
        //不要在foreach循环中进行元素的remove、add操作，使用Iterator模式
        while (iterator.hasNext()) {
            HistoryBean historyData1 = iterator.next();
            if (historyData1.getData().equals(data)) {
                historyBeanList.remove(historyData1);
                historyBeanList.add(historyBean);
                mHistoryDao.deleteAll();
                mHistoryDao.insertList(historyBeanList);
                return true;
            }
        }
        return false;
    }

    private void createHistoryData(){
        historyBean = new HistoryBean();
        historyBean.setDate(System.currentTimeMillis());
        historyBean.setData(data);
    }
}
