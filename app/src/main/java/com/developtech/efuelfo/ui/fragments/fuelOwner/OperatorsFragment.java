package com.developtech.efuelfo.ui.fragments.fuelOwner;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.FindOperatorRequestModel;
import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.stationOwner.OperatorsAdapter;
import com.developtech.efuelfo.ui.dialogFragments.AddOperatorDialog;
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
public class OperatorsFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.rvOperator)
    RecyclerView rvOperator;

    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

    OperatorsAdapter operatorsAdapter;

    List<OperatorsResponseModel> operatorsList = new ArrayList<>();

    View view;

    public OperatorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_operators, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.
                VERTICAL, false);
        rvOperator.setLayoutManager(layoutManager);
        operatorsAdapter = new OperatorsAdapter(getContext(), this, operatorsList, appComponent);
        rvOperator.setAdapter(operatorsAdapter);

        callApi();

    }

    void callApi() {
        FindOperatorRequestModel requestModel = new FindOperatorRequestModel();
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getOperators(requestModel),
                getOperatorsListener);
    }


    @OnClick({R.id.fabAddOperator})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabAddOperator: {
                AddOperatorDialog dialog = new AddOperatorDialog();

                dialog.setBlockListener(blockOperatorListener);
                dialog.setData(appComponent, addOperatorListener, null, blockOperatorListener, null, true, getActivity());
                dialog.show(getChildFragmentManager(), "add_operator");
                break;
            }
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onItemClick(View view, int position) {
        AddOperatorDialog dialog = new AddOperatorDialog();
        dialog.show(getChildFragmentManager(), "add_operator");
        dialog.setData(appComponent, deleteListener, updateOperatorListener, blockOperatorListener, operatorsList.get(position), false, getActivity());
        this.position = position;
    }

    int position;
    NetworkListener getOperatorsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                operatorsList = (List<OperatorsResponseModel>) responseBody.getResutData();

                if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.OPR) {
                    for (int i = 0; i < operatorsList.size(); i++) {
                        if (appComponent.getSpUtils().getUserData().getId().equals(operatorsList.get(i).getId())) {
                            operatorsList.remove(i);
                            break;
                        }
                    }
                }

                if (operatorsList.size() > 0) {
                    hideNoRecords();
                    operatorsAdapter.updateList(operatorsList);
                } else {
                    showNoRecords();
                }
            }
        }

        @Override
        public void onError(String msg) {
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

    NetworkListener blockOperatorListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, responseBody.getMessage());
                callApi();
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
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

    NetworkListener addOperatorListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, getResources().getString(R.string.operator_added_successfully));
                callApi();
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
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

    NetworkListener deleteListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(getResources().getString(R.string.operator_deleted_successfully));
                operatorsList.remove(position);
                operatorsAdapter.updateList(operatorsList);
            }
        }

        @Override
        public void onError(String msg) {
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

    NetworkListener updateOperatorListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                callApi();
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
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
