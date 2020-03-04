package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.OnlineTransactionsResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.developtech.efuelfo.ui.viewHolder.ProgressVH;
import com.developtech.efuelfo.ui.viewHolder.TransactionVH;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dt on 3/1/18.
 */

public class OnlineTransAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    AppComponent appComponent;
    List<OnlineTransactionsResponseModel> onlineTransList;
    OnItemClickListener listener;

    int progress = 1;
    int item = 2;
    boolean isProgress;
    OnlineTransactionsResponseModel transResponseModel;

    public OnlineTransAdapter(AppComponent appComponent, List<OnlineTransactionsResponseModel> onlineTransList, OnItemClickListener listener) {
        this.appComponent = appComponent;
        this.onlineTransList = onlineTransList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == item) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online_transaction, parent, false);
            return new TransactionVH(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
        return new ProgressVH(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final OnlineTransactionsResponseModel model = onlineTransList.get(position);

        if (holder instanceof TransactionVH) {
            TransactionVH transactionVH = (TransactionVH) holder;
            transactionVH.tvName.setText(model.getVehicleOwner().getFirstName() + " " + model.getVehicleOwner().getLastName());
            if (model.getVehicle() != null && model.getVehicle().getVehicleNumber() != null) {
                transactionVH.tvVehicleNumber.setText(model.getVehicle().getVehicleNumber());
            }


            transactionVH.tvDate.setText(appComponent.getUtilFunctions().getParsedDateOnly(model.getCreatedAt()));
            transactionVH.tvStatus.setText(model.getStatus());
            transactionVH.tvAmt.setText("Amount- " + appComponent.getContext().getResources().getString(R.string.rupee_symbol) + model.getAmount());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, holder.getAdapterPosition());
                }
            });
            if (model.getInvoiceStatus() != null && model.getInvoiceStatus().equalsIgnoreCase("DONE")) {
                // transactionVH.cbCheck.setChecked(model.isChecked());
                //transactionVH.cbCheck.setClickable(false);
                //  transactionVH.cbCheck.setEnabled(false);
                transactionVH.imgReceived.setVisibility(View.VISIBLE);
            } else {
                transactionVH.imgReceived.setVisibility(View.GONE);
                // transactionVH.cbCheck.setChecked(false);
                // transactionVH.cbCheck.setClickable(true);
                // transactionVH.cbCheck.setEnabled(true);
            }
            transactionVH.cbCheck.setChecked(model.isChecked());

            transactionVH.cbCheck.setOnCheckedChangeListener(null);

            transactionVH.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    model.setChecked(b);
                }
            });

        } else if (holder instanceof ProgressVH) {
            ProgressVH progressVH = (ProgressVH) holder;
            progressVH.bindData(isProgress);
        }
    }


    @Override
    public int getItemCount() {
        return onlineTransList.size();
    }

    public void updateList(List<OnlineTransactionsResponseModel> onlineTransList) {
        this.onlineTransList = onlineTransList;
        if (transResponseModel != null)
            onlineTransList.remove(transResponseModel);
        isProgress = true;
        transResponseModel = new OnlineTransactionsResponseModel();
        transResponseModel.setPagination("progress");
        onlineTransList.add(transResponseModel);
        notifyDataSetChanged();
    }

    public boolean isAlreadyReceived() {
        for (int i = 0; i < onlineTransList.size(); i++) {
            if (onlineTransList.get(i).isChecked() && onlineTransList.get(i).getInvoiceStatus().equalsIgnoreCase("DONE")) {
                return true;
            }
        }
        return false;
    }

    public List<String> getCheckedList() {
        List<String> checkedList = new ArrayList<>();
        for (int i = 0; i < onlineTransList.size(); i++) {
            if (onlineTransList.get(i).isChecked()) {
                checkedList.add(onlineTransList.get(i).getId());
            }
        }
        return checkedList;
    }

    public void addProgress() {
        isProgress = true;
        notifyDataSetChanged();
    }

    public void removeProgress() {
        isProgress = false;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        String pagination = onlineTransList.get(position).getPagination();
        if (pagination != null && pagination.equals("progress")) {
            return progress;
        }

        return item;

    }
}
