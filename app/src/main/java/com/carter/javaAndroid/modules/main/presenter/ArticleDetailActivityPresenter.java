package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.modules.main.contract.ArticleDetailActivityContract;

import javax.inject.Inject;

public class ArticleDetailActivityPresenter extends CollectEventPresenter<ArticleDetailActivityContract.View>
        implements ArticleDetailActivityContract.Presenter {

    @Inject
    ArticleDetailActivityPresenter(){}
}
