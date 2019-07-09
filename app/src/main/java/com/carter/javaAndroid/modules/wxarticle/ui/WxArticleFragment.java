package com.carter.javaAndroid.modules.wxarticle.ui;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.SparseArray;

import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.wxarticle.bean.WxChapterBean;
import com.carter.javaAndroid.modules.wxarticle.contract.WxArticleContract;
import com.carter.javaAndroid.modules.wxarticle.presenter.WxArticlePresenter;

import java.util.List;

import butterknife.BindView;

public class WxArticleFragment extends BaseFragment<WxArticlePresenter> implements WxArticleContract.View {

    @BindView(R.id.wx_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.wx_viewpager)
    ViewPager mViewPager;

    private List<WxChapterBean> mWxChapterBean;
    private SparseArray<WxArticleListFragment> fragmentSparseArray = new SparseArray<>();
    private WxArticleListFragment currentFragment;

    public static WxArticleFragment newInstance(){
        return new WxArticleFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_article;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWxChapterListData();
    }

    @Override
    public void showWxChapterListData(List<WxChapterBean> wxChapterBeans) {
        mWxChapterBean = wxChapterBeans;
        initTabAndViewPager();
    }

    private void initTabAndViewPager() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                WxArticleListFragment wxArticleListFragment = fragmentSparseArray.get(i);
                if (wxArticleListFragment != null){
                    return wxArticleListFragment;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.WX_CHAPTER_ID,mWxChapterBean.get(i).getId());
                    wxArticleListFragment = WxArticleListFragment.newInstance(bundle);
                    fragmentSparseArray.put(i,wxArticleListFragment);
                    return wxArticleListFragment;
                }
            }

            @Override
            public int getCount() {
                return mWxChapterBean == null ? 0 :mWxChapterBean.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mWxChapterBean.get(position).getName());
            }
        });
        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void jumpToTop(){
        currentFragment = fragmentSparseArray.get(mViewPager.getCurrentItem());
        if (currentFragment != null){
            currentFragment.jumpToTop();
        }
    }

    @Override
    public void onDestroyView() {
        if (fragmentSparseArray != null) {
            fragmentSparseArray.clear();
            fragmentSparseArray = null;
        }
        if (mWxChapterBean != null) {
            mWxChapterBean.clear();
            mWxChapterBean = null;
        }
        super.onDestroyView();
    }
}
