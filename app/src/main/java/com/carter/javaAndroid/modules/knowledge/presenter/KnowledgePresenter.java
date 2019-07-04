package com.carter.javaAndroid.modules.knowledge.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.core.rx.BaseObserver;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeContract;
import com.carter.javaAndroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class KnowledgePresenter extends BasePresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {

    @Inject
    KnowledgePresenter() {
    }

    @Override
    public void getKnowledgeTree() {
        addSubscribe(mDataManager.getKnowledgeTree()
                .compose(RxUtils.SchedulerTransformer())
                .filter(knowledgeTreeList -> mView != null)
                .subscribeWith(new BaseObserver<List<KnowledgeTreeBean>>(mView, "获取knowledge失败", true) {

                    @Override
                    public void onSuccess(List<KnowledgeTreeBean> knowledgeTreeBeans) {
                        mView.showKnowledgeTree(knowledgeTreeBeans);
                    }
                }));
    }
}
