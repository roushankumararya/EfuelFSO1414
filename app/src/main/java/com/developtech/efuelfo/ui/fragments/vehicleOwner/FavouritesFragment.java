package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.GetFavoriteStationResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.vehicleOwner.FuelStationDetail;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.FavouriteAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesFragment extends BaseFragment implements VehicleOwnerItemClick {

    @BindView(R.id.recycleFavourite)
    RecyclerView recycleFavourite;
    FavouriteAdapter adapter;
    private View view;
    private List<FuelStationResponseModel> responseModelList;
    NetworkListener favouriteListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                GetFavoriteStationResponseModel model = (GetFavoriteStationResponseModel) responseBody.getResutData();
                responseModelList = model.getFavourite();
                adapter.refreshData(responseModelList);
            } else {
                showMsg(view, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(view, msg);
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

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_favourite, container, false);
        ButterKnife.bind(this, view);
        init(view);
        initComponents();
        return view;
    }

    public void initComponents() {
        recycleFavourite.setLayoutManager(new LinearLayoutManager(getContext()));
        responseModelList = new ArrayList<>();
        adapter = new FavouriteAdapter(getContext(), responseModelList, this, appComponent);
        recycleFavourite.setAdapter(adapter);

        LocationRequestModel locationRequestModel = new LocationRequestModel();
        locationRequestModel.setLatitude("30.679");
        locationRequestModel.setLongitude("76.7264");
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getFavFuel(locationRequestModel), favouriteListener);
    }

    @Override
    public void onCLick(int position) {
        FuelStationResponseModel model = responseModelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        Intent intent = new Intent(getContext(), FuelStationDetail.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 100);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                boolean isFav = bundle.getBoolean("isFav");
                String id = bundle.getString("id");
                if (!isFav) {
                    for (FuelStationResponseModel model : responseModelList) {
                        if (model.getFuelStationId().equalsIgnoreCase(id)) {
                            responseModelList.remove(model);
                            adapter.refreshData(responseModelList);
                            break;
                        }
                    }
                }
            }
        }
    }
}
