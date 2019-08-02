package com.carter.javaAndroid.modules.main.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.core.constant.Constants;
import com.carter.javaAndroid.modules.main.bean.UsefulSiteBean;
import com.carter.javaAndroid.modules.main.contract.UsefulSiteContract;
import com.carter.javaAndroid.modules.main.presenter.UsefulSitePresenter;
import com.carter.javaAndroid.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UsefulSiteFragment extends BaseFragment<UsefulSitePresenter> implements UsefulSiteContract.View {

    @BindView(R.id.useful_sites_flow_layout)
    TagFlowLayout mUsefulSitesFlowLayout;

    private List<UsefulSiteBean> mUsefulSiteDataList;

    public static UsefulSiteFragment newInstance() {
        return new UsefulSiteFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_useful_site;
    }

    @Override
    protected void initEventAndData() {
        mUsefulSiteDataList = new ArrayList<>();
        mPresenter.getUsefulSites();
    }

    @Override
    public void showUsefulSites(List<UsefulSiteBean> usefulSiteData) {
        mUsefulSiteDataList = usefulSiteData;
        mUsefulSitesFlowLayout.setAdapter(new TagAdapter<UsefulSiteBean>(usefulSiteData){
            @Override
            public View getView(FlowLayout parent, int position, UsefulSiteBean usefulSiteBean) {
                TextView tv = (TextView) LayoutInflater.from(_mActivity)
                        .inflate(R.layout.flow_layout_tv, parent, false);
                if (usefulSiteBean != null) {
                    tv.setText(usefulSiteBean.getName());
                    tv.setTextColor(CommonUtils.getRandomColor());
                }
                return tv;
            }
        });
        mUsefulSitesFlowLayout.setOnTagClickListener((view, position, parent) -> {
            UsefulSiteBean bean =mUsefulSiteDataList.get(position);
            ARouter.getInstance().build(ARouterPath.ARTICLE_DETAIL_ACTIVITY)
                    .withInt(Constants.ARTICLE_ID, bean.getId())
                    .withString(Constants.ARTICLE_TITLE, bean.getName().trim())
                    .withString(Constants.ARTICLE_LINK, bean.getLink().trim())
                    .withBoolean(Constants.IS_COLLECTED, false)
                    .withBoolean(Constants.IS_SHOW_COLLECT_ICON, false)
                    .withInt(Constants.ARTICLE_ITEM_POSITION, -1)
                    .withString(Constants.EVENT_BUS_TAG, Constants.TAG_DEFAULT)
                    .navigation();
            return true;
        });
    }
}
