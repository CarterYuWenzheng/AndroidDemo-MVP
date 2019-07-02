package com.carter.javaAndroid.modules.homepager.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.main.contract.CollectEventContract;

import java.util.List;

public interface HomePagerContract {

    interface View extends CollectEventContract.View {
        void showArticleList(ArticleListBean articleListBean, boolean isRefresh);

        void showBannerData(List<BannerData> bannerDataList);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void getArticleList(boolean isShowStatusView);

        void getBannerData(boolean isShowStatusView);

        void getHomePagerData(boolean isShowStatusView);

        void refreshLayout(boolean isShowStatusView);

        void loadMore();

    }
}
