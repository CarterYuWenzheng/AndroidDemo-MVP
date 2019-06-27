package com.carter.javaAndroid.modules.splash;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.base.activity.BaseActivity;
import com.carter.javaAndroid.core.constant.ARouterPath;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.btnJump)
    Button btnJumpTime;

    @Override
    protected void initView() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3F, 1.0F);
        alphaAnimation.setDuration(5000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                countDownTimer.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        findViewById(R.id.layout_splash).setAnimation(alphaAnimation);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initEventAndData() {

    }

    private CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long millisUntilFinished) {
            btnJumpTime.setText("跳过广告(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            ARouter.getInstance().build(ARouterPath.MAIN_ACTIVITY).navigation();
            finish();
        }
    };

    @OnClick(R.id.btnJump)
    void onClick(View view){
        switch (view.getId()){
            case R.id.btnJump:
                countDownTimer.cancel();
                ARouter.getInstance().build(ARouterPath.MAIN_ACTIVITY).navigation();
                finish();
                break;
                default:
                    break;
        }
    }
}
