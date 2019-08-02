package com.carter.javaAndroid.modules.main.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.main.bean.UsefulSiteBean;
import com.carter.javaAndroid.modules.main.contract.UsefulSiteContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class UsefulSitePresenter extends BasePresenter<UsefulSiteContract.View> implements UsefulSiteContract.Presenter {

    @Inject
    UsefulSitePresenter() {
    }

    @Override
    public void getUsefulSites() {
        addSubscribe(mDataManager.getUsefulSites()
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<List<UsefulSiteBean>>(mView, "加载失败", true) {
                    @Override
                    public void onSuccess(List<UsefulSiteBean> usefulSiteBeans) {
                        mView.showUsefulSites(usefulSiteBeans);
                    }
                }));
    }
}
