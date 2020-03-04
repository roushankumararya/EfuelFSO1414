package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddVehicleRequestModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.HomeActivity;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.adapters.RegistrationPagerAdapter;
import com.developtech.efuelfo.ui.fragments.AddDriverFragment;
import com.developtech.efuelfo.ui.fragments.AddVehicleFragment;
import com.developtech.efuelfo.ui.fragments.ProfileFragment;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.SelectImage;
import com.yalantis.ucrop.UCrop;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class RegistrationActivity extends MyActionBar {

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.btnContinue)
    Button btnContinue;

    @BindView(R.id.ivDrawer)
    ImageView ivDrawer;

    AddVehicleFragment addVehicleFragment;
    AddDriverFragment addDriverFragment;
    AllVehicleResponseModel vehicleResponseModel;
    GetDriverResponseModel driverResponseModel;
    boolean isVehicleAdded, isProfileDone;
    NetworkListener updateProfileListener = new NetworkListener() {
        @Override
        public void onSuccess(ResultModel<?> responseBody) {
            if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
//                if (isVehicleAdded) {
                newIntentClear(HomeActivity.class, null, true);
//                } else {
//                    view_pager.setCurrentItem(0);
//                }
                isProfileDone = true;
            } else {
                showMsg(rootLayout, responseBody.getMessage());
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

    private RegistrationPagerAdapter adapter;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        init();
        intiComponents();
        setHomeTitle(getString(R.string.registration));
        hideHomeImage();
    }

    @Override
    public void initComponents() {
        ivDrawer.setVisibility(View.GONE);
    }

    @Override
    public void retry() {

    }

    public void intiComponents() {
        List<Fragment> fragmentList = new ArrayList<>();
        addVehicleFragment = new AddVehicleFragment();
        addDriverFragment = new AddDriverFragment();
        profileFragment = new ProfileFragment();
        profileFragment.setListener(updateProfileListener);

        fragmentList.add(addVehicleFragment);
        fragmentList.add(addDriverFragment);
        fragmentList.add(profileFragment);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.addvehicle)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.adddriver)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.profile)));

        adapter = new RegistrationPagerAdapter(getSupportFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        view_pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(view_pager);
    }

    public void setVehicleRequest(AddVehicleRequestModel vehicleRequest) {
        vehicleResponseModel = new AllVehicleResponseModel();
        vehicleResponseModel.setAlias(vehicleRequest.getAlias());
        vehicleResponseModel.setColor(vehicleRequest.getColor());
        vehicleResponseModel.setCompany(vehicleRequest.getCompany());
        vehicleResponseModel.setVehicleNumber(vehicleRequest.getVehicleNumber());
        vehicleResponseModel.setFuelCapacity(vehicleRequest.getFuelCapacity());
        vehicleResponseModel.setModel(vehicleRequest.getModel());
    }

    @OnClick(R.id.btnContinue)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinue: {
                if (!profileFragment.checkValidation(this)) {
                    if(view_pager.getCurrentItem() != 2)
                    {
                        view_pager.setCurrentItem(2);
                    }
                }
                break;
            }
        }
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
        if (requestCode == SelectImage.IMAGE_REQUEST_GALLERY_CODE ||
                requestCode == SelectImage.IMAGE_REQUEST_CAMERA_CODE || requestCode == UCrop.REQUEST_CROP)
            selectImage.onActivityResult(requestCode, resultCode, data);
    }
}
