package com.oridway.videopush.activity;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by lihao on 2017/1/11.
 */

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);// Set the scanner view as the content view
        testAudio();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        intent.putExtra("result", rawResult.getText());
        setResult(0, intent);
        finish();
    }

    public void testAudio() {
        int bufferSize = AudioTrack.getMinBufferSize(8000, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
        System.out.println("BufferSize大小:" + bufferSize + ";");
        AudioTrack trackplayer = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);
        trackplayer.play();
        trackplayer.flush();
        trackplayer.release();
    }
}
