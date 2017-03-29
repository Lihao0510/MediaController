package com.oridway.videopush.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oridway.mediamanager.R;
import com.oridway.videopush.model.EventMessage;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by lihao on 2017/3/27.
 */

public class SystemFragment extends BaseFragment {


    @BindView(R.id.title_back)
    LinearLayout backButton;
    @BindView(R.id.title_text)
    TextView titleText;

    @Override
    protected int setViewSource() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initData() {
        titleText.setText("系统管理");
    }

    @Override
    protected void start() {
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                EventBus.getDefault().post(new EventMessage(0, 1));
                break;
            default:
                break;
        }
    }
}
