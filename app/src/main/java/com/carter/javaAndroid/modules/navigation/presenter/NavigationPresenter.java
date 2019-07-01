package com.carter.javaAndroid.modules.navigation.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.navigation.contract.NavigationContract;

import javax.inject.Inject;

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter{

    @Inject
    NavigationPresenter(){}
}
