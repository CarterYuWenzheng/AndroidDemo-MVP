package com.carter.javaAndroid.modules.navigation.ui;


import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.navigation.bean.NavigationListBean;
import com.carter.javaAndroid.modules.navigation.contract.NavigationContract;
import com.carter.javaAndroid.modules.navigation.presenter.NavigationPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    @BindView(R.id.vertical_tab_layout)
    VerticalTabLayout tabLayout;
    @BindView(R.id.recycler_navigation)
    RecyclerView recyclerView;

    private NavigationAdapter navigationAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int index;
    private boolean needScroll;
    private boolean isClickTab;

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getNavigationList();
    }

    private void initRecyclerView() {
        List<NavigationListBean> navigationListBeans = new ArrayList<>();
        navigationAdapter = new NavigationAdapter(R.layout.adapter_navigation_item, navigationListBeans);
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(navigationAdapter);
    }

    @Override
    public void showNavigationList(List<NavigationListBean> navigationListBeans) {
        tabLayout.setTabAdapter(new SimpleTabAdapter() {

            @Override
            public int getCount() {
                return navigationListBeans == null ? 0 : navigationListBeans.size();
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationListBeans.get(position).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity, R.color.colorAccent),
                                ContextCompat.getColor(_mActivity, R.color.Grey500))
                        .build();
            }
        });
        navigationAdapter.replaceData(navigationListBeans);
        linkLeftRight();
    }

    private void linkLeftRight() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkLeft(newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });
        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isClickTab = true;
                selectTag(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - linearLayoutManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < recyclerView.getChildCount()) {
            int top = recyclerView.getChildAt(indexDistance).getTop();
            recyclerView.smoothScrollBy(0, top);
        }
    }

    private void selectTag(int position) {
        index = position;
        recyclerView.stopScroll();
        smoothScrollToPosition(position);
    }

    private void rightLinkLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    private void setChecked(int i) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (tabLayout == null) {
                return;
            }
            tabLayout.setTabSelected(index);
        }
        index = i;
    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            recyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = recyclerView.getChildAt(currentPosition - firstPosition).getTop();
            recyclerView.smoothScrollBy(0, top);
        } else {
            recyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }

    public void jumpToTop() {
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }
}
