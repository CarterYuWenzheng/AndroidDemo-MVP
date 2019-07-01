package com.carter.javaAndroid.modules.project.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface ProjectContract {

    interface View extends IView{

    }

    interface Presenter extends IPresenter<View>{}

}
