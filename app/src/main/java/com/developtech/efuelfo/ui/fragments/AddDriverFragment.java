package com.developtech.efuelfo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.AddDriverAdapter;
import com.developtech.efuelfo.ui.dialogFragments.AddDriverDialog;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDriverFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.fabAddDriver)
    FloatingActionButton fabAddDriver;
    @BindView(R.id.recyclerDriver)
    RecyclerView recyclerDriver;

    boolean toUpdate;
    int position;
    private View view;
    private AddDriverAdapter adapter;

    public AddDriverFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(View view) {
        super.init(view);

    }

    NetworkListener deleteDriverListener = new NetworkListener() {
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
    private List<GetDriverResponseModel> driverResponseModels;
    NetworkListener getDriverListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            Log.e("resposne:>", "sdsd");
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                driverResponseModels = (List<GetDriverResponseModel>) responseBody.getResutData();
                if (driverResponseModels.size() > 0)
                    adapter.refreshData(driverResponseModels);
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
            showMsg(view, msg);
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
    NetworkListener addDriverListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                GetDriverResponseModel driverResponseModel = (GetDriverResponseModel) responseBody.getResutData();
                refreshData(driverResponseModel);

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
            showMsg(view, msg);
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
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_driver, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerDriver.setLayoutManager(new LinearLayoutManager(getContext()));
        driverResponseModels = new ArrayList<>();
        adapter = new AddDriverAdapter(getContext(), driverResponseModels, this, appComponent);
        recyclerDriver.setAdapter(adapter);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getAllDriver(), getDriverListener);
    }

    public void refreshData(GetDriverResponseModel model) {
        if (toUpdate) {
            driverResponseModels.remove(position);
            driverResponseModels.add(position, model);
            adapter.refreshData(driverResponseModels);
        } else {
            driverResponseModels.add(model);
            adapter.refreshData(driverResponseModels);
        }
    }

    @OnClick(R.id.fabAddDriver)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabAddDriver: {
                AddDriverDialog dialogFragment = new AddDriverDialog();
                toUpdate = false;
                dialogFragment.setData(appComponent, addDriverListener, deleteDriverListener, false, null);
                dialogFragment.show(getFragmentManager(), "add_driver");
                break;
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onItemClick(View view, int position) {
        AddDriverDialog dialogFragment = new AddDriverDialog();
        toUpdate = true;
        dialogFragment.setData(appComponent, addDriverListener, deleteDriverListener, true, driverResponseModels.get(position));
        dialogFragment.show(getFragmentManager(), "add_driver");
        this.position = position;
    }


}
