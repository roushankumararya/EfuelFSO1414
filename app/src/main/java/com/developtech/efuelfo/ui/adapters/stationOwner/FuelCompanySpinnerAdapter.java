package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.responseModel.FuelCompanyRespnseModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dt on 2/21/18.
 */

public class FuelCompanySpinnerAdapter extends BaseAdapter {

    List<FuelCompanyRespnseModel> fuelCompaniesList;
    AppComponent appComponent;

    public FuelCompanySpinnerAdapter(List<FuelCompanyRespnseModel> fuelCompaniesList, AppComponent appComponent)
    {
        this.fuelCompaniesList = fuelCompaniesList;
        this.appComponent = appComponent;
    }

    @Override
    public int getCount() {
        return fuelCompaniesList.size();
    }

    @Override
    public Object getItem(int i) {
        return fuelCompaniesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemView = LayoutInflater.from(appComponent.getContext()).inflate(R.layout.item_fuel_company_spinner,viewGroup, false);

        ImageView ivFuelCompany = itemView.findViewById(R.id.ivFuelCompany);
        TextView tvFuelCompany = itemView.findViewById(R.id.tvFuelCompany);

        FuelCompanyRespnseModel model = fuelCompaniesList.get(i);

        if(model.getImage()!=null && !model.getImage().isEmpty())
        {
            Picasso.with(appComponent.getContext())
                    .load(appComponent.getUtilFunctions().getImageFullUrl(model.getImage()))
                    .into(ivFuelCompany);
        }

        tvFuelCompany.setText(model.getName());

        return itemView;
    }

    public void updateList(List<FuelCompanyRespnseModel> fuelCompaniesList)
    {
        this.fuelCompaniesList = fuelCompaniesList;
        notifyDataSetChanged();
    }
}
