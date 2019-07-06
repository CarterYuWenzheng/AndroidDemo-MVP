package com.carter.javaAndroid.core.http;


import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.login.bean.LoginData;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;

import java.util.List;

import io.reactivex.Observable;

public interface IHttp {

    Observable<BaseResponse<LoginData>> login(String username, String password);

    Observable<BaseResponse<ArticleListBean>> getArticleList(int pageNum);

    Observable<BaseResponse<List<BannerData>>> getBannerData();

    Observable<BaseResponse<List<ArticleItemBean>>> getTopArticles();

    Observable<BaseResponse<List<KnowledgeTreeBean>>> getKnowledgeTree();

    Observable<BaseResponse<List<NavigationListBean>>> getNavigationList();

    Observable<BaseResponse<ArticleListBean>> getWxArticlesData(int id,int page);

}
