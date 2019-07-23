package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.main.contract.MainActivityContract;

import javax.inject.Inject;

public class MainActivityPresenter extends BasePresenter<MainActivityContract.View>
        implements MainActivityContract.Presenter {

    @Inject
    MainActivityPresenter() {
    }
}
