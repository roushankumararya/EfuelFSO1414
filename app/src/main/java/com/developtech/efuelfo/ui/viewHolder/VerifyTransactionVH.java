package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

/**
 * Created by dt on 1/3/18.
 */

public class VerifyTransactionVH extends RecyclerView.ViewHolder {

    public TextView tvFuelType, tvFuelQty, tvTotalAmount, tvTimeDate, tvPaymentStatus;

    public VerifyTransactionVH(View itemView) {
        super(itemView);
        tvFuelQty = itemView.findViewById(R.id.tvFuelQty);
        tvPaymentStatus = itemView.findViewById(R.id.tvPaymentStatus);
        tvTimeDate = itemView.findViewById(R.id.tvTimeDate);
        tvTotalAmount = itemView.findViewById(R.id.tvAmount);
        tvFuelType = itemView.findViewById(R.id.tvFuelType);
    }
}
