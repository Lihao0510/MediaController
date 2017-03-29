package com.oridway.videopush.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by lihao on 2017/3/24.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutRes());
        mContext = this;
        ButterKnife.bind(this);
        initData();
        start();
    }

    protected abstract int setLayoutRes();

    protected abstract void initData();

    protected abstract void start();

    protected void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
