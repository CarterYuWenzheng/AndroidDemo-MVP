package com.carter.javaAndroid.modules.knowledge.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;

import java.util.List;

public interface KnowledgeContract {

    interface View extends IView {
        void showKnowledgeTree(List<KnowledgeTreeBean> knowledgeTreeBeans);
    }

    interface Presenter extends IPresenter<View> {
        void getKnowledgeTree();
    }
}
