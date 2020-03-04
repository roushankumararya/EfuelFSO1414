package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 2/22/18.
 */

public class ViewSchedulesVH extends ViewHolder {

    @BindView(R.id.tvScheduleDateTime)
    public CustomTextView tvScheduleDateTime;

    @BindView(R.id.tvCreatedOn)
    public CustomTextView tvCreatedOn;

    @BindView(R.id.tvModified)
    public CustomTextView tvModified;

    @BindView(R.id.tvCreatedBy)
    public CustomTextView tvCreatedBy;

    @BindView(R.id.tvModifiedBy)
    public CustomTextView tvModifiedBy;

    public ViewSchedulesVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
