package com.carter.javaAndroid.modules.main.ui.fragment;

import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.carter.javaAndroid.Application.MyApplication;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.modules.main.contract.AboutContract;
import com.carter.javaAndroid.modules.main.presenter.AboutPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutFragment extends BaseFragment<AboutPresenter> implements AboutContract.View {

    @BindView(R.id.about_version)
    TextView mAboutVersion;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initEventAndData() {
        showAboutContent();
    }

    private void showAboutContent() {
        try {
            String versionStr = getString(R.string.app_name)
                    + " V" + MyApplication.getContext().getPackageManager()
                    .getPackageInfo(MyApplication.getContext().getPackageName(), 0).versionName;
            mAboutVersion.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.about_upgrade, R.id.about_website, R.id.about_source_code,
            R.id.about_feedback, R.id.about_copyright})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_copyright:
                new AlertDialog.Builder(_mActivity)
                        .setTitle(R.string.about_copyright)
                        .setMessage(R.string.copyright_content)
                        .setCancelable(true)
                        .show();
                break;
            default:
                break;
        }
    }

}
