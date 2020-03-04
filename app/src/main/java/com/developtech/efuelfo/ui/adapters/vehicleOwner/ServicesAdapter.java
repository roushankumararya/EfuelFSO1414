package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.GetServiceResponseModel;
import com.developtech.efuelfo.ui.viewHolder.ExpenseVH;

import java.util.List;

/**
 * Created by dt on 2/5/18.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ExpenseVH> {

    private Context context;
    private VehicleOwnerItemClick vehicleOwnerItemClick;
    private List<GetServiceResponseModel> servicesList;

    public ServicesAdapter(Context context, VehicleOwnerItemClick vehicleOwnerItemClick, List<GetServiceResponseModel> servicesList) {
        this.context = context;
        this.vehicleOwnerItemClick = vehicleOwnerItemClick;
        this.servicesList = servicesList;
    }

    @Override
    public ExpenseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        return new ExpenseVH(view);
    }

    @Override
    public void onBindViewHolder(ExpenseVH holder, final int position) {
        holder.bindView(servicesList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleOwnerItemClick.onCLick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public void refreshData(List<GetServiceResponseModel> modelList) {
        this.servicesList = modelList;
        notifyDataSetChanged();
    }

}
