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
 * Created by dt on 2/24/18.
 */

public class SelectFuelStationDialogAdapter extends RecyclerView.Adapter<SelectFuelStationVH> {

    List<GetFuelStationResponseModel> allFuelStationsList;

    private VehicleOwnerItemClick listener;

    public SelectFuelStationDialogAdapter(List<GetFuelStationResponseModel> allFuelStationsList, VehicleOwnerItemClick listener) {
        this.allFuelStationsList = allFuelStationsList;
        this.listener = listener;
    }

    @Override
    public SelectFuelStationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_fuel_station, parent, false);
        return new SelectFuelStationVH(view);
    }

    @Override
    public void onBindViewHolder(SelectFuelStationVH holder, final int position) {
        holder.tvName.setText(allFuelStationsList.get(position).getName());
        holder.tvName.setText(allFuelStationsList.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCLick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allFuelStationsList.size();
    }
}
