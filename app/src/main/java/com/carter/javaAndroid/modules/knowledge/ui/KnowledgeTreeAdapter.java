package com.carter.javaAndroid.modules.knowledge.ui;

import android.support.annotation.Nullable;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class KnowledgeTreeAdapter extends BaseQuickAdapter<KnowledgeTreeBean, BaseViewHolder> {

    public KnowledgeTreeAdapter(int layoutResId, @Nullable List<KnowledgeTreeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeTreeBean item) {
        if(item.getName() == null) {
            return;
        }
        helper.setText(R.id.knowledge_title, item.getName());
        if (item.getChildren() == null) {
            return;
        }
        StringBuilder childTitles = new StringBuilder();
        for (KnowledgeTreeBean data: item.getChildren()) {
            childTitles.append(data.getName()).append("   ");
        }
        helper.setText(R.id.title_child, childTitles.toString());
    }
}
