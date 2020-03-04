package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.requestModel.ChangePasswordRequestModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.EmailInvoiceRequestModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.requestModel.TransRecievedRequestModel;
import com.developtech.efuelfo.model.responseModel.OnlineTransactionsResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.stationOwner.TransactionDetailsActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.OnlineTransAdapter;
import com.developtech.efuelfo.ui.adapters.stationOwner.PaymentHistoryAdapter;
import com.developtech.efuelfo.ui.dialogFragments.FilterTransDialogFragment;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlineTransactionFragment extends BaseFragment implements OnItemClickListener {

    private View view;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    @BindView(R.id.lytInvoiceRecieve)
    LinearLayout lytInvoiceRecieve;


    private OnlineTransAdapter adapter;

    List<OnlineTransactionsResponseModel> onlineTransList = new ArrayList<>();

    public int page = 0, count = 10;

    LinearLayoutManager layoutManager;

    public int fuelType;
    public boolean date;

    public String startDate, endDate;
    boolean isFilter;

    public OnlineTransactionFragment() {

    }

    public static OnlineTransactionFragment newInstance(String param1, String param2) {
        OnlineTransactionFragment fragment = new OnlineTransactionFragment();
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
        FilterTransDialogFragment dialogFragment = new FilterTransDialogFragment();
        dialogFragment.setFilterListner(new FilterTransDialogFragment.FilterListner() {
            @Override
            public void applyFilter(SearchScheduleRequestModel searchScheduleRequestModel) {
                isFilter = true;
                searchScheduleRequestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
                appComponent.getServiceCaller().callService(appComponent.getAllApis().findOnlineTransactions(searchScheduleRequestModel), onlineTransListener);

            }

            @Override
            public void clearFilter() {
                onlineTransList.clear();
                page = 0;
            }
        });
        dialogFragment.setData(appComponent, this);
        dialogFragment.show(getFragmentManager(), "");
    }

    @OnClick({R.id.btnEmailInvoice, R.id.btnReceive})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEmailInvoice: {

                if (adapter.getCheckedList().size() > 0) {

                    final Dialog dialog = new Dialog(getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_general);
                    TextView tvTitle = dialog.findViewById(R.id.tvTitle);
                    TextView tvDesc = dialog.findViewById(R.id.tvDesc);
                    final EditText etEmail = dialog.findViewById(R.id.etEmail);
                    Button btnCancel = dialog.findViewById(R.id.btnNo);
                    Button btnSend = dialog.findViewById(R.id.btnYes);

                    btnCancel.setText(getResources().getString(R.string.cancel));
                    btnSend.setText(getResources().getString(R.string.send));


                    tvDesc.setVisibility(View.GONE);
                    etEmail.setVisibility(View.VISIBLE);

                    tvTitle.setText(getResources().getString(R.string.enter_email_address));
                    etEmail.setText(appComponent.getSpUtils().getUserData().getEmail());
                    etEmail.setSelection(appComponent.getSpUtils().getUserData().getEmail().length());

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    btnSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (etEmail.getText().toString().trim().isEmpty()) {
                                etEmail.setError(getResources().getString(R.string.enter_email_address));
                                return;
                            }

                            if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                                etEmail.setError(getString(R.string.enter_email_address));
                                return;
                            }

                            EmailInvoiceRequestModel requestModel = new EmailInvoiceRequestModel(adapter.getCheckedList());
                            requestModel.setEmail(etEmail.getText().toString());

                            appComponent.getServiceCaller().callService(appComponent.getAllApis().sendInvoice(requestModel), emailInvoiceListener);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                } else {
                    showMsg(rootLayout, getResources().getString(R.string.select_trans_msg));
                }

                break;
            }
            case R.id.btnReceive: {

                if (adapter.getCheckedList().size() > 0) {
                    showRecieveConfirmation(true);
//                    if (appComponent.getSpUtils().isManager()) {
//                        showRecieveConfirmation(true);
//                    } else {
//                        receiveTransaction();
//                    }

                } else {
                    showMsg(rootLayout, getResources().getString(R.string.select_trans_msg));
                }

                break;
            }
        }
    }

    private void verifyPassword(String password) {
        ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel();
        requestModel.setOldPasscode(password);
        requestModel.setOldPassword(password);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().verifyPassword(requestModel), verifyListener);

    }

    private void receiveTransaction() {
        if (adapter.isAlreadyReceived()) {
            showMsg(rootLayout, "You can select only pending invoices.");
            return;
        }
        TransRecievedRequestModel requestModel = new TransRecievedRequestModel();
        List<String> foList = new ArrayList<>();
        foList.addAll(adapter.getCheckedList());
        requestModel.setVehicleOwner(foList);
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().transRecieved(requestModel), recievedListener);

    }

    private void showRecieveConfirmation(final boolean isAlert) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_general);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvDesc = dialog.findViewById(R.id.tvDesc);
        final EditText etEmail = dialog.findViewById(R.id.etEmail);

        Button btnCancel = dialog.findViewById(R.id.btnNo);
        Button btnSend = dialog.findViewById(R.id.btnYes);


        // btnSend.setText(getResources().getString(R.string.send));


        if (isAlert) {
            tvDesc.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.GONE);
            tvDesc.setText(R.string.desc_payment_received);
            tvTitle.setText(getResources().getString(R.string.confirm));

        } else {
            tvDesc.setVisibility(View.GONE);
            etEmail.setVisibility(View.VISIBLE);
            etEmail.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etEmail.setHint("xxxxxx");
            tvTitle.setText(getResources().getString(R.string.enter_password));
            btnCancel.setText(getResources().getString(R.string.cancel));
            btnSend.setText(getResources().getString(R.string.submited));
        }


        // etEmail.setText(appComponent.getSpUtils().getUserData().getEmail());
        // etEmail.setSelection(appComponent.getSpUtils().getUserData().getEmail().length());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAlert) {
                    showRecieveConfirmation(false);
                    dialog.dismiss();
                } else {
                    if (etEmail.getText().toString().trim().isEmpty()) {
                        showMsg(rootLayout, getResources().getString(R.string.enter_passwords));

                    } else {
                        dialog.dismiss();

                        verifyPassword(etEmail.getText().toString().trim());
                    }

                    //receiveTransaction();
                }

            }
        });

        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pending, container, false);
        init(view);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && getArguments().getString("page") != null) {
            String str = getArguments().getString("page");

            if (str.equals("SALE")) {
                lytInvoiceRecieve.setVisibility(View.VISIBLE);
            }
        }

        layoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
        adapter = new OnlineTransAdapter(appComponent, onlineTransList, this);
        recycleView.setAdapter(adapter);
        recycleView.addOnScrollListener(scrollListener);


        // callApi();
        if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.OPR && appComponent.getSpUtils().isManager() == false) {
            lytInvoiceRecieve.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        page = 0;
        count = 10;
        onlineTransList.clear();

        callApi();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void callApi() {
        isFilter = false;
        SearchScheduleRequestModel requestModel = new SearchScheduleRequestModel();
        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        requestModel.setPage(page);
        requestModel.setLimit(count);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().findOnlineTransactions(requestModel), onlineTransListener);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int last = layoutManager.findLastCompletelyVisibleItemPosition() + 1;
            if (last == (page + count)) {
                if (page > 0) {
                    if (last == (count * page)) {
                        page++;
                        callApi();
                    }
                } else {
                    if (last == count) {
                        page++;
                        callApi();
                    }
                }
            }
        }
    };


    NetworkListener onlineTransListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<OnlineTransactionsResponseModel> responseModels = (List<OnlineTransactionsResponseModel>) responseBody.getResutData();

                if (isFilter) {
                    onlineTransList = responseModels;
                } else {

                    onlineTransList.addAll(responseModels);
                }
                adapter.updateList(onlineTransList);

                if (onlineTransList.size() > 1) {
                    hideNoRecords();
                } else {
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
            adapter.removeProgress();
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
            if (page == 0) {
                showProgress();
            } else {
                adapter.addProgress();
            }
        }
    };

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("online", onlineTransList.get(position));
        newIntent(TransactionDetailsActivity.class, bundle, false);
    }


    NetworkListener emailInvoiceListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, getResources().getString(R.string.invoice_sent_successfully));
            }
        }

        @Override
        public void onError(String msg) {
            hideProgress();
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


    NetworkListener recievedListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, getResources().getString(R.string.trans_recieved));
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            hideProgress();
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };
    NetworkListener verifyListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                receiveTransaction();
            } else {
                showMsg(rootLayout, getResources().getString(R.string.invalid_password));
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            hideProgress();
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };

}
