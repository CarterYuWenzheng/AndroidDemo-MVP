package com.carter.javaAndroid.modules.main.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.core.db.bean.HistoryBean;
import com.carter.javaAndroid.modules.main.bean.TopSearchBean;

import java.util.List;

public interface SearchContract {

    interface View extends IView {
        void showTopSearchData(List<TopSearchBean> topSearchBeans);
        void showHistoryData(List<HistoryBean> historyBeans);
    }

    interface Presenter extends IPresenter<View> {

        void getTopSearchData();

        void addHistoryData(String data);

        void clearAllHistoryData();

        void deleteHistoryDataById(long id);

        void loadAllHistoryData();
    }
}
