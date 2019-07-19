package com.carter.javaAndroid.modules.knowledge.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.SparseArray;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.knowledge.bean.KnowledgeTreeBean;
import com.carter.javaAndroid.modules.knowledge.contract.KnowledgeActivityContract;
import com.carter.javaAndroid.modules.knowledge.presenter.KnowledgeActivityPresenter;

import java.util.List;

import butterknife.BindView;

@Route(path = ARouterPath.KNOWLEDGE_ACTIVITY)
public class KnowledgeActivity extends BaseActivity<KnowledgeActivityPresenter> implements KnowledgeActivityContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.knowledge_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.knowledge_viewpager)
    ViewPager mViewPager;

    private List<KnowledgeTreeBean> mKnowledgeTreeDataList;
    private SparseArray<KnowledgeListFragment> listFragmentSparseArray = new SparseArray<>();
    private KnowledgeListFragment mCurrentFragment;

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge;
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
        KnowledgeTreeBean knowledgeTreeBean = (KnowledgeTreeBean) getIntent().getSerializableExtra(Constants.KNOWLEDGE_DATA);
        if (knowledgeTreeBean == null || knowledgeTreeBean.getName() == null) {
            return;
        }
        mTitle.setText(knowledgeTreeBean.getName().trim());
        mKnowledgeTreeDataList = knowledgeTreeBean.getChildren();
        if (mKnowledgeTreeDataList == null) {
            return;
        }
        initTabAndViewPager();
    }

    private void initTabAndViewPager() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                KnowledgeListFragment fragment = listFragmentSparseArray.get(i);
                if (fragment != null) {
                    return fragment;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.KNOWLEDGE_CID, mKnowledgeTreeDataList.get(i).getId());
                    fragment = KnowledgeListFragment.newInstance(bundle);
                    listFragmentSparseArray.put(i,fragment);
                    return fragment;
                }
            }

            @Override
            public int getCount() {
                return mKnowledgeTreeDataList == null ? 0 : mKnowledgeTreeDataList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mKnowledgeTreeDataList.get(position).getName());
            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        if (listFragmentSparseArray != null) {
            listFragmentSparseArray.clear();
            listFragmentSparseArray = null;
        }
        if (mKnowledgeTreeDataList != null) {
            mKnowledgeTreeDataList.clear();
            mKnowledgeTreeDataList = null;
        }
        super.onDestroy();
    }
}
