package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.SaleFuelTypeResponseModel;
import com.developtech.efuelfo.ui.viewHolder.TotalSaleFuelTypeVH;

import java.util.List;

/**
 * Created by dt on 1/9/18.
 */

public class TotalSaleFuelTypeAdapter extends RecyclerView.Adapter<TotalSaleFuelTypeVH> {

    OnItemClickListener listener;
    List<SaleFuelTypeResponseModel> saleFuelTypeList;
    AppComponent appComponent;

    public TotalSaleFuelTypeAdapter(List<SaleFuelTypeResponseModel> saleFuelTypeList,OnItemClickListener listener, AppComponent appComponent)
    {
        this.listener = listener;
        this.saleFuelTypeList = saleFuelTypeList;
        this.appComponent = appComponent;
    }

    @Override
    public TotalSaleFuelTypeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_total_sale_fuel_type, parent, false);
        return new TotalSaleFuelTypeVH(view);

    }

    @Override
    public void onBindViewHolder(TotalSaleFuelTypeVH holder, final int position) {

        SaleFuelTypeResponseModel model = saleFuelTypeList.get(position);

        holder.tvFuelType.setText(":  "+model.getFuelType());

        holder.tvTotalSale.setText(":  "+appComponent.getContext().getResources().getString(R.string.rupee_symbol)+" "+model.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  saleFuelTypeList.size();
    }

    public void updateList(List<SaleFuelTypeResponseModel> saleFuelTypeList)
    {
        this.saleFuelTypeList = saleFuelTypeList;
        notifyDataSetChanged();
    }
}
