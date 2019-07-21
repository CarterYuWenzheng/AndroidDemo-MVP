package com.carter.javaAndroid.todo.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.todo.contract.TodoContract;

import javax.inject.Inject;

public class TodoPresenter extends BasePresenter<TodoContract.View> implements TodoContract.Presenter{

    @Inject
    TodoPresenter(){}
}
