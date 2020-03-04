package com.developtech.efuelfo.ui.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.NotificationResponseModel;
import com.developtech.efuelfo.ui.viewHolder.NotificationVH;
import com.developtech.efuelfo.ui.viewHolder.ProgressVH;
import com.developtech.efuelfo.util.UtilFunctions;

import java.util.List;

/**
 * Created by dt on 3/19/18.
 */

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<NotificationResponseModel> notificationList;
    AppComponent appComponent;

    int progress = 1;
    int item = 2;
    boolean isProgress;
    OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    NotificationResponseModel notificationModel;

    public NotificationAdapter(List<NotificationResponseModel> notificationList, AppComponent appComponent) {
        this.notificationList = notificationList;
        this.appComponent = appComponent;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == item) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
            return new NotificationVH(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);

        return new ProgressVH(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NotificationVH) {
            NotificationVH notificationVH = (NotificationVH) holder;
            notificationVH.tvTitle.setText(notificationList.get(position).getTitle());
            notificationVH.tvMsg.setText(notificationList.get(position).getMessage());
            notificationVH.tvTime.setText(appComponent.getUtilFunctions().getTimeLaps(notificationList.get(position).getCreatedAt()));
            notificationVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(v, holder.getAdapterPosition());

                }
            });
        } else if (holder instanceof ProgressVH) {
            ProgressVH progressVH = (ProgressVH) holder;
            progressVH.bindData(isProgress);
        }

    }

    public void updateList(List<NotificationResponseModel> notificationList) {
        this.notificationList = notificationList;
        if (notificationModel != null)
            notificationList.remove(notificationModel);
        isProgress = true;
        notificationModel = new NotificationResponseModel();
        notificationModel.setPagination("progress");
        notificationList.add(notificationModel);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
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
        String pagination = notificationList.get(position).getPagination();
        if (pagination != null && pagination.equals("progress")) {
            return progress;
        }

        return item;

    }
}
