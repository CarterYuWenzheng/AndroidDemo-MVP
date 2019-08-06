package com.carter.javaAndroid.core.http.api;

import com.carter.javaAndroid.core.http.BaseResponse;
import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.login.bean.LoginData;
import com.carter.javaAndroid.modules.main.bean.TopSearchBean;
import com.carter.javaAndroid.modules.main.bean.UsefulSiteBean;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;
import com.carter.javaAndroid.modules.project.bean.ProjectTreeBean;
import com.carter.javaAndroid.modules.todo.bean.TodoItemBean;
import com.carter.javaAndroid.modules.todo.bean.TodoListBean;
import com.carter.javaAndroid.modules.wxarticle.bean.WxChapterBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

    /**
     * 搜索
     * https://www.wanandroid.com/article/query/0/json
     *
     * @param page page
     * @param k    POST search key
     * @return 搜索数据
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListBean>> getSearchResultList(@Path("page") int page, @Field("k") String k);


    /**
     * 常用网站
     * https://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
    @GET("friend/json")
    Observable<BaseResponse<List<UsefulSiteBean>>> getUsefulSites();


    /**
     * 获取收藏列表
     * https://www.wanandroid.com/lg/collect/list/0/json
     *
     * @param page page number
     * @return 收藏列表数据
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<ArticleListBean>> getCollectList(@Path("page") int page);

    /**
     * 收藏列表中取消收藏文章
     * https://www.wanandroid.com/lg/uncollect/2805/json
     *
     * @param id       article id
     * @param originId originId 代表的是你收藏之前的那篇文章本身的id；
     *                 但是收藏支持主动添加，这种情况下，没有originId则为-1
     * @return 取消收藏列表中文章数据
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListBean>> cancelCollectInCollectPage(@Path("id") int id, @Field("originId") int originId);

    /**
     * 新增一条TODO
     * https://www.wanandroid.com/lg/todo/add/json
     * <p>
     * title: 新增标题（必须）
     * content: 新增详情（可选）
     * date: 2018-08-01 预定完成时间（不传默认当天，建议传）
     * type: 大于0的整数（可选）；
     * priority 大于0的整数（可选）；
     *
     * @return
     */
    @POST("lg/todo/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<TodoItemBean>> addTodo(@FieldMap Map<String, Object> map);

    /**
     * 更新一条TODO
     * https://www.wanandroid.com/lg/todo/update/{id}/json
     * <p>
     * id: 拼接在链接上，为唯一标识
     * title: 更新标题 （必须）
     * content: 新增详情（必须）
     * date: 2018-08-01（必须）
     * status: 0 // 0为未完成，1为完成
     * type: ；
     * priority: ；
     *
     * @return
     */
    @POST("lg/todo/update/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<TodoItemBean>> updateTodo(@Path("id") int id, @FieldMap Map<String, Object> map);

    /**
     * 获取TODO列表
     * https://www.wanandroid.com/lg/todo/v2/list/{page}/json
     * <p>
     * 页码从1开始，拼接在url 上
     * status 状态， 1-完成；0未完成; 默认全部展示；
     * type 创建时传入的类型, 默认全部展示
     * priority 创建时传入的优先级；默认全部展示
     * orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；（1和2只能获取到已完成的TODO）
     *
     * @return
     */
    @GET("lg/todo/v2/list/{page}/json")
    Observable<BaseResponse<TodoListBean>> getTodoListData(@Path("page") int page, @QueryMap Map<String, Object> map);
    
    /**
     * 删除一条TODO
     * https://www.wanandroid.com/lg/todo/delete/{id}/json
     * <p>
     * id: 拼接在链接上，为唯一标识

     * @return
     */
    @POST("lg/todo/delete/{id}/json")
    Observable<BaseResponse<TodoItemBean>> deleteTodo(@Path("id") int id);

    /**
     * 仅更新完成状态TODO
     * https://www.wanandroid.com/lg/todo/done/{id}/json
     * <p>
     * id: 拼接在链接上，为唯一标识
     * status: 0或1，传1代表未完成到已完成，反之则反之。
     * @return
     */
    @POST("lg/todo/done/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<TodoItemBean>> updateTodoStatus(@Path("id") int id, @Field("status") int status);
}
