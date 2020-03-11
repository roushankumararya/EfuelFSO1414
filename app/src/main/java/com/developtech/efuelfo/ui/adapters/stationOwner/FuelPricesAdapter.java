package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.ui.viewHolder.FuelPricesVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dt on 1/3/18.
 */

public class FuelPricesAdapter extends RecyclerView.Adapter<FuelPricesVH> {

    List<FuelDetailModel> fuelDetailList = new ArrayList<>();

    Context context;
    SpinnerAdapter4 spFuelTypeAdapter;
    AppComponent appComponent;
    List<String> fuelTypeList;
    boolean isDisabled;

    public FuelPricesAdapter(List<FuelDetailModel> fuelDetailList, Context context, AppComponent appComponent,
                             boolean isDisabled) {
        this.fuelDetailList = fuelDetailList;
        this.appComponent = appComponent;
        this.context = context;
        this.isDisabled = isDisabled;
        Log.e("hhh","succx:-"+ appComponent.getSpUtils());
      //  fuelTypeList = appComponent.getSpUtils().getFuelStationModel().getFuelCompany().getFuelType();
        spFuelTypeAdapter = new SpinnerAdapter4(context, R.layout.item_spinner_fuel_price, fuelTypeList);
    }

    @Override
    public FuelPricesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fuel_prices, parent, false);
        return new FuelPricesVH(view, new CustomEditTextListener(), isDisabled);
    }


    @Override
    public void onBindViewHolder(final FuelPricesVH holder, final int position) {
        holder.listener.updatePosition(holder.getAdapterPosition());
        holder.unregisterListener();
        holder.spinnerFuelType.setAdapter(spFuelTypeAdapter);
        if(fuelDetailList.get(position).getPrice()!=null && !fuelDetailList.get(position).getPrice().isEmpty())
        {
            holder.etPrice.setText(fuelDetailList.get(position).getPrice());
        }

        for (int i = 0; i < fuelTypeList.size(); i++) {
            if(fuelTypeList.get(i).equals(fuelDetailList.get(position).getFuelType()))
            {
                holder.spinnerFuelType.setSelection(i);
                break;
            }
        }



        holder.bindData(fuelDetailList.get(position));

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuelDetailList.remove(position);
                notifyDataSetChanged();
            }
        });


        holder.spinnerFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fuelDetailList.get(position).setFuelType(holder.spinnerFuelType.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.registerListener();
    }

    public void updateList(List<FuelDetailModel> fuelDetailList)
    {
        this.fuelDetailList = fuelDetailList;
        notifyDataSetChanged();
    }

    public List<FuelDetailModel> getList()
    {
        return fuelDetailList;
    }

    @Override
    public int getItemCount() {
        return fuelDetailList.size();
    }

    public class CustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position)
        {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            fuelDetailList.get(position).setPrice(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
