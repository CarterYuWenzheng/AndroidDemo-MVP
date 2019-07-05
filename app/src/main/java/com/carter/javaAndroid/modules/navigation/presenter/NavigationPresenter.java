package com.carter.javaAndroid.modules.navigation.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;
import com.carter.javaAndroid.modules.navigation.contract.NavigationContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {

    @Inject
    NavigationPresenter() {
    }

    @Override
    public void getNavigationList() {
        addSubscribe(mDataManager.getNavigationList()
                .compose(RxUtils.SchedulerTransformer())
                .filter(navigationListBean -> mView != null)
                .subscribeWith(new BaseObserver<List<NavigationListBean>>(mView,
                        "获取导航失败", true) {
                    @Override
                    public void onSuccess(List<NavigationListBean> navigationListBeans) {
                        mView.showNavigationList(navigationListBeans);
                    }
                }));
    }
}
