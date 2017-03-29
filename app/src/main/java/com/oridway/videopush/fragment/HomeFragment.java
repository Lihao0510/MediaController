package com.oridway.videopush.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.oridway.mediamanager.R;
import com.oridway.videopush.activity.VideoActivity;
import com.oridway.videopush.model.EventMessage;
import com.oridway.videopush.widge.WaveHelper;
import com.oridway.videopush.widge.WaveView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.ww_wave)
    WaveView waveView;
    @BindView(R.id.fab_start_push)
    FloatingActionButton pushButton;

    private WaveHelper waveHelper;
    private int frontColor = Color.parseColor("#2196F3");
    private int backColor = Color.parseColor("#00FFFFFF");

    @Override
    protected int setViewSource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        waveHelper = new WaveHelper(waveView);
    }

    @Override
    protected void start() {
        waveView.setBorder(0, frontColor);
        waveView.setWaveColor(backColor, frontColor);
        waveHelper.start();
        pushButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_start_push:
                Intent intent = new Intent(mActivity, VideoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
