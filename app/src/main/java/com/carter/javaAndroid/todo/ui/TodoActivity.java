package com.carter.javaAndroid.todo.ui;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.todo.contract.TodoContract;
import com.carter.javaAndroid.todo.presenter.TodoPresenter;

public class TodoActivity extends BaseActivity<TodoPresenter> implements TodoContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_todo;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initEventAndData() {

    }
}
