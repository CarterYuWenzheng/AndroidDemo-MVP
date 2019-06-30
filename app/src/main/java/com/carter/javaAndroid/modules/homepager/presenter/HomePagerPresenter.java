package com.carter.javaAndroid.modules.homepager.presenter;


import com.carter.javaAndroid.modules.homepager.contract.HomePagerContract;
import com.carter.javaAndroid.modules.main.presenter.CollectEventPresenter;

import javax.inject.Inject;

public class HomePagerPresenter extends CollectEventPresenter<HomePagerContract.View>
        implements HomePagerContract.Presenter {

    @Inject
    HomePagerPresenter(){

    }
}
