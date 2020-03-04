package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 2/14/18.
 */

public class FuelPricesStationDetailVH extends ViewHolder {

    @BindView(R.id.tvFuelType)
    public TextView tvFuelType;

    @BindView(R.id.tvFuelPrice)
    public TextView tvFuelPrice;

    public FuelPricesStationDetailVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
