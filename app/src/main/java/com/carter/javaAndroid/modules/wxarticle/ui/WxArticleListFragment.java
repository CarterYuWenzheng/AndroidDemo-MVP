package com.carter.javaAndroid.modules.wxarticle.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleContract;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleListContract;
import com.carter.javaAndroid.modules.wxarticle.presenter.WxArticleListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class WxArticleListFragment extends BaseFragment<WxArticleListPresenter> implements WxArticleListContract.View {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.wx_list_recycler_view)
    RecyclerView mRecyclerView;

    private List<ArticleItemBean> mArticleList;
    private WxArticleListAdapter mAdapter;

    private int id;

    public static WxArticleListFragment newInstance(Bundle bundle) {
        WxArticleListFragment wxArticleListFragment = new WxArticleListFragment();
        wxArticleListFragment.setArguments(bundle);
        return wxArticleListFragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        initRefreshListener();
    }

    private void initRecyclerView() {
        mArticleList = new ArrayList<>();
        mAdapter = new WxArticleListAdapter(R.layout.adapter_wx_article_list_item, mArticleList);
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

    private void initRefreshListener() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.refreshData(id, false);
            mRefreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            mRefreshLayout.finishLoadMore();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_article_list;
    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        id = getArguments().getInt(Constants.WX_CHAPTER_ID);
        mPresenter.refreshData(id, true);
    }

    @Override
    public void showWxArticlelist(ArticleListBean articleListBean, boolean isRefresh) {
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            mArticleList = articleListBean.getDatas();
            mAdapter.replaceData(mArticleList);
        } else {
            mArticleList.addAll(articleListBean.getDatas());
            mAdapter.addData(articleListBean.getDatas());
        }
    }

    public void jumpToTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollectSuccess(int position) {

    }
}
