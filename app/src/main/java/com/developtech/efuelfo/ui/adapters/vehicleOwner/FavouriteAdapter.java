package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.ui.viewHolder.FavouriteVH;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dt on 12/29/17.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteVH> {

    private Context context;
    private List<FuelStationResponseModel> responseModelList;
    private VehicleOwnerItemClick itemClick;
    private AppComponent appComponent;

    public FavouriteAdapter(Context context, List<FuelStationResponseModel> responseModelList, VehicleOwnerItemClick itemClick, AppComponent appComponent) {
        this.context = context;
        this.responseModelList = responseModelList;
        this.itemClick = itemClick;
        this.appComponent = appComponent;
    }

    public void refreshData(List<FuelStationResponseModel> responseModelList) {
        this.responseModelList = responseModelList;
        notifyDataSetChanged();
    }

    @Override
    public FavouriteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favourite, parent, false);
        return new FavouriteVH(view);
    }

    @Override
    public void onBindViewHolder(FavouriteVH holder, final int position) {
        FuelStationResponseModel model = responseModelList.get(position);

        if(model.getImage()!=null && !model.getImage().isEmpty())
        {
            Picasso.with(context).load(appComponent.getAllUrls().BASE_IMAGE_URL + model.getImage()).placeholder(R.drawable.place_holder).into(holder.ivLogo);
        }

        holder.tvPumpName.setText(model.getName());
        holder.tvDistance.setText(model.getDistance() + " KM");
        holder.tvPhoneNumber.setText(model.getMobileNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onCLick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return responseModelList.size();
    }
}
