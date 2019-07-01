package com.carter.javaAndroid.modules.wxarticle.ui;


import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleContract;
import com.carter.javaAndroid.modules.wxarticle.presenter.WxArticlePresenter;

public class WxArticleFragment extends BaseFragment<WxArticlePresenter> implements WxArticleContract.View {

    public static WxArticleFragment newInstance(){
        return new WxArticleFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_article;
    }

    @Override
    protected void initEventAndData() {

    }
}
