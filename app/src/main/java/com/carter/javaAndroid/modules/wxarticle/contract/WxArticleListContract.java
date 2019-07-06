package com.carter.javaAndroid.modules.wxarticle.contract;

import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.CollectEventContract;

public interface WxArticleListContract {

    interface View extends CollectEventContract.View {
        void showWxArticlelist(ArticleListBean articleListBean,boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View>{

        void getWxArticleList(boolean isShowStatusView);

        void refreshData(int id,boolean isShowStatusView);

        void loadMore();
    }
}
