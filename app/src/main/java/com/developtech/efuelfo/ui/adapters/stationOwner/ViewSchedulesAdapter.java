package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.ui.viewHolder.HeaderViewScheduleVH;
import com.developtech.efuelfo.ui.viewHolder.ViewSchedulesVH;
import com.getkeepsafe.relinker.elf.Elf;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by dt on 2/22/18.
 */

public class ViewSchedulesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int VIEW_HEADER = 0;
    private int VIEW_ITEM = 1;
    private int VIEW_FIELD = 2;

    List<SchedulesResponseModel> schedulesList;
    AppComponent appComponent;
    OnItemClickListener listener;

    private List<SchedulesResponseModel> upcomingScheduleList;

    public ViewSchedulesAdapter(List<SchedulesResponseModel> schedulesList, AppComponent appComponent, OnItemClickListener listener ) {
        this.schedulesList = schedulesList;
        this.appComponent = appComponent;
        this.listener = listener;

        upcomingScheduleList = new ArrayList<>();

        for (SchedulesResponseModel model : this.schedulesList)
        {
            Date scheduleDate = appComponent.getUtilFunctions().getLocalDateFromUtc(model.getTime());
            Date date = new Date();

            if (scheduleDate.after(date))
            {
                upcomingScheduleList.add(model);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_HEADER || viewType == VIEW_FIELD)
        {
            View itemVeiw = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view_schedule, parent, false);
            return new HeaderViewScheduleVH(itemVeiw);
        }
        else if (viewType == VIEW_ITEM)
        {
            View itemVeiw = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_schedule_redesigned, parent, false);
            return new ViewSchedulesVH(itemVeiw);
        }
        else
            throw new RuntimeException("No match for "+viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewSchedulesVH)
        {
            ViewSchedulesVH vh = (ViewSchedulesVH) holder;
            SchedulesResponseModel model;
            if (upcomingScheduleList.size()>0 && position>upcomingScheduleList.size()) {
                model=schedulesList.get(position-2);
            }
            else
            {
                model=schedulesList.get(position-1);
            }
            vh.tvScheduleDateTime.setText(appComponent.getUtilFunctions().toLocal(model.getTime()));
            vh.tvCreatedOn.setText(appComponent.getUtilFunctions().toLocal(model.getCreatedAt()));
            vh.tvCreatedBy.setText(appComponent.getSpUtils().getUserData().getFirstName()+" "+appComponent.getSpUtils().getUserData().getLastName());
            if (model.getUpdatedAt()!=null && !model.getUpdatedAt().isEmpty() && !model.getCreatedAt().equals(model.getUpdatedAt()))
            {
                vh.tvModified.setText(appComponent.getUtilFunctions().toLocal(model.getUpdatedAt()));
                vh.tvModifiedBy.setText(appComponent.getSpUtils().getUserData().getFirstName()+" "+appComponent.getSpUtils().getUserData().getLastName());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (upcomingScheduleList.size()>0 && position>upcomingScheduleList.size()) {
                        listener.onItemClick(view, position-2);
                    }
                    else
                    {
                        listener.onItemClick(view, position-1);
                    }

                }
            });
        }
        else if (holder instanceof HeaderViewScheduleVH)
        {
            HeaderViewScheduleVH vh = (HeaderViewScheduleVH) holder;
            if (upcomingScheduleList.size()>0) {
                if (isPositionHeader(position)) {
                    vh.tvHeaderSchedules.setText("Upcoming");
                } else if (isPositionField(position)) {
                    vh.tvHeaderSchedules.setText("History");
                }
            }
            else
            {
                vh.tvHeaderSchedules.setText("History");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (upcomingScheduleList.size()>0)
            return schedulesList.size()+2;
        else
            return schedulesList.size()+1;
    }

    public void updateList(List<SchedulesResponseModel> schedulesList)
    {
        this.schedulesList = schedulesList;
        upcomingScheduleList.clear();
        for (SchedulesResponseModel model : this.schedulesList)
        {
            Date scheduleDate = appComponent.getUtilFunctions().getLocalDateFromUtc(model.getTime());
            Date date = new Date();

            if (scheduleDate.after(date))
            {
                upcomingScheduleList.add(model);
            }
        }
        for (SchedulesResponseModel model : this.upcomingScheduleList)
        {
            this.schedulesList.remove(model);
        }
        Log.d("headersIssue", "updateList upcoming size: "+upcomingScheduleList.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return VIEW_HEADER;
        else if(isPositionField(position))
            return VIEW_FIELD;
        else
            return VIEW_ITEM;
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }

    private boolean isPositionField(int position)
    {
        return upcomingScheduleList.size() > 0 && position == (upcomingScheduleList.size() + 1);
    }
}
