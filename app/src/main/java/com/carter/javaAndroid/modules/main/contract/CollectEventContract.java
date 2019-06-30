package com.carter.javaAndroid.modules.main.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface CollectEventContract {

    interface View extends IView {

    }

    interface Presenter<V extends View> extends IPresenter<V> {

    }

}
