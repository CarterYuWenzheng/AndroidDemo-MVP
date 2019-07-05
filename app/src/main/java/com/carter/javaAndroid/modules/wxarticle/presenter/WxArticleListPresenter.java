package com.carter.javaAndroid.modules.wxarticle.presenter;

import com.carter.javaAndroid.modules.main.presenter.CollectEventPresenter;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleListContract;

import javax.inject.Inject;

public class WxArticleListPresenter extends CollectEventPresenter<WxArticleListContract.View>
        implements WxArticleListContract.Presenter {

    @Inject
    WxArticleListPresenter(){}

}
