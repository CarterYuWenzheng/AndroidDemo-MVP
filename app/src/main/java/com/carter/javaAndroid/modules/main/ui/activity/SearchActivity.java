package com.carter.javaAndroid.modules.main.ui.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.core.db.bean.HistoryBean;
import com.carter.javaAndroid.modules.main.bean.TopSearchBean;
import com.carter.javaAndroid.modules.main.contract.SearchContract;
import com.carter.javaAndroid.modules.main.presenter.SearchPresenter;
import com.carter.javaAndroid.modules.main.ui.adapter.SearchHistoryAdapter;
import com.carter.javaAndroid.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterPath.SEARCH_ACTIVITY)
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.hot_search_flow_layout)
    TagFlowLayout mTopSearchFlowLayout;
    @BindView(R.id.rv_history_search)
    RecyclerView mRecyclerView;

    private List<TopSearchBean> mTopSearchDataList;
    private SearchHistoryAdapter mAdapter;
    private List<HistoryBean> mSearchHistoryList;

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        mTopSearchDataList = new ArrayList<>();
        mPresenter.getTopSearchData();
        mPresenter.loadAllHistoryData();
    }

    private void initRecyclerView() {
        mSearchHistoryList = new ArrayList<>();
        mAdapter = new SearchHistoryAdapter(R.layout.adapter_search_history_item,mSearchHistoryList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position){
                return;
            }
            goToSearchResult(mAdapter.getData().get(position).getData());
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> clickChildEvent(view,position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter.bindToRecyclerView(mRecyclerView);
    }

    @OnClick({R.id.search_history_clear_all_tv})
    void onClick(View view) {
        clearAllHistoryData();
    }

    private void clearAllHistoryData() {
        mPresenter.clearAllHistoryData();
        mSearchHistoryList.clear();
        mAdapter.replaceData(mSearchHistoryList);
    }

    private void goToSearchResult(String searchString){
        mPresenter.addHistoryData(searchString);
        ARouter.getInstance().build(ARouterPath.COMMON_ACTIVITY)
                .withInt(Constants.TYPE_FRAGMENT_KEY, Constants.TYPE_SEARCH_RESULT)
                .withString(Constants.SEARCH_KEY, searchString)
                .navigation();
    }

    private void clickChildEvent(View view,int position){
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_clear:
                mPresenter.deleteHistoryDataById(mAdapter.getData().get(position).getId());
                mAdapter.remove(position);
                break;
            default:
                break;
        }
    }

    @Override
    public void showTopSearchData(List<TopSearchBean> topSearchData) {
        mTopSearchDataList = topSearchData;
        mTopSearchFlowLayout.setAdapter(new TagAdapter<TopSearchBean>(topSearchData) {
            @Override
            public View getView(FlowLayout parent, int position, TopSearchBean topSearchData) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this)
                        .inflate(R.layout.flow_layout_tv, parent, false);
                if (topSearchData != null) {
                    tv.setText(topSearchData.getName());
                    tv.setTextColor(CommonUtils.getRandomColor());
                }
                return tv;
            }
        });

        mTopSearchFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
            goToSearchResult(mTopSearchDataList.get(position1).getName().trim());
            return true;
        });
    }

    @Override
    public void showHistoryData(List<HistoryBean> historyDataList) {
        mSearchHistoryList = historyDataList;
        mAdapter.replaceData(historyDataList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search_button);

        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.search_tint));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                goToSearchResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setSubmitButtonEnabled(true);

        Field field;
        try {
            field = mSearchView.getClass().getDeclaredField("mGoButton");
            field.setAccessible(true);
            ImageView mGoButton = (ImageView) field.get(mSearchView);
            mGoButton.setImageResource(R.drawable.ic_search);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return super.onCreateOptionsMenu(menu);
    }
}
