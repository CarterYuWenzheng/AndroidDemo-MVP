package com.carter.javaAndroid.modules.navigation.ui;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;
import com.carter.javaAndroid.utils.CommonUtils;
import com.carter.javaAndroid.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class NavigationAdapter extends BaseQuickAdapter<NavigationListBean, BaseViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationListBean item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        TagFlowLayout tagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<ArticleItemBean> articleItemBeans = item.getArticles();
        tagFlowLayout.setAdapter(new TagAdapter<ArticleItemBean>(articleItemBeans) {
            @Override
            public View getView(FlowLayout parent, int position, ArticleItemBean articleItemBean) {
                TextView textView = (TextView) LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.flow_layout_tv, tagFlowLayout, false);
                if (articleItemBean == null) return null;
                String name = articleItemBean.getTitle();
                textView.setText(name);
                textView.setTextColor(CommonUtils.getRandomColor());
                tagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    //TODO startArticleActivity
                    ToastUtils.showToast(parent.getContext(),articleItemBean.getTitle());
                    return true;
                });
                return textView;
            }
        });
    }
}
