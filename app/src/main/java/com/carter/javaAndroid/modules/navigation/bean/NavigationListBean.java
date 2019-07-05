package com.carter.javaAndroid.modules.navigation.bean;

import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;

import java.util.List;

public class NavigationListBean {

    private List<ArticleItemBean> articles;
    private int cid;
    private String name;

    public List<ArticleItemBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleItemBean> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
