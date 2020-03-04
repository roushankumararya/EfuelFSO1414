package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.internal.operators.OperatorSampleWithObservable;

/**
 * Created by dt on 2/20/18.
 */

public class FuelStationVH extends RecyclerView.ViewHolder {

    @BindView(R.id.ivStationLogo)
    public RoundedImageView ivStationLogo;

    @BindView(R.id.tvStationName)
    public TextView tvName;

    @BindView(R.id.tvStationAddress)
    public TextView tvAddress;

    @BindView(R.id.switchCreditAgreement)
    public Switch switchCreditAgreement;

    @BindView(R.id.item_fuel_station)
    public LinearLayout rootLayout;

    public FuelStationVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
