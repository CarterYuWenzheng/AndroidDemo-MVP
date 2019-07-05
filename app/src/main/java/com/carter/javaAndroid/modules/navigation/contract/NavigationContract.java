package com.carter.javaAndroid.modules.navigation.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;

import java.util.List;

public interface NavigationContract {

    interface View extends IView {
        void showNavigationList(List<NavigationListBean> navigationListBeans);
    }

    interface Presenter extends IPresenter<View> {
        void getNavigationList();
    }
}
