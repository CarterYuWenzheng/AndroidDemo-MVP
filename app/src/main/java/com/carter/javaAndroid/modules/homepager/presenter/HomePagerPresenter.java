package com.carter.javaAndroid.modules.homepager.presenter;


import com.carter.javaAndroid.R;
import com.carter.javaAndroid.core.http.BaseResponse;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.homepager.contract.HomePagerContract;
import com.carter.javaAndroid.modules.main.presenter.CollectEventPresenter;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class HomePagerPresenter extends CollectEventPresenter<HomePagerContract.View>
        implements HomePagerContract.Presenter {

    private int currentPage;
    private boolean isRefresh = true;

    @Inject
    HomePagerPresenter() {
    }

    @Override
    public void getArticleList(boolean isShowStatusView) {
        addSubscribe(mDataManager.getArticleList(currentPage)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListBean -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView, "", isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListBean articleListBean) {
                        mView.showArticleList(articleListBean, isRefresh);
                    }
                }));
    }

    @Override
    public void getBannerData(boolean isShowStatusView) {
        addSubscribe(mDataManager.getBannerData()
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListBean -> mView != null)
                .subscribeWith(new BaseObserver<List<BannerData>>(mView, "", isShowStatusView) {
                    @Override
                    public void onSuccess(List<BannerData> bannerData) {
                        mView.showBannerData(bannerData);
                    }
                }));
    }

    @Override
    public void getHomePagerData(boolean isShowStatusView) {
        getBannerData(isShowStatusView);
        addSubscribe(Observable.zip(mDataManager.getTopArticles(), mDataManager.getArticleList(0),
                (listBaseResponse, articleListBeanBaseResponse) -> {
                    articleListBeanBaseResponse.getData().getDatas().addAll(0, listBaseResponse.getData());
                    return articleListBeanBaseResponse;
                })
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListBean -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView, "s", isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListBean articleListBean) {
                        mView.showArticleList(articleListBean, isRefresh);
                    }
                }));
    }

    @Override
    public void refreshLayout(boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        getHomePagerData(isShowStatusView);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getArticleList(false);
    }
}
