package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.ui.viewHolder.AddVehicleVH;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dt on 12/29/17.
 */

public class AddVehicleAdapter extends RecyclerView.Adapter<AddVehicleVH> {

    private Context context;
    private List<AllVehicleResponseModel> vehicleResponseModels;
    OnItemClickListener listener;
    AppComponent appComponent;

    public AddVehicleAdapter(Context context, List<AllVehicleResponseModel> vehicleResponseModels, OnItemClickListener listener, AppComponent appComponent) {
        this.context = context;
        this.vehicleResponseModels = vehicleResponseModels;
        this.listener = listener;
        this.appComponent = appComponent;
    }

    public void refreshList(List<AllVehicleResponseModel> vehicleResponseModels){
        this.vehicleResponseModels = vehicleResponseModels;
        notifyDataSetChanged();
    }

    public void removeItem(int position)
    {
        this.vehicleResponseModels.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public AddVehicleVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_vehicle, parent, false);
        return new AddVehicleVH(view);
    }

    @Override
    public void onBindViewHolder(AddVehicleVH holder, final int position) {
        AllVehicleResponseModel model = vehicleResponseModels.get(position);
        holder.tvFuelType.setText(model.getFuelType());
        holder.tvModelType.setText(model.getModel());
        holder.tvRegNo.setText(model.getVehicleNumber());
        holder.tvVehicleName.setText(model.getAlias());
        holder.tvFuelType.setText(model.getFuelType());
        holder.tvVehicleColor.setText(model.getColor());


        if (model.getImage()!=null && !model.getImage().isEmpty())
        {
            Picasso.with(context)
                    .load(appComponent.getAllUrls().BASE_IMAGE_URL + model.getImage())
                    .placeholder(R.drawable.place_holder_car).into(holder.ivImg);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return vehicleResponseModels.size();
    }
}
