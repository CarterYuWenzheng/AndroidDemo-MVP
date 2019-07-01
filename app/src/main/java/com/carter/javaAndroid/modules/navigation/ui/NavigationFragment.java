package com.carter.javaAndroid.modules.navigation.ui;


import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.navigation.contract.NavigationContract;
import com.carter.javaAndroid.modules.navigation.presenter.NavigationPresenter;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initEventAndData() {

    }
}
