package com.carter.javaAndroid.modules.main.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.main.bean.UsefulSiteBean;

import java.util.List;

public interface UsefulSiteContract {

    interface View extends IView{
        void showUsefulSites(List<UsefulSiteBean> usefulSiteData);
    }

    interface Presenter extends IPresenter<View>{

        void getUsefulSites();
    }

}
