package com.carter.javaAndroid.todo.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface TodoContract {

    interface View extends IView{

    }

    interface Presenter extends IPresenter<View>{

    }
}
