package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.main.contract.SearchContract;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    @Inject
    SearchPresenter() {
    }
}
