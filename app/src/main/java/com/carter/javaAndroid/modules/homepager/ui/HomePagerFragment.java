package com.carter.javaAndroid.modules.homepager.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.homepager.banner.BannerData;
import com.carter.javaAndroid.modules.homepager.banner.BannerGlideImageLoader;
import com.carter.javaAndroid.modules.homepager.bean.ArticleItemBean;
import com.carter.javaAndroid.modules.homepager.bean.ArticleListBean;
import com.carter.javaAndroid.modules.homepager.contract.HomePagerContract;
import com.carter.javaAndroid.modules.homepager.presenter.HomePagerPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomePagerFragment extends BaseFragment<HomePagerPresenter>
        implements HomePagerContract.View {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.home_pager_recycler_view)
    RecyclerView recyclerView;

    private ArticleAdapter adapter;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private List<Integer> mBannerIdList;
    private List<String> mBannerImageList;
    private Banner banner;

    public static HomePagerFragment newInstance() {
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        return homePagerFragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        initRefreshLayout();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.refreshLayout(true);
    }

    private void initRecyclerView() {
        List<ArticleItemBean> articleItemBeans = new ArrayList<>();
        adapter = new ArticleAdapter(R.layout.adapter_article_item, articleItemBeans);
        adapter.setOnItemClickListener((adapter1, view, position) -> startArticleDetailActivity(position));
        adapter.setOnItemChildClickListener((adapter1, view, position) -> clickChildEvent(view, position));
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setHasFixedSize(true);
        LinearLayout headLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.head_banner, null);
        banner = headLayout.findViewById(R.id.head_banner);

        headLayout.removeView(banner);
        adapter.setHeaderView(banner);
        recyclerView.setAdapter(adapter);
    }


    private void startArticleDetailActivity(int position) {
        if (adapter.getData().size() <= 0 || adapter.getData().size() < position) {
            return;
        }
        ArticleItemBean itemBean = adapter.getData().get(position);
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

    private void clickChildEvent(View view, int position) {

    }

    private void initRefreshLayout() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.refreshLayout(false);
            refreshLayout.finishRefresh();
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    public void showArticleList(ArticleListBean articleListBean, boolean isRefresh) {
        if (adapter == null) {
            return;
        }
        if (isRefresh) {
            adapter.replaceData(articleListBean.getDatas());
        } else {
            adapter.addData(articleListBean.getDatas());
        }

    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        mBannerTitleList = new ArrayList<>();
        mBannerIdList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        mBannerImageList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            mBannerIdList.add(bannerData.getId());
            mBannerUrlList.add(bannerData.getUrl());
            mBannerImageList.add(bannerData.getImagePath());
        }
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new BannerGlideImageLoader());
        //设置图片集合
        banner.setImages(mBannerImageList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setOnBannerListener(i ->
                {
                }
        );
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
