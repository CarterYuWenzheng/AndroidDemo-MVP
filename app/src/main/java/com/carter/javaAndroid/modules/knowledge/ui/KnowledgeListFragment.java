package com.carter.javaAndroid.modules.knowledge.ui;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeListContract;
import com.carter.javaAndroid.modules.knowledge.presenter.KnowledgeListPresenter;

public class KnowledgeListFragment extends BaseFragment<KnowledgeListPresenter> implements KnowledgeListContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_list;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showKnowledgeListData(ArticleListBean articleListBean, boolean isRefresh) {

    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollectSuccess(int position) {

    }
}
