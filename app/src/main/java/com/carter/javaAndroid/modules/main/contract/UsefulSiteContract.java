package com.carter.javaAndroid.modules.main.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface UsefulSiteContract {

    interface View extends IView{
        void showUsefulSites();
    }

    interface Presenter extends IPresenter<View>{

        void getUsefulSites();
    }

}
