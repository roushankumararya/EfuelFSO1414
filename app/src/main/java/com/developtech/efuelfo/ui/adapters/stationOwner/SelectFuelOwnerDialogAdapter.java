package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.developtech.efuelfo.ui.viewHolder.SelectFuelStationVH;

import java.util.List;

/**
 * Created by dt on 1/3/18.
 */

public class SelectFuelOwnerDialogAdapter extends RecyclerView.Adapter<SelectFuelStationVH> {

    private VehicleOwnerItemClick vehicleOwnerItemClick;
    private Context context;
    private List<VehicleOwnerResponseModel> vehicleOwnerHomeModels;

    public SelectFuelOwnerDialogAdapter(Context context, VehicleOwnerItemClick vehicleOwnerItemClick, List<VehicleOwnerResponseModel> vehicleOwnerHomeModels) {
        this.context = context;
        this.vehicleOwnerItemClick = vehicleOwnerItemClick;
        this.vehicleOwnerHomeModels = vehicleOwnerHomeModels;
    }

    @Override
    public SelectFuelStationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_fuel_station, parent, false);
        return new SelectFuelStationVH(view);
    }

    @Override
    public void onBindViewHolder(SelectFuelStationVH holder, final int position) {
        VehicleOwnerResponseModel model = vehicleOwnerHomeModels.get(position);
        holder.tvName.setText(model.getFirstName() + " " + model.getLastName());
        if (model.getVehicle()!=null)
        {
            holder.tvAddress.setText(model.getVehicle().getVehicleNumber());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleOwnerItemClick.onCLick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (vehicleOwnerHomeModels != null)
            return vehicleOwnerHomeModels.size();
        return 0;
    }
}
