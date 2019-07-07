package com.carter.javaAndroid.modules.wxarticle.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.wxarticle.bean.WxChapterBean;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class WxArticlePresenter extends BasePresenter<WxArticleContract.View> implements WxArticleContract.Presenter{

    @Inject
    WxArticlePresenter(){}

    @Override
    public void getWxChapterListData() {
        addSubscribe(mDataManager.getWxChapterListData()
        .compose(RxUtils.SchedulerTransformer())
        .filter(wxChapterlistBean -> mView != null)
        .subscribeWith(new BaseObserver<List<WxChapterBean>>(mView,"获取失败",
                true) {
            @Override
            public void onSuccess(List<WxChapterBean> wxChapterBeans) {
                mView.showWxChapterListData(wxChapterBeans);
            }
        }));
    }
}
