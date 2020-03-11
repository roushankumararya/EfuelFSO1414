package com.developtech.efuelfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developtech.efuelfo.customs.AwesomeToggle;
import com.developtech.efuelfo.interfaces.MyCallback;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.FuelStationAvailabilityRequestModel;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.model.requestModel.RegisterRequest;
import com.developtech.efuelfo.model.responseModel.ExtraNotificationModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.activities.stationOwner.RequestPendingActivity;
import com.developtech.efuelfo.ui.activities.stationOwner.TransactionDetailsActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.UserProfileActivity;
import com.developtech.efuelfo.ui.dialogFragments.SelectFuelStation;
import com.developtech.efuelfo.ui.dialogFragments.SelectVehicleOwnerDialog;
import com.developtech.efuelfo.ui.fragments.BaseFragment;
import com.developtech.efuelfo.ui.fragments.NotificationsFragment;
import com.developtech.efuelfo.ui.fragments.ReportFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.CashTransactionFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.FuelOwnerHomeFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.FuelPricesFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.FuelStationFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.OperatorsFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.ParkTransactionFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.SaleHistoryInvoicesFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.SaleInitiationFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.TankTypeFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.TotalSaleFuelTypeFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.VerifyTransactionFragment;
import com.developtech.efuelfo.ui.fragments.fuelStaff.StaffHomeFragment;
import com.developtech.efuelfo.ui.fragments.vehicleDriver.DriverHomeFragment;
import com.developtech.efuelfo.ui.fragments.vehicleDriver.DriverTransactionFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.CreditAgreementFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.DriverVehicleFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.ExpenseFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.FavouritesFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.HomeFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.NearByFuelStationFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.PayPalFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.PaymentHistoryFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.RefuelFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.ServiceFragment;
import com.developtech.efuelfo.ui.fragments.vehicleOwner.TrackDriverFragment;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeDashboard extends MyActionBar implements
        NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<LocationSettingsResult>, AwesomeToggle.OnCheckedChangeListner {

    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final String TAG = "DriverHome";
    private final static int REQUEST_CHECK_SETTINGS = 2222;

    public HomeActivity homeActivity;

    public NavigationView navigationView;
    LocationManager locationManager;
    GoogleApiClient gac;
    LocationRequest locationRequest;
    BaseFragment baseFragment;
    public Double latitude, longitude;

    LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            if (location != null) {
                appComponent.getSpUtils().saveCurrentLatLng(location.getLatitude() + "", location.getLongitude() + "");
                updateLocApi(location);
                Log.d("LocationIssue", "latitude: " + location.getLatitude() + ", lng: " + location.getLongitude());
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("LocationIssue..", "latitude: ");
        }

        public void onProviderEnabled(String provider) {
            Log.d("LocationIssue", "latitude: ");
        }

        public void onProviderDisabled(String provider) {
            locationManager.removeUpdates(locationListener);
        }
    };


    NetworkListener getFuelStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<GetFuelStationResponseModel> allFuelStationsList = (List<GetFuelStationResponseModel>)
                        responseBody.getResutData();
                if (allFuelStationsList.size() == 1) {
                    appComponent.getSpUtils().saveFuelStationsList(allFuelStationsList);
                    appComponent.getSpUtils().saveFuelStation(allFuelStationsList.get(0));
                    newIntentClear(HomeDashboard.class, null, true);
                } else if (allFuelStationsList.size() > 1) {
                    appComponent.getSpUtils().saveFuelStationsList(allFuelStationsList);
                    SelectFuelStation dialogFragment = new SelectFuelStation();
                    dialogFragment.setData(appComponent, allFuelStationsList, HomeDashboard.this);
                    dialogFragment.show(getSupportFragmentManager(), "select_owner");
                }

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


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.rootlayout)
    CoordinatorLayout rootLayout;

    @BindView(R.id.tvCount)
    TextView tvCount;


    SPUtils.ACCOUNT_TYPES account_types;
    View.OnClickListener headerClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.layHeader: {
                    Intent intent = new Intent(HomeDashboard.this, UserProfileActivity.class);
                    startActivityForResult(intent, 420);
                    break;
                }
                case R.id.laySwitchOwner: {

                    switch (appComponent.getSpUtils().getAccountType()) {
                        case DRV: {
                            List<VehicleOwnerResponseModel> list = appComponent.getSpUtils().getVehicleOwnerResponse();
                            SelectVehicleOwnerDialog dialogFragment = SelectVehicleOwnerDialog.newInstance("driver", list);
                            dialogFragment.setData(appComponent);
                            dialogFragment.show(getSupportFragmentManager(), "select_owner");
                            break;
                        }
                        case FSO: {

                            closeDrawer();

                            appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(),
                                    getFuelStationListener);

                            break;
                        }
                        case OPR: {
                            drawer_layout.closeDrawer(Gravity.START, true);
                            showProgress();
                            appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStationOwners(), getOwnersListener);
                        }
                    }


                    break;
                }
            }
        }
    };
    SelectImage selectImage;
    private android.location.LocationListener myLocationListener;
    private long UPDATE_INTERVAL = 1000;
    private Bundle bundle;
    private ImageView ivProfilePic;
    private TextView tvUserName, tvViewProfile, tvOwnerName, tvClickToSwitch, txtOpenClose;
    private LinearLayout layHeader, laySwitchOwner, lytToggle;
    AwesomeToggle awesomeToggle;
    private android.support.v4.app.ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        init();
        initComponents();
        setMenuItem();
    }

    @Override
    public void initComponents() {
        navigationView = (NavigationView) findViewById(R.id.navigation);




        //navigationView.getMenu().clear();

       // navigationView.getMenu();
       // account_types =SPUtils.ACCOUNT_TYPES.FSO;
        // if (appComponent.getSpUtils().getAccountType() != null) {
             navigationView.inflateMenu(R.menu.drawer_main_drawer_stationowner);
        if (account_types != null) {
            // account_types = appComponent.getSpUtils().getAccountType();
            // account_types =SPUtils.ACCOUNT_TYPES.FSO;
            //if (account_types != null) {
            if (account_types == SPUtils.ACCOUNT_TYPES.VCO) {
                navigationView.inflateMenu(R.menu.drawer_main_drawer);
            } else if (account_types == SPUtils.ACCOUNT_TYPES.DRV) {
                navigationView.inflateMenu(R.menu.drawer_main_drawer_driver);
            } else if (account_types == SPUtils.ACCOUNT_TYPES.FSO) {
                navigationView.inflateMenu(R.menu.drawer_main_drawer_stationowner);
            } else if (account_types == SPUtils.ACCOUNT_TYPES.OPR) {
                navigationView.inflateMenu(R.menu.drawer_main_drawer_operator);
            }
            //  }
        } else {
           account_types = SPUtils.ACCOUNT_TYPES.FSO;
            if (account_types != null) {
                if (account_types == SPUtils.ACCOUNT_TYPES.VCO) {
                    navigationView.inflateMenu(R.menu.drawer_main_drawer);
                } else if (account_types == SPUtils.ACCOUNT_TYPES.DRV) {
                    navigationView.inflateMenu(R.menu.drawer_main_drawer_driver);
                } else if (account_types == SPUtils.ACCOUNT_TYPES.FSO) {
                    navigationView.inflateMenu(R.menu.drawer_main_drawer_stationowner);
                } else if (account_types == SPUtils.ACCOUNT_TYPES.OPR) {
                    navigationView.inflateMenu(R.menu.drawer_main_drawer_operator);
                }
            }

        }
   // }
        View headerView = navigationView.getHeaderView(0);
        tvUserName = headerView.findViewById(R.id.tvUserName);
        txtOpenClose = headerView.findViewById(R.id.txtOpenClose);
        tvOwnerName = headerView.findViewById(R.id.tvOwnerName);
        ivProfilePic = headerView.findViewById(R.id.ivProfilePic);
        layHeader = headerView.findViewById(R.id.layHeader);
        laySwitchOwner = headerView.findViewById(R.id.laySwitchOwner);
        lytToggle = headerView.findViewById(R.id.lytToggle);
        tvClickToSwitch = headerView.findViewById(R.id.tvClickToSwitch);
        awesomeToggle = headerView.findViewById(R.id.awsmToggle);
        layHeader.setOnClickListener(headerClick);
        laySwitchOwner.setOnClickListener(headerClick);
        awesomeToggle.setOnCheckedChangeListner(this);

//        if (appComponent.getSpUtils().getAccountType() != null) {
//            bundle = getIntent().getExtras();
//            if (bundle!=null && bundle.getString("id")!=null)
//            {
//                Fragment fragment = new CreditAgreementFragment();
//                setHomeTitle(getString(R.string.creditagreement));
//                setHomeImage(true);
//                pushFragment(fragment);
//            }
//            else
//            {
//                setHomeFrag();
//            }
//
//        }

       setHomeFrag();

        if (appComponent.getSpUtils().getUserData() != null && appComponent.getSpUtils().getUserData().getImage() != null
                && !appComponent.getSpUtils().getUserData().getImage().trim().isEmpty()) {
            Picasso.with(this).load(appComponent.getAllUrls().BASE_IMAGE_URL +
                    appComponent.getSpUtils().getUserData().getImage()).placeholder(R.drawable.place_holder)
                    .into(ivProfilePic);
        }

        tvUserName.setText(appComponent.getSpUtils().getName());


        if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.FSO) {
            if (appComponent.getSpUtils().getFuelStationModel() != null) {
                awesomeToggle.setIsChecked(appComponent.getSpUtils().getFuelStationModel().getIsOpen());
                if (appComponent.getSpUtils().getFuelStationModel().getIsOpen()) {
                    txtOpenClose.setText("Open");
                } else {
                    txtOpenClose.setText("Closed");
                }

            }
        }


        navigationView.setNavigationItemSelectedListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.drawable.drawer_icon,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                drawer_layout.getChildAt(0).setTranslationX(slideOffset * drawerView.getWidth());
                rootLayout.setTranslationX(slideOffset * drawerView.getWidth());
                drawer_layout.bringChildToFront(drawerView);
                drawer_layout.requestLayout();
            }
        };
        drawer_layout.setScrimColor(getResources().getColor(android.R.color.transparent));
        drawer_layout.setDrawerListener(mDrawerToggle);
        bundle = getIntent().getExtras();


        if (bundle != null) {
            String page = bundle.getString("page");
            if (page != null && page.equalsIgnoreCase("refuel_page")) {
                RefuelFragment refuelFragment = new RefuelFragment();
                refuelFragment.setArguments(bundle);
                pushFragment(refuelFragment);
                setHomeTitle(getString(R.string.refuel_caps));
                setHomeImage(true);
                showNotification(false);
            }

        }


        if (!appComponent.getSpUtils().isRegistered() && !appComponent.getSpUtils().getFirebaseToken().isEmpty()) {
            RegisterRequest request = new RegisterRequest();
            request.deviceId = getDeviceId();
            request.setPushToken(appComponent.getSpUtils().getFirebaseToken());
            request.setDeviceType("ANDROID");
            appComponent.getServiceCaller().callService(appComponent.getAllApis().registerDevice(request),
                    new NetworkListener() {
                        @Override
                        public void onSuccess(ResultModel<?> responseBody) {
                            if (!responseBody.getResultCode().equalsIgnoreCase("OK")) {
                                onError(responseBody.getMessage());
                                return;
                            }
                            appComponent.getSpUtils().saveIsRegistered(true);
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
                    });
        }
        navigationView.getMenu().findItem(R.id.nav_operator_home).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_fuelprice).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_initiatesale).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_saleshistory).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_credit_agreement_list).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_park_trans).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_verify_trans).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_tank_types).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_totalsale).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_fuel_station).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_operator_cashtransaction).setVisible(true);

        // navigationView.getMenu().findItem(R.id.nav_operator_cashtransaction).setVisible(true);


        navigationView.getMenu().findItem(R.id.nav_station_ow_home).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_fuel_prices).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_sale_init).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_operator).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_sale_history).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_credit_agreement_list).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_park_trans).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_verify_transaction).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_tank_types).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_total_sale_by_fuel_type).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_fuel_station).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_cash_transaction).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_station_ow_reports).setVisible(true);

        if (appComponent.getSpUtils().isDeleted()) {
            showAccDeletedDialog(appComponent.getSpUtils().getNotiMsg(), "DELETED");
        }

        if (appComponent.getSpUtils().getIsManagerChanged()) {
            showAccDeletedDialog(appComponent.getSpUtils().getNotiMsg(), "MANAGER");
        }

    }

    final String TRANS = "TRANS", CREDIT = "CREDIT", VERIFY = "VERIFY", DELETED = "DELETED", MANAGER_TRUE = "MANAGER_TRUE", MANAGER_FALSE = "MANAGER_FALSE";

    public void handleNoti() {
        if (bundle != null && bundle.getString("type") != null) {
            String type = bundle.getString("type");
            ExtraNotificationModel model = new Gson().fromJson(bundle.getString("extra"), ExtraNotificationModel.class);
            String action = bundle.getString("action_noti");

            Bundle b = new Bundle();
            if (model != null && model.getRequestId() != null) {
                b.putString("id", model.getRequestId());
            }
            if (type.equalsIgnoreCase(appComponent.getSpUtils().getAccountType().toString())) {
                if (action.equalsIgnoreCase(TRANS)) {
                    newIntent(TransactionDetailsActivity.class, b, false);
                } else if (action.equalsIgnoreCase(CREDIT)) {
                    newIntent(RequestPendingActivity.class, b, false);
                } else if (action.equalsIgnoreCase(VERIFY)) {
                    b.putString("fuelStationId", model.getFuelStationId());
                    b.putString("action_noti", VERIFY);
                    Fragment verifyFrag = new VerifyTransactionFragment();
                    verifyFrag.setArguments(b);
                    pushFragment(verifyFrag);
                    setHomeTitle(getString(R.string.verifytransaction));
                    setHomeImage(true);
                    showNotification(false);
                } else if (action.equalsIgnoreCase(DELETED)) {
                    showAccDeletedDialog(bundle.getString("message"), "DELETED");
                } else if (action.equalsIgnoreCase(MANAGER_TRUE) || action.equalsIgnoreCase(MANAGER_FALSE)) {
                    if (action.equalsIgnoreCase(MANAGER_TRUE)) {
                        bundle.getString("MANAGER", MANAGER_TRUE);
                        GetFuelStationResponseModel fsModel = appComponent.getSpUtils().getFuelStationModel();
                        List<OperatorsResponseModel> oprList = fsModel.getOperator();
                        for (OperatorsResponseModel oprModel : oprList) {
                            if (appComponent.getSpUtils().getUserData().getId().equals(oprModel.getId())) {
                                oprModel.setManager(true);
                            } else {
                                oprModel.setManager(false);
                            }
                        }
                        fsModel.setOperator(oprList);
                        appComponent.getSpUtils().saveFuelStation(fsModel);
                    } else if (action.equalsIgnoreCase(MANAGER_FALSE)) {
                        bundle.getString("MANAGER", MANAGER_FALSE);
                        GetFuelStationResponseModel fsModel = appComponent.getSpUtils().getFuelStationModel();
                        List<OperatorsResponseModel> oprList = fsModel.getOperator();
                        for (OperatorsResponseModel oprModel : oprList) {
                            if (appComponent.getSpUtils().getUserData().getId().equals(oprModel.getId())) {
                                oprModel.setManager(false);
                            }
                        }
                        fsModel.setOperator(oprList);
                        appComponent.getSpUtils().saveFuelStation(fsModel);
                    }

                    showAccDeletedDialog(bundle.getString("message"), "MANAGER");
                }
            } else if (type.equalsIgnoreCase("MANAGER")) {
                showOperatorManager();
            }

            bundle = null;
        }
    }

    public boolean isOpratorManager;

    public void showOperatorManager() {
        List<OperatorsResponseModel> oprList = appComponent.getSpUtils().getFuelStationModel().getOperator();
        if (oprList != null && oprList.size() > 0) {
            for (OperatorsResponseModel model : oprList) {
                if (appComponent.getSpUtils().getUserData().getId().equals(model.getId())) {
                    if (model.isManager()) {
                        isOpratorManager = true;
                        navigationView.getMenu().findItem(R.id.nav_operator).setVisible(true);
                        navigationView.getMenu().findItem(R.id.nav_operator_credit_agreement_list).setVisible(true);
                        navigationView.getMenu().findItem(R.id.nav_operator_park_trans).setVisible(true);
                        navigationView.getMenu().findItem(R.id.nav_operator_tank_types).setVisible(true);
                    } else {
                        isOpratorManager = false;
                        navigationView.getMenu().findItem(R.id.nav_operator).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_operator_credit_agreement_list).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_operator_park_trans).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_operator_tank_types).setVisible(false);
                    }
                }
            }
        }
    }

    @Override
    public void retry() {

    }

    void setHomeFrag() {
        switch (account_types) {
            case FSO: {
                laySwitchOwner.setVisibility(View.VISIBLE);
                lytToggle.setVisibility(View.VISIBLE);
                tvClickToSwitch.setText(getResources().getString(R.string.clicktoswitchfuel));
                tvOwnerName.setText(appComponent.getSpUtils().getFuelStationModel().getName());
                awesomeToggle.setIsChecked(appComponent.getSpUtils().getFuelStationModel().getIsOpen());
                if (appComponent.getSpUtils().getFuelStationModel().getIsOpen()) {
                    txtOpenClose.setText("Open");
                } else {
                    txtOpenClose.setText("Closed");
                }
                pushFragment(new FuelOwnerHomeFragment());
                navigationView.setCheckedItem(R.id.nav_station_ow_home);
                break;
            }
            case OPR: {
                laySwitchOwner.setVisibility(View.VISIBLE);
                tvClickToSwitch.setText(getResources().getString(R.string.clicktoswitchfuel));
                tvOwnerName.setText(appComponent.getSpUtils().getFuelStationModel().getName());
                pushFragment(new StaffHomeFragment());
                break;
            }
            case VCO: {

                setHomeImage(false);
                showNotification(true);
                pushFragment(new HomeFragment());

                isGooglePlayServicesAvailable();
                if (!isLocationEnabled())
                    showAlert();
                if (checkLocationPermission(this))
                    setupGoogleClient();

                navigationView.setCheckedItem(R.id.nav_home);
                break;
            }
            case DRV: {
                laySwitchOwner.setVisibility(View.VISIBLE);
                VehicleOwnerResponseModel model = appComponent.getSpUtils().getVehicleOwnerResponseModel();
                if (model != null)
                    tvOwnerName.setText(model.getFirstName() + " " + model.getLastName());
                isGooglePlayServicesAvailable();
                if (!isLocationEnabled())
                    showAlert();
                if (checkLocationPermission(this))
                    setupGoogleClient();
                pushFragment(new DriverHomeFragment());
                break;
            }
        }
    }

    public void openDrawer() {
        hideKB();
        drawer_layout.openDrawer(GravityCompat.START);
    }

    public void closeDrawer() {
        hideKB();
        drawer_layout.closeDrawer(GravityCompat.START);
    }

    @OnClick({R.id.ivDrawer, R.id.ivBack, R.id.ivFilter, R.id.ivNotification})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivNotification: {
                pushFragment(new NotificationsFragment());
                setHomeTitle(getString(R.string.notification));
                setHomeImage(true);
                break;
            }
            case R.id.ivDrawer: {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    closeDrawer();
                } else {
                    openDrawer();
                }
                break;
            }
            case R.id.ivBack: {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    closeDrawer();
                } else {
                    pushFragment(getHomeFragment());
                    setHomeTitle(getString(R.string.home));
                    setHomeImage(false);
                }
                break;
            }
            case R.id.ivFilter: {
                if (baseFragment != null) {
                    baseFragment.onFilterClick();
                }
            }
        }

    }

    Fragment getHomeFragment() {
        switch (account_types) {
            case DRV: {
                return new DriverHomeFragment();
            }
            case FSO: {
                navigationView.setCheckedItem(R.id.nav_station_ow_home);
                return new FuelOwnerHomeFragment();
            }
            case OPR: {
                return new StaffHomeFragment();
            }
            case VCO: {
                navigationView.setCheckedItem(R.id.nav_home);
                return new HomeFragment();
            }
        }
        return null;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home: {
                fragment = getHomeFragment();
                setHomeTitle(getString(R.string.home));
                setHomeImage(false);
                break;
            }
            case R.id.nav_refuel: {
                fragment = new RefuelFragment();
                setHomeTitle(getString(R.string.refuel_caps));
                setHomeImage(true);
                break;
            }
            case R.id.nav_driver_vehicle: {
                fragment = new DriverVehicleFragment();
                setHomeTitle(getString(R.string.myvehicleanddrivers));
                setHomeImage(true);
                break;
            }
            case R.id.nav_track_driver: {
                fragment = new TrackDriverFragment();
                setHomeTitle(getString(R.string.track_driver));
                setHomeImage(true);
                break;
            }
            case R.id.nav_near_by_fuel_station: {
                fragment = new NearByFuelStationFragment();
                setHomeTitle(getString(R.string.near_by_st));
                setHomeImage(true);
                break;
            }
            case R.id.nav_payment_history: {
                fragment = new PaymentHistoryFragment();
                setHomeTitle(getString(R.string.transactions_caps));
                setHomeImage(true);
                break;
            }
            case R.id.nav_credit_agreements: {
                fragment = new CreditAgreementFragment();
                setHomeTitle(getString(R.string.creditagreement));
                setHomeImage(true);
                break;
            }
            case R.id.nav_pay4pal: {
                fragment = new PayPalFragment();
                setHomeTitle(getString(R.string.pay4pal));
                setHomeImage(true);
                break;
            }
            case R.id.nav_favourites: {
                fragment = new FavouritesFragment();
                setHomeTitle(getString(R.string.favarites));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_home: {
                fragment = new FuelOwnerHomeFragment();
                setHomeTitle(getString(R.string.home));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_fuel_prices: {
                fragment = new FuelPricesFragment();
                setHomeTitle(getString(R.string.fuel_prices));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_sale_init: {
                if (!appComponent.getSpUtils().getFuelStationModel().getFuelStationVerified()) {
                    showMsg(getString(R.string.station_not_verified));

                } else {
                    fragment = new SaleInitiationFragment();
                    setHomeTitle(getString(R.string.saleinitiation));
                    setHomeImage(true);
                }
                break;
            }
            case R.id.nav_station_ow_operator: {
                fragment = new OperatorsFragment();
                setHomeTitle(getString(R.string.operators));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_sale_history: {
                fragment = new SaleHistoryInvoicesFragment();
                setHomeTitle(getString(R.string.transaction_log));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_park_trans: {
                fragment = new ParkTransactionFragment();
                setHomeTitle(getString(R.string.park_transaction));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_credit_agreement_list: {
                fragment = new CreditAgreementFragment();
                setHomeTitle(getString(R.string.creditagreement));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_verify_transaction: {
                if (!appComponent.getSpUtils().getFuelStationModel().getFuelStationVerified()) {
                    showMsg(getString(R.string.station_not_verified));

                } else {
                    fragment = new VerifyTransactionFragment();
                    setHomeTitle(getString(R.string.verifytransaction));
                    setHomeImage(true);
                }
                break;
            }
            case R.id.nav_station_ow_tank_types: {
                fragment = new TankTypeFragment();
                setHomeTitle(getString(R.string.tanktypes));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_total_sale_by_fuel_type: {
                fragment = new TotalSaleFuelTypeFragment();
                setHomeTitle(getString(R.string.total_sale_caps));
                setHomeImage(true);
                break;
            }
//            case R.id.nav_station_ow_total_sale: {
//                fragment = new TotalSaleOperatorsFragment();
//                setHomeTitle(getString(R.string.total_sale));
//                setHomeImage(true);
//                break;
//            }
            case R.id.nav_station_ow_fuel_station: {
                fragment = new FuelStationFragment();
                setHomeTitle(getString(R.string.fuel_station));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_cash_transaction: {
                fragment = new CashTransactionFragment();
                setHomeTitle(getString(R.string.cash_transaction));
                setHomeImage(true);
                break;
            }
            case R.id.nav_station_ow_reports: {
                fragment = new ReportFragment();
                setHomeTitle(getResources().getString(R.string.reports));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_home: {
                StaffHomeFragment homeFragment = new StaffHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.content_frame, homeFragment, homeFragment.getClass().getName())
                        .addToBackStack(homeFragment.getClass().getName()).commit();
                break;
            }
            case R.id.nav_operator_fuelprice: {
                if (isOpratorManager) {
                    fragment = new FuelPricesFragment();
                    setHomeTitle(getString(R.string.fuel_station));
                } else {
                    fragment = new com.developtech.efuelfo.ui.fragments.fuelStaff.FuelPricesFragment();
                    setHomeTitle(getString(R.string.fuel_prices));
                }
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator: {
                fragment = new OperatorsFragment();
                setHomeTitle(getString(R.string.operators));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_credit_agreement_list: {
                fragment = new CreditAgreementFragment();
                setHomeTitle(getString(R.string.creditagreement));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_park_trans: {
                fragment = new ParkTransactionFragment();
                setHomeTitle(getString(R.string.park_transaction));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_tank_types: {
                fragment = new TankTypeFragment();
                setHomeTitle(getString(R.string.tanktypes));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_verify_trans: {
                fragment = new VerifyTransactionFragment();
                setHomeTitle(getString(R.string.verifytransaction));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_saleshistory: {
                fragment = new SaleHistoryInvoicesFragment();
                setHomeTitle(getString(R.string.transaction_log));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_initiatesale: {
                fragment = new SaleInitiationFragment();
                setHomeTitle(getString(R.string.saleinitiation));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_totalsale: {
                fragment = new TotalSaleFuelTypeFragment();
                setHomeTitle(getString(R.string.total_sale_caps));
                setHomeImage(true);
                break;
            }
            case R.id.nav_operator_cashtransaction: {
                fragment = new CashTransactionFragment();
                setHomeTitle(getString(R.string.cash_transaction));
                setHomeImage(true);
                break;
            }
            case R.id.nav_driver_refuel: {
                fragment = new RefuelFragment();
                setHomeTitle(getString(R.string.refuel));
                setHomeImage(true);
                break;
            }
            case R.id.nav_driver_nearbyfuel: {
                fragment = new NearByFuelStationFragment();
                setHomeTitle(getString(R.string.near_by_st));
                setHomeImage(true);
                break;
            }
            case R.id.nav_driver_payment_history: {
                fragment = new DriverTransactionFragment();
                setHomeTitle(getString(R.string.transaction_log));
                setHomeImage(true);
                break;
            }
            case R.id.nav_expense: {
                fragment = new ExpenseFragment();
                setHomeTitle(getString(R.string.expense));
                setHomeImage(true);
                break;
            }
            case R.id.nav_service: {
                fragment = new ServiceFragment();
                setHomeTitle(getString(R.string.service));
                setHomeImage(true);
                break;
            }
        }
        pushFragment(fragment);
        closeDrawer();
        return true;
    }

    public void pushFragment(Fragment fragment) {
        if (fragment != null) {
            if (fragment.getClass().getName().equals((String) getHomeClassName())) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment,
                        fragment.getClass().getName())
                        .addToBackStack(fragment.getClass().getName()).commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
            }
        }
    }

    public Object getHomeClassName() {
        switch (account_types) {
            case DRV: {
                return DriverHomeFragment.class.getName();
            }
            case FSO: {
                return FuelOwnerHomeFragment.class.getName();
            }
            case OPR: {
                return StaffHomeFragment.class.getName();
            }
            case VCO: {
                return HomeFragment.class.getName();
            }
        }
        return "";
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.START)) {
            closeDrawer();
        } else {
            if (baseFragment instanceof TrackDriverFragment && ((TrackDriverFragment) baseFragment)
                    .ivMapList.getVisibility() == View.VISIBLE) {
                ((TrackDriverFragment) baseFragment).ivMapList.performClick();
                return;
            }
            if (baseFragment instanceof NearByFuelStationFragment && ((NearByFuelStationFragment) baseFragment)
                    .recycleStationList.getVisibility() == View.GONE) {
                ((NearByFuelStationFragment) baseFragment).ivMap.performClick();
                return;
            }
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {

                // Check if last fragment in back stack is Services Fragment
                if (fm.findFragmentByTag((String) getHomeClassName()).isVisible()) {
                    finish();
                } else {
                    Fragment fragment = null;
                    FragmentTransaction ft = fm.beginTransaction();

                    switch (account_types) {
                        case DRV: {
                            fragment = new DriverHomeFragment();

                            break;
                        }
                        case VCO: {
                            fragment = new HomeFragment();
                            navigationView.setCheckedItem(R.id.nav_home);
                            break;
                        }
                        case FSO: {
                            fragment = new FuelOwnerHomeFragment();
                            navigationView.setCheckedItem(R.id.nav_station_ow_home);
                            break;
                        }
                        case OPR: {
                            fragment = new StaffHomeFragment();
                            navigationView.setCheckedItem(R.id.nav_operator_home);
                            break;
                        }
                    }
                    ft.replace(R.id.content_frame, fragment, fragment.getClass().getName());
                    ft.commit();
                    setHomeTitle(getResources().getString(R.string.home));
                    setHomeImage(false);
                }
            } else {
                finish();
            }
        }
    }

    public void setSelectImage(SelectImage selectImage) {
        this.selectImage = selectImage;
        selectImage.showDialog();
    }

    public Uri getResultUri() {
        if (selectImage == null)
            return null;
        return selectImage.getResultUri();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectImage.IMAGE_REQUEST_GALLERY_CODE || requestCode == SelectImage.IMAGE_REQUEST_CAMERA_CODE || requestCode == UCrop.REQUEST_CROP)
            selectImage.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                getLocation();
            }
        }
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && resultCode == Activity.RESULT_OK) {
            setupGoogleClient();
        }
        if (requestCode == 420 && resultCode == RESULT_OK) {
//            View headerView = navigationView.getHeaderView(0);
//            tvUserName = headerView.findViewById(R.id.tvUserName);
//            tvOwnerName = headerView.findViewById(R.id.tvOwnerName);
//            ivProfilePic = headerView.findViewById(R.id.ivProfilePic);
            SignInResponseModel userResponseModel = appComponent.getSpUtils().getUserData();
            tvUserName.setText(userResponseModel.getFirstName() + " " + userResponseModel.getLastName());
            Picasso.with(this).load(appComponent.getUtilFunctions().getImageFullUrl(userResponseModel.getImage())).placeholder(R.drawable.place_holder).into(ivProfilePic);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SelectImage.IMAGE_REQUEST_GALLERY_CODE || requestCode == SelectImage.IMAGE_REQUEST_CAMERA_CODE || requestCode == UCrop.REQUEST_CROP)
            selectImage.onRequestPermissionChange(requestCode, permissions, grantResults);

        else if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupGoogleClient();

                if (permissionCallbackListener != null) {
                    permissionCallbackListener.permissionGranted();
                }
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        setUpRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    @SuppressWarnings("MissingPermission")
    public void getLocation() {
        if (locationManager != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, UPDATE_INTERVAL, 100, locationListener);
        }
    }

    public void setUpRequest() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        } else {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);
            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(gac, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                    Status status = locationSettingsResult.getStatus();
                    LocationSettingsStates states = locationSettingsResult.getLocationSettingsStates();

                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS: {
                            getLocation();
                            break;
                        }
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED: {
                            try {
                                status.startResolutionForResult(HomeDashboard.this, REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE: {
//                        finish();
                            break;
                        }
                    }
                }
            });
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public boolean checkLocationPermission(Activity activity) {
        boolean permission = false;
        int hasLocationPerm = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasLocationPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            permission = true;
        }
        return permission;
    }

    public void updateLocApi(Location location) {
        if (appComponent.getSpUtils().getAccountType() != SPUtils.ACCOUNT_TYPES.DRV)
            return;

        LocationRequestModel model = new LocationRequestModel();
        model.setLongitude(String.valueOf(location.getLongitude()));
        model.setLatitude(String.valueOf(location.getLatitude()));
        model.setDriverId(appComponent.getSpUtils().getUserData().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().trackDriver(model));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (gac != null) {
            gac.disconnect();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        System.out.println("-------------------- onAttachFragment: " + fragment.getClass().getName());
        if (fragment instanceof DialogFragment)
            return;

        if (fragment instanceof SupportMapFragment)
            return;

        if (fragment.getClass().getName().equals("com.google.android.gms.common.api.internal.zzdb")) {
            return;
        }
        try {
            baseFragment = (BaseFragment) fragment;

        } catch (Exception e) {
            System.out.println(fragment instanceof BaseFragment);
            e.printStackTrace();
        }
    }


    public void setupGoogleClient() {
        gac = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        gac.connect();
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private boolean isGooglePlayServicesAvailable() {
        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.d(TAG, "This device is not supported.");
//                getActivity().finish();
            }
            return false;
        }
        Log.d(TAG, "This device is supported.");
        return true;
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location").setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        paramDialogInterface.dismiss();
                    }
                });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ivProfilePic != null) {
            if (appComponent.getSpUtils().getUserData() != null && appComponent.getSpUtils().getUserData().getImage() != null
                    && !appComponent.getSpUtils().getUserData().getImage().trim().isEmpty()) {
                Picasso.with(this).load(appComponent.getAllUrls().BASE_IMAGE_URL + appComponent.getSpUtils().getUserData().getImage()).placeholder(R.drawable.place_holder).into(ivProfilePic);
            }
        }
    }

    boolean isAvailable;

    @Override
    public void onChecked(boolean isChecked) {
        FuelStationAvailabilityRequestModel requestModel = new FuelStationAvailabilityRequestModel();
        if (isChecked) {
            requestModel.setIsAvailable("1");
            txtOpenClose.setText("Open");
        } else {
            requestModel.setIsAvailable("0");
            txtOpenClose.setText("Closed");
        }

        isAvailable = isChecked;

        requestModel.setFuelStationId(appComponent.getSpUtils().getFuelStationModel().getId());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().setStationAvailability(requestModel), stationAvailabilityListener);
    }

    NetworkListener stationAvailabilityListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                if (isAvailable) {
                    showMsg(drawer_layout, getResources().getString(R.string.station_available));
                } else {
                    showMsg(drawer_layout, getResources().getString(R.string.station_unavailable));
                }
                GetFuelStationResponseModel fuelStationModel = appComponent.getSpUtils().getFuelStationModel();
                fuelStationModel.setIsOpen(isAvailable);
                appComponent.getSpUtils().saveFuelStation(fuelStationModel);
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

    NetworkListener getOwnersListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<GetFuelStationResponseModel> allFuelStationsList = (List<GetFuelStationResponseModel>) responseBody.getResutData();
                SelectFuelStation dialogFragment = new SelectFuelStation();
                dialogFragment.setData(appComponent, allFuelStationsList, HomeDashboard.this);
                dialogFragment.show(getSupportFragmentManager(), "select_owner");
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

    MyCallback permissionCallbackListener;

    public void setPermissionCallbackListener(MyCallback permissionCallbackListener) {
        this.permissionCallbackListener = permissionCallbackListener;
    }
}
