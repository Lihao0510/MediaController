package com.oridway.videopush.fragment;

import android.graphics.Color;
import android.view.View;

import com.oridway.mediamanager.R;
import com.oridway.videopush.widge.WaveHelper;
import com.oridway.videopush.widge.WaveView;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.ww_wave)
    WaveView waveView;

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

    }

    @Override
    public void onClick(View v) {

    }
}
