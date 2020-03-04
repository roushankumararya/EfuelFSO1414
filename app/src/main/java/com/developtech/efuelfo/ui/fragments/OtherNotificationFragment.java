package com.developtech.efuelfo.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.PaginationModel;
import com.developtech.efuelfo.model.responseModel.NotificationResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.notifications.MyFirebaseMessagingService;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.stationOwner.RequestPendingActivity;
import com.developtech.efuelfo.ui.activities.stationOwner.TransactionDetailsActivity;
import com.developtech.efuelfo.ui.adapters.NotificationAdapter;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherNotificationFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.rvOthers)
    RecyclerView rvOthers;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    NotificationAdapter adapter;

    List<NotificationResponseModel> notificationList = new ArrayList<>();

    int page = 0, count = 10;

    LinearLayoutManager layoutManager;

    public OtherNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other_notification, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        NotificationResponseModel model = notificationList.get(position);

        Class value = HomeActivity.class;
        String action = model.getClickAction();

        if (action.equalsIgnoreCase(MyFirebaseMessagingService.TRANS)) {
            value = TransactionDetailsActivity.class;
        } else if (action.equalsIgnoreCase(MyFirebaseMessagingService.CREDIT)) {
            value = RequestPendingActivity.class;
        }

        Bundle bundle = new Bundle();
        bundle.putString("id", model.getRequestId());
        Intent intent = new Intent(this.context, value);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getContext());
        rvOthers.setLayoutManager(layoutManager);
        adapter = new NotificationAdapter(notificationList, appComponent);
        adapter.setListener(this);
        rvOthers.setAdapter(adapter);
        rvOthers.addOnScrollListener(scrollListener);

        callApi();

    }

    void callApi() {
        PaginationModel requestModel = new PaginationModel(count, page);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getNotifications(requestModel), notificationListener);
    }


    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int last = layoutManager.findLastCompletelyVisibleItemPosition();
            if (last == (page + count)) {
                page = page + count;
                callApi();
            }
        }
    };

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    NetworkListener notificationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<NotificationResponseModel> notiList = (List<NotificationResponseModel>) responseBody.getResutData();
                notificationList.addAll(notiList);
                adapter.updateList(notificationList);
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
                    adapter.removeProgress();
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
}
