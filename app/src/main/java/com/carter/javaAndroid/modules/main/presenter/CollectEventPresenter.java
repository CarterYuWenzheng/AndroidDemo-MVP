package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.CollectEventContract;
import com.carter.javaAndroid.modules.main.ui.activity.MainActivity;
import com.carter.javaAndroid.utils.RxUtils;

public class CollectEventPresenter<V extends CollectEventContract.View>
        extends BasePresenter<V> implements CollectEventContract.Presenter<V> {


    @Override
    public void addCollectArticle(int position, int id) {
        addSubscribe(mDataManager.addCollectArticle(id)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView, "失败", false) {
                    @Override
                    public void onSuccess(ArticleListBean articleListBean) {
                        mView.showCollectSuccess(position);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(int position, int id) {

        addSubscribe(mDataManager.cancelCollectArticle(id)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView,
                        "失败",
                        false) {
                    @Override
                    public void onSuccess(ArticleListBean articleListData) {
                        mView.showCancelCollectSuccess(position);
                    }
                }));
    }
}
