package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.responseModel.VerifyVehicleModel;
import com.developtech.efuelfo.ui.viewHolder.ProgressVH;
import com.developtech.efuelfo.ui.viewHolder.VerifyTransactionVH;

import java.util.List;

/**
 * Created by dt on 1/3/18.
 */

public class VerifyTransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    VehicleOwnerItemClick vehicleOwnerItemClick;
    AppComponent appComponent;
    List<VerifyVehicleModel> verifyModelsList;
    boolean isProgress;
    int progress = 1;
    int item = 2;
    VerifyVehicleModel verifyModel;

    public VerifyTransactionAdapter(VehicleOwnerItemClick vehicleOwnerItemClick,List<VerifyVehicleModel> verifyModelsList, AppComponent appComponent) {
        this.appComponent = appComponent;
        this.vehicleOwnerItemClick = vehicleOwnerItemClick;
        this.verifyModelsList = verifyModelsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == item)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_verify_transaction, parent, false);
            return new VerifyTransactionVH(view);
        }


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
        return new ProgressVH(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if (holder instanceof VerifyTransactionVH)
        {
            VerifyTransactionVH transactionVH = (VerifyTransactionVH) holder;


            VerifyVehicleModel model = verifyModelsList.get(position);
            transactionVH.tvFuelType.setText(model.getFuelType());
            transactionVH.tvTotalAmount.setText(appComponent.getContext().getResources().getString(R.string.rupee_symbol)+" "+model.getAmount());
            transactionVH.tvFuelQty.setText(model.getQuantity()+" "+appComponent.getContext().getResources().getString(R.string.ltrs_notation));
            transactionVH.tvTimeDate.setText(appComponent.getUtilFunctions().toLocal(model.getCreatedAt()));
            transactionVH.tvPaymentStatus.setText(model.getStatus());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vehicleOwnerItemClick.onCLick(position);
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
        return verifyModelsList.size();
    }

    public void clearList()
    {
        verifyModelsList.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<VerifyVehicleModel> verifyModelsList)
    {
        this.verifyModelsList = verifyModelsList;
        if (verifyModel!=null)
            verifyModelsList.remove(verifyModel);
        isProgress = true;
        verifyModel = new VerifyVehicleModel();
        verifyModel.setPagination("progress");
        verifyModelsList.add(verifyModel);
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
        String pagination = verifyModelsList.get(position).getPagination();
        if (pagination!=null && pagination.equals("progress"))
        {
            return progress;
        }

        return item;

    }
}
