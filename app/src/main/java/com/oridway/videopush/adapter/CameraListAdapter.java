package com.oridway.videopush.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oridway.mediamanager.R;
import com.oridway.videopush.model.RTMPSource;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihao on 2017/3/31.
 */

public class CameraListAdapter extends RecyclerView.Adapter<CameraListAdapter.ViewHolder> {

    private Context mContext;
    private List<RTMPSource> sourceList;

    public CameraListAdapter(Context context, List<RTMPSource> sourceList) {
        this.mContext = context;
        this.sourceList = sourceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_video, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(sourceList.get(position).getVideoName());
        holder.url.setText(sourceList.get(position).getSourceUrl());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, sourceList.get(position).getSourceUrl(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @BindView(R.id.rl_list_button)
        RelativeLayout button;
        @BindView(R.id.iv_video_icon)
        ImageView icon;
        @BindView(R.id.tv_video_name)
        TextView title;
        @BindView(R.id.tv_video_owner)
        TextView owner;
        @BindView(R.id.tv_video_time)
        TextView time;
        @BindView(R.id.tv_video_url)
        TextView url;
    }
}
