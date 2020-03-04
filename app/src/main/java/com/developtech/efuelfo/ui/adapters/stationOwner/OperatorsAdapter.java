package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.ui.viewHolder.OperatorVH;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by developtech on 1/9/18.
 */

public class OperatorsAdapter extends RecyclerView.Adapter<OperatorVH> {

    OnItemClickListener listener;
    List<OperatorsResponseModel> operatorsList;
    Context context;
    AppComponent appComponent;

    public OperatorsAdapter(Context context, OnItemClickListener listener, List<OperatorsResponseModel> operatorsList, AppComponent appComponent) {
        this.listener = listener;
        this.operatorsList = operatorsList;
        this.context = context;
        this.appComponent = appComponent;
    }

    @Override
    public OperatorVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_driver, parent, false);
        return new OperatorVH(view);
    }

    @Override
    public void onBindViewHolder(OperatorVH holder, final int position) {
        OperatorsResponseModel model = operatorsList.get(position);


        holder.tvName.setText(model.getFirstName() + " " + model.getLastName());
        holder.tvPhoneNumber.setText(model.getMobileNumber());
        if (model.isBlocked()) {
            holder.viewIsBlocked.setBackgroundResource(R.drawable.ellipse_red);
        } else {
            holder.viewIsBlocked.setBackgroundResource(R.drawable.ellipse_green_dark);
        }
        if (model.getImage() != null && !model.getImage().isEmpty()) {
            Picasso.with(context).load(appComponent.getAllUrls().BASE_IMAGE_URL + model.getImage()).placeholder(R.drawable.place_holder).into(holder.ivPic);
        }

        if (model.isManager()) {
            holder.lytManager.setVisibility(View.VISIBLE);
        } else {
            holder.lytManager.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return operatorsList.size();
    }


    public void updateList(List<OperatorsResponseModel> operatorsList) {
        this.operatorsList = operatorsList;
        notifyDataSetChanged();
    }
}
