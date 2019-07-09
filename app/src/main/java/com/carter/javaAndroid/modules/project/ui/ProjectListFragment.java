package com.carter.javaAndroid.modules.project.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.project.contract.ProjectListContract;
import com.carter.javaAndroid.modules.project.presenter.ProjectListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ProjectListFragment extends BaseFragment<ProjectListPresenter> implements ProjectListContract.View {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.project_list_recycler_view)
    RecyclerView mRecyclerView;

    private List<ArticleItemBean> mArticleList;
    private ProjectListAdapter mAdapter;

    private int cid;

    public static ProjectListFragment newInstance(Bundle bundle) {
        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRecyclerView() {
        mArticleList = new ArrayList<>();
        mAdapter = new ProjectListAdapter(R.layout.adapter_project_list_item, mArticleList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
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
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        cid = getArguments().getInt(Constants.PROJECT_CID);
        mPresenter.refreshLayout(cid, true);
    }

    @Override
    public void showProjectListData(ArticleListBean articleListBean, boolean isRefresh) {
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            mArticleList = articleListBean.getDatas();
            mAdapter.replaceData(mArticleList);
        } else {
            mArticleList.addAll(articleListBean.getDatas());
            mAdapter.addData(mArticleList);
        }
    }

    public void jumpToTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
}
