package com.carter.javaAndroid.modules.todo.ui;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.modules.todo.bean.TodoItemBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class TodoListAdapter extends BaseQuickAdapter<TodoItemBean, BaseViewHolder> {


    public TodoListAdapter(int layoutResId, @Nullable List<TodoItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodoItemBean item) {
        helper.setText(R.id.tv_todo_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_todo_content, item.getContent());
        if (!TextUtils.isEmpty(item.getCompleteDateStr())) {
            helper.setText(R.id.tv_todo_date, item.getCompleteDateStr());
        } else {
            helper.setText(R.id.tv_todo_date, item.getDateStr());
        }

        if (item.getPriority() == 1) {
            helper.getView(R.id.tv_todo_priority).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_todo_priority).setVisibility(View.GONE);
        }

    }
}
