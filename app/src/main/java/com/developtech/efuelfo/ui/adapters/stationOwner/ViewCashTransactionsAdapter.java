package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.ViewCashTransactionResponseModel;
import com.developtech.efuelfo.ui.viewHolder.ProgressVH;
import com.developtech.efuelfo.ui.viewHolder.SaleHistoryVH;

import java.util.List;

/**
 * Created by dt on 3/1/18.
 */

public class ViewCashTransactionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<ViewCashTransactionResponseModel> transactionsList;
    AppComponent appComponent;
    OnItemClickListener listener;

    int progress = 1;
    int item = 2;
    boolean isProgress;

    ViewCashTransactionResponseModel cashModel;

    public ViewCashTransactionsAdapter(List<ViewCashTransactionResponseModel> transactionsList, AppComponent appComponent, OnItemClickListener listener) {
        this.transactionsList = transactionsList;
        this.appComponent = appComponent;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == item)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online_transaction, parent, false);
            return new SaleHistoryVH(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
        return new ProgressVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {

        if (holder instanceof SaleHistoryVH)
        {
            SaleHistoryVH cashVH = (SaleHistoryVH) holder;
            ViewCashTransactionResponseModel model = transactionsList.get(position);
            cashVH.tvVehicleNumber.setText(model.getVehicleNumber());
            cashVH.tvAmount.setText("Amount- "+appComponent.getContext().getResources().getString(R.string.rupee_symbol)+model.getAmount());
            cashVH.tvDate.setText(appComponent.getUtilFunctions().toLocal(model.getCreatedAt()));
            cashVH.tvName.setText(model.getMobileNumber());

            cashVH.cbCheck.setVisibility(View.GONE);
            cashVH.ellipeseStatus.setVisibility(View.GONE);

            cashVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });

        }
        else if (holder instanceof ProgressVH)
        {
            ProgressVH progressVH = (ProgressVH) holder;
            progressVH.bindData(isProgress);
        }


    }


    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    public void updateList(List<ViewCashTransactionResponseModel> transactionsList, boolean fromFilter)
    {
        this.transactionsList = transactionsList;
        if (!fromFilter) {
            if (cashModel != null) {
                transactionsList.remove(cashModel);
            }
            isProgress = true;
            cashModel = new ViewCashTransactionResponseModel();
            cashModel.setPagination("progress");
            transactionsList.add(cashModel);
        }
        notifyDataSetChanged();
    }

    public void addProgress()
    {
        isProgress = true;
        notifyDataSetChanged();
    }

    public void removeProgress()
    {
        isProgress = false;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        String pagination = transactionsList.get(position).getPagination();
        if (pagination!=null && pagination.equals("progress"))
        {
            return progress;
        }

        return item;

    }


    public void clearList()
    {
        this.transactionsList.clear();
        notifyDataSetChanged();
    }
}
