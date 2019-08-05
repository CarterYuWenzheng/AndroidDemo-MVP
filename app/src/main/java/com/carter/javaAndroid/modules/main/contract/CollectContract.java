package com.carter.javaAndroid.modules.main.contract;

import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;

public interface CollectContract {

    interface View extends CollectEventContract.View {
        void showCollectList(ArticleListBean listBean, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {
        void getCollectArticle(boolean isShowStatusView);

        void loadMore();

        void getCollectList(boolean isShowStatusView);

        void cancelCollectInCollectPage(int position, int id, int originId);
    }
}
