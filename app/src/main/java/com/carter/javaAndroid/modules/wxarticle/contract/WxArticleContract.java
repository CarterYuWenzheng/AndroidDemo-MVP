package com.carter.javaAndroid.modules.wxarticle.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.wxarticle.bean.WxChapterBean;

import java.util.List;

public interface WxArticleContract {

    interface View extends IView{
        void showWxChapterListData(List<WxChapterBean> wxChapterBeans);
    }

    interface Presenter extends IPresenter<View>{
        void getWxChapterListData();
    }

}
