package com.carter.javaAndroid.modules.main.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.main.contract.ArticleDetailActivityContract;
import com.carter.javaAndroid.modules.main.presenter.ArticleDetailActivityPresenter;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.NestedScrollAgentWebView;

import butterknife.BindView;

@Route(path = ARouterPath.ARTICLE_DETAIL_ACTIVITY)
public class ArticleDetailActivity extends BaseActivity<ArticleDetailActivityPresenter>
        implements ArticleDetailActivityContract.View {

    @BindView(R.id.content_layout)
    CoordinatorLayout mContent;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;


    private int articleId;
    private String articleLink;
    private String title;
    private boolean isCollected;
    private boolean isShowCollectIcon;
    private int articleItemPosition;
    private String eventBusTag;
    private MenuItem mCollectItem;

    private AgentWeb mAgentWeb;

    @Override
    protected void onResume() {
        super.onResume();
        mAgentWeb.getWebLifeCycle().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolBar() {
        getBundleData();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(Html.fromHtml(title));
            mTitle.setSelected(true);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        initWebView();
    }

    private void initWebView() {
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle.setText(Html.fromHtml(title));
            }
        };
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        NestedScrollAgentWebView scrollAgentWebView = new NestedScrollAgentWebView(this);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContent, layoutParams)
                .useDefaultIndicator()
                .setWebView(scrollAgentWebView)
                .setWebChromeClient(webChromeClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .createAgentWeb()
                .ready()
                .go(articleLink);
    }

    @Override
    public void onBackPressedSupport() {
        if (!mAgentWeb.back()){
            super.onBackPressedSupport();
        }
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        title = bundle.getString(Constants.ARTICLE_TITLE);
        articleLink = bundle.getString(Constants.ARTICLE_LINK);
        articleId = bundle.getInt(Constants.ARTICLE_ID);
        isCollected = bundle.getBoolean(Constants.IS_COLLECTED);
        isShowCollectIcon = bundle.getBoolean(Constants.IS_SHOW_COLLECT_ICON);
        articleItemPosition = bundle.getInt(Constants.ARTICLE_ITEM_POSITION, -1);
        eventBusTag = bundle.getString(Constants.EVENT_BUS_TAG);
    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollectSuccess(int position) {

    }
}
