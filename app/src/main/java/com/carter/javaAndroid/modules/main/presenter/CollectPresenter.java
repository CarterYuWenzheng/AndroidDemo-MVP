package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.modules.main.contract.CollectContract;

import javax.inject.Inject;

public class CollectPresenter extends CollectEventPresenter<CollectContract.View> implements CollectContract.Presenter{

    @Inject
    CollectPresenter(){}

    @Override
    public void getCollectArticle(boolean isShowStatusView) {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void getCollectList(boolean isShowStatusView) {

    }

    @Override
    public void cancelCollectInCollectPage(int position, int id, int originId) {

    }
}
