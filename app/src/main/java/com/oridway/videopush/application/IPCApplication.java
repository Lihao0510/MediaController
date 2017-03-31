package com.oridway.videopush.application;

import android.app.Application;
import android.content.Context;

import com.oridway.mediamanager.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by lihao on 2017/3/22.
 */

public class IPCApplication extends Application {

    private static Context applicationContext;

    private static IPCApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        applicationContext = this;
    }

    public static Context getAppContext() {
        return applicationContext;
    }

    public static IPCApplication getInstance() {
        return mInstance;
    }

    public String getConfig(String key) {
        Properties config = new Properties();
        InputStream in = null;
        BufferedReader reader = null;
        in = applicationContext.getResources().openRawResource(R.raw.config);
        reader = new BufferedReader(new InputStreamReader(in));
        try {
            config.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return config.getProperty(key);
    }
}
