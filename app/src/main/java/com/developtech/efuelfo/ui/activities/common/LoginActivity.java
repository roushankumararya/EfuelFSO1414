package com.developtech.efuelfo.ui.activities.common;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.SettingsRequestModel;
import com.developtech.efuelfo.model.requestModel.SignInRequestModel;
import com.developtech.efuelfo.model.requestModel.SignUpRequestModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.SettingsResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.model.responseModel.SignUpResponseModel;
import com.developtech.efuelfo.model.responseModel.VehicleOwnerResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.fuelStation.StationRegistrationActivity;
import com.developtech.efuelfo.ui.adapters.SpinnerAdapter4;
import com.developtech.efuelfo.ui.dialogFragments.SelectFuelStation;
import com.developtech.efuelfo.ui.dialogFragments.SelectVehicleOwnerDialog;
import com.developtech.efuelfo.util.SPUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MyActionBar implements GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_SIGN_IN = 9001;
    @BindView(R.id.layLogin)
    LinearLayout layLogin;
    @BindView(R.id.laySocialLogin)
    LinearLayout laySocialLogin;
    @BindView(R.id.layRegister)
    LinearLayout layRegister;
    @BindView(R.id.layoutRegister)
    LinearLayout layoutRegister;
    @BindView(R.id.layoutLogin)
    LinearLayout layoutLogin;
    @BindView(R.id.cbLogIn)
    CheckBox cbLogIn;
    @BindView(R.id.tvForgetPass)
    TextView tvForgetPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.viewLogin)
    View viewLogin;
    @BindView(R.id.viewRegister)
    View viewRegister;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPasswordSignUp)
    EditText etPasswordSignUp;
    @BindView(R.id.etMobileNumber)
    EditText etMobileNumber;
    @BindView(R.id.spinnerCode)
    Spinner spinnerCode;
    @BindView(R.id.etEmailAddress)
    EditText etEmailAddress;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLoginId)
    EditText etLoginId;
    @BindView(R.id.layForget)
    LinearLayout layForget;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvOr)
    TextView tvOr;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.spinnerCode1)
    Spinner spinnerCode1;
    @BindView(R.id.tvTermsCond)
    TextView tvTermsCond;

    @BindView(R.id.tvTitleBottom)
    TextView tvTitleBottom;
    SignUpRequestModel requestModel;
    SignInRequestModel signInRequestModel;
    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient googleApiClient;
    String TAG = LoginActivity.class.getSimpleName();
    CallbackManager callbackManager;
    Bundle bundle;
    List<VehicleOwnerResponseModel> vehicleOwnerResponseModels; //used in case of driver login only
    NetworkListener listener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SignUpResponseModel model = (SignUpResponseModel) responseBody.getResutData();
                  bundle.putString("otp_id", model.getId());
                bundle.putSerializable("signUpData", requestModel);
                newIntent(OtpActivity.class, bundle, false);
            } else if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.FAIL.toString())) {
                showMsg(rootLayout, responseBody.getMessage());
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

    String personName, personEmail;
    SpinnerAdapter4 spinnerAdapter;
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
                    dialogFragment.setData(appComponent, allFuelStationsList, LoginActivity.this);
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
        NetworkListener signInListener = new NetworkListener() {
            @Override
            public void onSuccess(ResultModel<?> responseBody) {
                if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                    SignInResponseModel model = (SignInResponseModel) responseBody.getResutData();
                    appComponent.getSpUtils().saveUserData(model);
                    MyApplication application = (MyApplication) getApplication();
                    application.initDagger();
                    appComponent.getSpUtils().setKeepMeLogin(cbLogIn.isChecked());
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
    NetworkListener socialSignInListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SignInResponseModel model = (SignInResponseModel) responseBody.getResutData();
                appComponent.getSpUtils().saveUserData(model);
                MyApplication application = (MyApplication) getApplication();
                application.initDagger();
               // appComponent.getSpUtils().setKeepMeLogin(cbLogIn.isChecked());
                goToNextPage(model);
            } else if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.FAIL.toString())) {
                laySocialLogin.setVisibility(View.GONE);
                layLogin.setVisibility(View.GONE);
                tvOr.setVisibility(View.GONE);
                layRegister.performClick();

               // String[] name = personName.split(" ");
                //etFirstName.setText(name[0]);
               /* if (name[1] != null)
                   etLastName.setText(name[1]);
                   etEmailAddress.setText(personEmail);*/
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


    NetworkListener settingsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                SettingsResponseModel responseModel = (SettingsResponseModel) responseBody.getResutData();
                showSettingsDialog(responseModel);
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


    private String firstName, lastName, email, mobile, password, loginId;
    private boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_signup_screen);
        setContentView(R.layout.activity_login_signup_screen);
        ButterKnife.bind(this);
        init();
        initComponents();
    }

    @Override
    public void initComponents() {
        if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.VCO
                ||
                appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.FSO) {
                laySocialLogin.setVisibility(View.VISIBLE);
        } else {
            laySocialLogin.setVisibility(View.GONE);
            layRegister.setVisibility(View.GONE);
            tvOr.setVisibility(View.GONE);
        }

        if (appComponent.getSpUtils().getAccountType() == SPUtils.ACCOUNT_TYPES.OPR) {
            tvTitle.setVisibility(View.GONE);
            tvTitleBottom.setVisibility(View.VISIBLE);
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("194371147377-j0g038i0f5rvj9nt3p0upjanmfv5t925.apps.googleusercontent.com")
                .requestEmail().build();
      mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleApiClient = new GoogleApiClient.Builder(this)
               .enableAutoManage(this /* FragmentActivity */, this /*
                OnConnectionFailedListener */)
               .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        callbackManager = CallbackManager.Factory.create();
        bundle = new Bundle();

        ArrayList<String> arrCode = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.country_code)));
        spinnerAdapter = new SpinnerAdapter4(this, android.R.layout.simple_spinner_dropdown_item, arrCode);
        spinnerCode.setAdapter(spinnerAdapter);

        /*/--------------------------my-----------------------/*/



       // spinnerCode1.setAdapter(spinnerAdapter);


//        if (appComponent.getSpUtils().getAccountType()!=null &&
//                appComponent.getSpUtils().getAccountType()==SPUtils.ACCOUNT_TYPES.FSO &&
//                appComponent.getSpUtils().getAllFuelStationsList()!=null &&
//                appComponent.getSpUtils().getFuelStationModel()==null)
//        {
//            SelectFuelStation dialogFragment = new SelectFuelStation();
//            dialogFragment.setData(appComponent, appComponent.getSpUtils().getAllFuelStationsList(), LoginActivity.this);
//            dialogFragment.show(getSupportFragmentManager(), "select_owner");
//        }

    }

    @Override
    public void retry() {

    }

    private void signIn() {
       // Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
       // startActivityForResult(signInIntent, RC_SIGN_IN);
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

                } else if (account_types == SPUtils.ACCOUNT_TYPES.FSO) {
                    showProgress();
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(),
                            getFuelStationListener);
                } else if (account_types == SPUtils.ACCOUNT_TYPES.OPR) {
                    hideProgress();
                    if (model.getFuelStations().size() == 1) {
                        appComponent.getSpUtils().saveFuelStation(model.getFuelStations().get(0));
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        LoginActivity.this.finish();
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

    private void fbLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback() {
            @Override
            public void onSuccess(Object o) {
                LoginResult loginResult = (LoginResult) o;
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("LoginActivity", "onSuccess");
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        bundle = getFacebookData(object);
                        personName = bundle.getString("name");
                        personEmail = bundle.getString("email");

                        SignInRequestModel requestModel = new SignInRequestModel();
                        requestModel.setEmail(personEmail);
                        requestModel.setUserType(appComponent.getSpUtils().getAccountType().toString());
                        callSocialLoginDetail(requestModel);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d(TAG, "onError: " + exception.toString());
            }
        });
    }

    private Bundle getFacebookData(JSONObject object) {
        if (object != null) {
            try {
                String id = object.getString("id");
                bundle.putString("idFacebook", id);
                if (object.has("name"))
                    bundle.putString("name", object.getString("name"));
                if (object.has("email"))
                    bundle.putString("email", object.getString("email"));
                return bundle;
            } catch (JSONException e) {
                Log.d(TAG, "Error parsing JSON");
            }
        }
        return null;
    }

    @OnClick({R.id.layRegister, R.id.layLogin, R.id.btnLogin, R.id.tvForgetPass,
            R.id.btnGoogle, R.id.btnFacebook, R.id.tvTermsCond})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layRegister: {
                layoutLogin.setVisibility(View.GONE);
                viewLogin.setVisibility(View.INVISIBLE);
                viewRegister.setVisibility(View.VISIBLE);
                layoutRegister.setVisibility(View.VISIBLE);
                tvForgetPass.setVisibility(View.GONE);
                tvTitle.setText(getString(R.string.description));
                btnLogin.setText(getString(R.string.register));
                tvTermsCond.setText(getResources().getString(R.string.accept_terms));
                cbLogIn.setChecked(false);
                isLogin = false;
                break;
            }
            case R.id.layLogin: {
                tvForgetPass.setVisibility(View.VISIBLE);
                tvTitle.setText(getString(R.string.login_description));
                layoutLogin.setVisibility(View.VISIBLE);
                btnLogin.setText(getString(R.string.login));
                layoutRegister.setVisibility(View.GONE);
                viewLogin.setVisibility(View.VISIBLE);
                viewRegister.setVisibility(View.INVISIBLE);
                tvTermsCond.setText(getResources().getString(R.string.keep_me_in));
                isLogin = true;
                break;
            }
            case R.id.btnLogin: {
                hideKB();
                if (!isLogin) {
                    checkValidationSignUp();
                } else
                    checkValidationLogin();
                break;
            }
            case R.id.tvForgetPass: {
                newIntent(ForgotPassword.class, null, false);
                break;
            }
            case R.id.btnGoogle: {
                signIn();
                break;
            }
            case R.id.btnFacebook: {
                fbLogin();
            }
            case R.id.tvTermsCond: {
                if (!isLogin) {
                    List<String> required = new ArrayList(Arrays.asList("faqs", "disclaimer", "licenseAgreement", "paymentAgreement", "creditAgreement", "termAndCondition", "privacyPolicy"));
                    SettingsRequestModel requestModel = new SettingsRequestModel();
                    requestModel.setRequired(required);
                    appComponent.getServiceCaller().callService(appComponent.getAllApis()
                            .getSettings(requestModel), settingsListener);
                }
            }
        }
    }

    public void checkValidationSignUp() {
        requestModel = new SignUpRequestModel();
        firstName = etFirstName.getText().toString().trim();
        lastName = etLastName.getText().toString().trim();
        email = etEmailAddress.getText().toString().trim();
        mobile = etMobileNumber.getText().toString().trim();
        password = etPasswordSignUp.getText().toString().trim();
        if (firstName.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_first_name));
            etFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_last_name));
            etLastName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_email_address));
            etEmailAddress.requestFocus();
            return;
        }

        String emailPatternStr = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";

        Pattern emailPattern = Pattern.compile(emailPatternStr);

        Matcher emailMatcher = emailPattern.matcher(email);

        if (!emailMatcher.matches()) {
            showMsg(rootLayout, getString(R.string.please_enter_valid_email_id));
            etEmailAddress.requestFocus();
            return;
        }
        if (mobile.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_mobile_number));
            etMobileNumber.requestFocus();
            return;
        }

        if (mobile.length() != 10) {
            showMsg(rootLayout, getString(R.string.mobile_number_length_check));
            etMobileNumber.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_password));
            etPasswordSignUp.requestFocus();
            return;
        }

        if (password.length() < 6) {
            showMsg(rootLayout, getString(R.string.password_length_check));
            etPasswordSignUp.requestFocus();
            return;
        }

        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ' ') {
                showMsg(rootLayout, getString(R.string.password_space_check));
                return;
            }
        }

//        String numberRegx = "(.)*(\\d)(.)*";
//
//        Pattern pattern = Pattern.compile(numberRegx);
//
//        Matcher matcher = pattern.matcher(password);
//
//        if (!matcher.matches())
//        {
//            showMsg(rootLayout, getString(R.string.password_number_check));
//            etPasswordSignUp.requestFocus();
//            return;
//        }
//
//        String alphabetRegx = ".*[a-zA-Z]+.*";
//
//        pattern = Pattern.compile(alphabetRegx);
//
//        matcher = pattern.matcher(password);
//
//        if (!matcher.matches())
//        {
//            showMsg(rootLayout, getString(R.string.password_alphabet_check));
//            etPasswordSignUp.requestFocus();
//            return;
//        }

        if (!isLogin && !cbLogIn.isChecked()) {
            showMsg(rootLayout, getResources().getString(R.string.please_accept));
            return;
        }

        requestModel.setEmail(email);
        requestModel.setFirstName(firstName);
        requestModel.setLastName(lastName);
        requestModel.setMobileNumber(mobile);
        requestModel.setPassword(password);
        requestModel.setCountryCode(spinnerCode.getSelectedItem().toString());
        requestModel.setUserType(appComponent.getSpUtils().getAccountType().toString());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().signUp(requestModel), listener);
    }

    public void checkValidationLogin() {
        signInRequestModel = new SignInRequestModel();
        loginId = etLoginId.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (loginId.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_login_id));
            etLoginId.requestFocus();
            return;
        }

        if (loginId.length() != 10) {
            showMsg(rootLayout, getString(R.string.login_id_number_length_check));
            etLoginId.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showMsg(rootLayout, getString(R.string.enter_password));
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            showMsg(rootLayout, getString(R.string.password_length_check));
            etPassword.requestFocus();
            return;
        }

        signInRequestModel.setLoginId(loginId);
        signInRequestModel.setPassword(password);
        signInRequestModel.setPasscode(password);
        signInRequestModel.setUserType(appComponent.getSpUtils().getAccountType().toString());
        appComponent.getServiceCaller().callService(appComponent.getAllApis().signIn(signInRequestModel), signInListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        hideProgress();
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("googleAccount:>", "handleSignInResult: " + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            Log.e("googleAccount:>", account.getDisplayName());
            Log.e("googleAccount:>", account.getEmail());
            SignInRequestModel requestModel = new SignInRequestModel();
            requestModel.setEmail(account.getEmail());
            requestModel.setUserType(appComponent.getSpUtils().getAccountType().toString());
            callSocialLoginDetail(requestModel);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
            if (acct != null) {
                personName = acct.getDisplayName();
                personEmail = acct.getEmail();
//                    Uri personPhoto = acct.getPhotoUrl();
            }
        }

    }

    void showSettingsDialog(SettingsResponseModel model) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_settings);
        TextView tvContent = dialog.findViewById(R.id.tvSettings);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        tvContent.setText(Html.fromHtml(model.getTermAndCondition()).toString());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbLogIn.setChecked(true);
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void callSocialLoginDetail(SignInRequestModel requestModel) {
        appComponent.getServiceCaller().callService(
                         appComponent
                        .getAllApis()
                        .socialSignIn(requestModel),
                        socialSignInListener
        );
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
