package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.DriverResponseModel;
import com.developtech.efuelfo.ui.viewHolder.SelectNameVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dt on 2/13/18.
 */

public class SelectDriverAdapter extends RecyclerView.Adapter<SelectNameVH> {

    List<DriverResponseModel> driversList;

    RadioButton radioButton;

    int pos;

    public SelectDriverAdapter(List<DriverResponseModel> driversList) {
        this.driversList = driversList;
    }

    @Override
    public SelectNameVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_name, parent, false);
        return new SelectNameVH(itemView);
    }

    @Override
    public void onBindViewHolder(final SelectNameVH holder,final int position) {
        if(driversList.get(position)!=null && driversList.get(position).getFirstName()!=null)
        {
            holder.rbName.setText(driversList.get(position).getFirstName()+" "+driversList.get(position).getLastName());
        }

        holder.rbName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(radioButton!=null)
                {
                    radioButton.setChecked(false);
                }
                radioButton = holder.rbName;
                driversList.get(pos).setSelected(false);
                pos = position;
                driversList.get(pos).setSelected(true);

            }
        });
    }

    @Override
    public int getItemCount() {
        return driversList.size();
    }

    public List<DriverResponseModel> getSelectedList()
    {
        List<DriverResponseModel> selectedlist = new ArrayList<>();
        for (DriverResponseModel model : driversList)
        {
            if(model.isSelected())
            {
                selectedlist.add(model);
            }
        }

        return selectedlist;
    }

    public void resetSelections()
    {
        for (DriverResponseModel model : driversList)
        {
            model.setSelected(false);
        }

        notifyDataSetChanged();
    }
}
