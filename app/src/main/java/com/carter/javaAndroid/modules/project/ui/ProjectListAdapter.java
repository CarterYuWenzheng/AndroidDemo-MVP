package com.carter.javaAndroid.modules.project.ui;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.utils.GlideImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ProjectListAdapter extends BaseQuickAdapter<ArticleItemBean, BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<ArticleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemBean item) {
        helper.setText(R.id.item_project_list_title_tv, Html.fromHtml(item.getTitle()))
                .setText(R.id.item_project_list_author_tv, item.getAuthor())
                .setImageResource(R.id.item_project_list_like_iv, item.isCollect() ? R.drawable.ic_like : R.drawable.ic_like_not);
        if (!TextUtils.isEmpty(item.getDesc())) {
            helper.setText(R.id.item_project_list_content_tv, Html.fromHtml(item.getDesc()));
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.item_project_list_time_tv, item.getNiceDate());
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            GlideImageLoader.load(mContext, item.getEnvelopePic(),
                    helper.getView(R.id.item_project_list_iv));
        }
        helper.addOnClickListener(R.id.item_project_list_like_iv);
    }
}
