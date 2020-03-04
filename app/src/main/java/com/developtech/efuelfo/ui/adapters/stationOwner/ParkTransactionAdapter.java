package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.ParkTransactionResponseModel;
import com.developtech.efuelfo.ui.viewHolder.ParkTransactionVH;
import com.developtech.efuelfo.ui.viewHolder.ProgressVH;

import java.util.List;

/**
 * Created by dt on 1/3/18.
 */

public class ParkTransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<ParkTransactionResponseModel> parkTransactionsList;
    AppComponent appComponent;
    OnItemClickListener listener;

    int progress = 1;
    int item = 2;
    boolean isProgress;
    ParkTransactionResponseModel parkModel;

    public ParkTransactionAdapter(List<ParkTransactionResponseModel> parkTransactionsList, AppComponent appComponent, OnItemClickListener listener) {
        this.parkTransactionsList = parkTransactionsList;
        this.appComponent = appComponent;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == item)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_park_transaction, parent, false);
            return new ParkTransactionVH(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
        return new ProgressVH(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if (holder instanceof ParkTransactionVH)
        {
            ParkTransactionVH parkVH = (ParkTransactionVH) holder;

            ParkTransactionResponseModel model = parkTransactionsList.get(position);
            parkVH.tvParkTransNumber.setText(model.getVehicle().getVehicleNumber());
            parkVH.tvParkTransFuelType.setText(model.getFuelType()+" - "+appComponent.getContext().getResources().getString(R.string.rupee_symbol)+model.getAmount());
            String mobileWithStar ="";
            if(model.getVehicle().getDriver().getMobileNumber().length()>=10){
                mobileWithStar = "xxxxxx"+model.getVehicle().getDriver().getMobileNumber().substring(6);
            }
            parkVH.tvParkTransVehicleOwner.setText(model.getVehicle().getDriver().getFirstName()+" "+model.getVehicle().getDriver().getLastName()+" - "+mobileWithStar);

            parkVH.itemView.setOnClickListener(new View.OnClickListener() {
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
        return parkTransactionsList.size();
    }

    public void updateList(List<ParkTransactionResponseModel> parkTransactionsList)
    {
        this.parkTransactionsList = parkTransactionsList;
        if (parkModel!=null)
            parkTransactionsList.remove(parkModel);
        isProgress = true;
        parkModel = new ParkTransactionResponseModel();
        parkModel.setPagination("progress");
        parkTransactionsList.add(parkModel);
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
        String pagination = parkTransactionsList.get(position).getPagination();
        if (pagination!=null && pagination.equals("progress"))
        {
            return progress;
        }

        return item;

    }
}
