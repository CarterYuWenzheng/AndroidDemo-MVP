package com.carter.javaAndroid.modules.knowledge.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeContract;

import javax.inject.Inject;

public class KnowledgePresenter extends BasePresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter{

    @Inject
    KnowledgePresenter(){}

}
