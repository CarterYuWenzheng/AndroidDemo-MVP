package com.carter.javaAndroid.core.http;


import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.login.bean.LoginData;

import java.util.List;

import io.reactivex.Observable;

public interface IHttp {


    Observable<BaseResponse<LoginData>> login(String username, String password);

    Observable<BaseResponse<ArticleListBean>> getArticleList(int pageNum);

    Observable<BaseResponse<List<BannerData>>> getBannerData();

    Observable<BaseResponse<List<ArticleItemBean>>> getTopArticles();
}
