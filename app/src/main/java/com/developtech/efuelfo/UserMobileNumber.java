package com.developtech.efuelfo;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.app.UserPreference;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SignInRequestModel;
import com.developtech.efuelfo.model.requestModel.SignUpRequestModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.model.responseModel.SignUpResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.common.LoginActivity;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.activities.fuelStation.StationRegistrationActivity;
import com.developtech.efuelfo.ui.activities.vehicleOwner.RegistrationActivity;
import com.developtech.efuelfo.ui.dialogFragments.SelectFuelStation;
import com.developtech.efuelfo.ui.dialogFragments.SelectVehicleOwnerDialog;
import com.developtech.efuelfo.util.SPUtils;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;


public  class UserMobileNumber extends  MyActionBar implements AdapterView.OnItemSelectedListener {

    SignUpRequestModel requestModelmobile;

    GoogleApiClient googleApiClient ;
    String TAG = LoginActivity.class.getSimpleName();
    CallbackManager callbackManager;
    Bundle bundle;
    AppComponent appComponent;
    Spinner spinner;
    EditText etMobileNumber;
    Button btn1;
    String str1;
    String[] countryvalue={"+91"/*,"+92","+93","+94","+95","+96"*/};
    int flags[] = {R.drawable.flag, /*R.drawable.pakistan, R.drawable.china, R.drawable.canada,
            R.drawable.america, R.drawable.bangaledesh1,R.drawable.austrialia*/};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        appComponent = ((MyApplication) getApplicationContext()).getAppComponent();
        etMobileNumber=(EditText)findViewById(R.id.enterEditTextPhoneNumber);
        btn1=(Button)findViewById(R.id.entermobilenumber);
        spinner=(Spinner)findViewById(R.id.spinnercountrycode);
        spinner.setBackground(null);
        spinner.setOnItemSelectedListener(this);
        CustomAdapter  customAdapter=new CustomAdapter(getApplicationContext(),flags,countryvalue);
        spinner.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
        bundle = new Bundle();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestModelmobile = new SignUpRequestModel();
                if(TextUtils.isEmpty(etMobileNumber.getText().toString())){
                    etMobileNumber.setError("Empty field not allowed!");
                    etMobileNumber.requestFocus();
                    return;

                }else if(TextUtils.getTrimmedLength(etMobileNumber.getText().toString())!=10){
                    etMobileNumber.setError("Please enter 10 digit!");
                    etMobileNumber.requestFocus();
                    return;

                }else{
                  //   checkValidationSignIn();
                    checkValidationSignUp();
                     /*bundle=new Bundle();*/
                  /*  requestModelmobile.setMobileNumber(etMobileNumber.getText().toString());
                   // requestModelmobile.setUserType(appComponent.getSpUtils().getAccountType().toString());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis()
                            .signUp(requestModelmobile),listener );
                    String mobilenumber=etMobileNumber.getText().toString().trim();
                    Intent intent=new Intent(getApplicationContext(), OtpVerifyNumber.class);
                   // Toast.makeText(UserMobileNumber.this, "Proceed..", Toast.LENGTH_SHORT).show();
                  //  bundle.putString("number",mobilenumber);
                    intent.putExtra("number",mobilenumber);
                    startActivity(intent);
                    finish();*/



                }
            }
        });
    }



    NetworkListener listener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {

            Log.e("kkk","success "+responseBody.getMessage().toString().contains("")+" "+responseBody.getResutData());
             //  bundle=new Bundle();
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SignUpResponseModel model = (SignUpResponseModel) responseBody.getResutData();
                Log.e("listener","success "+model.getId());

                //  goToNextPage(model);
               // newIntentClear(OtpVerifyNumber.class, null, false);
                 bundle.putString("otp_id", model.getId());
               //  bundle.putString("getotp",model.getOtpdigits());
                 bundle.putSerializable("signUpData", model);
                 bundle.putString("MobileNo",etMobileNumber.getText().toString());
                 // bundle.putString(appComponent.getServiceCaller().callService(appComponent.getAllApis().signUp(requestModelmobile), listener));
                newIntent(OtpVerifyNumber.class, bundle, false);
            } else if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.FAIL.toString())) {
                showMsg(rootLayout, responseBody.getMessage());
                appComponent.getSpUtils().setKeepMeLogin(true);
                if (responseBody.getMessage().toString().contains("User with given mobile number is already exists")){
                    UserPreference userPreference=new UserPreference(getApplicationContext())
                            .getInstance(getApplicationContext());
                    //shareprefer save boolean set tru, number
                    userPreference.setNumber(etMobileNumber.getText().toString());
                    userPreference.setIsLogin(true);
                }else {
                    //boolean set false,
                }
                goToNextPage(SPUtils.ACCOUNT_TYPES.FSO);

              //  goToNextPage(SPUtils.ACCOUNT_TYPES.VCO);
               // newIntent(OtpVerifyNumber.class, bundle, false);
            }
        }

        @Override
        public void onError(String msg) {
            Log.e("hhhh","fail "+msg);
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

    public void goToNextPage(SPUtils.ACCOUNT_TYPES accountTYpe) {
        Log.e(TAG,"djfjdhfdf"+accountTYpe);
        switch (accountTYpe) {
            case DRV: {
                DialogFragment dialogFragment = new SelectVehicleOwnerDialog();
                dialogFragment.show(getSupportFragmentManager(), "select_owner");
//                newIntent(ChangePasswordActivity.class, null, false);
                break;
            }
            case VCO: {
                newIntent(RegistrationActivity.class, null, false);
//                newIntent(StationRegistrationActivity.class, null, false);
                break;
            }
            case FSO: {
                newIntent(HomeActivity.class, null, false);
             //  DialogFragment dialogFragment = new SelectVehicleOwnerDialog();
              //  dialogFragment.show(getSupportFragmentManager(), "select_owner");
                break;
            }
            case OPR: {
                //  newIntent(HomeActivity.class, null, false);
//                newIntent(StationRegistrationActivity.class,null,false);
//                newIntent(StationRegistrationActivity.class,null,false);
               DialogFragment dialogFragment = new SelectVehicleOwnerDialog();
               dialogFragment.show(getSupportFragmentManager(), "select_owner");
                break;
            }
        }
    }

    private String  mobile;
    private boolean isLogin = true;


    @Override
    public void initComponents() {

        if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.VCO
                ||
                appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.FSO) {
           //   laySocialLogin.setVisibility(View.VISIBLE);
        } else {
           // laySocialLogin.setVisibility(View.GONE);
            // layRegister.setVisibility(View.GONE);
            // tvOr.setVisibility(View.GONE);
        }

        if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.OPR) {
            //tvTitle.setVisibility(View.GONE);
            //tvTitleBottom.setVisibility(View.VISIBLE);
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("194371147377-j0g038i0f5rvj9nt3p0upjanmfv5t925.apps.googleusercontent.com")
                .requestEmail().build();
        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        (GoogleApiClient.OnConnectionFailedListener) this/*
                OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        callbackManager = CallbackManager.Factory.create();



    }

    public void goToNextPage(SignUpResponseModel model) {
        appComponent = ((MyApplication) getApplication()).appComponent;
        if (appComponent.getSpUtils().getAccountType() != null) {
            SPUtils.ACCOUNT_TYPES account_types = appComponent.getSpUtils().getAccountType();
            if (account_types != null) {
                if (account_types == SPUtils.ACCOUNT_TYPES.VCO) {
                    newIntentClear(OtpVerifyNumber.class, null, false);
                }  else if (account_types == SPUtils.ACCOUNT_TYPES.OPR) {
                    hideProgress();
                }
            }
        }
    }

    public void goToNextPage(SignInResponseModel model) {
        appComponent = ((MyApplication) getApplication()).appComponent;
        if (appComponent.getSpUtils().getAccountType() != null) {
            SPUtils.ACCOUNT_TYPES account_types = appComponent.getSpUtils().getAccountType();
            if (account_types != null) {
                if (account_types == SPUtils.ACCOUNT_TYPES.VCO) {
                    newIntentClear(HomeActivity.class, null, false);
                } else if (account_types == SPUtils.ACCOUNT_TYPES.DRV) {
                    appComponent.getSpUtils().saveVehicleOwnerResponse(model.getVehicleOwners());
                    List<VehicleOwnerResponseModel> vehicleOwnerResponseModels = model.getVehicleOwners();
                    SelectVehicleOwnerDialog dialogFragment = SelectVehicleOwnerDialog.newInstance
                            ("driver", vehicleOwnerResponseModels);
                    dialogFragment.setData(appComponent);
                    dialogFragment.show(getSupportFragmentManager(), "select_owner");

                } else if (account_types == SPUtils.ACCOUNT_TYPES.FSO){
                    showProgress();
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(),
                            getFuelStationListener);
                } else if (account_types == SPUtils.ACCOUNT_TYPES.OPR) {
                    hideProgress();
                    if (model.getFuelStations().size() == 1) {
                        appComponent.getSpUtils().saveFuelStation(model.getFuelStations().get(0));
                       /* Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        LoginActivity.this.finish();*/
                    } else {
                        SelectFuelStation dialogFragment = new SelectFuelStation();
                        dialogFragment.setData(appComponent, model.getFuelStations(), this);

                        appComponent.getSpUtils().saveFuelStationsList(model.getFuelStations());
                        dialogFragment.show(getSupportFragmentManager(), "select_owner");
                    }

                }
            }
        }
    }
    NetworkListener getFuelStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                List<GetFuelStationResponseModel> allFuelStationsList = (List<GetFuelStationResponseModel>)
                        responseBody.getResutData();
                if (allFuelStationsList.size() == 0) {
                    newIntent(StationRegistrationActivity.class, null, false);
                } else if (allFuelStationsList.size() == 1) {
                    appComponent.getSpUtils().saveFuelStationsList(allFuelStationsList);
                    appComponent.getSpUtils().saveFuelStation(allFuelStationsList.get(0));
                    newIntentClear(HomeActivity.class, null, true);
                } else if (allFuelStationsList.size() > 1) {
                    appComponent.getSpUtils().saveFuelStationsList(allFuelStationsList);
                    SelectFuelStation dialogFragment = new SelectFuelStation();
                    dialogFragment.setData(appComponent, allFuelStationsList, UserMobileNumber.this);
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
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLogin: {
                hideKB();
                checkValidationSignUp();
               /* if (!isLogin) {
                    checkValidationSignUp();
                } else
                    checkValidationLogin();*/
                break;
            }
        }
    }



    public void checkValidationSignIn() {
        SignInRequestModel signInRequestModel=new SignInRequestModel();
        signInRequestModel.setLoginId(etMobileNumber.getText().toString());
        signInRequestModel.setPassword(etMobileNumber.getText().toString());
        signInRequestModel.setPasscode(etMobileNumber.getText().toString());
        signInRequestModel.setUserType(SPUtils.ACCOUNT_TYPES.VCO.toString());
        appComponent.getServiceCaller().callService(appComponent.getAllApis()
                .signIn(signInRequestModel),signInListener);

    }


    NetworkListener signInListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SignInResponseModel model = (SignInResponseModel) responseBody.getResutData();
                appComponent.getSpUtils().saveUserData(model);
                MyApplication application = (MyApplication) getApplication();
                application.initDagger();
                appComponent.getSpUtils().setKeepMeLogin(true);
                goToNextPage(model);

            } else if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.FAIL.toString())) {
                showMsg(rootLayout, responseBody.getMessage());
                hideProgress();
            }
        }

        @Override
        public void onError(String msg) {
            showMsg(rootLayout, msg);
            hideProgress();
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onStart() {
            showProgress();
        }
    };

    public void checkValidationSignUp() {
        requestModelmobile = new SignUpRequestModel();
        mobile = etMobileNumber.getText().toString().trim();
        requestModelmobile.setMobileNumber(mobile);
        requestModelmobile.setEmail("abcd@gmail.com");
        requestModelmobile.setFirstName("abcd");
        requestModelmobile.setLastName("abccd");
        requestModelmobile.setPassword("0000110");
        requestModelmobile.setCountryCode("+91");
       // requestModelmobile.setUserType(appComponent.getSpUtils().getAccountType().toString());
        requestModelmobile.setUserType(SPUtils.ACCOUNT_TYPES.FSO.toString());
      //  requestModelmobile.setUserType(SPUtils.ACCOUNT_TYPES.OPR.toString());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().signUp(requestModelmobile), listener);
    }


    @Override
    public void retry() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
