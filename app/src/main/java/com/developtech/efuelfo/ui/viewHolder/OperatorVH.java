package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by developtech on 1/9/18.
 */

public class OperatorVH extends RecyclerView.ViewHolder {

    @BindView(R.id.ivPic)
    public ImageView ivPic;

    @BindView(R.id.tvPhoneNumber)
    public CustomTextView tvPhoneNumber;

    @BindView(R.id.tvName)
    public CustomTextView tvName;

    @BindView(R.id.lytManager)
    public LinearLayout lytManager;
    @BindView(R.id.viewIsBlocked)
    public View viewIsBlocked;

    public OperatorVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
