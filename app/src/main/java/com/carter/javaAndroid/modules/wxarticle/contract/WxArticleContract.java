package com.carter.javaAndroid.modules.wxarticle.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface WxArticleContract {

    interface View extends IView{

    }

    interface Presenter extends IPresenter<View>{}

}
