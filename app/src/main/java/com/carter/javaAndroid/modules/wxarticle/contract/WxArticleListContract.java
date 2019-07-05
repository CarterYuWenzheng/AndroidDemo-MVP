package com.carter.javaAndroid.modules.wxarticle.contract;

import com.carter.javaAndroid.modules.main.contract.CollectEventContract;

public interface WxArticleListContract {

    interface View extends CollectEventContract.View {

    }

    interface Presenter extends CollectEventContract.Presenter<View>{

    }
}
