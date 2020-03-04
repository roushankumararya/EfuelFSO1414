package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.responseModel.GetExpensesResponseModel;
import com.developtech.efuelfo.ui.viewHolder.ExpenseVH;

import java.util.List;

/**
 * Created by dt on 12/29/17.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseVH> {

    AppComponent appComponent;
    private Context context;
    private VehicleOwnerItemClick vehicleOwnerItemClick;
    private List<GetExpensesResponseModel> modelList;

    public ExpenseAdapter(Context context, AppComponent appComponent, VehicleOwnerItemClick vehicleOwnerItemClick, List<GetExpensesResponseModel> modelList) {
        this.context = context;
        this.appComponent = appComponent;
        this.vehicleOwnerItemClick = vehicleOwnerItemClick;
        this.modelList = modelList;
    }

    @Override
    public ExpenseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        return new ExpenseVH(view);
    }

    public void refreshData(List<GetExpensesResponseModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ExpenseVH holder, final int position) {
        GetExpensesResponseModel model = modelList.get(position);

        holder.bindData(model);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleOwnerItemClick.onCLick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
