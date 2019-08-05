package com.carter.javaAndroid.core.http;


import com.carter.javaAndroid.core.http.api.ApiService;
import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.login.bean.LoginData;
import com.carter.javaAndroid.modules.main.bean.TopSearchBean;
import com.carter.javaAndroid.modules.main.bean.UsefulSiteBean;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;
import com.carter.javaAndroid.modules.project.bean.ProjectTreeBean;
import com.carter.javaAndroid.modules.wxarticle.bean.WxChapterBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class HttpImpl implements IHttp {

    private ApiService mApiService;

    @Inject
    HttpImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mApiService.login(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mApiService.logout();
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

    @Override
    public Observable<BaseResponse<List<WxChapterBean>>> getWxChapterListData() {
        return mApiService.getWxChapterListData();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getWxArticlesData(int id, int page) {
        return mApiService.getWxArticlesData(id, page);
    }

    @Override
    public Observable<BaseResponse<List<ProjectTreeBean>>> getProjectTreeData() {
        return mApiService.getProjectTreeData();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getProjectListData(int page, int cid) {
        return mApiService.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> addCollectArticle(int id) {
        return mApiService.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> cancelCollectArticle(int id) {
        return mApiService.cancelCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getKnowledgeListData(int page, int cid) {
        return mApiService.getKnowledgeListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<TopSearchBean>>> getTopSearchData() {
        return mApiService.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getSearchResultList(int pageNum, String k) {
        return mApiService.getSearchResultList(pageNum, k);
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteBean>>> getUsefulSites() {
        return mApiService.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getCollectList(int page) {
        return mApiService.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> cancelCollectInCollectPage(int id, int originId) {
        return mApiService.cancelCollectInCollectPage(id, originId);
    }
}
