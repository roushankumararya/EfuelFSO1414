package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 12/29/17.
 */

public class CreditAgreementVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tvOwnerName)
    public TextView tvOwnerName;

    @BindView(R.id.tvOwnerNumber)
    public TextView tvOwnerNumber;

    @BindView(R.id.tvDate)
    public TextView tvDate;

    @BindView(R.id.tvDuration)
    public TextView tvDuration;

    @BindView(R.id.tvBalance)
    public TextView tvBalance;

    @BindView(R.id.tvStatus)
    public TextView tvStatus;

    @BindView(R.id.viewEllipeseStatus)
    public View viewEllipseStatus;

    public CreditAgreementVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
