package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 2/12/18.
 */

public class SelectNameVH extends RecyclerView.ViewHolder {

    @BindView(R.id.rbName)
    public RadioButton rbName;

    public SelectNameVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
