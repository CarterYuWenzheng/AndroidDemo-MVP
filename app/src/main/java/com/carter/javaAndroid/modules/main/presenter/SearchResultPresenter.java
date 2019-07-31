package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.SearchResultContracrt;
import com.carter.javaAndroid.utils.RxUtils;

import javax.inject.Inject;

public class SearchResultPresenter extends CollectEventPresenter<SearchResultContracrt.View> implements SearchResultContracrt.Presenter {

    private int currentPage;
    private boolean isRefresh = true;
    private String searchKey;

    @Inject
    SearchResultPresenter() {
    }

    @Override
    public void search(String searchData, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        searchKey = searchData;
        getSearchResultList(isShowStatusView);
    }

    @Override
    public void reload() {
        search(searchKey, true);
    }

    @Override
    public void getSearchResultList(boolean isShowStatusView) {
        addSubscribe(mDataManager.getSearchResultList(currentPage, searchKey)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView, "获取失败", isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListBean articleListBean) {
                        mView.showSearchResultList(articleListBean, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getSearchResultList(false);
    }

}
