package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.main.contract.AboutContract;

import javax.inject.Inject;

public class AboutPresenter extends BasePresenter<AboutContract.View> implements AboutContract.Presenter {

    @Inject
    AboutPresenter() {
    }
}
