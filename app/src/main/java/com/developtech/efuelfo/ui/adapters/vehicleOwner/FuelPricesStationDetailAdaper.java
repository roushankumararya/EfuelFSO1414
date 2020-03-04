package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.ui.viewHolder.FuelPricesStationDetailVH;

import java.util.List;

/**
 * Created by dt on 2/14/18.
 */

public class FuelPricesStationDetailAdaper extends RecyclerView.Adapter<FuelPricesStationDetailVH> {

    List<FuelDetailModel> fuelDetailList;
    Context context;

    public FuelPricesStationDetailAdaper(List<FuelDetailModel> fuelDetailList, Context context) {
        this.fuelDetailList = fuelDetailList;
        this.context = context;
    }

    @Override
    public FuelPricesStationDetailVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fuel_prices_station_detail, parent, false);
        return new FuelPricesStationDetailVH(itemView);
    }

    @Override
    public void onBindViewHolder(FuelPricesStationDetailVH holder, int position) {
        holder.tvFuelType.setText(fuelDetailList.get(position).getFuelType());
        holder.tvFuelPrice.setText(context.getResources().getString(R.string.rupee_symbol)+fuelDetailList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return fuelDetailList.size();
    }
}
