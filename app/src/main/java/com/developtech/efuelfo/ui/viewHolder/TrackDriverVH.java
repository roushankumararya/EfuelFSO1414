package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;

/**
 * Created by dt on 12/29/17.
 */

public class TrackDriverVH extends RecyclerView.ViewHolder {
    public TextView tvPhoneNumber, tvName;
    public ImageView ivImg;
    public FrameLayout frameLoc, frameArrow;

    public TrackDriverVH(View itemView) {
        super(itemView);
        ivImg = itemView.findViewById(R.id.ivImg);
        tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
        frameArrow = itemView.findViewById(R.id.frameArrow);
        frameLoc = itemView.findViewById(R.id.frameLoc);
        tvName = itemView.findViewById(R.id.tvName);
    }
}
