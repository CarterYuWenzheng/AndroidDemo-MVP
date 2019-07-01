package com.carter.javaAndroid.modules.project.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.project.contract.ProjectContract;

import javax.inject.Inject;

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter{

    @Inject
    ProjectPresenter(){}
}
