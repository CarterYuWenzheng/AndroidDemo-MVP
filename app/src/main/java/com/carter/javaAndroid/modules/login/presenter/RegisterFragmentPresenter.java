package com.carter.javaAndroid.modules.login.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.login.contract.RegisterFragmentContract;

import javax.inject.Inject;

public class RegisterFragmentPresenter extends BasePresenter<RegisterFragmentContract.View> implements RegisterFragmentContract.Presenter{

    @Inject
    public RegisterFragmentPresenter() {
    }


}
