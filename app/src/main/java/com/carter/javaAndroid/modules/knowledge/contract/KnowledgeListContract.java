package com.carter.javaAndroid.modules.knowledge.contract;

import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.CollectEventContract;

public interface KnowledgeListContract {

    interface View extends CollectEventContract.View {
        void showKnowledgeListData(ArticleListBean articleListBean, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void refreshLayout(int cid, boolean isShowStatusView);

        void getKnowledgeListData(boolean isShowStatusView);

        void loadMore();
    }

}
