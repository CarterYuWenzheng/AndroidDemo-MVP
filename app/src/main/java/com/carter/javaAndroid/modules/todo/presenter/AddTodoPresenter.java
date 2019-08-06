package com.carter.javaAndroid.modules.todo.presenter;

import com.carter.javaAndroid.Application.MyApplication;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.todo.bean.TodoItemBean;
import com.carter.javaAndroid.modules.todo.contract.AddTodoContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.HashMap;

import javax.inject.Inject;

public class AddTodoPresenter extends BasePresenter<AddTodoContract.View>
        implements AddTodoContract.Presenter {

    @Inject
    AddTodoPresenter() {
    }

    @Override
    public void addTodo(HashMap<String, Object> map) {
        addSubscribe(mDataManager.addTodo(map)
                .compose(RxUtils.SchedulerTransformer())
                .filter(todoItemData -> mView != null)
                .subscribeWith(new BaseObserver<TodoItemBean>(mView,
                        MyApplication.getContext().getString(R.string.add_todo_failed),
                        true) {
                    @Override
                    public void onSuccess(TodoItemBean todoItemData) {
                        mView.addTodoSuccess(todoItemData);
                    }
                }));
    }

    @Override
    public void updateTodo(int id, HashMap<String, Object> map) {
        addSubscribe(mDataManager.updateTodo(id, map)
                .compose(RxUtils.SchedulerTransformer())
                .filter(todoItemData -> mView != null)
                .subscribeWith(new BaseObserver<TodoItemBean>(mView,
                        MyApplication.getContext().getString(R.string.update_todo_failed),
                        false) {
                    @Override
                    public void onSuccess(TodoItemBean todoItemData) {
                        mView.updateTodoSuccess(todoItemData);
                    }
                }));
    }
}
