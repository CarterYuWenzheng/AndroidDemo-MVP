package com.carter.javaAndroid.modules.wxarticle.presenter;

import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.presenter.CollectEventPresenter;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleListContract;
import com.carter.javaAndroid.utils.RxUtils;

import javax.inject.Inject;

public class WxArticleListPresenter extends CollectEventPresenter<WxArticleListContract.View>
        implements WxArticleListContract.Presenter {

    private int currentPage = 1;
    private boolean isRefresh = true;
    private int id;

    @Inject
    WxArticleListPresenter(){}

    @Override
    public void getWxArticleList(boolean isShowStatusView) {
        addSubscribe(mDataManager.getWxArticlesData(id,currentPage)
                .compose(RxUtils.SchedulerTransformer())
        .filter(articleListBean -> mView != null)
        .subscribeWith(new BaseObserver<ArticleListBean>(mView, "获取失败", isShowStatusView) {
            @Override
            public void onSuccess(ArticleListBean articleListBean) {
                if(isShowStatusView && currentPage == 1 &&
                        articleListBean.getDatas().size() < 1) {
                    mView.showEmpty();
                } else {
                    mView.showWxArticlelist(articleListBean, isRefresh);
                }
            }
        }));
    }

    @Override
    public void refreshData(int id, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 1;
        this.id = id;
        getWxArticleList(isShowStatusView);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getWxArticleList(false);
    }
}
