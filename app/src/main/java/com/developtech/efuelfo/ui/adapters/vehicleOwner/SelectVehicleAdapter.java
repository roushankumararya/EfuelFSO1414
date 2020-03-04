package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.ui.viewHolder.SelectNameVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dt on 2/12/18.
 */

public class SelectVehicleAdapter extends RecyclerView.Adapter<SelectNameVH> {

    List<AllVehicleResponseModel> vehicleList;

    RadioButton radioButton;
    int pos;

    public SelectVehicleAdapter(List<AllVehicleResponseModel> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public SelectNameVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_name, parent, false);
        return new SelectNameVH(itemView);
    }

    @Override
    public void onBindViewHolder(final SelectNameVH holder, final int position) {

        if(vehicleList.get(position)!=null && vehicleList.get(position).getVehicleNumber()!=null)
        {
            holder.rbName.setText(vehicleList.get(position).getVehicleNumber());
        }

        holder.rbName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(radioButton!=null)
                {
                    radioButton.setChecked(false);
                }
                radioButton = holder.rbName;
                vehicleList.get(pos).setSelected(false);
                pos = position;
                vehicleList.get(pos).setSelected(true);

            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public List<AllVehicleResponseModel> getSelectedList()
    {
        List<AllVehicleResponseModel> selectedlist = new ArrayList<>();
        for (AllVehicleResponseModel model : vehicleList)
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
        for (AllVehicleResponseModel model : vehicleList)
        {
            model.setSelected(false);
        }

        notifyDataSetChanged();
    }
}
