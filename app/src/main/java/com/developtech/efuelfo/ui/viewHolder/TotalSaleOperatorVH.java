package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.customs.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 1/9/18.
 */

public class TotalSaleOperatorVH extends RecyclerView.ViewHolder {

    @BindView(R.id.ivOperator)
    public RoundedImageView ivOperator;

    @BindView(R.id.tvOperatorName)
    public CustomTextView tvOperatorName;

    @BindView(R.id.tvOperatorMobile)
    public CustomTextView tvOperatorMobile;

    @BindView(R.id.tvTotalSale)
    public CustomTextView tvTotalSale;

    @BindView(R.id.lytFuelPrices)
    public LinearLayout lytFuelPrices;


    public TotalSaleOperatorVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
