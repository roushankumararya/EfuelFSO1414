package com.developtech.efuelfo.ui.adapters.operator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.ui.viewHolder.FuelPricesOperatorVH;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dt on 3/5/18.
 */

public class FuelPricesOperatorAdapter extends RecyclerView.Adapter<FuelPricesOperatorVH> {

    List<FuelDetailModel> fuelDetailList;
    AppComponent appComponent;

    public FuelPricesOperatorAdapter(List<FuelDetailModel> fuelDetailList, AppComponent appComponent) {
        this.fuelDetailList = fuelDetailList;
        this.appComponent = appComponent;
    }

    @Override
    public FuelPricesOperatorVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fuel_prices_operator, parent, false);
        return new FuelPricesOperatorVH(itemView);
    }

    @Override
    public void onBindViewHolder(FuelPricesOperatorVH holder, int position) {
        FuelDetailModel model = fuelDetailList.get(position);

        holder.tvFuelType.setText(model.getFuelType());
        holder.tvFuelPrice.setText(appComponent.getContext().getResources().getString(R.string.rupee_ltrs)+"          "+Float.parseFloat(model.getPrice()));

    }

    public void updateList(List<FuelDetailModel> fuelDetailList)
    {
        this.fuelDetailList = fuelDetailList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return fuelDetailList.size();
    }
}
