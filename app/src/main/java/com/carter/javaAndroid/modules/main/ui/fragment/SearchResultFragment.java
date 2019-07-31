package com.carter.javaAndroid.modules.main.ui.fragment;

import android.support.v4.app.Fragment;

import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.SearchResultContracrt;
import com.carter.javaAndroid.modules.main.presenter.SearchResultPresenter;

public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements SearchResultContracrt.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showSearchResultList(ArticleListBean articleListBean, boolean isRefresh) {

    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollectSuccess(int position) {

    }
}
