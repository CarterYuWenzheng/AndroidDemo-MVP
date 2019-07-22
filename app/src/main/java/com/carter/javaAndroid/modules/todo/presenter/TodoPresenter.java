package com.carter.javaAndroid.modules.todo.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.todo.contract.TodoContract;

import javax.inject.Inject;

public class TodoPresenter extends BasePresenter<TodoContract.View> implements TodoContract.Presenter{

    @Inject
    TodoPresenter(){}
}
