package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;

/**
 * Created by dt on 12/29/17.
 */

public class FavouriteVH extends RecyclerView.ViewHolder {
    public ImageView ivLogo;
    public TextView tvPumpName, tvDistance, tvPhoneNumber;
    public LinearLayout layBuy;

    public FavouriteVH(View itemView) {
        super(itemView);
        ivLogo = (ImageView) itemView.findViewById(R.id.ivLogo);
        tvDistance = (TextView) itemView.findViewById(R.id.tvDistance);
        tvPumpName = (TextView) itemView.findViewById(R.id.tvPumpName);
        tvPhoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
        layBuy = (LinearLayout) itemView.findViewById(R.id.layBuy);
    }
}
