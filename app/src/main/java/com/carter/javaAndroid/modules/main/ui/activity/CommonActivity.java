package com.carter.javaAndroid.modules.main.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.modules.main.contract.CommonContract;
import com.carter.javaAndroid.modules.main.presenter.CommonPresenter;

@Route(path = ARouterPath.COMMON_ACTIVITY)
public class CommonActivity extends BaseActivity<CommonPresenter> implements CommonContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initEventAndData() {

    }
}
