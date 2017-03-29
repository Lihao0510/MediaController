package com.oridway.videopush.util;

import android.util.Log;

import com.oridway.videopush.application.Constant;

/**
 * Created by lihao on 2017/3/24.
 */

public class LogUtil {

    public static void debugLog(String msg) {
        Log.d(Constant.APP_NAME, msg);
    }

    public static void errorLog(String msg) {
        Log.e(Constant.APP_NAME, msg);
    }

    public static void infoLog(String msg) {
        Log.i(Constant.APP_NAME, msg);
    }
}
