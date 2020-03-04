package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.GetExpensesResponseModel;
import com.developtech.efuelfo.model.responseModel.GetServiceResponseModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 2/5/18.
 */

public class ExpenseVH extends RecyclerView.ViewHolder{
    @BindView(R.id.tvVehicleName)
     TextView tvVehicleName;

    @BindView(R.id.tvOdometer)
     TextView tvOdometer;

    @BindView(R.id.tvTotal)
     TextView tvTotal;

    @BindView(R.id.tvDate)
     TextView tvDate;

    @BindView(R.id.tvTime)
     TextView tvTime;

    public ExpenseVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(GetExpensesResponseModel model)
    {

        if(model!=null)
        {

            if (model.getVehicle()!=null && model.getVehicle().getAlias() != null) {
                tvVehicleName.setText(model.getVehicle().getAlias());
            }
            tvOdometer.setText(model.getOdoMeter());
            tvTotal.setText(model.getTotalOfExpense() + "");
            if (model.getDate() != null) {
                tvDate.setText(getParsedDate(model.getDate()));
                tvTime.setText(getParsedTime(model.getDate()));
            }

        }


    }

    public void bindView(GetServiceResponseModel model)
    {
        if (model!=null)
        {
            if (model.getVehicle()!=null && model.getVehicle().getAlias()!=null) {
                tvVehicleName.setText(model.getVehicle().getAlias());
            }
                tvOdometer.setText(model.getOdoMeter());
                tvTotal.setText(model.getTotalOfService() + "");
                tvDate.setText(getParsedDate(model.getDate()));
                tvTime.setText(getParsedTime(model.getDate()));

        }
    }

    String getParsedDate(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter.format(date);
    }

    String getParsedTime(String strDate)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter.format(date);
    }
}
