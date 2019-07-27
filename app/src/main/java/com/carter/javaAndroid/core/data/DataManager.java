package com.carter.javaAndroid.core.data;

import com.carter.javaAndroid.core.http.BaseResponse;
import com.carter.javaAndroid.core.http.HttpImpl;
import com.carter.javaAndroid.core.http.IHttp;
import com.carter.javaAndroid.core.preference.IPreference;
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

public class DataManager implements IHttp, IPreference {


    private IHttp mIHttp;
    private IPreference mIPreference;

    public DataManager(IHttp iHttp,IPreference iPreference){
        mIHttp = iHttp;
        mIPreference = iPreference;
    }

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mIHttp.login(username,password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mIHttp.logout();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mIPreference.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mIPreference.getLoginStatus();
    }

    @Override
    public void setLoginAccount(String account) {
        mIPreference.setLoginAccount(account);
    }

    @Override
    public String getLoginAccount() {
        return mIPreference.getLoginAccount();
    }

    @Override
    public void setNightMode(boolean isNightMode) {
        mIPreference.setNightMode(isNightMode);
    }

    @Override
    public boolean isNightMode() {
        return mIPreference.isNightMode();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getArticleList(int pageNum) {
        return mIHttp.getArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mIHttp.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<ArticleItemBean>>> getTopArticles() {
        return mIHttp.getTopArticles();
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeTreeBean>>> getKnowledgeTree() {
        return mIHttp.getKnowledgeTree();
    }

    @Override
    public Observable<BaseResponse<List<NavigationListBean>>> getNavigationList() {
        return mIHttp.getNavigationList();
    }

    @Override
    public Observable<BaseResponse<List<WxChapterBean>>> getWxChapterListData() {
        return mIHttp.getWxChapterListData();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getWxArticlesData(int id, int page) {
        return mIHttp.getWxArticlesData(id, page);
    }

    @Override
    public Observable<BaseResponse<List<ProjectTreeBean>>> getProjectTreeData() {
        return mIHttp.getProjectTreeData();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getProjectListData(int page, int cid) {
        return mIHttp.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> addCollectArticle(int id) {
        return mIHttp.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> cancelCollectArticle(int id) {
        return mIHttp.cancelCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getKnowledgeListData(int page, int cid) {
        return mIHttp.getKnowledgeListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<TopSearchBean>>> getTopSearchData() {
        return mIHttp.getTopSearchData();
    }
}
