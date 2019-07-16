package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.main.contract.CommonContract;

import javax.inject.Inject;

public class CommonPresenter extends BasePresenter<CommonContract.View> implements CommonContract.Presenter {

    @Inject
    CommonPresenter() {
    }
}
