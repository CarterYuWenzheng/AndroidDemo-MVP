package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.main.contract.UsefulSiteContract;

import javax.inject.Inject;

public class UsefulSitePresenter extends BasePresenter<UsefulSiteContract.View> implements UsefulSiteContract.Presenter{

    @Inject
    UsefulSitePresenter(){}

    @Override
    public void getUsefulSites() {

    }
}
