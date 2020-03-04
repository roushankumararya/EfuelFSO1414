package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;

/**
 * Created by dt on 12/29/17.
 */

public class ParkTransactionVH extends RecyclerView.ViewHolder {
    public TextView tvParkTransFuelType, tvParkTransVehicleOwner, tvParkTransNumber;
    public ImageView ivNext;

    public ParkTransactionVH(View itemView) {
        super(itemView);
        tvParkTransFuelType = (TextView) itemView.findViewById(R.id.tvParkTransFuelType);
        tvParkTransVehicleOwner = (TextView) itemView.findViewById(R.id.tvParkTransVehicleOwner);
        tvParkTransNumber = (TextView) itemView.findViewById(R.id.tvParkTransNumber);
        ivNext = (ImageView) itemView.findViewById(R.id.ivNext);
    }
}
