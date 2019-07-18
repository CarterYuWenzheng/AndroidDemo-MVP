package com.carter.javaAndroid.modules.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.core.db.MyDatabase;
import com.carter.javaAndroid.core.db.bean.User;
import com.carter.javaAndroid.core.event.LoginEvent;
import com.carter.javaAndroid.modules.homepager.ui.HomePagerFragment;
import com.carter.javaAndroid.modules.knowledge.ui.KnowledgeFragment;
import com.carter.javaAndroid.modules.main.contract.MainActivityContract;
import com.carter.javaAndroid.modules.main.presenter.MainActivityPresenter;
import com.carter.javaAndroid.modules.navigation.ui.NavigationFragment;
import com.carter.javaAndroid.modules.project.ui.ProjectFragment;
import com.carter.javaAndroid.modules.wxarticle.ui.WxArticleFragment;
import com.carter.javaAndroid.utils.ToastUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.carter.javaAndroid.Application.MyApplication.getContext;

@Route(path = ARouterPath.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.fragment_layout)
    FrameLayout mFrameGroup;
    TextView mUsTv;
    private AlertDialog mDialog;

    private HomePagerFragment homePagerFragment;
    private KnowledgeFragment knowledgeFragment;
    private NavigationFragment navigationFragment;
    private WxArticleFragment wxArticleFragment;
    private ProjectFragment projectFragment;
    private int mLastFragmentIndex = -1;
    private int mCurrentFragmentIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentFragmentIndex = savedInstanceState.getInt(Constants.CURRENT_FRAGMENT_KEY);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.CURRENT_FRAGMENT_KEY, mCurrentFragmentIndex);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        showFragment(mCurrentFragmentIndex);
        initNavigationView();
        initButtonNavigationView();
        initDrawerLayout();
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
    }

    private void showFragment(int index) {
        mCurrentFragmentIndex = index;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hintFragment(fragmentTransaction);
        mLastFragmentIndex = index;
        switch (index) {
            case Constants.FRAGMENT_HOME_PAGER:
                mTitle.setText(R.string.home_pager);
                if (homePagerFragment == null) {
                    homePagerFragment = HomePagerFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment_layout, homePagerFragment);
                }
                fragmentTransaction.show(homePagerFragment);
                break;
            case Constants.FRAGMENT_KNOWLEDGE:
                mTitle.setText(R.string.knowledge_hierarchy);
                if (knowledgeFragment == null) {
                    knowledgeFragment = KnowledgeFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment_layout, knowledgeFragment);
                }
                fragmentTransaction.show(knowledgeFragment);
                break;
            case Constants.FRAGMENT_NAVIGATION:
                mTitle.setText(R.string.navigation);
                if (navigationFragment == null) {
                    navigationFragment = NavigationFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment_layout, navigationFragment);
                }
                fragmentTransaction.show(navigationFragment);
                break;
            case Constants.FRAGMENT_WX_ARTICLE:
                mTitle.setText(R.string.wx_article);
                if (wxArticleFragment == null) {
                    wxArticleFragment = WxArticleFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment_layout, wxArticleFragment);
                }
                fragmentTransaction.show(wxArticleFragment);
                break;
            case Constants.FRAGMENT_PROJECT:
                mTitle.setText(R.string.project);
                if (projectFragment == null) {
                    projectFragment = ProjectFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment_layout, projectFragment);
                }
                fragmentTransaction.show(projectFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    private void hintFragment(FragmentTransaction fragmentTransaction) {
        switch (mLastFragmentIndex) {
            case Constants.FRAGMENT_HOME_PAGER:
                if (homePagerFragment != null) {
                    fragmentTransaction.hide(homePagerFragment);
                }
                break;
            case Constants.FRAGMENT_KNOWLEDGE:
                if (knowledgeFragment != null) {
                    fragmentTransaction.hide(knowledgeFragment);
                }
                break;
            case Constants.FRAGMENT_NAVIGATION:
                if (navigationFragment != null) {
                    fragmentTransaction.hide(navigationFragment);
                }
                break;
            case Constants.FRAGMENT_WX_ARTICLE:
                if (wxArticleFragment != null) {
                    fragmentTransaction.hide(wxArticleFragment);
                }
                break;
            case Constants.FRAGMENT_PROJECT:
                if (projectFragment != null) {
                    fragmentTransaction.hide(projectFragment);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(R.string.home_pager);
        }
    }

    @Override
    protected void initEventAndData() {

    }

    private void initButtonNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main:
                    showFragment(Constants.FRAGMENT_HOME_PAGER);
                    break;
                case R.id.tab_knowledge:
                    showFragment(Constants.FRAGMENT_KNOWLEDGE);
                    break;
                case R.id.tab_navigation:
                    showFragment(Constants.FRAGMENT_NAVIGATION);
                    break;
                case R.id.tab_wx_article:
                    showFragment(Constants.FRAGMENT_WX_ARTICLE);
                    break;
                case R.id.tab_project:
                    showFragment(Constants.FRAGMENT_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                ToastUtils.showToast(this, "搜索页面");
                ARouter.getInstance().build(ARouterPath.SEARCH_ACTIVITY).navigation();
                break;
            case R.id.action_usage:
                ToastUtils.showToast(this, "常用工具");
                ARouter.getInstance().build(ARouterPath.COMMON_ACTIVITY).navigation();
                break;
            default:
                break;
        }
        return true;
    }

    @OnClick({R.id.main_floating_action_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_floating_action_btn:
                jumpToTop();
                break;
            default:
                break;
        }
    }

    private void jumpToTop() {
        switch (mCurrentFragmentIndex) {
            case Constants.FRAGMENT_HOME_PAGER:
                if (homePagerFragment != null) {
                    homePagerFragment.jumpToTop();
                }
                break;
            case Constants.FRAGMENT_KNOWLEDGE:
                if (knowledgeFragment != null) {
                    knowledgeFragment.jumpToTop();
                }
                break;
            case Constants.FRAGMENT_WX_ARTICLE:
                if (wxArticleFragment != null) {
                    wxArticleFragment.jumpToTop();
                }
            case Constants.FRAGMENT_NAVIGATION:
                if (navigationFragment != null) {
                    navigationFragment.jumpToTop();
                }
                break;
            case Constants.FRAGMENT_PROJECT:
                if (projectFragment != null) {
                    projectFragment.jumpToTop();
                }
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventListener(LoginEvent loginEvent) {
        ToastUtils.showToast(getContext(), loginEvent.getmTag());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
