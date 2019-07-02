package com.carter.javaAndroid.modules.homepager.presenter;


import com.carter.javaAndroid.modules.homepager.contract.HomePagerContract;
import com.carter.javaAndroid.modules.main.presenter.CollectEventPresenter;

import javax.inject.Inject;

public class HomePagerPresenter extends CollectEventPresenter<HomePagerContract.View>
        implements HomePagerContract.Presenter {

    private int currentPage;
    private boolean isRefresh = true;

    @Inject
    HomePagerPresenter(){}

    @Override
    public void getArticleList(boolean isShowStatusView) {

    }

    @Override
    public void getBannerData(boolean isShowStatusView) {

    }

    @Override
    public void getHomePagerData(boolean isShowStatusView) {
        getBannerData(isShowStatusView);
    }

    @Override
    public void refreshLayout(boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        getHomePagerData(isShowStatusView);
    }

    @Override
    public void loadMore() {
        refreshLayout(true);
    }
}
