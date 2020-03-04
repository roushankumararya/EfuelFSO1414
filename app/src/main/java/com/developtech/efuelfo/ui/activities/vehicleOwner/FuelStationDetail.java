package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddFavFuelRequestModel;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelTypeResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.FuelPricesStationDetailAdaper;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.StationInfoWindowAdapter;
import com.developtech.efuelfo.ui.dialogFragments.RequestCreditDialog;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FuelStationDetail extends MyActionBar implements OnMapReadyCallback {

    public final static int WHITE = 0xFFFFFFFF;
    public final static int BLACK = 0xFF000000;
    public final static int WIDTH = 300;
    public final static int HEIGHT = 300;
    @BindView(R.id.favoriteImage)
    ImageView favoriteImage;
    @BindView(R.id.tvStationAddress)
    TextView tvStationAddress;
    @BindView(R.id.tvPhoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.tvStationCode)
    TextView tvStationCode;
    @BindView(R.id.tvDistance)
    TextView tvDistance;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.tvFuelPrice)
    TextView tvFuelPrice;
    @BindView(R.id.ivQrCode)
    ImageView ivQrCode;
    @BindView(R.id.btnBuyNow)
    Button btnBuyNow;
    @BindView(R.id.btnRequestCredit)
    Button btnRequestCredit;
    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;

    @BindView(R.id.rvFuelPrices)
    RecyclerView rvFuelPrices;

    boolean is_favourite;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    Bitmap bmNew, bmAccept, mutableBitmap;

    private LocationRequestModel requestModel;


    NetworkListener requestCreditListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                showMsg(rootLayout, getResources().getString(R.string.credit_request_added));
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            runOnUiThread(new Runnable() {
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

    private FuelStationResponseModel fuelStationResponseModel;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_details);
        ButterKnife.bind(this);
        init();
        initComponents();
        setHomeTitle(getString(R.string.details));
        setHomeImage(true);
        callApi();
    }

    @Override
    public void initComponents() {
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        supportMapFragment.getMapAsync(this);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            fuelStationResponseModel = (FuelStationResponseModel) bundle.getSerializable("model");
            requestModel = (LocationRequestModel) bundle.getSerializable("loc_model");
            if (fuelStationResponseModel != null)
                setData();
        }
    }

    @Override
    public void retry() {

    }

    List<FuelDetailModel> fuelDetailList = new ArrayList<>();

    public void setData() {
        tvStationAddress.setText(fuelStationResponseModel.getAddress());
        tvName.setText(fuelStationResponseModel.getName());
        tvPhoneNumber.setText(fuelStationResponseModel.getMobileNumber());
        tvStationCode.setText(fuelStationResponseModel.getFuelStationId());
        tvDistance.setText(fuelStationResponseModel.getLatitude());
        tvStartTime.setText(fuelStationResponseModel.getStartTime());
        tvEndTime.setText(fuelStationResponseModel.getEndTime());
        is_favourite = fuelStationResponseModel.isFavourite();
        if (is_favourite) {
            favoriteImage.setImageResource(R.drawable.favorite_selected);
        } else {
            favoriteImage.setImageResource(R.drawable.favorite_unselected);
        }
        try {
            ivQrCode.setImageBitmap(encodeAsBitmap(fuelStationResponseModel.getId()));
        } catch (WriterException e) {
            e.printStackTrace();
        }

        if (fuelStationResponseModel.getFuelTypeResponseList()==null || fuelStationResponseModel
                .getFuelTypeResponseList().size() < 1) {
            tvFuelPrice.setText(getResources().getString(R.string.fuel_prices_not_updated));
            rvFuelPrices.setVisibility(View.GONE);
            return;
        }

        for (FuelTypeResponseModel model : fuelStationResponseModel.getFuelTypeResponseList()) {
            fuelDetailList.addAll(model.getFuelDetail());
        }


        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvFuelPrices.setLayoutManager(layoutManager);
        FuelPricesStationDetailAdaper adaper = new FuelPricesStationDetailAdaper(fuelDetailList, this);
        rvFuelPrices.setAdapter(adaper);

    }

    @OnClick({R.id.favoriteImage, R.id.btnBuyNow, R.id.btnRequestCredit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favoriteImage: {
                AddFavFuelRequestModel fuelRequestModel = new AddFavFuelRequestModel();
                fuelRequestModel.setFuelStationId(fuelStationResponseModel.getId());
                if (!is_favourite) {
                    favoriteImage.setImageResource(R.drawable.favorite_selected);
                    is_favourite = true;
                    fuelRequestModel.setFavourite("1");
                } else {
                    favoriteImage.setImageResource(R.drawable.favorite_unselected);
                    is_favourite = false;
                    fuelRequestModel.setFavourite("0");
                }
                manageFavFuel(fuelRequestModel);
                break;
            }
            case R.id.btnBuyNow: {
                Bundle bundle = new Bundle();
                bundle.putSerializable("page", "refuel_page");
                bundle.putSerializable("modeaddListenerl", fuelStationResponseModel);
                newIntent(HomeActivity.class, bundle, true);
                break;
            }
            case R.id.btnRequestCredit: {
                RequestCreditDialog dialogFragment = new RequestCreditDialog();
                dialogFragment.setData(appComponent, requestCreditListener, fuelStationResponseModel);
                dialogFragment.show(getSupportFragmentManager(), "request_credit");
                break;
            }
        }
    }

    void manageFavFuel(AddFavFuelRequestModel requestModel) {
        appComponent.getServiceCaller().callService(appComponent.getAllApis().addFavFuel(requestModel));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("id", fuelStationResponseModel.getFuelStationId());
        bundle.putBoolean("isFav", is_favourite);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iae) {
            return null;
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    104);
            return;
        }
    }

    private void addMarker(FuelStationResponseModel model) {
        if (map != null) {
            map.clear();
            if (bmNew == null) {
                bmNew = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),
                        R.drawable.location_marker_black));
            }

            Double lat = Double.parseDouble(model.getLatitude());
            Double lng = Double.parseDouble(model.getLongitude());

            LatLng latLng = new LatLng(lat, lng);
            MarkerOptions userIndicator = new MarkerOptions().position(latLng);
            mutableBitmap = bmNew.copy(Bitmap.Config.ARGB_8888, true);
            userIndicator.icon(BitmapDescriptorFactory.fromBitmap(mutableBitmap)).anchor(0.5f, 1f);
            Marker marker = map.addMarker(userIndicator);
            marker.setTag(model);

            LatLngBounds.Builder builder = LatLngBounds.builder();

            builder.include(marker.getPosition());

            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 1);
            map.setMaxZoomPreference(14f);
            map.animateCamera(cu);
        }
        map.setInfoWindowAdapter(new StationInfoWindowAdapter(this, appComponent));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 104) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Allow Permission to use this feature", Toast.LENGTH_SHORT)
                        .show();
                return;
            } else if (map != null) {

            }
        }
    }

    void callApi() {
        requestModel = new LocationRequestModel();
        requestModel.setLatitude(appComponent.getSpUtils().getLat());
        requestModel.setLongitude(appComponent.getSpUtils().getLng());
        appComponent.getServiceCaller().callService(appComponent.getAllApis()
                .getNearByFuelStation(requestModel), nearByListener);
    }

    NetworkListener nearByListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                addMarker(fuelStationResponseModel);
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {
            runOnUiThread(new Runnable() {
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
