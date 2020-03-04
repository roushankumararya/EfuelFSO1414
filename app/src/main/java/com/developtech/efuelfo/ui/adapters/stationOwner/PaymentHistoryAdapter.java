package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.ui.viewHolder.TransactionVH;

import java.util.List;

/**
 * Created by dt on 1/3/18.
 */

public class PaymentHistoryAdapter extends RecyclerView.Adapter<TransactionVH> {

    OnItemClickListener listener;
    private List<PaymentHistoryResponseModel> modelList;
    private AppComponent appComponent;

    public PaymentHistoryAdapter(List<PaymentHistoryResponseModel> modelList, AppComponent appComponent, OnItemClickListener listener) {
        this.modelList = modelList;
        this.appComponent = appComponent;
        this.listener = listener;
    }

    public void refreshData(List<PaymentHistoryResponseModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public TransactionVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online_transaction, parent, false);
        return new TransactionVH(view);
    }

    @Override
    public void onBindViewHolder(final TransactionVH holder, final int position) {
        PaymentHistoryResponseModel model = modelList.get(position);
        holder.tvName.setText(appComponent.getSpUtils().getName());
        if (model.getVehicle()!=null && model.getVehicle().getVehicleNumber()!=null) {
            holder.tvVehicleNumber.setText(model.getVehicle().getVehicleNumber());
        }
        holder.tvDate.setText(appComponent.getUtilFunctions().getParsedDateOnly(model.getCreatedAt()));
        holder.tvStatus.setText(model.getStatus());
        holder.tvAmt.setText("Amount- "+appComponent.getContext().getResources().getString(R.string.rupee_symbol)+model.getAmount());

        if (model.getVehicle() != null)
            holder.tvVehicleNumber.setText(model.getVehicle().getVehicleNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
