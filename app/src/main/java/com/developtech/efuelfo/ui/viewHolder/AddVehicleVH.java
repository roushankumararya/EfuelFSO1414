package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;

/**
 * Created by dt on 12/29/17.
 */

public class AddVehicleVH extends RecyclerView.ViewHolder {
    public TextView tvModelType, tvRegNo, tvFuelType, tvVehicleColor, tvVehicleName, tvSubscription;
    public ImageView ivImg;

    public AddVehicleVH(View itemView) {
        super(itemView);
        ivImg = itemView.findViewById(R.id.ivImg);
        tvModelType = itemView.findViewById(R.id.tvModelType);
        tvRegNo = itemView.findViewById(R.id.tvRegNo);
        tvFuelType = itemView.findViewById(R.id.tvFuelType);
        tvVehicleColor = itemView.findViewById(R.id.tvVehicleColor);
        tvVehicleName = itemView.findViewById(R.id.tvVehicleName);
        tvSubscription = itemView.findViewById(R.id.tvSubscription);
    }
}
