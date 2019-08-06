package com.carter.javaAndroid.modules.todo.presenter;

import com.carter.javaAndroid.Application.MyApplication;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.core.event.RefreshTodoEvent;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.todo.bean.TodoItemBean;
import com.carter.javaAndroid.modules.todo.bean.TodoListBean;
import com.carter.javaAndroid.modules.todo.contract.TodoListContract;
import com.carter.javaAndroid.utils.RxUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import javax.inject.Inject;

public class TodoListPresenter extends BasePresenter<TodoListContract.View> implements TodoListContract.Presenter {

    @Inject
    TodoListPresenter() {
    }

    private int currentPage = 1;
    private boolean isRefresh = true;
    private int type;
    private int status;

    @Override
    public void refreshLayout(int type, int status, boolean isShowStatusView) {
        this.type = type;
        this.status = status;
        isRefresh = true;
        currentPage = 1;
        getTodoListData(isShowStatusView);
    }

    @Override
    public void getTodoListData(boolean isShowStatusView) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.KEY_TODO_TYPE, type);
        map.put(Constants.KEY_TODO_STATUS, status);
        map.put(Constants.KEY_TODO_PRIORITY, 0); //默认全部
        if (status == 1) {
            map.put(Constants.KEY_TODO_ORDERBY, 2);
        } else {
            map.put(Constants.KEY_TODO_ORDERBY, 4);
        }
        addSubscribe(mDataManager.getTodoListData(currentPage, map)
                .compose(RxUtils.SchedulerTransformer())
                .filter(todoListData -> mView != null)
                .subscribeWith(new BaseObserver<TodoListBean>(mView,
                        MyApplication.getContext().getString(R.string.failed_to_obtain_todo_list),
                        isShowStatusView) {
                    @Override
                    public void onSuccess(TodoListBean todoListData) {
                        if(isShowStatusView && currentPage == 1 &&
                                todoListData.getDatas().size() < 1) {
                            mView.showEmpty();
                        } else {
                            mView.showTodoListData(todoListData, isRefresh);
                        }
                    }
                }));
    }

    @Override
    public void reload() {
        refreshLayout(type, status, true);
    }



    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getTodoListData(false);
    }

    @Override
    public void updateTodoStatus(int id, int status) {
        addSubscribe(mDataManager.updateTodoStatus(id, status)
                .compose(RxUtils.SchedulerTransformer())
                .filter(TodoItemBean -> mView != null)
                .subscribeWith(new BaseObserver<TodoItemBean>(mView,
                        MyApplication.getContext().getString(R.string.update_todo_status_failed),
                        false) {
                    @Override
                    public void onSuccess(TodoItemBean TodoItemBean) {
                        mView.updateTodoStatusSuccess(TodoItemBean);
                    }
                }));
    }

    @Override
    public void deleteTodo(int id) {
        addSubscribe(mDataManager.deleteTodo(id)
                .compose(RxUtils.SchedulerTransformer())
                .filter(TodoItemBean -> mView != null)
                .subscribeWith(new BaseObserver<TodoItemBean>(mView,
                        MyApplication.getContext().getString(R.string.delete_todo_failed),
                        false) {
                    @Override
                    public void onSuccess(TodoItemBean TodoItemBean) {
                        mView.deleteTodoSuccess(TodoItemBean);
                    }
                }));
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void RefreshTodoEvent(RefreshTodoEvent refreshTodoEvent) {
        mView.todoStatusChange(refreshTodoEvent.getStatus());
    }
}
