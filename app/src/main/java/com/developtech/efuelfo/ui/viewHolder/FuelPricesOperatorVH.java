package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 3/5/18.
 */

public class FuelPricesOperatorVH extends RecyclerView.ViewHolder
{
    @BindView(R.id.tvFuelType)
    public TextView tvFuelType;

    @BindView(R.id.tvFuelPrice)
    public TextView tvFuelPrice;

    public FuelPricesOperatorVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
