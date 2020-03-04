package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleModelsVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tvModel)
    public TextView tvModel;

    public VehicleModelsVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
