package com.developtech.efuelfo.ui.activities.fuelStation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.interfaces.MyCallback;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.StationOwnerRegistrationAdapter;
import com.developtech.efuelfo.ui.dialogFragments.SelectFuelStation;
import com.developtech.efuelfo.ui.fragments.ProfileFragment;
import com.developtech.efuelfo.ui.fragments.fuelOwner.FuelStationFragment;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;
import com.yalantis.ucrop.UCrop;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StationRegistrationActivity extends MyActionBar {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.btnContinue)
    Button btnContinue;
    StationOwnerRegistrationAdapter adapter;
    ProfileFragment profileFragment;
    FuelStationFragment addFuelStation;
    AddFuelStationRequestModel stationRequestModel;

    List<GetFuelStationResponseModel> fuelStationList;

    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_reg);
        ButterKnife.bind(this);
        init();
        initComponents();
        setHomeTitle(getString(R.string.registration));
        showNotification(false);

        fuelStationList = addFuelStation.getFuelStationsList();
    }


   /* @Override
    protected void setSupportActionBar(Toolbar toolbar) {

    }*/


    @Override
    public void initComponents() {
        ivDrawer.setVisibility(View.GONE);

        List<Fragment> fragmentList = new ArrayList<>();
        profileFragment = new ProfileFragment();
        addFuelStation = new FuelStationFragment();
        profileFragment.setListener(updateProfileListener);
        addFuelStation.setListener(addFuelStationListener);
        fragmentList.add(profileFragment);
        fragmentList.add(addFuelStation);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.profile)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.fuel_station_caps)));

        adapter = new StationOwnerRegistrationAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void retry() {

    }

    @OnClick(R.id.btnContinue)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinue: {
                hideKB();
                if (!isProfileDone)
                {
                    if (viewPager.getCurrentItem()==1) {
                        viewPager.setCurrentItem(0);
                        return;
                    }
                    else {
                        profileFragment.checkValidation(this);
                        return;
                    }
                }

                if (!isStationAdded) {
                    if (viewPager.getCurrentItem()==0) {
                        viewPager.setCurrentItem(1);
                        return;
                    }
                    else
                    {
                        showMsg(rootLayout, getResources().getString(R.string.please_add_atleast_one_fuel_station));
                        return;
                    }
                }

                setFuelStation();
                break;
            }
        }
    }

    boolean isProfileDone, isStationAdded;

    public void setStationAdded(boolean isStationAdded)
    {
        this.isStationAdded = isStationAdded;
    }

    NetworkListener updateProfileListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {

            // String token = appComponent.getSpUtils().getUserData().getToken();
            SignInResponseModel responseModel = (SignInResponseModel) responseBody.getResutData();
          //  responseModel.setToken(token);
            appComponent.getSpUtils().saveUserData(responseModel);
            isProfileDone = true;
            btnContinue.performClick();
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

    NetworkListener addFuelStationListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            isStationAdded = true;
            appComponent.getServiceCaller().callService(appComponent.getAllApis().getFuelStations(), getFuelStationsListener);
            if (!isProfileDone)
            {
                viewPager.setCurrentItem(0);
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


    void setFuelStation()
    {

        if (fuelStationList.size()==1)
        {
            appComponent.getSpUtils().saveFuelStationsList(fuelStationList);
            appComponent.getSpUtils().saveFuelStation(fuelStationList.get(0));
            newIntentClear(HomeActivity.class, null, true);
        }
        else if (fuelStationList.size()>1)
        {
            appComponent.getSpUtils().saveFuelStationsList(fuelStationList);
            SelectFuelStation dialogFragment = new SelectFuelStation();
            dialogFragment.setData(appComponent, fuelStationList, StationRegistrationActivity.this);
            dialogFragment.show(getSupportFragmentManager(), "select_owner");
        }

        newIntentClear(HomeActivity.class, null, true);
    }


    NetworkListener getFuelStationsListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
                fuelStationList = (List<GetFuelStationResponseModel>) responseBody.getResutData();
                addFuelStation.adapter.updateList(fuelStationList);
                if (fuelStationList.size()>0)
                {
                    addFuelStation.hideNoRecords();
                }
                else
                {
                    addFuelStation.showNoRecords();
                }
//                setFuelStation();
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                System.out.println("PermissionResult() Called");
                permissionCallbackListener.permissionGranted();

            }
        }
    }

    MyCallback permissionCallbackListener;

    public void setPermissionCallbackListener(MyCallback permissionCallbackListener)
    {
        this.permissionCallbackListener = permissionCallbackListener;
    }


    SelectImage selectImage;


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectImage.IMAGE_REQUEST_GALLERY_CODE || requestCode == SelectImage.IMAGE_REQUEST_CAMERA_CODE || requestCode == UCrop.REQUEST_CROP)
            selectImage.onActivityResult(requestCode, resultCode, data);
    }
}
