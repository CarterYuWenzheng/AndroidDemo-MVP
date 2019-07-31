package com.carter.javaAndroid.modules.main.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.homepager.ui.ArticleAdapter;
import com.carter.javaAndroid.modules.main.contract.SearchResultContracrt;
import com.carter.javaAndroid.modules.main.presenter.SearchResultPresenter;
import com.carter.javaAndroid.utils.CommonUtils;
import com.carter.javaAndroid.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements SearchResultContracrt.View {

    @BindView(R.id.sr_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.search_result_recycler_view)
    RecyclerView mRecyclerView;
    private List<ArticleItemBean> mArticleList;
    private ArticleAdapter mAdapter;
    private String mSearchKey = "";

    public static SearchResultFragment newInstance(Bundle bundle) {
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        searchResultFragment.setArguments(bundle);
        return searchResultFragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        initRefreshLayout();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        mSearchKey = getArguments().getString(Constants.SEARCH_KEY, "");
        mPresenter.search(mSearchKey, true);
    }

    private void initRecyclerView() {
        mArticleList = new ArrayList<>();
        mAdapter = new ArticleAdapter(R.layout.adapter_article_item, mArticleList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPage(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startArticleDetailPage(View view, int position) {
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
                .withString(Constants.EVENT_BUS_TAG, Constants.SEARCH_PAGER)
                .navigation();
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.tv_article_chapterName:

                break;
            case R.id.iv_article_like:
                collectClickEvent(position);
                break;
            case R.id.tv_article_tag:

                break;
            default:
                break;
        }
    }

    private void collectClickEvent(int position) {
        if (mPresenter.getLoginStatus()) {
            if (mAdapter.getData().get(position).isCollect()) {
                mPresenter.cancelCollectArticle(position, mAdapter.getData().get(position).getId());
            } else {
                mPresenter.addCollectArticle(position, mAdapter.getData().get(position).getId());
            }
        } else {
            ToastUtils.showToast(_mActivity, getString(R.string.login_first));
            CommonUtils.startLoginActivity();
        }
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.search(mSearchKey, false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    public void showSearchResultList(ArticleListBean articleListBean, boolean isRefresh) {
        if (mAdapter == null) return;
        if (isRefresh) {
            mAdapter.replaceData(articleListBean.getDatas());
        } else {
            mAdapter.addData(articleListBean.getDatas());
        }
    }

    @Override
    public void showCollectSuccess(int position) {
        mAdapter.getData().get(position).setCollect(true);
        mAdapter.setData(position, mAdapter.getData().get(position));
        ToastUtils.showToast(_mActivity, getString(R.string.collect_success));
    }

    @Override
    public void showCancelCollectSuccess(int position) {
        mAdapter.getData().get(position).setCollect(false);
        mAdapter.setData(position, mAdapter.getData().get(position));
        ToastUtils.showToast(_mActivity, getString(R.string.cancel_collect));
    }
}
