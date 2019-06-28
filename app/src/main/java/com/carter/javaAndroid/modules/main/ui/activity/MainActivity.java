package com.carter.javaAndroid.modules.main.ui.activity;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.db.MyDatabase;
import com.carter.javaAndroid.core.db.bean.User;
import com.carter.javaAndroid.core.event.LoginEvent;
import com.carter.javaAndroid.modules.main.contract.MainActivityContract;
import com.carter.javaAndroid.modules.main.presenter.MainActivityPresenter;
import com.carter.javaAndroid.utils.ToastUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.OnClick;

import static com.carter.javaAndroid.Application.MyApplication.getContext;

@Route(path = ARouterPath.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityContract.View {

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick({})
    void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventListener(LoginEvent loginEvent) {
        ToastUtils.showToast(getContext(), loginEvent.getmTag());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
