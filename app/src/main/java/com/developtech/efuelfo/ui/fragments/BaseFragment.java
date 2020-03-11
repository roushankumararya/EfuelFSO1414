package com.developtech.efuelfo.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.ui.dialogFragments.LogoutDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class BaseFragment extends Fragment {

    public View rootLayout;
    public AppComponent appComponent;
    protected FrameLayout progress_bar;

    protected FrameLayout lytNoRecords;
    Context context;
    DialogInterface.OnClickListener onClickListener = null;

    protected void init(View view) {
        context = getContext();
        progress_bar = (FrameLayout) view.findViewById(R.id.progress_bar);
        if (progress_bar != null) {
            progress_bar.setVisibility(View.GONE);
        }
        lytNoRecords = view.findViewById(R.id.lytNoRecords);
        if (lytNoRecords != null) {
            lytNoRecords.setVisibility(View.VISIBLE);
        }
        rootLayout = view.findViewById(R.id.rootlayout);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = ((MyApplication) getActivity().getApplicationContext()).getAppComponent();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected void setErrorEnabled(boolean enabled, TextInputLayout... inputLayout) {
        for (TextInputLayout textInputLayout : inputLayout) {
            textInputLayout.setErrorEnabled(enabled);
            textInputLayout.setError(null);
        }
    }

    protected void showProgress() {
        if (progress_bar != null) {
            progress_bar.setVisibility(View.VISIBLE);
        }
    }

    protected void hideProgress() {
        if (progress_bar != null) {
            progress_bar.setVisibility(View.GONE);
        }
    }

    public void showNoRecords() {
        if (lytNoRecords != null) {
            lytNoRecords.setVisibility(View.VISIBLE);
        }
    }

    public void hideNoRecords() {
        if (lytNoRecords != null) {
            lytNoRecords.setVisibility(View.VISIBLE);
        }
    }


    public void showMsg(View v, String msg) {
        if (v == null) {
            showMsg(msg);
            return;
        }
        Snackbar s = Snackbar.make(getActivity().findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View view = s.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" + getResources().getString(R.string.font_light)));
        tv.setTextColor(Color.WHITE);
        s.show();
    }

    public void showMsg(String msg) {
        Toast t = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
        t.show();
    }

    public void hideKB() {
        if (getActivity() == null) {
            return;
        }
        if (getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus() instanceof EditText) {
            InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void noInternetMsg(View v) {
        String msg = "No internet connection.";
        if (v == null) {
            showMsg(msg);
            return;
        }
        final Snackbar s = Snackbar.make(getActivity().findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View view = s.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" + getResources().getString(R.string.font_light)));
        tv.setTextColor(Color.WHITE);
        s.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry();
                s.dismiss();
            }
        });
        tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_action);
        tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" + getResources().getString(R.string.font_bold)));
        tv.setTextColor(Color.RED);
        s.show();
    }

    public abstract void retry();

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    public void newIntent(Class<?> c, Bundle bundle, boolean finish) {
        Intent i = new Intent(getContext(), c);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        startActivity(i);
        if (finish) {
            getActivity().finish();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        appComponent.getServiceCaller().removeSubscription();
    }

    public void showAlertExpireDialog() {
        LogoutDialog logoutDialog = new LogoutDialog();
        logoutDialog.show(getFragmentManager(), "logout");
        logoutDialog.setData(appComponent, LogoutDialog.DIALOG_TYPE.TOKEN_EXPIRE, null, null);
    }

    public abstract void onFilterClick();


    public String get12HourTime(String time) {
        String convertedTime = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final Date dateObj = sdf.parse(time);
            convertedTime = new SimpleDateFormat("hh:mm a").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }

    public String get24HourTime(String time) {
        String convertedTime = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("K:mm a");
            final Date dateObj = sdf.parse(time);
            convertedTime = new SimpleDateFormat("HH:mm").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }

    public String getFormatedDateUTC(String date) {
        String formatedDate = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            final Date dateObj = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            calendar.setTime(dateObj);
            Date time = calendar.getTime();
            SimpleDateFormat outputFmt = new SimpleDateFormat("MMM dd, yyy h:mm a zz");
            String dateAsString = outputFmt.format(time);
            formatedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return formatedDate;
    }

    public String getFormatedDate(String date) {
        String formatedDate = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            final Date dateObj = sdf.parse(date);
            formatedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return formatedDate;
    }

}
