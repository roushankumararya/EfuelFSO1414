package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.ui.viewHolder.FuelStationVH;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dt on 2/20/18.
 */

public class FuelStationAdapter extends RecyclerView.Adapter<FuelStationVH> {

    List<GetFuelStationResponseModel> fuelStationsList = new ArrayList<>();
    AppComponent appComponent;
    OnItemClickListener listener;

    public FuelStationAdapter(List<GetFuelStationResponseModel> fuelStationsList, AppComponent appComponent, OnItemClickListener listener) {
        this.fuelStationsList = fuelStationsList;
        this.appComponent = appComponent;
        this.listener = listener;
    }

    @Override
    public FuelStationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fuel_station, parent, false);
        return new FuelStationVH(itemView);
    }

    @Override
    public void onBindViewHolder(final FuelStationVH holder, final int position) {
        GetFuelStationResponseModel model = fuelStationsList.get(position);
        holder.tvName.setText(model.getName());
        holder.tvAddress.setText(model.getAddress());

        if (model.getImage()!=null && !model.getImage().isEmpty())
        {
            Picasso.with(appComponent.getContext())
                    .load(appComponent.getUtilFunctions().getImageFullUrl(model.getImage()))
                    .placeholder(R.drawable.fuel_info)
                    .into(holder.ivStationLogo);
        }

        holder.switchCreditAgreement.setOnCheckedChangeListener(null);

        if (model.isCreditAgreement())
            holder.switchCreditAgreement.setChecked(true);
        else
            holder.switchCreditAgreement.setChecked(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.rootLayout, position);
            }
        });
        holder.switchCreditAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onItemClick(holder.switchCreditAgreement, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fuelStationsList.size();
    }

    public void updateList(List<GetFuelStationResponseModel> fuelStationsList)
    {
        this.fuelStationsList = fuelStationsList;
        notifyDataSetChanged();
    }
}
