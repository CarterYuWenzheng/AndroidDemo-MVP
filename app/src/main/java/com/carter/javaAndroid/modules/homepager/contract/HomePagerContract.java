package com.carter.javaAndroid.modules.homepager.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.main.contract.CollectEventContract;

public interface HomePagerContract {

    interface View extends CollectEventContract.View {

    }

    interface Presenter extends CollectEventContract.Presenter<View>{

    }
}
