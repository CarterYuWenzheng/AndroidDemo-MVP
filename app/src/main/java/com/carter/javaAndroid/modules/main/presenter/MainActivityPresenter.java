package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.event.LoginEvent;
import com.carter.javaAndroid.core.event.LogoutEvent;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.login.bean.LoginData;
import com.carter.javaAndroid.modules.main.contract.MainActivityContract;
import com.carter.javaAndroid.utils.RxUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainActivityPresenter extends BasePresenter<MainActivityContract.View>
        implements MainActivityContract.Presenter {

    @Inject
    MainActivityPresenter() {
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void loginSuccessEvent(LoginEvent loginEvent) {
        mView.handleLoginSuccess();
    }


    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void logout() {
        addSubscribe(mDataManager.logout()
                .compose(RxUtils.SchedulerTransformer())
                .filter(loginData -> mView != null)
                .subscribeWith(new BaseObserver<LoginData>(mView, "logout fail", false) {
                    @Override
                    public void onSuccess(LoginData loginData) {
                        setLoginStatus(false);
                        setLoginAccount("");
                        mView.handleLogoutSuccess();
                        EventBus.getDefault().post(new LogoutEvent());
                    }
                }));
    }

    @Override
    public void setNightMode(boolean isNightMode) {

    }

    @Override
    public boolean isNightMode() {
        return false;
    }
}
