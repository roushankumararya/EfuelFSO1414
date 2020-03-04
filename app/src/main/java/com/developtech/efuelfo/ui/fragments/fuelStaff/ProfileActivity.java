package com.developtech.efuelfo.ui.fragments.fuelStaff;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends MyActionBar {


//    @BindView(R.id.tvNameLabel)
//    CustomTextView tvNameLabel;
//
//    @BindView(R.id.tvMobileLabel)
//    CustomTextView tvMobileLabel;
//
//    @BindView(R.id.tvEmailLabel)
//    CustomTextView tvEmailLabel;
//
//    @BindView(R.id.tvCountryLabel)
//    CustomTextView tvCountryLabel;
//
//    @BindView(R.id.tvLanguageLabel)
//    CustomTextView tvLanguageLabel;
//
//    @BindView(R.id.etName)
//    CustomEditText etName;
//
//    @BindView(R.id.etPhone)
//    CustomEditText etPhone;
//
//    @BindView(R.id.etEmail)
//    CustomEditText etEmail;
//
//    @BindView(R.id.spCountryCode)
//    Spinner spCountryCode;
//
//    @BindView(R.id.spCountry)
//    Spinner spCountry;
//
//    @BindView(R.id.spLanguage)
//    Spinner spLanguage;
//
//    @BindView(R.id.cbAdditionalInfo)
//    CheckBox cbAdditionalInfo;
//
//    @BindView(R.id.ivProfilePic)
//    ImageView ivProfilePic;
//
//    @BindView(R.id.nestedScrollView)
//    NestedScrollView nestedScrollView;
//
//    boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        initComponents();
    }

    @Override
    public void initComponents() {

    }

//    @OnClick(R.id.ivEdit)
//    void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.ivEdit: {
//                if (isEdit) {
//                    isEdit = false;
//                    changeView();
//                } else {
//                    isEdit = true;
//                    changeView();
//                }
//                break;
//            }
//        }
//    }
//
//
//    void changeView() {
//        if (isEdit) {
//            tvNameLabel.setTextColor(getResources().getColor(android.R.color.white));
//            tvMobileLabel.setTextColor(getResources().getColor(android.R.color.white));
//            tvEmailLabel.setTextColor(getResources().getColor(android.R.color.white));
//            tvCountryLabel.setTextColor(getResources().getColor(android.R.color.white));
//            tvLanguageLabel.setTextColor(getResources().getColor(android.R.color.white));
//
//            etName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
//            spCountryCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
//            etPhone.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
//            etEmail.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
//            spCountry.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
//            spLanguage.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius));
//
//            etName.setTextColor(getResources().getColor(R.color.blackDarker));
//            etPhone.setTextColor(getResources().getColor(R.color.blackDarker));
//            etEmail.setTextColor(getResources().getColor(R.color.blackDarker));
//
//            cbAdditionalInfo.setVisibility(View.VISIBLE);
//        } else {
//            tvNameLabel.setTextColor(getResources().getColor(R.color.blackLessText));
//            tvMobileLabel.setTextColor(getResources().getColor(R.color.blackLessText));
//            tvEmailLabel.setTextColor(getResources().getColor(R.color.blackLessText));
//            tvCountryLabel.setTextColor(getResources().getColor(R.color.blackLessText));
//            tvLanguageLabel.setTextColor(getResources().getColor(R.color.blackLessText));
//
//            etName.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
//            spCountryCode.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
//            etPhone.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
//            etEmail.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
//            spCountry.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
//            spLanguage.setBackground(getResources().getDrawable(R.drawable.corner_background_less_radius_dark));
//
//            etName.setTextColor(getResources().getColor(R.color.white));
//            etPhone.setTextColor(getResources().getColor(R.color.white));
//            etEmail.setTextColor(getResources().getColor(R.color.white));
//
//            cbAdditionalInfo.setVisibility(View.GONE);
//        }
//    }


    @Override
    public void retry() {

    }
}
