package com.carter.javaAndroid.modules.todo.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.todo.bean.TodoItemBean;
import com.carter.javaAndroid.modules.todo.bean.TodoListBean;

public interface TodoListContract {

    interface View extends IView {
        void showTodoListData(TodoListBean todoListData, boolean isRefresh);

        void todoStatusChange(int status);

        void updateTodoStatusSuccess(TodoItemBean todoItemData);

        void deleteTodoSuccess(TodoItemBean todoItemData);
    }

    interface Presenter extends IPresenter<View> {
        void refreshLayout(int type, int status, boolean isShowStatusView);

        void getTodoListData(boolean isShowStatusView);

        void loadMore();

        void updateTodoStatus(int id, int status);

        void deleteTodo(int id);
    }
}
