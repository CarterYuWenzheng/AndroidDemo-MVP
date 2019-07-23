package com.carter.javaAndroid.core.http;


import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.login.bean.LoginData;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;
import com.carter.javaAndroid.modules.project.bean.ProjectTreeBean;
import com.carter.javaAndroid.modules.wxarticle.bean.WxChapterBean;

import java.util.List;

import io.reactivex.Observable;

public interface IHttp {

    Observable<BaseResponse<LoginData>> login(String username, String password);

    Observable<BaseResponse<LoginData>> logout();

    Observable<BaseResponse<ArticleListBean>> getArticleList(int pageNum);

    Observable<BaseResponse<List<BannerData>>> getBannerData();

    Observable<BaseResponse<List<ArticleItemBean>>> getTopArticles();

    Observable<BaseResponse<List<KnowledgeTreeBean>>> getKnowledgeTree();

    Observable<BaseResponse<List<NavigationListBean>>> getNavigationList();

    Observable<BaseResponse<List<WxChapterBean>>> getWxChapterListData();

    Observable<BaseResponse<ArticleListBean>> getWxArticlesData(int id,int page);

    Observable<BaseResponse<List<ProjectTreeBean>>> getProjectTreeData();

    Observable<BaseResponse<ArticleListBean>> getProjectListData(int page,int cid);

    Observable<BaseResponse<ArticleListBean>> addCollectArticle(int id);

    Observable<BaseResponse<ArticleListBean>> cancelCollectArticle(int id);

    Observable<BaseResponse<ArticleListBean>> getKnowledgeListData(int page, int cid);

}
