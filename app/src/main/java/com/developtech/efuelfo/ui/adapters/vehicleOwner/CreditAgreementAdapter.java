package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.ui.viewHolder.CreditAgreementVH;
import com.developtech.efuelfo.ui.viewHolder.ProgressVH;

import java.util.List;

/**
 * Created by dt on 12/29/17.
 */

public class CreditAgreementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    OnItemClickListener listener;
    private List<CreditAgreementResponseModel> modelList;
    private AppComponent appComponent;
    boolean fromHistory;

    int progress = 1;
    int item = 2;
    boolean isProgress;
    CreditAgreementResponseModel creditModel;

    int page = 0, count = 10;

    LinearLayoutManager layoutManager;

    public CreditAgreementAdapter(OnItemClickListener listener, Context context, List<CreditAgreementResponseModel> modelList, AppComponent appComponent, boolean fromHistory) {
        this.context = context;
        this.listener = listener;
        this.modelList = modelList;
        this.appComponent = appComponent;
        this.fromHistory = fromHistory;
    }

    public void refreshData(List<CreditAgreementResponseModel> modelList) {
        this.modelList = modelList;
        if (creditModel!=null)
            modelList.remove(creditModel);
        isProgress = true;
        creditModel = new CreditAgreementResponseModel();
        creditModel.setPagination("progress");
        modelList.add(creditModel);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        if (viewType == item)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_pending_credit, parent, false);
            return new CreditAgreementVH(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
        return new ProgressVH(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        if (holder instanceof CreditAgreementVH)
        {
            CreditAgreementVH agreementVH = (CreditAgreementVH) holder;

            CreditAgreementResponseModel model = modelList.get(position);
            if (model.getPagination()==null || !model.getPagination().equals("progress")) {
                agreementVH.tvOwnerName.setText(model.getVehicleOwner().getFirstName() + " " + model.getVehicleOwner().getLastName());
                agreementVH.tvOwnerNumber.setText("Phone - " + model.getVehicleOwner().getMobileNumber());
                if (fromHistory && model.getRemainingCredits()!=model.getCreditLimit()) {
                    agreementVH.tvBalance.setText("Amount - " + appComponent.getContext().getResources().getString(R.string.rupee_symbol) + " " +model.getRemainingCredits()+"/"+model.getCreditLimit());
                    agreementVH.tvStatus.setVisibility(View.VISIBLE);
                    agreementVH.viewEllipseStatus.setVisibility(View.VISIBLE);
                    agreementVH.tvStatus.setText(model.getStatus());
                }
                else
                {
                    agreementVH.tvBalance.setText("Amount - " + appComponent.getContext().getResources().getString(R.string.rupee_symbol) + " " + model.getCreditLimit());
                }
                agreementVH.tvDuration.setText("Duration - " + model.getDuration() + " Days");
                agreementVH.tvDate.setText(appComponent.getUtilFunctions().toLocal(model.getCreatedAt()));

                agreementVH.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(view, position);
                    }
                });
            }
        }
        else if (holder instanceof ProgressVH)
        {
            ProgressVH progressVH = (ProgressVH) holder;
            progressVH.bindData(isProgress);
        }
    }


    @Override
    public int getItemCount() {
        return modelList.size();
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
        String pagination = modelList.get(position).getPagination();
        if (pagination!=null && pagination.equals("progress"))
        {
            return progress;
        }

        return item;
    }
}
