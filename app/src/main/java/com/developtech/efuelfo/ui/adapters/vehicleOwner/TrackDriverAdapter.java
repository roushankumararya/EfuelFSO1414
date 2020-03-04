package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.ui.viewHolder.TrackDriverVH;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dt on 12/29/17.
 */

public class TrackDriverAdapter extends RecyclerView.Adapter<TrackDriverVH> {

    private Context context;
    private List<GetDriverResponseModel> driverResponseModels;
    VehicleOwnerItemClick listener;
    AppComponent appComponent;

    public TrackDriverAdapter(Context context, AppComponent appComponent, List<GetDriverResponseModel> driverResponseModels, VehicleOwnerItemClick listener) {
        this.context = context;
        this.driverResponseModels = driverResponseModels;
        this.listener = listener;
        this.appComponent = appComponent;
    }

    public void refreshData(List<GetDriverResponseModel> driverResponseModels) {
        this.driverResponseModels = driverResponseModels;
        notifyDataSetChanged();
    }

    @Override
    public TrackDriverVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_track_driver, parent, false);
        return new TrackDriverVH(view);
    }

    @Override
    public void onBindViewHolder(TrackDriverVH holder, final int position) {
        GetDriverResponseModel model = driverResponseModels.get(position);
        holder.tvName.setText(model.getFirstName() + " " + model.getLastName());
        holder.tvPhoneNumber.setText(model.getMobileNumber());
        Picasso.with(context).load(appComponent.getUtilFunctions().getImageFullUrl(model.getImage())).placeholder(R.drawable.place_holder).into(holder.ivImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCLick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return driverResponseModels.size();
    }
}
