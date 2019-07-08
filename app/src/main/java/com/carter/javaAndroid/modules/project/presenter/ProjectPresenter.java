package com.carter.javaAndroid.modules.project.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.project.bean.ProjectTreeBean;
import com.carter.javaAndroid.modules.project.contract.ProjectContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    @Inject
    ProjectPresenter() {
    }

    @Override
    public void getProjectTreeData() {
        addSubscribe(mDataManager.getProjectTreeData()
                .compose(RxUtils.SchedulerTransformer())
                .filter(projectTreeDataList -> mView != null)
                .subscribeWith(new BaseObserver<List<ProjectTreeBean>>(mView, "获取失败", true) {
                    @Override
                    public void onSuccess(List<ProjectTreeBean> projectTreeBeans) {
                        mView.showProjectTreeData(projectTreeBeans);
                    }
                }));
    }
}
