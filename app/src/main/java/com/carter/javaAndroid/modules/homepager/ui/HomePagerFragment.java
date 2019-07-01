package com.carter.javaAndroid.modules.homepager.ui;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.homepager.contract.HomePagerContract;
import com.carter.javaAndroid.modules.homepager.presenter.HomePagerPresenter;

public class HomePagerFragment extends BaseFragment<HomePagerPresenter>
        implements HomePagerContract.View {

    public static HomePagerFragment newInstance(){
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        return homePagerFragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initEventAndData() {

    }
}
