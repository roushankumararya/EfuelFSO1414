package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.customs.RoundedImageView;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by developtech on 1/25/18.
 */

public class FuelStationDetailDialog extends DialogFragment {

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvDealerCode)
    TextView tvDealerCode;

    @BindView(R.id.tvFuelStationId)
    TextView tvFuelStationId;

    @BindView(R.id.tvMobile)
    TextView tvMobile;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvTransactionDate)
    TextView tvTransactionDate;

    @BindView(R.id.ivImg)
    RoundedImageView ivImg;

    private View view;
    private AlertDialog alertDialog;

    PaymentHistoryResponseModel paymentHistoryModel;
    GetFuelStationResponseModel fuelStationModel;

    AppComponent appComponent;
    Context context;

    public void setData(AppComponent appComponent, Context context)
    {
        this.appComponent = appComponent;
        this.context = context;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fuel_station_details, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();

        if(getArguments()!=null)
        {
            Bundle bundle = getArguments();

            if (bundle.getSerializable("model")!=null)
            {
                fuelStationModel = (GetFuelStationResponseModel) bundle.getSerializable("model");
                bindData();
            }
            else if (bundle.getSerializable("cash")!=null)
            {
                fuelStationModel = (GetFuelStationResponseModel) bundle.getSerializable("cash");
                bindData();
            }
        }

        return alertDialog;
    }

//    void setData()
//    {
//        FuelStationResponseModel model =
//        tvName.setText(model.getName());
//        tvDealerCode.setText(model.getDealerCode());
//        tvAddress.setText(model.getAddress());
//        tvFuelStationId.setText(model.getFuelStationId());
//        tvMobile.setText(model.getMobileNumber());
//        tvTransactionDate.setText(getParsedDate2(model.getCreatedAt()));
//    }

    void bindData()
    {
        if (fuelStationModel.getImage()!=null && !fuelStationModel.getImage().isEmpty())
        {
            Picasso.with(getActivity())
                    .load(appComponent.getUtilFunctions().getImageFullUrl(fuelStationModel.getImage()))
                    .placeholder(context.getResources().getDrawable(R.drawable.fuel_info))
                    .into(ivImg);
        }
        tvName.setText(fuelStationModel.getName());
        tvDealerCode.setText(fuelStationModel.getDealerCode());
        tvAddress.setText(fuelStationModel.getAddress());
        tvFuelStationId.setText(fuelStationModel.getFuelStationId());
        tvMobile.setText(fuelStationModel.getMobileNumber());
        tvTransactionDate.setText(getParsedDate2(fuelStationModel.getCreatedAt()));
    }

    public String getParsedDate2(String strDate)
    {
        if (strDate==null)
            return "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        String strFormatted =  formatter.format(date);
        return strFormatted;
    }
}
