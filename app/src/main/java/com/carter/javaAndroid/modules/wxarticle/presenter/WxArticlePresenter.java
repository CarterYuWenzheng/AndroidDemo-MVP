package com.carter.javaAndroid.modules.wxarticle.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleContract;

import javax.inject.Inject;

public class WxArticlePresenter extends BasePresenter<WxArticleContract.View> implements WxArticleContract.Presenter{

    @Inject
    WxArticlePresenter(){}
}
