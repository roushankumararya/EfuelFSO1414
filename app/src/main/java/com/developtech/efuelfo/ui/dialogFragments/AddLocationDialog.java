package com.developtech.efuelfo.ui.dialogFragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.listeners.CallbackListener;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.responseModel.PlacesApiModels.ParentAutoComplete;
import com.developtech.efuelfo.model.responseModel.PlacesApiModels.Predictions;
import com.developtech.efuelfo.network.Api;
import com.developtech.efuelfo.network.RestClient;
import com.developtech.efuelfo.ui.adapters.GooglePlacesAutocompleteAdapter;
import com.developtech.efuelfo.ui.fragments.fuelOwner.FuelStationFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

/**
 * Created by dt on 2/20/18.
 */

public class AddLocationDialog extends DialogFragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult>,
        OnMapReadyCallback, AdapterView.OnItemClickListener {

    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private final static int REQUEST_CHECK_SETTINGS = 2222;
    private static final String TAG = "FuelStation";
    static View view;
    @BindView(R.id.actvSearch)
    AutoCompleteTextView actvSearch;
    @BindView(R.id.tvSearch)
    TextView tvSearch;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.ivPin)
    ImageView ivPin;

    AppComponent appComponent;
    LocationManager locationManager;
    GoogleApiClient gac;
    LocationRequest locationRequest;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    CallbackListener callbackListener;
    CountDownTimer countDownTimer;
    LatToAddressTask latToAddressTask = null;
    private AlertDialog alertDialog;
    private CameraPosition cameraLoc;
    FuelStationFragment fuelStationFragment;

    String city, state, country;
    private FusedLocationProviderClient mFusedLocationClient;


    GooglePlacesAutocompleteAdapter autocompleteAdapter;


    LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            if (location != null) {
                drawLocation(location);
                if (locationManager!=null) {
                    locationManager.removeUpdates(locationListener);
                    locationManager = null;
                }
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("LocationIssue..", "latitude: ");
        }

        public void onProviderEnabled(String provider) {
            Log.d("LocationIssue", "latitude: ");
        }

        public void onProviderDisabled(String provider) {
            if (locationManager!=null)
                locationManager.removeUpdates(locationListener);
        }
    };
    private long UPDATE_INTERVAL = 1000;
    Activity activity;

    public void setData(AppComponent appComponent, CallbackListener callbackListener, Activity activity) {
        this.appComponent = appComponent;
        this.callbackListener = callbackListener;
        this.activity = activity;
        this.fuelStationFragment = fuelStationFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_location, null);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        initComponents(view);
        return alertDialog;
    }


    @SuppressLint("MissingPermission")
    private void getLastKnownLocation()
    {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);


        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location!=null)
                        {
                            drawLocation(location);
                            locationManager.removeUpdates(locationListener);
                            locationManager = null;
                        }
                    }
                });

    }

    void drawLocation(Location location)
    {
        progressBar.setVisibility(View.GONE);
        ivPin.setVisibility(View.VISIBLE);

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        LatLngBounds.Builder builder = LatLngBounds.builder();

        builder.include(latLng);

        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 150);
        map.setMaxZoomPreference(15f);
        map.animateCamera(cu);
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                cameraLoc = cameraPosition;
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });
    }

    void initComponents(View view) {
        if (isGooglePlayServicesAvailable()) {
            supportMapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
            supportMapFragment.getMapAsync(this);
        }

        autocompleteAdapter = new GooglePlacesAutocompleteAdapter(getContext(), R.layout.item_places_autocomplete, R.id.tvPlacesAutoComplete, resultPlacesList, this);
        actvSearch.setAdapter(autocompleteAdapter);

        if (!isLocationEnabled())
            showAlert();
        if (checkLocationPermission(getActivity()))
            getLastKnownLocation();
        setupGoogleClient();


        actvSearch.setOnItemClickListener(this);

    }

    @Override
    public void onDestroyView() {
        System.out.println("cancel the shit");
        if (countDownTimer != null)
            countDownTimer.cancel();
        if (latToAddressTask != null) {
            latToAddressTask.cancel(true);
        }
        super.onDestroyView();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        System.out.println("cancel the shit in dismiss ");

        super.onDismiss(dialog);
    }

    @OnClick({R.id.btnAddLocation, R.id.ivCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddLocation: {
                if (!actvSearch.getText().toString().trim().isEmpty()) {
                    AddFuelStationRequestModel address = new AddFuelStationRequestModel();
                    address.setCity(city);
                    address.setState(state);
                    address.setCountry(country);
                    address.setAddress(actvSearch.getText().toString().trim());
                    address.setLatitude(cameraLoc.target.latitude + "");
                    address.setLongitude(cameraLoc.target.longitude + "");
                    callbackListener.onAddLocation(address);
                    dismiss();
                }
                break;
            }
            case R.id.ivCancel: {
                actvSearch.setText("");
                break;
            }
        }
    }

    private void countDown() {
        countDownTimer = new CountDownTimer(1000 * 2, 1000) {

            public void onTick(long millisUntilFinished) {
                //timer.setText("seconds remaining: " +new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                //  Toast.makeText(CreateFuelStationDialogueFragment.this, "Getting Address....", Toast.LENGTH_SHORT).show();
                long ms = millisUntilFinished;
                String text = String.format("%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                        TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));

            }

            public void onFinish() {
                //Log.e("Timer","Finished");
                progressBar.setVisibility(View.VISIBLE);
                latToAddressTask = new LatToAddressTask();
                latToAddressTask.execute();
            }
        };
    }

    public void setupGoogleClient() {
        gac = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        gac.connect();
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    private boolean isGooglePlayServicesAvailable() {
        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(getActivity(), resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.d(TAG, "This device is not supported.");
//                getActivity().finish();
            }
            return false;
        }
        Log.d(TAG, "This device is supported.");
        return true;
    }

    @SuppressWarnings("MissingPermission")
    public void getLocation() {

        if (locationManager!=null)
        {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                setUpRequest();
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_INTERVAL, 100, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, UPDATE_INTERVAL, 100, locationListener);
        }

    }

    public void setUpRequest() {
        if (locationManager==null)
            return;

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
                                status.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                getLocation();
            }
        }
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && resultCode == RESULT_OK) {
            setupGoogleClient();
        }
        if (resultCode == RESULT_OK) {

        }
    }





    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (locationManager!=null)
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
        getLocation();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
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
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        countDown();
    }

    public void show(FragmentManager fragmentManager, String name) {
    }

    private class LatToAddressTask extends AsyncTask<Void, Void, Void> {
        public String mLatitude = "0.0";
        public String mLongitude = "0.0";
        public String mAddress = "";
        public String mCity = "";
        public String mState = "";
        public String mCountry = "", mStarttime = "", mEndtime = "";
        List<Address> addresses;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            System.out.println("cancel the shit is not working");

            Geocoder geocoder;
            if(getContext()==null){
                cancel(true);
                return null;
            }
            geocoder = new Geocoder(getContext(), Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(cameraLoc.target.latitude, cameraLoc.target.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (addresses != null) {
                try {
                    mAddress = (((((addresses.get(0).getAddressLine(0) == null) ? "" : (addresses.get(0).getAddressLine(0)))).equals("")) ? "" : "" + addresses.get(0).getAddressLine(0) + ",") + (((addresses.get(0).getLocality()) == null) ? "" : addresses.get(0).getLocality()); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    mCity = (((addresses.get(0).getLocality()) == null) ? "" : addresses.get(0).getLocality());
                    mState = (((addresses.get(0).getAdminArea()) == null) ? "" : addresses.get(0).getAdminArea());
                    mCountry = (((addresses.get(0).getCountryName()) == null) ? "" : addresses.get(0).getCountryName());
                    String finalString = ((((mAddress).equals("")) ? "" : mAddress + ",") + (((mState).equals("")) ? "" : mState + ",") + mCountry).replaceAll("[\\,]{2,5}", ",");
                    actvSearch.setText(finalString);
                    tvSearch.setText(finalString);
                    city = mCity;
                    state = mState;
                    country = mCountry;
                } catch (IndexOutOfBoundsException e) {
                    actvSearch.setText("");
                }
                mLatitude = cameraLoc.target.latitude + "";
                mLongitude = cameraLoc.target.longitude + "";
                //Log.e("Country1", mCountry + "");
            } else {
                Snackbar.make(getView(), R.string.please_check_internet, Snackbar.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }
    }


    List<Predictions> predictionsList = new ArrayList<>();
    private LocationRequestModel requestModel;
    public static final String GOOGLE_PLACES_BASE_URL = "https://maps.googleapis.com/maps/api/";
    ParentAutoComplete parentAutoComplete;
    ArrayList resultPlacesList = new ArrayList();

    public void autoComplete(String input) {
        Retrofit client = RestClient.build(GOOGLE_PLACES_BASE_URL);

        Api api = client.create(Api.class);

        Call<ParentAutoComplete> call = api.getPlaces(input);

        call.enqueue(new Callback<ParentAutoComplete>() {
            @Override
            public void onResponse(Call<ParentAutoComplete> call, Response<ParentAutoComplete> response) {

                if (response.code() == 200) {
                    parentAutoComplete = response.body();

                    if (resultPlacesList.size() > 0) {
                        resultPlacesList.clear();
                    }

                    if (predictionsList.size() > 0) {
                        predictionsList.clear();
                    }

                    if (parentAutoComplete.getPredictions().size() > 0) {
                        for (int i = 0; i < parentAutoComplete.getPredictions().size(); i++) {
                            Predictions predictions = parentAutoComplete.getPredictions().get(i);
                            predictionsList.add(predictions);
                            resultPlacesList.add(predictions.getDescription());

                        }
                    }

                    autocompleteAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ParentAutoComplete> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String placeId = predictionsList.get(i).getPlace_id();


        requestModel = new LocationRequestModel();

        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                .getPlaceById(gac, placeId);
        placeResult.setResultCallback(new ResultCallbacks<PlaceBuffer>() {
            @Override
            public void onSuccess(@NonNull PlaceBuffer places) {

                Place place = places.get(0);
                final LatLng placelatLng = place.getLatLng();
                requestModel.setLatitude(placelatLng.latitude + "");
                requestModel.setLongitude(placelatLng.longitude + "");

                CameraPosition position =
                        new CameraPosition.Builder().target(new LatLng(placelatLng.latitude, placelatLng.longitude))
                                .zoom(15f)
                                .build();
                map.moveCamera(CameraUpdateFactory.newCameraPosition(position));

            }

            @Override
            public void onFailure(@NonNull Status status) {

            }
        });
    }

}
