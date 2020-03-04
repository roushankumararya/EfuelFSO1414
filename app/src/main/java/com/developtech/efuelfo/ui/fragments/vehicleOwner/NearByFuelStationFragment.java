package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.VehicleOwnerItemClick;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.FuelStationDetail;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.FavouriteAdapter;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.StationInfoWindowAdapter;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NearByFuelStationFragment extends BaseFragment implements VehicleOwnerItemClick, OnMapReadyCallback {

    View view;

    @BindView(R.id.recycleStationList)
    public RecyclerView recycleStationList;
    @BindView(R.id.frameCancel)
    FrameLayout frameCancel;
    @BindView(R.id.frameSearch)
    FrameLayout frameSearch;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ivMap)
   public ImageView ivMap;

    SupportMapFragment supportMapFragment;

    GoogleMap map;
    boolean showMap;
    ArrayList<Marker> markers = new ArrayList<>();
    Bitmap bmNew, bmAccept, mutableBitmap;
    private List<FuelStationResponseModel> fuelStationResponseModels;
    private FavouriteAdapter adapter;
    NetworkListener nearByListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelStationResponseModels = (List<FuelStationResponseModel>) responseBody.getResutData();
                adapter.refreshData(fuelStationResponseModels);
                if (showMap) {
                    addMarker();
                }
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(view, msg);
        }

        @Override
        public void onComplete() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        }

        @Override
        public void onStart() {
            showProgress();
        }
    };
    private LocationRequestModel requestModel;


    public NearByFuelStationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_near_fuel_stations, container, false);
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents();
    }

    public void initComponents() {
        recycleStationList.setLayoutManager(new LinearLayoutManager(getContext()));
        fuelStationResponseModels = new ArrayList<>();
        adapter = new FavouriteAdapter(getContext(), fuelStationResponseModels, this, appComponent);
        recycleStationList.setAdapter(adapter);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentMap);
        supportMapFragment.getView().setVisibility(View.GONE);
        supportMapFragment.getMapAsync(this);
        callApi();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = charSequence.toString();
                List<FuelStationResponseModel> list = new ArrayList<>();
                for (FuelStationResponseModel model : fuelStationResponseModels) {
                    if (model.getName().toLowerCase().contains(search.toLowerCase())) {
                        list.add(model);
                    }
                }
                adapter.refreshData(list);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    HomeActivity homeActivity;
    void callApi() {
        homeActivity = (HomeActivity) getActivity();
        requestModel = new LocationRequestModel();
        requestModel.setLatitude(appComponent.getSpUtils().getLat());
        requestModel.setLongitude(appComponent.getSpUtils().getLng());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().getNearByFuelStation(requestModel), nearByListener);
    }

    @Override
    public void retry() {

    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void onCLick(int position) {
        FuelStationResponseModel model = fuelStationResponseModels.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        bundle.putSerializable("loc_model", requestModel);
        Intent intent = new Intent(getContext(), FuelStationDetail.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 100);
//        newIntent(FuelStationDetail.class, bundle, false);
    }

    @OnClick({R.id.frameSearch, R.id.frameCancel, R.id.ivMap})
    public void onCLick(View view) {
        switch (view.getId()) {
            case R.id.frameCancel: {
                etSearch.setText("");
                break;
            }
            case R.id.frameSearch: {
                break;
            }
            case R.id.ivMap: {
                if (showMap) {
                    ivMap.setImageResource(R.drawable.viewmap);
                    recycleStationList.setVisibility(View.VISIBLE);
                    supportMapFragment.getView().setVisibility(View.GONE);
                    showMap = false;
                } else {
                    ivMap.setImageResource(R.drawable.maplist);
                    supportMapFragment.getView().setVisibility(View.VISIBLE);
                    recycleStationList.setVisibility(View.GONE);
                    showMap = true;
                    callApi();
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 104);
            return;
        }
        googleMap.setMyLocationEnabled(true);
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
                map.setMyLocationEnabled(true);
            }
        }
    }

    private void addMarker() {
        if (map != null) {
            map.clear();
            markers.clear();

//            if (bmNew == null) {
//
//
//
//                bmNew = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location_marker));
//            }

            for (FuelStationResponseModel model : fuelStationResponseModels) {
                Double lat = Double.parseDouble(model.getLatitude());
                Double lng = Double.parseDouble(model.getLongitude());
                View markerView = ((LayoutInflater) getActivity()
                        .getSystemService(
                                getActivity().LAYOUT_INFLATER_SERVICE))
                        .inflate(R.layout.marker_layout, null);
                Picasso.with(getContext()).load(appComponent.getUtilFunctions().getImageFullUrl(model.getImage())).into((ImageView) markerView.findViewById(R.id.img_pump));
                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions userIndicator = new MarkerOptions().position(latLng);
                userIndicator.icon(BitmapDescriptorFactory
                        .fromBitmap(createDrawableFromView(
                                getActivity(),
                                markerView))).anchor(0.5f, 1f);
                Marker marker = map.addMarker(userIndicator);
                marker.setTag(model);
                markers.add(marker);
            }

            LatLngBounds.Builder builder = LatLngBounds.builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }

            LatLngBounds bounds = builder.build();

            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 150);
            map.setMaxZoomPreference(14f);
            map.animateCamera(cu);

        }

        map.setInfoWindowAdapter(new StationInfoWindowAdapter(getContext(), appComponent));

    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels,
                displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                boolean isFav = bundle.getBoolean("isFav");
                String id = bundle.getString("id");
                for (FuelStationResponseModel model : fuelStationResponseModels) {
                    if (model.getFuelStationId().equalsIgnoreCase(id)) {
                        model.setFavourite(isFav);
                        adapter.refreshData(fuelStationResponseModels);
                        break;
                    }
                }
            }
        }
    }
}
