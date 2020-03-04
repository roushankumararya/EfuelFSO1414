package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 3/19/18.
 */

public class NotificationVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tvTitle)
    public TextView tvTitle;

    @BindView(R.id.tvMsg)
    public TextView tvMsg;

    @BindView(R.id.tvTime)
    public TextView tvTime;

    public NotificationVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
