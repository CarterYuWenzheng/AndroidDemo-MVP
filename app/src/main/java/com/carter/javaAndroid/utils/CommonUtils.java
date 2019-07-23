package com.carter.javaAndroid.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.carter.javaAndroid.Application.MyApplication;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.core.constant.ARouterPath;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class CommonUtils {

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public static void startLoginActivity() {
        ARouter.getInstance().build(ARouterPath.LOGIN_ACTIVITY).navigation();
    }

    public static int getRandomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red;
        int green;
        int blue;
        if (MyApplication.isNightMode()) {
//            150-255
            red = random.nextInt(105) + 150;
            green = random.nextInt(105) + 150;
            blue = random.nextInt(105) + 150;
        } else {
            red = random.nextInt(190);
            green = random.nextInt(190);
            blue = random.nextInt(190);
        }
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }

    public static AlertDialog getLoadingDialog(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_progressbar, null, false);
        TextView loadingText = view.findViewById(R.id.loading_text);
        loadingText.setText(message);
        AlertDialog dialog = new AlertDialog.Builder(context).setView(view).create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public static void hideKeyBoard(Context context, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat.getDateInstance();
        return simpleDateFormat.format(new Date());
    }

    public static Calendar dateString2Calendar(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static PopupWindow showPopupWindow(View anchorView, View contentView) {
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(contentView.getBackground());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        int windowPos[] = calculatePopWindowPos(anchorView, contentView);
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, windowPos[0], windowPos[1]);
        return popupWindow;
    }

    /**
     * 计算出来的位置，y方向就在anchorView的中心对齐显示，x方向就是与View的中心点对齐
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        final int anchorWidth = anchorView.getWidth();
        final int screenHeight = anchorView.getContext().getResources().getDisplayMetrics().heightPixels;
        final int screenWidth = anchorView.getContext().getResources().getDisplayMetrics().widthPixels;
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (anchorLoc[1] > screenHeight / 3);
        //偏移，否则会弹出在屏幕外
        int offset = windowWidth > anchorWidth ? (windowWidth - anchorWidth) : 0;
        //实际坐标中心点为触发view的中间
        windowPos[0] = (anchorLoc[0] + anchorWidth / 2) + offset;
        int offset2 = windowPos[0] + windowWidth - screenWidth;
        if (offset2 > 0) {
            windowPos[0] = windowPos[0] - offset2;
        }
        windowPos[1] = isNeedShowUp ? anchorLoc[1] - windowHeight + anchorHeight / 2 : anchorLoc[1] + anchorHeight / 2;
        return windowPos;
    }
}
