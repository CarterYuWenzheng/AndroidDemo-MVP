package com.carter.javaAndroid.modules.main.ui.fragment;

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
import com.carter.javaAndroid.modules.main.contract.CollectContract;
import com.carter.javaAndroid.modules.main.presenter.CollectPresenter;
import com.carter.javaAndroid.modules.main.ui.adapter.CollectListAdapter;
import com.carter.javaAndroid.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectContract.View {

    @BindView(R.id.collect_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.collect_recycler_view)
    RecyclerView mRecyclerView;
    private List<ArticleItemBean> mCollectList;
    private CollectListAdapter mAdapter;

    public static CollectFragment newInstance() {
        return new CollectFragment();
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initEventAndData() {
        initRefreshLayout();
        mPresenter.getCollectArticle(true);
    }

    private void initRecyclerView() {
        mCollectList = new ArrayList<>();
        mAdapter = new CollectListAdapter(R.layout.adapter_article_list, mCollectList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.getCollectArticle(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore();
        });
    }

    private void startArticleDetailPager(int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }

        ArticleItemBean bean = mAdapter.getData().get(position);
        ARouter.getInstance().build(ARouterPath.ARTICLE_DETAIL_ACTIVITY)
                .withInt(Constants.ARTICLE_ID, bean.getOriginId())
                .withString(Constants.ARTICLE_TITLE, bean.getTitle())
                .withString(Constants.ARTICLE_LINK, bean.getLink().trim())
                .withBoolean(Constants.IS_COLLECTED, true)
                .withBoolean(Constants.IS_SHOW_COLLECT_ICON, true)
                .withInt(Constants.ARTICLE_ITEM_POSITION, position)
                .withString(Constants.EVENT_BUS_TAG, Constants.COLLECT_PAGER)
                .navigation();
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.iv_article_like:
                cancelCollect(position);
                break;
            default:
                break;
        }
    }

    private void cancelCollect(int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }
        mPresenter.cancelCollectInCollectPage(position, mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getOriginId());
    }

    @Override
    public void showCollectList(ArticleListBean articleListData, boolean isRefresh) {
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            mAdapter.replaceData(articleListData.getDatas());
        } else {
            mAdapter.addData(articleListData.getDatas());
        }
    }

    @Override
    public void showCollectSuccess(int position) {
        ToastUtils.showToast(_mActivity, getString(R.string.collect_success));
    }

    @Override
    public void showCancelCollectSuccess(int position) {
        mAdapter.remove(position);
        ToastUtils.showToast(_mActivity, getString(R.string.cancel_collect));
    }
}
