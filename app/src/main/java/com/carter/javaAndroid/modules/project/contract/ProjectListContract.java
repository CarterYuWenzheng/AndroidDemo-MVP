package com.carter.javaAndroid.modules.project.contract;

import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.CollectEventContract;

public interface ProjectListContract {

    interface View extends CollectEventContract.View {
        void showProjectListData(ArticleListBean articleListBean, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void getProjectListData(boolean isShowStatusView);

        void refreshLayout(int cid, boolean isShowStatusView);

        void loadMore();
    }
}
