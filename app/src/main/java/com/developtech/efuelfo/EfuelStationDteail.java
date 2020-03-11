package com.developtech.efuelfo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.listeners.CallbackListener;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.fuelStation.StationRegistrationActivity;
import com.developtech.efuelfo.ui.adapters.stationOwner.FuelStationAdapter;
import com.developtech.efuelfo.ui.dialogFragments.AddLocationDialog;
import com.developtech.efuelfo.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EfuelStationDteail extends AppCompatActivity implements OnItemClickListener, CallbackListener {
    public AppComponent appComponent;
    EditText state, city, addressline1, addressline2, registeranme, dealercodenumber, usergstin;
    Button btnnextpage,btnback;
    //AppComponent appComponent;
    ImageView gmap;
    NetworkListener addListener;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    List<GetFuelStationResponseModel> fuelStationsList = new ArrayList<>();

    public FuelStationAdapter adapter;
    String usename;
    String usealstname;
    String useemail;
    String usedatbirth;
    String getoption;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main5);
        Intent intent=getIntent();
        usename=intent.getStringExtra("UserName");
        usealstname=intent.getStringExtra("UserLastNAme");
        useemail=intent.getStringExtra("UserEmail");
        usedatbirth=intent.getStringExtra("DateBirth");
         getoption=intent.getStringExtra("GetOption");
        registeranme=(EditText)findViewById(R.id.registrationnamefuelstation);
        dealercodenumber=(EditText)findViewById(R.id.dealercodefuelstation);
        addressline2 = (EditText) findViewById(R.id.addresslineonefuelstation);
        addressline1 = (EditText) findViewById(R.id.addresslinetwofuelstation);
        state = (EditText) findViewById(R.id.userstatefuelstation);
        city = (EditText) findViewById(R.id.usercityfuelstation);
        usergstin = (EditText) findViewById(R.id.usergstinfuelstation);
        gmap = (ImageView) findViewById(R.id.opengmapfuelstation);
        btnback=(Button)findViewById(R.id.btn_fuel_back);
        btnnextpage = (Button) findViewById(R.id.fuelstationnext);
        appComponent = ((MyApplication) getApplicationContext()).getAppComponent();
        btnnextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(registeranme.getText().toString())){
                    registeranme.setError("Empty field not allowed");
                    registeranme.requestFocus();
                }else if(TextUtils.isEmpty(dealercodenumber.getText().toString())){
                    dealercodenumber.setError("Empty field not allowed");
                    dealercodenumber.requestFocus();
                }else if(TextUtils.isEmpty(addressline1.getText().toString())){
                    addressline1.setError("Empty field not allowed");
                    addressline1.requestFocus();
                }
                else if(TextUtils.isEmpty(addressline2.getText().toString())){
                    addressline2.setError("Empty field not allowed");
                    addressline2.requestFocus();
                }
                else if(TextUtils.isEmpty(state.getText().toString())){
                    state.setError("Empty field not allowed");
                    state.requestFocus();
                }
                else if(TextUtils.isEmpty(city.getText().toString())){
                    city.setError("Empty field not allowed");
                    city.requestFocus();
                }
                else if(TextUtils.isEmpty(usergstin.getText().toString())){
                    usergstin.setError("Empty field not allowed");
                    usergstin.requestFocus();
                }else {

                    String regname=registeranme.getText().toString();
                    String dealcode=dealercodenumber.getText().toString();
                    String addone=addressline1.getText().toString();
                    String addtwo=addressline2.getText().toString();
                    String stat=state.getText().toString();
                    String cty=city.getText().toString();
                    String usegstn=usergstin.getText().toString();


                 //   final RequestBody LoginId = RequestBody.create(MediaType.parse("multipart/form-data"), model.getId());
                    final RequestBody fstname = RequestBody.create(MediaType.parse("multipart/form-data"), regname);
                    final RequestBody dc = RequestBody.create(MediaType.parse("multipart/form-data"), dealcode);
                    final RequestBody ad = RequestBody.create(MediaType.parse("multipart/form-data"),  addone+addtwo);
                  // final RequestBody adtwo = RequestBody.create(MediaType.parse("multipart/form-data"), addtwo);
                    final RequestBody st = RequestBody.create(MediaType.parse("multipart/form-data"),stat);
                    final RequestBody ct = RequestBody.create(MediaType.parse("multipart/form-data"), cty);
                    final RequestBody ugst = RequestBody.create(MediaType.parse("multipart/form-data"), usegstn);




                    final RequestBody usnm = RequestBody.create(MediaType.parse("multipart/form-data"), usename);
                    final RequestBody uslsnm = RequestBody.create(MediaType.parse("multipart/form-data"), usealstname);
                    final RequestBody usem = RequestBody.create(MediaType.parse("multipart/form-data"), useemail);
                    final RequestBody usdb = RequestBody.create(MediaType.parse("multipart/form-data"), usedatbirth);
                    final RequestBody usgtoption = RequestBody.create(MediaType.parse("multipart/form-data"), getoption);




                    appComponent.getServiceCaller().callService(appComponent.getAllApis().
                            updateProfile(fstname,dc,ad,st,ct,ugst,usnm,uslsnm,usem,usdb,null), listener);

                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserPersonalDeatil.class);
                startActivity(intent);
                /*finish();*/

            }
        });

        gmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()) {
                    case R.id.opengmapfuelstation: {
                        checkLocationPermission(EfuelStationDteail.this);



                        break;
                    }
                }

            }
        });

    }
    NetworkListener listener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            Log.e("kk/mklklmkl","jjj"+responseBody.getResutData()+"\n"+responseBody.getMessage());
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {

                SignInResponseModel responseModel = (SignInResponseModel) responseBody.getResutData();

                String token = appComponent.getSpUtils().getUserData().getToken();
                responseModel.setToken(token);
                appComponent.getSpUtils().saveUserData(responseModel);

                Intent intent=new Intent(getApplicationContext(),UserAccountDetail.class);
                startActivity(intent);

            }
        }

        @Override
        public void onError(String msg) {
            //
            if (msg.contains("Unauthorized")){
                Intent dashBoard=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(dashBoard);
            }
            //Log.e(EfuelStationDteail.class.getSimpleName(),"onError "+msg);

        }

        @Override
        public void onComplete() {
        }

        @Override
        public void onStart() {

        }
    };
    public void checkLocationPermission(Activity activity) {
        int hasLocationPerm = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasLocationPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{
                            Manifest.permission
                                    .ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            showLocationDialog();
        }
    }

    void showLocationDialog() {
        AddLocationDialog addLocationDialog = new AddLocationDialog();
        addLocationDialog.setData(appComponent, this, EfuelStationDteail.this);
        addLocationDialog.show(getSupportFragmentManager(), AddLocationDialog.class.getName());
    }


    private NetworkListener getFuelStationsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelStationsList = (List<GetFuelStationResponseModel>) responseBody.getResutData();
                if (fuelStationsList.size() > 0) {
                    adapter.updateList(fuelStationsList);

                    if (getApplicationContext() instanceof StationRegistrationActivity && fuelStationsList.size() > 0) {
                        ((StationRegistrationActivity) getApplicationContext()).setStationAdded(true);
                    }
                } else {
                    // showNoRecords();
                }
            }
        }

        @Override
        public void onError(String msg) {
            // showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {

            if (getApplicationContext() == null)
                return;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // hideProgress();
                }
            });
        }

        @Override
        public void onStart() {
            //showProgress();
        }
    };


    private NetworkListener addFuelStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(),
                        getFuelStationsListener);
            }
        }

        @Override
        public void onError(String msg) {

            // showMsg(rootLayout, msg);
        }

        @Override
        public void onComplete() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //hideProgress();
                }
            });
        }

        @Override
        public void onStart() {
            // showProgress();
        }
    };


    private NetworkListener sendQrListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                // showMsg(getResources().getString(R.string.sent_qr_tomail));
            }
        }

        @Override
        public void onError(String msg) {

        }

        @Override
        public void onComplete() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // hideProgress();
                }
            });
        }

        @Override
        public void onStart() {
            // showProgress();
        }
    };

    @Override
    public void onItemClick(View view, int position) {

    }


    @Override
    public void onAddLocation(AddFuelStationRequestModel address) {
        String addressgain;
        Log.e("Gmap", " dd " + address.getCity() + address.getState());
        state.setText(address.getState());
        city.setText(address.getCity());
        // addressline1.setText(address.getAddress());
        String str1 = address.getState();
        String str2 = address.getCity();
        String addres = address.getAddress();
        String addresline1 = "";
        String adressline2 = "";
        String[] ss = address.getAddress().split(",");
        for (int i = 0; i < ss.length; i++) {


             Log.e("kkk","str1"+str1+" "+ss[i]);
             Log.e("uuu","str2"+str2+" "+ss[i]);
                if (i==0) {
                    adressline2= adressline2+""+ss[i];
                 //   adressline1= adressline1+""+ss[i];
                }else if (i==1) {
                    adressline2=adressline2+" "+ss[i];
                  //  adressline1=adressline1+" "+ss[i];
                }else {
                    addresline1=addresline1+""+ss[i];
                 //   addresline2=addresline2+""+ss[i];
                }
            }
        if (!addresline1.isEmpty()) {
       // if (!addresline2.isEmpty()) {
            addresline1 = addresline1.replace(str1, " ").replace(str2, " ");
           // addresline2 = addresline2.replace(str1, " ").replace(str2, " ");
        }
            addressline1.setText("" + addresline1);
            addressline2.setText("" + adressline2);


        }
}
