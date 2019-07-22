package com.carter.javaAndroid.modules.todo.ui;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.modules.todo.contract.TodoContract;
import com.carter.javaAndroid.modules.todo.presenter.TodoPresenter;

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
