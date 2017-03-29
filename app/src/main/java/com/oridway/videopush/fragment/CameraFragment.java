package com.oridway.videopush.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oridway.mediamanager.R;
import com.oridway.videopush.model.EventMessage;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by lihao on 2017/3/27.
 */

public class CameraFragment extends BaseFragment {

    @BindView(R.id.title_back)
    LinearLayout backButton;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.rv_camera_list)
    RecyclerView cameraList;
    @BindView(R.id.ptr_camera)
    PtrClassicFrameLayout ptrManager;

    private LinearLayoutManager listManager;

    @Override
    protected int setViewSource() {
        return R.layout.fragment_camera;
    }

    @Override
    protected void initData() {
        titleText.setText("视频源列表");
    }

    @Override
    protected void start() {
        backButton.setOnClickListener(this);
        initPullRefresh();
    }

    private void initPullRefresh() {

        ptrManager.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //listManager.findFirstCompletelyVisibleItemPosition() == 0;
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrManager.refreshComplete();
                    }
                }, 1800);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                EventBus.getDefault().post(new EventMessage(0, 0));
                break;
            default:
                break;
        }
    }

}
