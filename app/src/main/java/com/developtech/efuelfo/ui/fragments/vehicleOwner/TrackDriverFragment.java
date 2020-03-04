package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.UpdateDriverRequestModel;
import com.developtech.efuelfo.model.responseModel.DriverLocationResponseModel;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.TrackDriverAdapter;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.TrackDriverInfoWindowAdapter;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.util.SPUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrackDriverFragment extends BaseFragment implements VehicleOwnerItemClick, OnMapReadyCallback {

    @BindView(R.id.recycleTrackDriver)
    RecyclerView recycleTrackDriver;
    @BindView(R.id.ivMapList)
   public ImageView ivMapList;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    boolean showMap;
    TrackDriverAdapter adapter;
    List<GetDriverResponseModel> driverList = new ArrayList<>();
    GetDriverResponseModel model;
    Bitmap bmNew, bmAccept, mutableBitmap;
    DriverLocationResponseModel driverLoactionModel;
    NetworkListener getDriverListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                driverList = (List<GetDriverResponseModel>) responseBody.getResutData();
                if (driverList.size() > 0)
                    adapter.refreshData(driverList);
            } else {
                showMsg(rootLayout, responseBody.getMessage());
            }
        }

        @Override
        public void onError(String msg) {
            hideProgress();
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
    NetworkListener driverLocationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                driverLoactionModel = (DriverLocationResponseModel) responseBody.getResutData();

                if (showMap) {
                    addMarker();
                }
            }
        }

        @Override
        public void onError(String msg) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onStart() {

        }
    };
    private View view;

    public TrackDriverFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_track_driver, container, false);
        ButterKnife.bind(this, view);
        init(view);
        initComponent();
        return view;
    }

    public void initComponent() {
        recycleTrackDriver.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TrackDriverAdapter(getContext(), appComponent, driverList, this);
        recycleTrackDriver.setAdapter(adapter);
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getAllDriver(), getDriverListener);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentMap);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (supportMapFragment != null) {
            supportMapFragment.getView().setVisibility(View.GONE);
            supportMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onCLick(int position) {
        model = driverList.get(position);
        showMap = true;
        UpdateDriverRequestModel requestModel = new UpdateDriverRequestModel();
        requestModel.driverId = model.getId();
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getDriverLocation(requestModel), driverLocationListener);
        recycleTrackDriver.setVisibility(View.GONE);
        supportMapFragment.getView().setVisibility(View.VISIBLE);
        ivMapList.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.ivMapList})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMapList: {
                showMap = false;
                supportMapFragment.getView().setVisibility(View.GONE);
                ivMapList.setVisibility(View.GONE);
                recycleTrackDriver.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 104);
            return;
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 104) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Allow Permission to use this feature", Toast.LENGTH_SHORT).show();
                return;
            } else if (map != null) {

            }
        }
    }

    private void addMarker() {
        if (map != null) {
            map.clear();
            if (bmNew == null) {
                bmNew = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location_marker_black));
            }

            Double lat = Double.parseDouble(driverLoactionModel.getLatitude());
            Double lng = Double.parseDouble(driverLoactionModel.getLongitude());

            LatLng latLng = new LatLng(lat, lng);
            MarkerOptions userIndicator = new MarkerOptions().position(latLng);
            mutableBitmap = bmNew.copy(Bitmap.Config.ARGB_8888, true);
            userIndicator.icon(BitmapDescriptorFactory.fromBitmap(mutableBitmap)).anchor(0.5f, 1f);
            Marker marker = map.addMarker(userIndicator);
            model.setVehicleNumber(driverLoactionModel.getVehicleNumber());
            marker.setTag(model);

            LatLngBounds.Builder builder = LatLngBounds.builder();

            builder.include(marker.getPosition());

            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 150);
            map.setMaxZoomPreference(15f);
            map.animateCamera(cu);

        }

        map.setInfoWindowAdapter(new TrackDriverInfoWindowAdapter(getContext(), appComponent));
    }

}
