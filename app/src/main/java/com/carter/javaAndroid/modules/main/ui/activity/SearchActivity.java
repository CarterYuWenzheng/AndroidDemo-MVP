package com.carter.javaAndroid.modules.main.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.modules.main.contract.SearchContract;
import com.carter.javaAndroid.modules.main.presenter.SearchPresenter;

@Route(path = ARouterPath.SEARCH_ACTIVITY)
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {


    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initEventAndData() {

    }
}
