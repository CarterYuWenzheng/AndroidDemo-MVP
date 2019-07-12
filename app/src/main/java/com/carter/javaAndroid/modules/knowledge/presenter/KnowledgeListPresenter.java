package com.carter.javaAndroid.modules.knowledge.presenter;

import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeListContract;
import com.carter.javaAndroid.modules.main.presenter.CollectEventPresenter;
import com.carter.javaAndroid.utils.RxUtils;

import javax.inject.Inject;

public class KnowledgeListPresenter extends CollectEventPresenter<KnowledgeListContract.View> implements KnowledgeListContract.Presenter {

    private int currentPage = 0;
    private boolean isRefresh = true;
    private int cid;

    @Inject
    KnowledgeListPresenter() {
    }

    @Override
    public void refreshLayout(int cid, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        this.cid = cid;
        getKnowledgeListData(isShowStatusView);
    }

    @Override
    public void getKnowledgeListData(boolean isShowStatusView) {
        addSubscribe(mDataManager.getKnowledgeListData(currentPage, cid)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView, "获取失败", isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListBean articleListBean) {
                        if (isShowStatusView && currentPage == 0 &&
                                articleListBean.getDatas().size() < 1) {
                            mView.showEmpty();
                        } else {
                            mView.showKnowledgeListData(articleListBean, isRefresh);
                        }
                    }
                }));
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getKnowledgeListData(false);
    }
}
