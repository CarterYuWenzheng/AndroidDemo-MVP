package com.carter.javaAndroid.modules.login.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.fragment.BaseFragment;
import com.carter.javaAndroid.core.constant.ARouterPath;
import com.carter.javaAndroid.jni.JNITest;
import com.carter.javaAndroid.modules.login.contract.LoginFragmentContract;
import com.carter.javaAndroid.modules.login.presenter.LoginFragmentPresenter;
import com.carter.javaAndroid.utils.ToastUtils;


import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment<LoginFragmentPresenter> implements LoginFragmentContract.View {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPassword)
    EditText etPassWord;

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick({R.id.btnLogin, R.id.btnRegister,R.id.btnMainActivity})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnRegister:
                goToRegister();
                break;
            case R.id.btnMainActivity:
//                goToMainActivity();
                ToastUtils.showToast(_mActivity, JNITest.getJNIName());
                break;
            default:
                break;
        }
    }

    private void goToMainActivity() {
        ARouter.getInstance().build(ARouterPath.MAIN_ACTIVITY).navigation();
    }

    private void goToRegister() {
        RegisterFragment registerFragment = findFragment(RegisterFragment.class);
        if (registerFragment == null) {
            registerFragment = RegisterFragment.getInstance();
        }
        getSupportDelegate().showHideFragment(registerFragment,LoginFragment.this);
        LoginActivity loginActivity = (LoginActivity) _mActivity;
        loginActivity.mTitle.setText("注册");
    }

    private void login() {
        String name = etName.getText().toString();
        String password = etPassWord.getText().toString();
        mPresenter.login(name, password);
    }

    @Override
    public void loginSuccess() {
        ToastUtils.showToast(_mActivity,"登录成功");
        _mActivity.finish();
    }

}
