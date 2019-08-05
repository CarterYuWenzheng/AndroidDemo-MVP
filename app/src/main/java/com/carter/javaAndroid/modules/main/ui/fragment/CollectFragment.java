package com.carter.javaAndroid.modules.main.ui.fragment;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.CollectContract;
import com.carter.javaAndroid.modules.main.presenter.CollectPresenter;

public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectContract.View {


    public static CollectFragment newInstance(){return new CollectFragment();}

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showCollectList(ArticleListBean listBean, boolean isRefresh) {

    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollectSuccess(int position) {

    }
}
