/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.carter.javaAndroid.modules.todo.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.todo.bean.TodoItemBean;

import java.util.HashMap;

public interface AddTodoContract {
    interface View extends IView {
        void addTodoSuccess(TodoItemBean todoItemData);

        void updateTodoSuccess(TodoItemBean todoItemData);
    }

    interface Presenter extends IPresenter<View> {
        void addTodo(HashMap<String, Object> map);

        void updateTodo(int id, HashMap<String, Object> map);
    }
}
