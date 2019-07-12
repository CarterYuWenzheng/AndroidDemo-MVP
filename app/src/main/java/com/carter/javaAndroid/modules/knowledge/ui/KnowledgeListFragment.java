package com.carter.javaAndroid.modules.knowledge.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.homepager.ui.ArticleAdapter;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeListContract;
import com.carter.javaAndroid.modules.knowledge.presenter.KnowledgeListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeListFragment extends BaseFragment<KnowledgeListPresenter> implements KnowledgeListContract.View {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.project_list_recycler_view)
    RecyclerView mRecyclerView;

    private ArticleAdapter mAdapter;

    private int cid;

    public static KnowledgeListFragment newInstance(Bundle bundle) {
        KnowledgeListFragment fragment = new KnowledgeListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRecyclerView() {
        List<ArticleItemBean> mArticleList = new ArrayList<>();
        mAdapter = new ArticleAdapter(R.layout.adapter_article_item, mArticleList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startArticleDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }
        ArticleItemBean itemBean = mAdapter.getData().get(position);
        ARouter.getInstance().build(ARouterPath.ARTICLE_DETAIL_ACTIVITY)
                .withInt(Constants.ARTICLE_ID, itemBean.getId())
                .withString(Constants.ARTICLE_TITLE, itemBean.getTitle())
                .withString(Constants.ARTICLE_LINK, itemBean.getLink())
                .withBoolean(Constants.IS_COLLECTED, itemBean.isCollect())
                .withBoolean(Constants.IS_SHOW_COLLECT_ICON, true)
                .withInt(Constants.ARTICLE_ITEM_POSITION, position)
                .withString(Constants.EVENT_BUS_TAG, Constants.MAIN_PAGER)
                .navigation();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.refreshLayout(cid, false);
            mRefreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            mRefreshLayout.finishLoadMore();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_list;
    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        cid = getArguments().getInt(Constants.KNOWLEDGE_CID);
        mPresenter.refreshLayout(cid, true);
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
