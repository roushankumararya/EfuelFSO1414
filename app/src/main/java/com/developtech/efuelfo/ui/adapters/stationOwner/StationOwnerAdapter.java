package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.ui.viewHolder.SelectFuelStationVH;

import java.util.List;

/**
 * Created by dt on 2/8/18.
 */

public class StationOwnerAdapter extends RecyclerView.Adapter<SelectFuelStationVH>{

    List<GetFuelStationResponseModel> fuelStationList;
    private VehicleOwnerItemClick listener;

    public StationOwnerAdapter(List<GetFuelStationResponseModel> fuelStationList, VehicleOwnerItemClick listener) {
        this.fuelStationList = fuelStationList;
        this.listener = listener;
    }

    @Override
    public SelectFuelStationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_fuel_station, parent, false);
        return new SelectFuelStationVH(view);
    }

    @Override
    public void onBindViewHolder(SelectFuelStationVH holder, final int position) {
        holder.tvName.setText(fuelStationList.get(position).getName());
        holder.tvAddress.setText(fuelStationList.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCLick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fuelStationList.size();
    }
}
