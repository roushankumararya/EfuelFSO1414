package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.TankResponseModel;
import com.developtech.efuelfo.ui.viewHolder.TankTypeVH;

import java.util.List;

/**
 * Created by dt on 1/3/18.
 */

public class TankTypeAdapter extends RecyclerView.Adapter<TankTypeVH> {

    List<TankResponseModel> tanksList;
    OnItemClickListener listener;

    public TankTypeAdapter(List<TankResponseModel> tanksList, OnItemClickListener listener) {
        this.tanksList = tanksList;
        this.listener = listener;
    }

    @Override
    public TankTypeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tank_type, parent, false);
        return new TankTypeVH(view, new EditTextCFListener(), new EditTextOFListener());
    }


    @Override
    public void onBindViewHolder(TankTypeVH holder, final int position) {
        TankResponseModel model = tanksList.get(position);
        holder.tvTankName.setText(model.getTankName());
        holder.ivEditTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, position);
            }
        });

        holder.ofListener.updatePosition(holder.getAdapterPosition());
        holder.cfListener.updatePosition(holder.getAdapterPosition());

        if (tanksList.get(position).getOFQty()!=null && !tanksList.get(position).getOFQty().trim().isEmpty())
        {
            holder.etOFQty.setText(tanksList.get(position).getOFQty());
        }
        else
        {
            holder.etOFQty.setText("");
        }

        if (tanksList.get(holder.getAdapterPosition()).getCFQty()!=null && !tanksList.get(holder.getAdapterPosition()).getCFQty().trim().isEmpty())
        {
            holder.etCFQty.setText(tanksList.get(holder.getAdapterPosition()).getCFQty());
        }
        else
        {
            holder.etCFQty.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return tanksList.size();
    }

    public void updateList(List<TankResponseModel> tanksList)
    {
        this.tanksList = tanksList;
        notifyDataSetChanged();
    }

    public List<TankResponseModel> getTankTypeList()
    {
        return this.tanksList;
    }


    public class EditTextOFListener implements TextWatcher {
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
            tanksList.get(position).setOFQty(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    public class EditTextCFListener implements TextWatcher {
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
            tanksList.get(position).setCFQty(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
