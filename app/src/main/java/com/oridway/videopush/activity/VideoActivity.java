package com.oridway.videopush.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.faucamp.simplertmp.RtmpHandler;
import com.oridway.mediamanager.R;
import com.oridway.mediaplayer.PlayerManager;
import com.oridway.mediaplayer.SmartVideoView;

import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;

import java.io.IOException;
import java.net.SocketException;

import butterknife.BindView;

public class VideoActivity extends BaseActivity implements PlayerManager.PlayerStateListener {

    private static final String TAG = "RTMP_PUSH";

    @BindView(R.id.video_view)
    SmartVideoView videoView;
    @BindView(R.id.push_view)
    SrsCameraView cameraView;
    @BindView(R.id.bt_start_publish)
    Button startPublish;
    @BindView(R.id.bt_refresh_receive)
    Button refreshReceive;

    private PlayerManager player;
    private SrsPublisher mPublisher;
    //private static String url = "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    //private static String url = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    //private String url = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    private String url = "rtmp://192.168.2.254/live/test1";

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_video;
    }

    @Override
    protected void initData() {
        initVideoView();
        initPushView();
        initClick();
    }

    private void initClick() {
        startPublish.setOnClickListener(this);
        refreshReceive.setOnClickListener(this);
    }

    private void initVideoView() {
        player = new PlayerManager(this, videoView);
        player.setFullScreenOnly(false);
        player.setScaleType(PlayerManager.SCALETYPE_WRAPCONTENT);
        player.playInFullScreen(false);
        player.setPlayerStateListener(this);
        player.play(url);
    }

    private void initPushView() {
        mPublisher = new SrsPublisher(cameraView);
        mPublisher.setEncodeHandler(new SrsEncodeHandler(new SrsEncodeHandler.SrsEncodeListener() {
            @Override
            public void onNetworkWeak() {

            }

            @Override
            public void onNetworkResume() {

            }

            @Override
            public void onEncodeIllegalArgumentException(IllegalArgumentException e) {

            }
        }));
        mPublisher.setRtmpHandler(new RtmpHandler(new RtmpHandler.RtmpListener() {
            @Override
            public void onRtmpConnecting(String msg) {

            }

            @Override
            public void onRtmpConnected(String msg) {

            }

            @Override
            public void onRtmpVideoStreaming() {

            }

            @Override
            public void onRtmpAudioStreaming() {

            }

            @Override
            public void onRtmpStopped() {

            }

            @Override
            public void onRtmpDisconnected() {

            }

            @Override
            public void onRtmpVideoFpsChanged(double fps) {

            }

            @Override
            public void onRtmpVideoBitrateChanged(double bitrate) {
                int rate = (int) bitrate;
                if (rate / 1000 > 0) {
                    Log.i(TAG, String.format("Video bitrate: %f kbps", bitrate / 1000));
                } else {
                    Log.i(TAG, String.format("Video bitrate: %d bps", rate));
                }
            }

            @Override
            public void onRtmpAudioBitrateChanged(double bitrate) {
                int rate = (int) bitrate;
                if (rate / 1000 > 0) {
                    Log.i(TAG, String.format("Audio bitrate: %f kbps", bitrate / 1000));
                } else {
                    Log.i(TAG, String.format("Audio bitrate: %d bps", rate));
                }
            }

            @Override
            public void onRtmpSocketException(SocketException e) {

            }

            @Override
            public void onRtmpIOException(IOException e) {

            }

            @Override
            public void onRtmpIllegalArgumentException(IllegalArgumentException e) {

            }

            @Override
            public void onRtmpIllegalStateException(IllegalStateException e) {

            }
        }));
        mPublisher.setRecordHandler(new SrsRecordHandler(new SrsRecordHandler.SrsRecordListener() {
            @Override
            public void onRecordPause() {

            }

            @Override
            public void onRecordResume() {

            }

            @Override
            public void onRecordStarted(String msg) {

            }

            @Override
            public void onRecordFinished(String msg) {

            }

            @Override
            public void onRecordIllegalArgumentException(IllegalArgumentException e) {

            }

            @Override
            public void onRecordIOException(IOException e) {

            }
        }));
        initCamera();
    }

    public void initCamera() {
        mPublisher.setPreviewResolution(480, 640);
        mPublisher.setOutputResolution(480, 640);
        mPublisher.setVideoSmoothMode();
    }

    public void startPush() {
        Thread pushThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mPublisher.startPublish("rtmp://192.168.2.254/live/test1");
            }
        });
        pushThread.start();
    }

    @Override
    protected void start() {
        player.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start_publish:
                startPush();
                break;
            case R.id.bt_refresh_receive:
                player.stop();
                player.play(url);
                break;
            default:
                break;
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
        mPublisher.stopEncode();
        mPublisher.stopPublish();
    }
}
