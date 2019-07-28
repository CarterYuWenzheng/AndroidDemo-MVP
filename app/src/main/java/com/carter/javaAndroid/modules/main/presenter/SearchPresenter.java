package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.db.bean.HistoryBean;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.bean.TopSearchBean;
import com.carter.javaAndroid.modules.main.contract.SearchContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

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
        addSubscribe(Observable.create((ObservableOnSubscribe<List<HistoryBean>>) e -> {
//            List<HistoryBean> historyBeans = mDataManager.addHistoryData(data);
//            e.onNext(historyBeans);
        })
        .compose(RxUtils.SchedulerTransformer())
        .filter(articleListBean -> mView != null)
        .subscribe(historyBeanList -> {}));
    }

    @Override
    public void clearAllHistoryData() {
        mDataManager.clearAllHistoryData();
    }

    @Override
    public void deleteHistoryDataById(long id) {
        mDataManager.deleteHistoryDataById(id);
    }

    @Override
    public void loadAllHistoryData() {
        addSubscribe(Observable.create((ObservableOnSubscribe<List<HistoryBean>>) e -> {
            //TODO
//            List<HistoryBean> historyDataList = mDataManager.loadAllHistoryData();
        })
        .compose(RxUtils.SchedulerTransformer())
        .filter(articleListBean -> mView != null)
        .subscribe(historyDataList -> {
            Collections.reverse(historyDataList);
            mView.showHistoryData(historyDataList);
        }));
    }
}
