package com.carter.javaAndroid.modules.main.contract;


import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;

public interface SearchResultContracrt {

    interface View extends CollectEventContract.View {
        void showSearchResultList(ArticleListBean articleListBean, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void search(String searchData, boolean isShowStatusView);

        void getSearchResultList(boolean isShowStatusView);

        void loadMore();
    }
}
