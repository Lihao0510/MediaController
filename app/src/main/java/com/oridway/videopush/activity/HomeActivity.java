package com.oridway.videopush.activity;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oridway.mediamanager.R;
import com.oridway.videopush.fragment.CameraFragment;
import com.oridway.videopush.fragment.HomeFragment;
import com.oridway.videopush.fragment.SystemFragment;
import com.oridway.videopush.util.FullScreenUtil;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    private static final int BLANK_VIEW = 0;
    private static final int HOME_VIEW = 1;
    private static final int CAMERA_VIEW = 2;
    private static final int SYS_VIEW = 3;

    //Toolbar对应的三个Fragment切换按钮
    @BindView(R.id.ll_home_layout)
    LinearLayout homeButton;
    @BindView(R.id.ll_camera_layout)
    LinearLayout cameraButton;
    @BindView(R.id.ll_sys_layout)
    LinearLayout sysButton;

    //Toolbar对应三个按钮的图片
    @BindView(R.id.iv_home_icon)
    ImageView homeIcon;
    @BindView(R.id.iv_camera_icon)
    ImageView cameraIcon;
    @BindView(R.id.iv_sys_icon)
    ImageView sysIcon;

    //Toolbar对应三个按钮的文字
    @BindView(R.id.tv_home_text)
    TextView homeText;
    @BindView(R.id.tv_camera_text)
    TextView cameraText;
    @BindView(R.id.tv_sys_text)
    TextView sysText;

    //管理Fragment形态的support.v4.app.FragmentManager
    private FragmentManager fragmentManager;
    private int curView = BLANK_VIEW;
    private int normalColor;
    private int checkColor;

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        FullScreenUtil.setStatusColor(this);
        fragmentManager = getSupportFragmentManager();
        normalColor = R.color.main_gray;
        checkColor = R.color.main_blue;
    }

    @Override
    protected void start() {
        loadHomeFragment();
        homeButton.setOnClickListener(this);
        cameraButton.setOnClickListener(this);
        sysButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_layout:
                loadHomeFragment();
                break;
            case R.id.ll_camera_layout:
                loadCameraFragment();
                break;
            case R.id.ll_sys_layout:
                loadSysFragment();
                break;
        }
    }

    private void dimAllButton() {
        homeText.setTextColor(normalColor);
        cameraText.setTextColor(normalColor);
        sysText.setTextColor(normalColor);
        homeIcon.setImageResource(R.mipmap.icon_home_normal);
        cameraIcon.setImageResource(R.mipmap.icon_camera_normal);
        sysIcon.setImageResource(R.mipmap.icon_sys_normal);
    }

    private void loadHomeFragment() {
        if (curView == HOME_VIEW) {
            return;
        }
        dimAllButton();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.flow_in_left, R.anim.flow_out_left)
                .replace(R.id.fl_fragment, new HomeFragment(), "HomeFragment")
                .commit();
        homeText.setTextColor(checkColor);
        homeIcon.setImageResource(R.mipmap.icon_home_check);
        curView = HOME_VIEW;
    }

    private void loadCameraFragment() {
        if (curView == CAMERA_VIEW) {
            return;
        }
        dimAllButton();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.flow_in_left, R.anim.flow_out_left)
                .replace(R.id.fl_fragment, new CameraFragment(), "CameraFragment")
                .commit();
        cameraText.setTextColor(checkColor);
        cameraIcon.setImageResource(R.mipmap.icon_camera_check);
        curView = CAMERA_VIEW;
    }

    private void loadSysFragment() {
        if (curView == SYS_VIEW) {
            return;
        }
        dimAllButton();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.flow_in_left, R.anim.flow_out_left)
                .replace(R.id.fl_fragment, new SystemFragment(), "SystemFragment")
                .commit();
        sysText.setTextColor(checkColor);
        sysIcon.setImageResource(R.mipmap.icon_sys_check);
        curView = SYS_VIEW;
    }
}
