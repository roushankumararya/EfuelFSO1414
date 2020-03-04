package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 1/9/18.
 */

public class TotalSaleFuelTypeVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tvFuelType)
    public CustomTextView tvFuelType;

    @BindView(R.id.tvTotalSale)
    public CustomTextView tvTotalSale;

    public TotalSaleFuelTypeVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
