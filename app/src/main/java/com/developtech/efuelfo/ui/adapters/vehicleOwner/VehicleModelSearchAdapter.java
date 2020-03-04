package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.ui.viewHolder.VehicleModelsVH;

import java.util.ArrayList;

public class VehicleModelSearchAdapter extends RecyclerView.Adapter<VehicleModelsVH> {

    ArrayList<String> modelsList;
    OnItemClickListener listener;

    public VehicleModelSearchAdapter(ArrayList<String> modelsList, OnItemClickListener listener) {
        this.modelsList = modelsList;
        this.listener = listener;
    }

    @Override
    public VehicleModelsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_models_search,parent, false);
        return new VehicleModelsVH(itemView);
    }

    @Override
    public void onBindViewHolder(VehicleModelsVH holder, final int position) {
        holder.tvModel.setText(modelsList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelsList.size();
    }

    public void updateList(ArrayList<String> modelsList)
    {
        this.modelsList = modelsList;
        notifyDataSetChanged();
    }
}
