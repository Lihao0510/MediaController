package com.oridway.videopush.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oridway.mediamanager.R;
import com.oridway.videopush.application.IPCApplication;
import com.oridway.videopush.util.FullScreenUtil;

import butterknife.BindView;

/*
* 登录/注册Activity
* @Author Lihao 2017/03/24
*
*/
public class LoginActivity extends BaseActivity {
    //注册/登录Button
    @BindView(R.id.bt_submit)
    Button submitButton;
    @BindView(R.id.view_dummy)
    View dummyView;
    //切换注册/登录的TextView
    @BindView(R.id.tv_rigister)
    TextView goRigister;
    //登录状态的EditText组
    @BindView(R.id.et_login_name)
    EditText loginName;
    @BindView(R.id.et_login_password)
    EditText loginPwd;
    //注册状态的EditText组
    @BindView(R.id.et_regist_name)
    EditText registerName;
    @BindView(R.id.et_register_pwd)
    EditText registerPwd;
    @BindView(R.id.et_register_check)
    EditText registerCheck;
    //登录组件容器
    @BindView(R.id.ll_group_login)
    LinearLayout loginGroup;
    //注册组件容器
    @BindView(R.id.ll_group_rigister)
    LinearLayout rigisterGroup;
    //第三方登录组件容器
    @BindView(R.id.ll_group_social)
    LinearLayout thirdPartyGroup;
    //进入第三方登录
    @BindView(R.id.ll_social_login)
    LinearLayout socialLogin;
    //微信登录按钮
    @BindView(R.id.ll_login_wechat)
    LinearLayout wechatLogin;
    //微博登录按钮
    @BindView(R.id.ll_login_weibo)
    LinearLayout weiboLogin;

    private boolean isRigister;
    private boolean isSocialLogin;
    private boolean isDevEnv;
    private InputMethodManager keyBoardManager;

    private Animation fadeInFromRight;
    private Animation fadeOutToLeft;
    private Animation fadeInBottom;
    private Animation fadeOutBottom;



    @Override
    protected int setLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        FullScreenUtil.setFullScrean(this);
        String devStatus = IPCApplication.getInstance().getConfig("APP_MODE");
        if (devStatus != null && devStatus.length() != 0) {
            isDevEnv = devStatus.equals("DEV") ? true : false;
        }
        keyBoardManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        fadeInBottom = AnimationUtils.loadAnimation(mContext,R.anim.fade_in_bottom);
        fadeOutToLeft = AnimationUtils.loadAnimation(mContext,R.anim.flow_out_left);
        fadeInFromRight = AnimationUtils.loadAnimation(mContext,R.anim.flow_in_left);
    }

    @Override
    protected void start() {
        dummyView.requestFocus();
        submitButton.setOnClickListener(this);
        socialLogin.setOnClickListener(this);
        goRigister.setOnClickListener(this);
        wechatLogin.setOnClickListener(this);
        weiboLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_submit:
                if (isDevEnv) {
                    keyBoardManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.SHOW_FORCED);
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    keyBoardManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.SHOW_FORCED);
                }
                break;
            case R.id.ll_login_weibo:
            case R.id.ll_login_wechat:
                showToast("该功能暂未开放!");
                break;
            case R.id.ll_social_login:
                if (isSocialLogin) {
                    isSocialLogin = false;
                    thirdPartyGroup.setVisibility(View.GONE);
                } else {
                    isSocialLogin = true;
                    thirdPartyGroup.setVisibility(View.VISIBLE);
                    socialLogin.startAnimation(fadeInBottom);
                    thirdPartyGroup.startAnimation(fadeInBottom);
                }
                break;
            case R.id.tv_rigister:
                if (isRigister) {
                    isRigister = false;
                    rigisterGroup.setVisibility(View.GONE);
                    rigisterGroup.startAnimation(fadeOutToLeft);
                    loginGroup.setVisibility(View.VISIBLE);
                    loginGroup.startAnimation(fadeInFromRight);
                } else {
                    isRigister = true;
                    loginGroup.setVisibility(View.GONE);
                    loginGroup.startAnimation(fadeOutToLeft);
                    rigisterGroup.setVisibility(View.VISIBLE);
                    rigisterGroup.startAnimation(fadeInFromRight);

                }
                break;
            default:
                break;
        }
    }
}
