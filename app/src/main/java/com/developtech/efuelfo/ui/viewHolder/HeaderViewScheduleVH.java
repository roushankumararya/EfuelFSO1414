package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderViewScheduleVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tvHeaderSchedules)
    public TextView tvHeaderSchedules;

    public HeaderViewScheduleVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
