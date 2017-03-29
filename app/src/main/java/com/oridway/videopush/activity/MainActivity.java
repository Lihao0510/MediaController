package com.oridway.videopush.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oridway.videopush.encode.CharEncoder;
import com.oridway.videopush.sinvoice.LogHelper;
import com.oridway.videopush.sinvoice.SinVoicePlayer;
import com.oridway.videopush.sinvoice.SinVoiceRecognition;
import com.oridway.mediamanager.R;

public class MainActivity extends Activity implements SinVoiceRecognition.Listener, SinVoicePlayer.Listener {
    private final static String TAG = "MainActivity";
    private final static int MAX_NUMBER = 678;
    private final static int MSG_SET_RECG_TEXT = 1;
    private final static int MSG_RECG_START = 2;
    private final static int MSG_RECG_END = 3;

    private final static String CODEBOOK = "0123456789ABCDEF";

    private Handler mHanlder;
    private SinVoicePlayer mSinVoicePlayer;
    private SinVoiceRecognition mRecognition;
    private TextView tv_result;
    private EditText wifiName;
    private EditText wifiPwd;
    private Button playStart;
    private Button playStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSinVoicePlayer = new SinVoicePlayer(CODEBOOK);
        mSinVoicePlayer.setListener(this);

        //mRecognition = new SinVoiceRecognition(CODEBOOK);
        //mRecognition.setListener(this);


        wifiName = (EditText) findViewById(R.id.wifi_name);
        wifiPwd = (EditText) findViewById(R.id.wifi_pwd);
        playStart = (Button) this.findViewById(R.id.bt_play);
        playStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] message = new String[2];
                message[0] = wifiName.getText().toString();
                message[1] = wifiPwd.getText().toString();
                String soundSource = CharEncoder.encodeWifiMessage(message);
                mSinVoicePlayer.play(soundSource, true, 1000);
            }
        });

        tv_result = (TextView) this.findViewById(R.id.tv_text);


        playStop = (Button) this.findViewById(R.id.bt_stop);
        playStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mSinVoicePlayer.stop();
            }
        });

        Button startScan = (Button) this.findViewById(R.id.bt_scan);
        startScan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private String genText(int count) {
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        while (count > 0) {
            int x = (int) (Math.random() * MAX_NUMBER + 1);
            if (Math.abs(x - pre) > 0) {
                sb.append(x);
                --count;
                pre = x;
            }
        }

        return sb.toString();
    }

    private static class RegHandler extends Handler {
        private StringBuilder mTextBuilder = new StringBuilder();
        private TextView mRecognisedTextView;

        public RegHandler(TextView textView) {
            mRecognisedTextView = textView;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SET_RECG_TEXT:
                    char ch = (char) msg.arg1;
                    mTextBuilder.append(ch);
                    if (null != mRecognisedTextView) {
                        mRecognisedTextView.setText(mTextBuilder.toString());
                    }
                    break;

                case MSG_RECG_START:
                    mTextBuilder.delete(0, mTextBuilder.length());
                    break;

                case MSG_RECG_END:
                    LogHelper.d(TAG, "recognition end");
                    break;
            }
            super.handleMessage(msg);
        }
    }

    @Override
    public void onRecognitionStart() {
        mHanlder.sendEmptyMessage(MSG_RECG_START);
    }

    @Override
    public void onRecognition(char ch) {
        mHanlder.sendMessage(mHanlder.obtainMessage(MSG_SET_RECG_TEXT, ch, 0));
    }

    @Override
    public void onRecognitionEnd() {
        mHanlder.sendEmptyMessage(MSG_RECG_END);
    }

    @Override
    public void onPlayStart() {
        LogHelper.d(TAG, "start play");
    }

    @Override
    public void onPlayEnd() {
        LogHelper.d(TAG, "stop play");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == 0) {
            tv_result.setText(data.getStringExtra("result"));
        }
    }
}
