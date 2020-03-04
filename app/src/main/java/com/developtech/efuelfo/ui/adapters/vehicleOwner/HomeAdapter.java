package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.VehicleOwnerHomeModel;
import com.developtech.efuelfo.ui.viewHolder.VehicleOwnerHomeVH;

import java.util.List;

/**
 * Created by dt on 12/29/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<VehicleOwnerHomeVH> {

    private Context context;
    private List<VehicleOwnerHomeModel> ownerHomeList;
    private VehicleOwnerItemClick vehicleOwnerItemClick;

    public HomeAdapter(Context context, List<VehicleOwnerHomeModel> ownerHomeList, VehicleOwnerItemClick vehicleOwnerItemClick) {
        this.context = context;
        this.ownerHomeList = ownerHomeList;
        this.vehicleOwnerItemClick = vehicleOwnerItemClick;
    }


    @Override
    public VehicleOwnerHomeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_vehicl_owner, null);
        return new VehicleOwnerHomeVH(view);
    }

    @Override
    public void onBindViewHolder(VehicleOwnerHomeVH holder, final int position) {
        VehicleOwnerHomeModel ownerHome = ownerHomeList.get(position);

        holder.tvTitle.setText(ownerHome.getTitle());
        holder.ivImg.setImageResource(ownerHome.getDrawable());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleOwnerItemClick.onCLick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ownerHomeList.size();
    }
}
