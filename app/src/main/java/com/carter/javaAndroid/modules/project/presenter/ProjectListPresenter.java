package com.carter.javaAndroid.modules.project.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.project.contract.ProjectListContract;
import com.carter.javaAndroid.utils.RxUtils;

import javax.inject.Inject;

public class ProjectListPresenter extends BasePresenter<ProjectListContract.View> implements ProjectListContract.Presenter {

    private int currentPage = 1;
    private boolean isRefresh = true;
    private int cid;

    @Inject
    ProjectListPresenter() {
    }

    @Override
    public void getProjectListData(boolean isShowStatusView) {
        addSubscribe(mDataManager.getProjectListData(currentPage, cid)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListBean -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListBean>(mView, "获取失败", isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListBean articleListBean) {
                        if (isShowStatusView && currentPage == 1 && articleListBean.getDatas().size() < 1) {
                            mView.showEmpty();
                        } else {
                            mView.showProjectListData(articleListBean, isRefresh);
                        }
                    }
                }));
    }

    @Override
    public void refreshLayout(int cid, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 1;
        this.cid = cid;
        getProjectListData(isShowStatusView);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getProjectListData(false);
    }
}
