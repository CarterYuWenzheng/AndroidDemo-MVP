package com.carter.javaAndroid.modules.main.ui.adapter;

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

public class CollectListAdapter extends BaseQuickAdapter<ArticleItemBean, BaseViewHolder> {


    public CollectListAdapter(int layoutResId, @Nullable List<ArticleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemBean item) {
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_article_author, item.getAuthor())
        .setImageResource(R.id.iv_article_like, R.drawable.ic_like);
        if (!TextUtils.isEmpty(item.getChapterName())) {
            helper.setText(R.id.tv_article_chapterName, item.getChapterName());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_article_niceDate, item.getNiceDate());
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.VISIBLE);
            GlideImageLoader.load(mContext, item.getEnvelopePic(), helper.getView(R.id.iv_article_thumbnail));
        } else {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.tv_article_chapterName);
        helper.addOnClickListener(R.id.iv_article_like);

    }
}
