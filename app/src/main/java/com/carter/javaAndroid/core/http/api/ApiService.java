package com.carter.javaAndroid.core.http.api;

import com.carter.javaAndroid.core.http.BaseResponse;
import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.login.bean.LoginData;
import com.carter.javaAndroid.modules.main.bean.TopSearchBean;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;
import com.carter.javaAndroid.modules.project.bean.ProjectTreeBean;
import com.carter.javaAndroid.modules.wxarticle.bean.WxChapterBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "https://www.wanandroid.com/";

    /**
     * 获取文章列表
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<ArticleListBean>> getArticleList(@Path("pageNum") int pageNum);

    /**
     * 广告栏
     * https://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 热搜
     * https://www.wanandroid.com//hotkey/json
     *
     * @return 热门搜索数据
     */
    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<BaseResponse<List<TopSearchBean>>> getTopSearchData();

    /**
     * 获取首页置顶文章列表
     * https://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<ArticleItemBean>>> getTopArticles();

    /**
     * 知识体系
     * https://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeTreeBean>>> getKnowledgeTreeData();


    /**
     * 导航列表
     * https://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationListBean>>> getNavigationListData();

    /**
     * 获取公众号列表
     * https://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WxChapterBean>>> getWxChapterListData();

    /**
     * 获取当前公众号的数据
     * https://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号的数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getWxArticlesData(@Path("id") int id, @Path("page") int page);

    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectTreeBean>>> getProjectTreeData();

    /**
     * 项目列表数据
     * https://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid  child page id
     * @return 项目列表数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getProjectListData(@Path("page") int page, @Query("cid") int cid);

    /**
     * 收藏站内文章
     * https://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id article id
     * @return 收藏站内文章数据
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<ArticleListBean>> addCollectArticle(@Path("id") int id);

    /**
     * 文章列表中取消收藏文章
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     *
     * @param id 列表中文章的id
     * @return 取消站内文章数据
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<ArticleListBean>> cancelCollectArticle(@Path("id") int id);

    /**
     * 知识体系下的文章
     * https://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid  second page id
     * @return 知识体系文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getKnowledgeListData(@Path("page") int page, @Query("cid") int cid);


    /**
     * 登录
     * https://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登录数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> login(@Field("username") String username, @Field("password") String password);


    /**
     * 退出登录
     * https://www.wanandroid.com/user/logout/json
     *
     * @return 登录数据
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();
}
