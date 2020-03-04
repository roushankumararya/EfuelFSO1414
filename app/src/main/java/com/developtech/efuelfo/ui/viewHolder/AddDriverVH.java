package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 12/29/17.
 */

public class AddDriverVH extends RecyclerView.ViewHolder {
    @BindView(R.id.tvPhoneNumber)
    public TextView tvPhoneNumber;

    @BindView(R.id.tvManager)
    public TextView tvManager;

    @BindView(R.id.tvName)
    public TextView tvName;

    @BindView(R.id.ivPic)
    public ImageView ivPic;

    public AddDriverVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
