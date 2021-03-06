package com.carter.javaAndroid.modules.knowledge.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeContract;
import com.carter.javaAndroid.modules.knowledge.presenter.KnowledgePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.knowledge_recycler)
    RecyclerView recyclerView;

    private KnowledgeTreeAdapter adapter;

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Override
    protected void initView() {
        initRecyclerView();
        initRefreshLayout();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getKnowledgeTree();
    }

    private void initRecyclerView() {
        List<KnowledgeTreeBean> knowledgeTreeBeans = new ArrayList<>();
        adapter = new KnowledgeTreeAdapter(R.layout.adapter_knowledge_tree,knowledgeTreeBeans);
        adapter.setOnItemClickListener((adapter1, view, position) -> startKnowledgeActivity(position));
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void startKnowledgeActivity(int position) {
        if (adapter.getData().size() <= 0 || adapter.getData().size() < position) {
            return;
        }
        KnowledgeTreeBean knowledgeTreeBean = adapter.getData().get(position);
        ARouter.getInstance().build(ARouterPath.KNOWLEDGE_ACTIVITY)
                .withSerializable(Constants.KNOWLEDGE_DATA, knowledgeTreeBean)
                .navigation();
    }

    private void initRefreshLayout() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.getKnowledgeTree();
            refreshLayout.finishRefresh();
        });
    }

    @Override
    public void showKnowledgeTree(List<KnowledgeTreeBean> knowledgeTreeBeans) {
        if (adapter.getData().size() < knowledgeTreeBeans.size()){
            adapter.replaceData(knowledgeTreeBeans);
        }
    }

    public void jumpToTop() {
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }
}
