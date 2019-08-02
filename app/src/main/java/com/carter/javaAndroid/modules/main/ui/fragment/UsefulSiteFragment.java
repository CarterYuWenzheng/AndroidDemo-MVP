package com.carter.javaAndroid.modules.main.ui.fragment;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.main.contract.UsefulSiteContract;
import com.carter.javaAndroid.modules.main.presenter.UsefulSitePresenter;

public class UsefulSiteFragment extends BaseFragment<UsefulSitePresenter> implements UsefulSiteContract.View {


    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_useful_site;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showUsefulSites() {

    }
}
