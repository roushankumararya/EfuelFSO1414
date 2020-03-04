package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.ui.viewHolder.AddDriverVH;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dt on 12/29/17.
 */

public class AddDriverAdapter extends RecyclerView.Adapter<AddDriverVH> {

    OnItemClickListener listener;
    AppComponent appComponent;
    private Context context;
    private List<GetDriverResponseModel> driverResponseModels;

    public AddDriverAdapter(Context context, List<GetDriverResponseModel> driverResponseModels, OnItemClickListener listener, AppComponent appComponent) {
        this.context = context;
        this.driverResponseModels = driverResponseModels;
        this.listener = listener;
        this.appComponent = appComponent;
    }

    public void refreshData(List<GetDriverResponseModel> driverResponseModels) {
        this.driverResponseModels = driverResponseModels;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.driverResponseModels.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public AddDriverVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_driver, parent, false);
        return new AddDriverVH(view);
    }

    @Override
    public void onBindViewHolder(AddDriverVH holder, final int position) {
        GetDriverResponseModel model = driverResponseModels.get(position);
        holder.tvName.setText(model.getFirstName() + " " + model.getLastName());
        holder.tvPhoneNumber.setText(model.getMobileNumber());

        if (model.getImage() != null && !model.getImage().isEmpty()) {
            Picasso.with(context)
                    .load(appComponent.getAllUrls().BASE_IMAGE_URL + model.getImage())
                    .placeholder(R.drawable.place_holder).into(holder.ivPic);
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
        return driverResponseModels.size();
    }
}
