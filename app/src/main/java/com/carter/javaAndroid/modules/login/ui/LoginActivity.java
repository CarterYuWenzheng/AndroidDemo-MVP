package com.carter.javaAndroid.modules.login.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.modules.login.contract.LoginContract;
import com.carter.javaAndroid.modules.login.presenter.LoginPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;

    @Override
    protected void initView() {
        loadMultipleRootFragment(R.id.login_frame_layout,0,LoginFragment.getInstance(),RegisterFragment.getInstance());
        mTitle.setText("登录");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);//左上角是否有返回按钮
            actionBar.setDisplayShowTitleEnabled(false);//https://www.jianshu.com/p/7b5c99e1cfa3
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void loginSuccess() {

    }
}
