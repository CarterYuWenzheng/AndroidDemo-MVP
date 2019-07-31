package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.main.contract.SearchResultContracrt;

import javax.inject.Inject;

public class SearchResultPresenter extends BasePresenter<SearchResultContracrt.View> implements SearchResultContracrt.Presenter {

    @Inject
    SearchResultPresenter(){}

    @Override
    public void search(String searchData, boolean isShowStatusView) {

    }

    @Override
    public void getSearchResultList(boolean isShowStatusView) {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void addCollectArticle(int position, int id) {

    }

    @Override
    public void cancelCollectArticle(int position, int id) {

    }
}
