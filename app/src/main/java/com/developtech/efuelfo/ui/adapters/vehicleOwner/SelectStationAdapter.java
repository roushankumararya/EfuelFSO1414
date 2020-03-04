package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.ui.viewHolder.SelectNameVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dt on 2/14/18.
 */

public class SelectStationAdapter extends RecyclerView.Adapter<SelectNameVH> {

    List<FuelStationResponseModel> stationList;

    RadioButton radioButton;

    int pos;
    public SelectStationAdapter(List<FuelStationResponseModel> stationList) {
        this.stationList = stationList;
    }

    @Override
    public SelectNameVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_name, parent, false);
        return new SelectNameVH(itemView);
    }

    @Override
    public void onBindViewHolder(final SelectNameVH holder,final int position) {
        if(stationList.get(position)!=null && stationList.get(position).getName()!=null)
        {
            holder.rbName.setText(stationList.get(position).getName());
        }

        holder.rbName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(radioButton!=null)
                {
                    radioButton.setChecked(false);
                }
                radioButton = holder.rbName;
                stationList.get(pos).setSelected(false);
                pos = position;
                stationList.get(pos).setSelected(true);

            }
        });
    }


    public List<FuelStationResponseModel> getSelectedList()
    {
        List<FuelStationResponseModel> selectedlist = new ArrayList<>();
        for (FuelStationResponseModel model : stationList)
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
        for (FuelStationResponseModel model : stationList)
        {
            model.setSelected(false);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }
}
