package com.carter.javaAndroid.modules.knowledge.presenter;

import com.carter.javaAndroid.base.presenter.BasePresenter;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeActivityContract;

import javax.inject.Inject;

public class KnowledgeActivityPresenter extends BasePresenter<KnowledgeActivityContract.View> implements KnowledgeActivityContract.Presenter{

    @Inject
    KnowledgeActivityPresenter(){}
}
