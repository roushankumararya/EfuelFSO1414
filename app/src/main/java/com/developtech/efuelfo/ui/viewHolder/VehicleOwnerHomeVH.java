package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;

/**
 * Created by dt on 12/29/17.
 */

public class VehicleOwnerHomeVH extends RecyclerView.ViewHolder {
    public ImageView ivImg;
    public TextView tvTitle;

    public VehicleOwnerHomeVH(View itemView) {
        super(itemView);
        ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
    }
}
