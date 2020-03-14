package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.fuelStation.StationRegistrationActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.FuelStationAdapter;
import com.developtech.efuelfo.ui.dialogFragments.AddFuelStationDialog;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuelStationFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.rvFuelStation)
    RecyclerView rvFuelStaton;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    View view;


    List<GetFuelStationResponseModel> fuelStationsList = new ArrayList<>();

    public FuelStationAdapter adapter;

    NetworkListener addListener;

    public FuelStationFragment() {
        // Required empty public constructor
    }

    public void setListener(NetworkListener addListener)
    {
        this.addListener = addListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fuel_station, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(), getFuelStationsListener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvFuelStaton.setLayoutManager(layoutManager);
        adapter = new FuelStationAdapter(fuelStationsList, appComponent, this);
        rvFuelStaton.setAdapter(adapter);
    }


    @OnClick({R.id.fabAdd})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fabAdd :
            {
                AddFuelStationDialog dialog = new AddFuelStationDialog();
                if(addListener!=null)
                {
                    dialog.setData(addListener,sendQrListener, appComponent, null, getActivity() );
                }
                else
                {
                    dialog.setData(addFuelStationListener, sendQrListener, appComponent, null, getActivity());
                }

                dialog.show(getFragmentManager(), "add_fuel_station");
                break;
            }
        }
    }

   /* @Override
    public void onItemClick(View view, int position) {
        switch (view.getId())
        {
            case R.id.item_fuel_station : {
                AddFuelStationDialog dialog = new AddFuelStationDialog();

                dialog.setData(updateListener,sendQrListener, appComponent, fuelStationsList.get(position), (getActivity() instanceof StationRegistrationActivity) ? (StationRegistrationActivity) getActivity() : null);

                dialog.show(getFragmentManager(), "add_fuel_station");

                break;
            }
            case R.id.switchCreditAgreement : {
                Switch sw = (Switch) view;
                SwitchCreditAgreementReqModel reqModel = new SwitchCreditAgreementReqModel();
                reqModel.setFuelStationId(fuelStationsList.get(position).getId());
                reqModel.setCreditAgreement(sw.isChecked());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().switchCreditAgreement(reqModel), switchCreditAgreementListener);
            }
        }

    }
*/

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    public List<GetFuelStationResponseModel> getFuelStationsList()
    {
        return this.fuelStationsList;
    }

    private NetworkListener getFuelStationsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelStationsList = (List<GetFuelStationResponseModel>) responseBody.getResutData();
                if (fuelStationsList.size()>0) {
                    adapter.updateList(fuelStationsList);

                    if (getActivity() instanceof StationRegistrationActivity && fuelStationsList.size() > 0) {
                        ((StationRegistrationActivity) getActivity()).setStationAdded(true);
                    }
                }
                else {
                    showNoRecords();
                }
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {

            if(getActivity()==null)
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


    private NetworkListener addFuelStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(), getFuelStationsListener);
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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

    private NetworkListener updateListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(), getFuelStationsListener);
                showMsg(getResources().getString(R.string.fuel_station_updated));
            }
        }

        @Override
        public void onError(String msg) {
            showProgress();
        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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

    private NetworkListener sendQrListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(getResources().getString(R.string.sent_qr_tomail));
            }
        }

        @Override
        public void onError(String msg) {

        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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

    private NetworkListener switchCreditAgreementListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {

            }
        }

        @Override
        public void onError(String msg) {
            if(getActivity()==null)
                return;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });
        }

        @Override
        public void onComplete() {
            if(getActivity()==null)
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }*/
}
