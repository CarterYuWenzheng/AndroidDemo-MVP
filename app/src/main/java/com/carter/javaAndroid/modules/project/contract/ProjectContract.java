package com.carter.javaAndroid.modules.project.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.project.bean.ProjectTreeBean;

import java.util.List;

public interface ProjectContract {

    interface View extends IView{
        void showProjectTreeData(List<ProjectTreeBean> projectTreeBeans);
    }

    interface Presenter extends IPresenter<View>{
        void getProjectTreeData();
    }

}
