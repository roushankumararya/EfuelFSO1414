package com.developtech.efuelfo.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.RefuelingModel;
import com.developtech.efuelfo.ui.viewHolder.RefuelingVH;

import java.util.List;

public class RefuelingAdapter extends RecyclerView.Adapter<RefuelingVH> {

    List<RefuelingModel> adapterList;
    Context context;

    public RefuelingAdapter(List<RefuelingModel> adapterList, Context context) {
        this.adapterList = adapterList;
        this.context = context;
    }

    @Override
    public RefuelingVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_refueling, parent, false);
        return new RefuelingVH(itemView);
    }

    @Override
    public void onBindViewHolder(RefuelingVH holder, int position) {

        RefuelingModel model = adapterList.get(position);

        holder.tvFuelType.setText(model.getFuelType());
        holder.tvCost.setText(context.getResources().getString(R.string.rupee_symbol)+" "+model.getTotalSum());
        holder.tvVolume.setText("Ltr "+model.getVolByFuelType());
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }
}
