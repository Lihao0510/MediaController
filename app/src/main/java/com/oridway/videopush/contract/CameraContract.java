package com.oridway.videopush.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lihao on 2017/3/31.
 */

public interface CameraContract {

    interface CameraView {
        void setPresenter(CameraPresenter presenter);
        Context getContext();
    }

    interface CameraPresenter {
        void initPresenter();
        void initList(RecyclerView listView);
        void refreshList();
    }
}
