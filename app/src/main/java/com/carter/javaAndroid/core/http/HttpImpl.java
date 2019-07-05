package com.carter.javaAndroid.core.http;



import com.carter.javaAndroid.core.http.api.ApiService;
import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.login.bean.LoginData;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class HttpImpl implements IHttp {

    private ApiService mApiService;

    @Inject
    HttpImpl(ApiService apiService){
        mApiService = apiService;
    }

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mApiService.login(username,password);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getArticleList(int pageNum) {
        return mApiService.getArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mApiService.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<ArticleItemBean>>> getTopArticles() {
        return mApiService.getTopArticles();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeTreeBean>>> getKnowledgeTree() {
        return mApiService.getKnowledgeTreeData();
    }

    @Override
    public Observable<BaseResponse<List<NavigationListBean>>> getNavigationList() {
        return mApiService.getNavigationListData();
    }
}
