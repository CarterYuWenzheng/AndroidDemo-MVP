package com.carter.javaAndroid.modules.todo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.modules.todo.contract.TodoContract;
import com.carter.javaAndroid.modules.todo.presenter.TodoPresenter;

@Route(path = ARouterPath.TODO_ACTIVITY)
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
