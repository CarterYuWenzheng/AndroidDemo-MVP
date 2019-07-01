package com.carter.javaAndroid.modules.navigation.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface NavigationContract {

    interface View extends IView{

    }

    interface Presenter extends IPresenter<View>{

    }
}
