package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.ui.viewHolder.TotalSaleOperatorVH;

/**
 * Created by dt on 1/9/18.
 */

public class TotalSaleOperatorAdapter extends RecyclerView.Adapter<TotalSaleOperatorVH> {

    OnItemClickListener listener;
    Context context;

    public TotalSaleOperatorAdapter(OnItemClickListener listener, Context context)
    {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public TotalSaleOperatorVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_total_sale_operator, parent, false);
        return new TotalSaleOperatorVH(view);
    }

    @Override
    public void onBindViewHolder(TotalSaleOperatorVH holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, position);
            }
        });

        holder.lytFuelPrices.removeAllViews();

        for (int i = 0; i < 2 ; i++) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_fuel_price, null);

            CustomTextView tvFuelName = itemView.findViewById(R.id.tvFuelName);
            CustomTextView tvFuelPrice = itemView.findViewById(R.id.tvFuelPrice);

            holder.lytFuelPrices.addView(itemView);
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
