package com.carter.javaAndroid.modules.knowledge.ui;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeContract;
import com.carter.javaAndroid.modules.knowledge.presenter.KnowledgePresenter;


public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initEventAndData() {

    }
}
