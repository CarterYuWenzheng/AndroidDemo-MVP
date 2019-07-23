package com.carter.javaAndroid.modules.main.contract;


import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface MainActivityContract {

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {

        void logout();

        void setNightMode(boolean isNightMode);

        boolean isNightMode();
    }
}
