package com.oridway.videopush.fragment;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Button;

import com.oridway.mediamanager.R;
import com.oridway.videopush.activity.VideoActivity;
import com.oridway.videopush.util.LogUtil;

import butterknife.BindView;

/**
 * Created by lihao on 2017/3/27.
 */

public class CameraFragment extends BaseFragment {

    private static final int DEFAULT_SAMPLE_RATE = 44100;
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    @BindView(R.id.bt_start_collect)
    Button startCollect;
    @BindView(R.id.bt_stop_collect)
    Button stopCollect;

    private AudioRecord record;
    private byte[] bufferRead;
    private Thread collectThread;

    @Override
    protected int setViewSource() {
        return R.layout.fragment_camera;
    }

    @Override
    protected void start() {
        startCollect.setOnClickListener(this);
        stopCollect.setOnClickListener(this);
        record = new AudioRecord(MediaRecorder.AudioSource.MIC,
                DEFAULT_SAMPLE_RATE, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, DEFAULT_SAMPLE_RATE * 6);
    }

    @Override
    protected void initData() {
        bufferRead = new byte[DEFAULT_BUFFER_SIZE];
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start_collect:
                startCollect();
                break;
            case R.id.bt_stop_collect:
                stopCollect();
                break;
            default:
                break;
        }
    }

    private void stopCollect() {

    }

    private void startCollect() {
        Intent intent = new Intent(mActivity, VideoActivity.class);
        startActivity(intent);
    }

}
