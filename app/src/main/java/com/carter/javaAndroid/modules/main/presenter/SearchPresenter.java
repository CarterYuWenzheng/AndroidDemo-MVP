package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.bean.TopSearchBean;
import com.carter.javaAndroid.modules.main.contract.SearchContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    @Inject
    SearchPresenter() {
    }

    @Override
    public void getTopSearchData() {
        addSubscribe(mDataManager.getTopSearchData()
        .compose(RxUtils.SchedulerTransformer())
        .filter(ArticleListBean -> mView != null)
        .subscribeWith(new BaseObserver<List<TopSearchBean>>(mView, "失败", false) {
            @Override
            public void onSuccess(List<TopSearchBean> topSearchBeans) {
                mView.showTopSearchData(topSearchBeans);
            }
        }));
    }

    @Override
    public void addHistoryData(String data) {

    }

    @Override
    public void clearAllHistoryData() {

    }

    @Override
    public void deleteHistoryDataById(long id) {

    }

    @Override
    public void loadAllHistoryData() {

    }
}
