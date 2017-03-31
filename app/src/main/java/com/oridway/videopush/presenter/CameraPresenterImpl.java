package com.oridway.videopush.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.oridway.videopush.adapter.CameraListAdapter;
import com.oridway.videopush.contract.CameraContract;
import com.oridway.videopush.model.RTMPSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihao on 2017/3/31.
 */

public class CameraPresenterImpl implements CameraContract.CameraPresenter {

    private CameraContract.CameraView mView;
    private Context viewContext;
    private List<RTMPSource> dataList;
    private CameraListAdapter mAdapter;

    public CameraPresenterImpl(CameraContract.CameraView view) {
        mView = view;
    }

    @Override
    public void initPresenter() {
        dataList = new ArrayList<>();
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test", "电影直播"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test1", "直播测试1"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test2", "直播测试2"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test3", "直播测试3"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test4", "直播测试14"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test5", "直播测试15"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test6", "直播测试16"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test7", "直播测试21"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test8", "直播测试31"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test9", "直播测试41"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test10", "直播测试51"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test11", "直播测试61"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/IPC1", "机房IPC"));
    }

    @Override
    public void initList(RecyclerView listView) {
        mAdapter = new CameraListAdapter(mView.getContext(), dataList);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void refreshList() {
        dataList.clear();
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test", "电影直播"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test1", "直播测试1"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test2", "直播测试2"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/test3", "直播测试3"));
        dataList.add(new RTMPSource("rtmp://192.168.2.254/live/IPC1", "机房IPC"));
        mAdapter.notifyDataSetChanged();
    }


}
