package com.developtech.efuelfo.ui.viewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 12/29/17.
 */

public class TransactionVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    public TextView tvName;

    @BindView(R.id.tvVehicleNumber)
    public TextView tvVehicleNumber;

    @BindView(R.id.tvStatus)
    public TextView tvStatus;

    @BindView(R.id.tvDate)
    public TextView tvDate;

    @BindView(R.id.tvAmt)
    public TextView tvAmt;

    @BindView(R.id.cbCheck)
    public CheckBox cbCheck;
    @BindView(R.id.imgReceived)
    public ImageView imgReceived;

    public TransactionVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
