package com.carter.javaAndroid.modules.project.ui;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.project.contract.ProjectContract;
import com.carter.javaAndroid.modules.project.presenter.ProjectPresenter;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {

    }
}
