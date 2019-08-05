package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.core.event.CollectEvent;
import com.carter.javaAndroid.core.event.RefreshHomeEvent;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.CollectContract;
import com.carter.javaAndroid.utils.RxUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class CollectPresenter extends CollectEventPresenter<CollectContract.View> implements CollectContract.Presenter{

    private int currentPage;
    private boolean isRefresh = true;
    private boolean isReCollected = false;

    @Inject
    CollectPresenter(){}

    @Override
    public void getCollectArticle(boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        getCollectList(isShowStatusView);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getCollectList(false);
    }

    @Override
    public void getCollectList(boolean isShowStatusView) {
        addSubscribe(mDataManager.getCollectList(currentPage)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView,
                        "获取失败",
                        isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListBean articleListData) {
                        mView.showCollectList(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void cancelCollectInCollectPage(int position, int id, int originId) {
        addSubscribe(mDataManager.cancelCollectInCollectPage(id, originId)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView,
                        "操作失败",
                        false) {
                    @Override
                    public void onSuccess(ArticleListBean articleListData) {
                        mView.showCancelCollectSuccess(position);
                        EventBus.getDefault().post(new RefreshHomeEvent());
                    }
                }));
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            if (isReCollected) {
                isReCollected = false;
                mView.showCancelCollectSuccess(0);
            } else {
                mView.showCancelCollectSuccess(collectEvent.getArticlePostion());
            }
        } else {
            getCollectList(false);
            mView.showCollectSuccess(-1);
            isReCollected = true;
        }
        EventBus.getDefault().post(new RefreshHomeEvent());
    }
}
