package com.carter.javaAndroid.modules.project.ui;

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
import com.carter.javaAndroid.modules.project.bean.ProjectTreeBean;
import com.carter.javaAndroid.modules.project.contract.ProjectContract;
import com.carter.javaAndroid.modules.project.presenter.ProjectPresenter;

import java.util.List;

import butterknife.BindView;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.project_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;

    private List<ProjectTreeBean> mProjectTreeData;
    private SparseArray<ProjectListFragment> fragmentSparseArray = new SparseArray<>();
    private ProjectListFragment currentFragment;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getProjectTreeData();
    }

    @Override
    public void showProjectTreeData(List<ProjectTreeBean> projectTreeBeans) {
        mProjectTreeData = projectTreeBeans;
        initTabAndViewPager();
    }

    private void initTabAndViewPager() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                ProjectListFragment projectListFragment = fragmentSparseArray.get(i);
                if (projectListFragment != null) {
                    return projectListFragment;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.PROJECT_CID, mProjectTreeData.get(i).getId());
                    projectListFragment = ProjectListFragment.newInstance(bundle);
                    fragmentSparseArray.put(i, projectListFragment);
                    return projectListFragment;
                }
            }

            @Override
            public int getCount() {
                return mProjectTreeData == null ? 0 : mProjectTreeData.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mProjectTreeData.get(position).getName());
            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消页面切换动画
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
    public void onDestroyView() {
        if (fragmentSparseArray != null) {
            fragmentSparseArray.clear();
            fragmentSparseArray = null;
        }
        if (mProjectTreeData != null) {
            mProjectTreeData.clear();
            mProjectTreeData = null;
        }
        super.onDestroyView();
    }
}
