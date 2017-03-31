package com.oridway.videopush.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oridway.mediamanager.R;
import com.oridway.videopush.contract.CameraContract;
import com.oridway.videopush.model.EventMessage;
import com.oridway.videopush.presenter.CameraPresenterImpl;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by lihao on 2017/3/27.
 */

public class CameraFragment extends BaseFragment implements CameraContract.CameraView {

    @BindView(R.id.title_back)
    LinearLayout backButton;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.rv_camera_list)
    RecyclerView cameraList;
    @BindView(R.id.ptr_camera)
    PtrClassicFrameLayout ptrManager;

    private LinearLayoutManager listManager;

    private CameraContract.CameraPresenter mPresenter;

    @Override
    protected int setViewSource() {
        return R.layout.fragment_camera;
    }

    @Override
    protected void initData() {
        setPresenter(new CameraPresenterImpl(this));
        titleText.setText("视频源列表");
    }

    @Override
    protected void start() {
        backButton.setOnClickListener(this);
        listManager = new LinearLayoutManager(mActivity);
        initPullRefresh();
        cameraList.setLayoutManager(listManager);
        mPresenter.initList(cameraList);
    }

    private void initPullRefresh() {

        ptrManager.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return listManager.findFirstCompletelyVisibleItemPosition() == 0;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.refreshList();
                ptrManager.refreshComplete();
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

    @Override
    public void setPresenter(CameraContract.CameraPresenter presenter) {
        mPresenter = presenter;
        mPresenter.initPresenter();
    }

    @Override
    public Context getContext() {
        return mActivity;
    }
}
