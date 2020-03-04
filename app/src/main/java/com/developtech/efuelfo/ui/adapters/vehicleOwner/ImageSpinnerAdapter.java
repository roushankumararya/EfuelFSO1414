package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;

/**
 * Created by developtech on 1/24/18.
 */

public class ImageSpinnerAdapter extends BaseAdapter {
    Context context;
    int stationIdImages[];
    String[] stationIdList;
    LayoutInflater inflter;
    VehicleOwnerItemClick vehicleOwnerItemClick;

    public ImageSpinnerAdapter(Context applicationContext, int[] stationIdImages, String[] stationIdList, VehicleOwnerItemClick vehicleOwnerItemClick) {
        this.context = applicationContext;
        this.stationIdImages = stationIdImages;
        this.stationIdList = stationIdList;
        this.vehicleOwnerItemClick = vehicleOwnerItemClick;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return stationIdImages.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_refuel_spinner, null);
        ImageView icon = view.findViewById(R.id.ivItemIcon);
        TextView names = view.findViewById(R.id.tvItemName);
        icon.setImageResource(stationIdImages[i]);
        names.setText(stationIdList[i]);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                vehicleOwnerItemClick.onCLick(i);
//            }
//        });
        /*
        names.setVisibility(View.INVISIBLE);*/
        return view;
    }
}
