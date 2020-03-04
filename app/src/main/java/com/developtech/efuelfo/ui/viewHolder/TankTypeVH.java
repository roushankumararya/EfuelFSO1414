package com.developtech.efuelfo.ui.viewHolder;

import android.media.Image;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.ui.adapters.stationOwner.TankTypeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 1/3/18.
 */

public class TankTypeVH extends RecyclerView.ViewHolder{

    @BindView(R.id.tvTankName)
    public TextView tvTankName;

    @BindView(R.id.etOFQty)
    public EditText etOFQty;

    @BindView(R.id.etCFQty)
    public EditText etCFQty;

    @BindView(R.id.ivEditTankType)
    public ImageView ivEditTank;

    public TankTypeAdapter.EditTextCFListener cfListener;
    public TankTypeAdapter.EditTextOFListener ofListener;

    public TankTypeVH(View itemView, TankTypeAdapter.EditTextCFListener cfListener, TankTypeAdapter.EditTextOFListener ofListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.cfListener = cfListener;
        this.ofListener = ofListener;
        etCFQty.addTextChangedListener(this.cfListener);
        etOFQty.addTextChangedListener(this.ofListener);
    }
}
