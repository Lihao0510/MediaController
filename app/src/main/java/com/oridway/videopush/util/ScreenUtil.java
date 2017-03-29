package com.oridway.videopush.util;

/**
 * Created by lihao on 2017/3/27.
 */


import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;


public class ScreenUtil {
    /**
     * dp转px
     *
     * @param context
     * @param dpVal   需要转换的dp值
     * @return
     * @CreateTime 20170318
     * @Author Lihao
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal   需要转换的sp值
     * @return
     * @CreateTime 20170318
     * @Author Lihao
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal   需要转换的px值
     * @return
     * @CreateTime 20170318
     * @Author Lihao
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal   需要转换的px值
     * @return
     * @CreateTime 20170318
     * @Author Lihao
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }


    /**
     * sp转px
     *
     * @param activity
     * @return
     * @CreateTime 20170318
     * @Author Lihao
     */
    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * sp转px
     *
     * @param activity
     * @return
     * @CreateTime 20170318
     * @Author Lihao
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * sp转px
     *
     * @param activity
     * @return
     * @CreateTime 20170318
     * @Author Lihao
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 获取系统状态栏的高度
     *
     * @param activity activity
     * @return 状态栏的高度
     */
    public static int getStatusBarHeight(Activity activity) {
        int statusBarHeight = dp2px(activity, 25);
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}