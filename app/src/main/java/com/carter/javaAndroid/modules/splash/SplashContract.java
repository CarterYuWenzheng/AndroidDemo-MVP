package com.carter.javaAndroid.modules.splash;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface SplashContract {

    interface View extends IView{

    }

    interface Presenter extends IPresenter<View>{

    }
}
