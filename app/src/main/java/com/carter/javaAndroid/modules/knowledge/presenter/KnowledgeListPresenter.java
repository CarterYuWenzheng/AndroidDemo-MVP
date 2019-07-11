package com.carter.javaAndroid.modules.knowledge.presenter;

import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeListContract;
import com.carter.javaAndroid.modules.main.presenter.CollectEventPresenter;

import javax.inject.Inject;

public class KnowledgeListPresenter extends CollectEventPresenter<KnowledgeListContract.View> implements KnowledgeListContract.Presenter{

    @Inject
    KnowledgeListPresenter(){}

    @Override
    public void refreshLayout(int cid, boolean isShowStatusView) {

    }

    @Override
    public void getKnowledgeListData(boolean isShowStatusView) {

    }

    @Override
    public void loadMore() {

    }
}
