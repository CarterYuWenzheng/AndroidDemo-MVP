package com.carter.javaAndroid.modules.homepager.ui;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.utils.GlideImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<ArticleItemBean, BaseViewHolder> {

    public ArticleAdapter(int layoutResId, @Nullable List<ArticleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemBean item) {
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_article_author, item.getAuthor())
                .setImageResource(R.id.iv_article_like, item.isCollect() ? R.drawable.ic_like : R.drawable.ic_like_not);
        if (!TextUtils.isEmpty(item.getChapterName())){
            String classifyName = item.getSuperChapterName() + "/" + item.getChapterName();
            helper.setText(R.id.tv_article_chapterName, classifyName);
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_article_niceDate, item.getNiceDate());
        }
        helper.getView(R.id.tv_article_top).setVisibility(item.getType() == 1 ? View.VISIBLE : View.GONE);

        helper.getView(R.id.tv_article_fresh).setVisibility(item.isFresh() ? View.VISIBLE : View.GONE);

        if (item.getTags().size() > 0) {
            helper.setText(R.id.tv_article_tag, item.getTags().get(0).getName()).
                    getView(R.id.tv_article_tag).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_article_tag).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.VISIBLE);
            GlideImageLoader.load(mContext, item.getEnvelopePic(), helper.getView(R.id.iv_article_thumbnail));
        } else {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.tv_article_chapterName);
        helper.addOnClickListener(R.id.iv_article_like);
        helper.addOnClickListener(R.id.tv_article_tag);
    }
}
