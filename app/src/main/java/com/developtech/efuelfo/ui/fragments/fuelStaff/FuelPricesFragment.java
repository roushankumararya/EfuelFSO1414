package com.developtech.efuelfo.ui.fragments.fuelStaff;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.operator.FuelPricesOperatorAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuelPricesFragment extends BaseFragment {

    @BindView(R.id.rvFuelPrices)
    RecyclerView rvFuelPrices;

    @BindView(R.id.tvStationName)
    TextView tvStationName;
    @BindView(R.id.txtErrorMsg)
    TextView txtErrorMsg;

    @BindView(R.id.tvStationAddress)
    TextView tvStationAddress;

    @BindView(R.id.ivStation)
    ImageView ivStation;

    FuelPricesOperatorAdapter adapter;

    List<FuelDetailModel> fuelPricesList = new ArrayList<>();

    public FuelPricesFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fuel_prices_operator, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Picasso.with(appComponent.getContext())
                .load(appComponent.getUtilFunctions().getImageFullUrl(appComponent.getSpUtils().getFuelStationModel().getFuelCompany().getImage()))
                .into(ivStation);

        tvStationName.setText(appComponent.getSpUtils().getFuelStationModel().getName());
        tvStationAddress.setText(appComponent.getSpUtils().getFuelStationModel().getAddress());

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        String strDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        String strTime = date.getHours() + ":" + date.getMinutes();

        String jointDate = strDate + " " + strTime;

        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();

        requestModel.setDateTime(getFormatedDate(jointDate));
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        requestModel.setLimit(10);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().searchSchedule(requestModel), scheduleListener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvFuelPrices.setLayoutManager(layoutManager);
        adapter = new FuelPricesOperatorAdapter(fuelPricesList, appComponent);
        rvFuelPrices.setAdapter(adapter);

    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }


    NetworkListener scheduleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<SchedulesResponseModel> schedulesList = (List<SchedulesResponseModel>) responseBody.getResutData();

                for (SchedulesResponseModel model : schedulesList) {
                    fuelPricesList.addAll(model.getFuelDetail());
                }
                adapter.updateList(fuelPricesList);
                rvFuelPrices.setVisibility(View.VISIBLE);
                txtErrorMsg.setVisibility(View.GONE);
            }
            if (fuelPricesList.isEmpty()) {
                rvFuelPrices.setVisibility(View.GONE);
                txtErrorMsg.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onError(String msg) {

        }

        @Override
        public void onComplete() {
            if (getActivity() == null)
                return;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };
}
