package com.carter.javaAndroid.core.data;

import com.carter.javaAndroid.core.db.DbHelper;
import com.carter.javaAndroid.core.db.bean.HistoryBean;
import com.carter.javaAndroid.core.http.BaseResponse;
import com.carter.javaAndroid.core.http.IHttp;
import com.carter.javaAndroid.core.preference.IPreference;
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

import io.reactivex.Observable;

public class DataManager implements IHttp, IPreference, DbHelper {

    private IHttp mIHttp;
    private IPreference mIPreference;
    private DbHelper mDbHelper;

    public DataManager(IHttp iHttp, IPreference iPreference, DbHelper dbHelper) {
        mIHttp = iHttp;
        mIPreference = iPreference;
        mDbHelper = dbHelper;
    }

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mIHttp.login(username, password);
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

    @Override
    public List<HistoryBean> addHistoryData(String data) {
        return mDbHelper.addHistoryData(data);
    }

    @Override
    public void clearAllHistoryData() {
        mDbHelper.clearAllHistoryData();
    }

    @Override
    public void deleteHistoryDataById(Long id) {
        mDbHelper.deleteHistoryDataById(id);
    }

    @Override
    public List<HistoryBean> loadAllHistoryData() {
        return mDbHelper.loadAllHistoryData();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getSearchResultList(int pageNum, String k) {
        return mIHttp.getSearchResultList(pageNum, k);
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteBean>>> getUsefulSites() {
        return mIHttp.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> getCollectList(int page) {
        return mIHttp.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<ArticleListBean>> cancelCollectInCollectPage(int id, int originId) {
        return mIHttp.cancelCollectInCollectPage(id, originId);
    }
}
