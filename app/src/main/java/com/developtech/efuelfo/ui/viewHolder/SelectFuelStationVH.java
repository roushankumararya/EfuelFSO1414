package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

/**
 * Created by dt on 1/3/18.
 */

public class SelectFuelStationVH extends RecyclerView.ViewHolder {

    public TextView tvName, tvAddress;

    public SelectFuelStationVH(View itemView) {
        super(itemView);
        tvAddress = itemView.findViewById(R.id.tvAddress);
        tvName = itemView.findViewById(R.id.tvName);
    }
}
