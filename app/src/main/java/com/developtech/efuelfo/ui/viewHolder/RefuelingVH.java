package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefuelingVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tvFuelType)
    public TextView tvFuelType;

    @BindView(R.id.tvCost)
    public TextView tvCost;

    @BindView(R.id.tvVolume)
    public TextView tvVolume;

    public RefuelingVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
