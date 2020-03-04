package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.RegisterRequest;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;
import com.developtech.efuelfo.ui.activities.common.SelectAccountActivity;
import com.developtech.efuelfo.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dt on 12/26/17.
 */

public class LogoutDialog extends DialogFragment {
    public DIALOG_TYPE dialog_type = DIALOG_TYPE.LOGOUT;
    @BindView(R.id.btnYes)
    Button btnYes;
    @BindView(R.id.btnNo)
    Button btnNo;
    @BindView(R.id.tvTitle)
    CustomTextView tvTitle;
    @BindView(R.id.tvDesc)
    CustomTextView tvDesc;
    AppComponent appComponent;
    private View view;
    private AlertDialog alertDialog;
    NetworkListener logoutListener;
    MyActionBar myActionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_general, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        setCancelable(false);
        switch (dialog_type) {
            case LOGOUT: {
                initLogout();
                break;
            }
            case TOKEN_EXPIRE: {
                initTokenExpire();
                break;
            }
        }
        return alertDialog;
    }

    void initLogout() {
        tvTitle.setText("Logout");
        tvDesc.setText("Are you sure you want to logout?");
    }

    void initTokenExpire() {
        tvTitle.setText("Session Expire");
        tvDesc.setText("Your session has expired. Please log in again to continue.");
        btnNo.setVisibility(View.GONE);
        btnYes.setText("OK");
    }

    public void setData(AppComponent appComponent, DIALOG_TYPE dialog_type, NetworkListener logoutListener, MyActionBar myActionBar) {
        this.appComponent = appComponent;
        this.dialog_type = dialog_type;
        this.logoutListener = logoutListener;
        this.myActionBar = myActionBar;
    }

    @OnClick({R.id.btnYes, R.id.btnNo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnYes: {

                if (dialog_type==DIALOG_TYPE.TOKEN_EXPIRE)
                {
                    appComponent.getSpUtils().clearUserData();
                    appComponent.getSpUtils().saveIsRegistered(false);
                    appComponent.getSpUtils().saveIsDeleted(false);
                    appComponent.getSpUtils().saveFuelStation(null);
                    newIntentClear(SelectAccountActivity.class, null, true);
                }
                else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setDeviceId(myActionBar.getDeviceId());
                    appComponent.getServiceCaller().callService(appComponent.getAllApis().logout(registerRequest), logoutListener);
                }
                dismissDialog();
                break;
            }
            case R.id.btnNo: {
                dismissDialog();
                break;
            }
        }
    }

    public void dismissDialog() {
        Fragment fragment = getFragmentManager().findFragmentByTag("logout");
        if (fragment != null) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }
    }

    public void showMsg(View v, String msg) {
        if (v == null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            return;
        }
        Snackbar s = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        View view = s.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" + getResources().getString(R.string.font_light)));
        tv.setTextColor(Color.WHITE);
        s.show();
    }

    public void newIntentClear(Class<?> c, Bundle bundle, boolean finish) {
        Intent i = new Intent(getActivity(), c);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        startActivity(i);
        if (finish) {
            getActivity().finish();
        }
    }

    public enum DIALOG_TYPE {
        LOGOUT, TOKEN_EXPIRE;
    }



}
