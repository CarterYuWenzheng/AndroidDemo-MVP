package com.carter.javaAndroid.modules.main.ui.adapter;

import android.support.annotation.Nullable;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.core.db.bean.HistoryBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SearchHistoryAdapter extends BaseQuickAdapter<HistoryBean, BaseViewHolder> {

    public SearchHistoryAdapter(int layoutResId, @Nullable List<HistoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryBean item) {
        helper.setText(R.id.tv_search_key,item.getData());
        helper.addOnClickListener(R.id.iv_clear);
    }
}
