package com.developtech.efuelfo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddVehicleRequestModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.AddVehicleAdapter;
import com.developtech.efuelfo.ui.dialogFragments.AddVehicleDialog;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddVehicleFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.fabAddVehicle)
    FloatingActionButton fabAddVehicle;
    @BindView(R.id.recycleVehicle)
    RecyclerView recycleVehicle;
//    private NetworkListener listener;
    private List<AllVehicleResponseModel> vehicleResponseModels = new ArrayList<>();

    private View view;
    private AddVehicleAdapter adapter;
    private AddVehicleRequestModel requestModel;
    AllVehicleResponseModel vehicleResponseModel;

    public AddVehicleFragment() {

    }

    public static AddVehicleFragment newInstance(String param1, String param2) {
        AddVehicleFragment fragment = new AddVehicleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = getLayoutInflater().inflate(R.layout.fragment_add_vehicle, container, false);
        init(view);
        initalise();
        return view;
    }

    public void initalise() {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleVehicle.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AddVehicleAdapter(getContext(), vehicleResponseModels, this, appComponent);
        recycleVehicle.setAdapter(adapter);

        appComponent.getServiceCaller().callService(appComponent.getAllApis().getVehicleList(), getVehicleListener);
    }

    public void refreshData(AllVehicleResponseModel model, boolean isUpdate) {
        if (isUpdate) {
            vehicleResponseModels.remove(position);
            vehicleResponseModels.add(position, model);
            adapter.refreshList(vehicleResponseModels);

        } else {
            vehicleResponseModels.add(model);
            adapter.refreshList(vehicleResponseModels);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @OnClick(R.id.fabAddVehicle)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabAddVehicle: {
                AddVehicleDialog dialogFragment = new AddVehicleDialog();
                dialogFragment.setData(appComponent, addVehicleListener, null, false, null);
                dialogFragment.show(getFragmentManager(), "add_vehicle");
                break;
            }
        }
    }

    int position;

    @Override
    public void onItemClick(View view, int position) {
        AddVehicleDialog dialogFragment = new AddVehicleDialog();
        dialogFragment.setData(appComponent, updateListener, deleteListener, true, vehicleResponseModels.get(position));
        dialogFragment.show(getFragmentManager(), "add_vehicle");
        this.position = position;
    }

    NetworkListener getVehicleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                vehicleResponseModels = (List<AllVehicleResponseModel>) responseBody.getResutData();
                if (vehicleResponseModels.size() > 0)
                    adapter.refreshList(vehicleResponseModels);
            } else {
                showMsg(rootLayout, responseBody.getMessage());
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

    NetworkListener addVehicleListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                AllVehicleResponseModel model = (AllVehicleResponseModel) responseBody.getResutData();
                refreshData(model, false);
                showMsg(rootLayout, getResources().getString(R.string.vehicle_added_successfully));
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            if(getActivity() == null)
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
            if(getActivity() == null)
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

    //CHange

    NetworkListener updateListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                AllVehicleResponseModel model = (AllVehicleResponseModel) responseBody.getResutData();
                refreshData(model, true);
            } else {
                showMsg(rootLayout, responseBody.getMessage());
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

    NetworkListener deleteListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                adapter.removeItem(position);
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
}
