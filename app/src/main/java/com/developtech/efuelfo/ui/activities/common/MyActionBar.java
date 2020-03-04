package com.developtech.efuelfo.ui.activities.common;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.app.MyApplication;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.network.NetworkListener;
import com.developtech.efuelfo.network.RestClient;
import com.developtech.efuelfo.ui.dialogFragments.LogoutDialog;
import com.developtech.efuelfo.util.SPUtils;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class MyActionBar extends AppCompatActivity {

  public View rootLayout;
  public AppComponent appComponent;
  protected SharedPreferences sp;
  protected Context ctx;
  protected String email;
  protected FrameLayout progress_bar;
  protected FrameLayout lytNoRecords;
  protected int screen_width, screen_height;
  protected Toolbar toolbar;
  protected TextView tvHeadTitle;
  protected ImageView ivDrawer, ivBack, ivFilter, ivNotification;
  RestClient restClient;
  boolean onStop = false;
  LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    appComponent = ((MyApplication) getApplicationContext()).getAppComponent();
    DisplayMetrics displaymetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    screen_height = displaymetrics.heightPixels;
    screen_width = displaymetrics.widthPixels;
    ctx = this;
  }

  protected void setMenuItem() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(null);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings: {
        newIntent(SettingActivity.class, null, false);
        break;
      }
      case R.id.action_logout: {

        LogoutDialog logoutDialog = new LogoutDialog();
        logoutDialog.show(getSupportFragmentManager(), "logout");
        logoutDialog.setData(appComponent, LogoutDialog.DIALOG_TYPE.LOGOUT, logoutListener, this);
        break;
      }
      case R.id.action_contact_us : {
        newIntent(ContactUsActivity.class, null, false);
      }
    }
    return false;
  }

  protected void init() {
    progress_bar = (FrameLayout) findViewById(R.id.progress_bar);
    if (progress_bar != null) {
      progress_bar.setVisibility(View.GONE);
    }
    lytNoRecords = findViewById(R.id.lytNoRecords);
    if (lytNoRecords!=null)
    {
      lytNoRecords.setVisibility(View.GONE);
    }
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      tvHeadTitle = findViewById(R.id.tvHeadTitle);
      ivDrawer = findViewById(R.id.ivDrawer);
      ivBack = findViewById(R.id.ivBack);
      ivFilter = findViewById(R.id.ivFilter);
      ivNotification = findViewById(R.id.ivNotification);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          finish();
        }
      });
      ivBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          hideKB();
          onBackPressed();
        }
      });
//            setToolBarFont();
    }
    rootLayout = findViewById(R.id.rootlayout);
  }

  public void setHomeTitle(String title) {
    if (tvHeadTitle==null)
      return;

    tvHeadTitle.setText(title.toUpperCase());
  }

  public void setHomeImage(boolean flag) {
    if (flag) {
      ivBack.setVisibility(View.VISIBLE);
      ivDrawer.setVisibility(View.INVISIBLE);
    } else {
      ivBack.setVisibility(View.GONE);
      ivDrawer.setVisibility(View.VISIBLE);
    }
  }

  public void hideHomeImage()
  {
    ivBack.setVisibility(View.GONE);
    ivDrawer.setVisibility(View.GONE);
  }

  public void showFilter(boolean isVisible) {
    if (isVisible) {
      ivFilter.setVisibility(View.VISIBLE);
    } else {
      ivFilter.setVisibility(View.GONE);
    }
  }

  public void showNotification(boolean isVisible) {
    if (isVisible) {
      ivNotification.setVisibility(View.VISIBLE);
    } else {
      ivNotification.setVisibility(View.GONE);
    }
  }

  protected void setToolBarFont() {
    try {
      Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
      f.setAccessible(true);
      TextView titleTextView = (TextView) f.get(toolbar);
      titleTextView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + getString(R.string.font_light)));
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

  }

  public void showProgress() {
    if (progress_bar != null) {
      progress_bar.setVisibility(View.VISIBLE);
    }
  }

  public void hideProgress() {
    if (progress_bar != null) {
      progress_bar.setVisibility(View.GONE);
    }
  }

  protected void showNoRecords()
  {
    if (lytNoRecords!=null)
    {
      lytNoRecords.setVisibility(View.VISIBLE);
    }
  }

  protected void hideNoRecords()
  {
    if (lytNoRecords!=null)
    {
      lytNoRecords.setVisibility(View.GONE);
    }
  }

  protected void clearSP() {
    sp.edit().clear().commit();
  }

  abstract public void initComponents();

  public void back() {
    super.onBackPressed();
  }

  @Override
  public void onBackPressed() {
    back();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    onStop = true;

  }

  public void newIntent(Class<?> c, Bundle bundle, boolean finish) {
    Intent i = new Intent(MyActionBar.this, c);
    if (bundle != null) {
      i.putExtra("bundle",bundle);
      i.putExtras(bundle);
    }
    startActivity(i);
    if (finish) {
      finish();
    }
  }

  public void newIntentClear(Class<?> c, Bundle bundle, boolean finish) {
    Intent i = new Intent(MyActionBar.this, c);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    if (bundle != null) {
      i.putExtras(bundle);
    }
    startActivity(i);
    if (finish) {
      finish();
    }
  }

  public void showMsg(View v, String msg) {
    try {
      if (v == null) {
        showMsg(msg);
        return;
      }
      Snackbar s = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
      View view = s.getView();
      view.setBackgroundColor(getResources().getColor(R.color.bg_color));
      TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
      tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + getResources().getString(R.string.font_light)));
      tv.setTextColor(Color.WHITE);
      s.show();
    } catch (Exception e) {
      Log.e("excep_myAction", e + "");
    }
  }

  protected void showMsg(String msg) {
    Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
    t.show();
  }

  public void noInternetMsg(View v) {
    String msg = "No internet connection.";
    if (v == null) {
      showMsg(msg);
      return;
    }
    final Snackbar s = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
    View view = s.getView();
    view.setBackgroundColor(getResources().getColor(R.color.bg_color));
//        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + getResources().getString(R.string.font_light)));
//        tv.setTextColor(Color.WHITE);
//        s.setAction("Retry", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retry();
//                s.dismiss();
//            }
//        });
//        tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_action);
//        tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + getResources().getString(R.string.font_bold)));
//        tv.setTextColor(Color.RED);
    s.show();
  }

  public abstract void retry();

  protected void technicalError() {
    String msg = "Unable to process your request.\n Check you internet connection.";
    Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
    t.show();
  }

  public void printName() {
    System.out.println("adPankajj");
  }

  public void hideKB(View v) {
    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    mgr.hideSoftInputFromWindow(v.getWindowToken(), 0);
  }

  public void hideKB() {
    if (getCurrentFocus() != null && getCurrentFocus() instanceof EditText) {
      InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      mgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
  }

  public void showKey() {
    InputMethodManager imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);
    if (imm != null) {
      imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
  }

  public boolean isGPSEnabled() {
    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
  }

  public boolean isInternetOn() {
    NetworkInfo localNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    if (localNetworkInfo != null) {
      return localNetworkInfo.isConnected();
    }
    return false;
  }

  public void showAlertExpireDialog() {
    LogoutDialog logoutDialog = new LogoutDialog();
    logoutDialog.show(getSupportFragmentManager(), "logout");
    logoutDialog.setData(appComponent, LogoutDialog.DIALOG_TYPE.TOKEN_EXPIRE, logoutListener, this);
  }

  public void showLogoutDialog() {
    LogoutDialog logoutDialog = new LogoutDialog();
    logoutDialog.show(getSupportFragmentManager(), "logout");
    logoutDialog.setData(appComponent, LogoutDialog.DIALOG_TYPE.LOGOUT, logoutListener, this);
  }

  public String getFormatedDate(String date)
  {
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

  public String formattedToUTC(String date)
  {
    String convertedTime="";
    final SimpleDateFormat sdfUTC = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    convertedTime = sdfUTC.format(date);
    return convertedTime;
  }

  public String getParsedDate(String strDate)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = null;
    try {
      date = dateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    formatter.setTimeZone(TimeZone.getTimeZone("IST"));
    String strFormatted =  formatter.format(date);
    return strFormatted;
  }

  public String getParsedDate3(String strDate)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = null;
    try {
      date = dateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DateFormat formatter = new SimpleDateFormat("HH:mm a");
    formatter.setTimeZone(TimeZone.getTimeZone("IST"));
    String strFormatted =  formatter.format(date);
    return strFormatted;
  }

  public String getParsedTime(String strDate)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = null;
    try {
      date = dateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DateFormat formatter = new SimpleDateFormat("HH:mm");
    formatter.setTimeZone(TimeZone.getTimeZone("IST"));
    return formatter.format(date);
  }

  public String getParsedDate2(String strDate)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = null;
    try {
      date = dateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
    formatter.setTimeZone(TimeZone.getTimeZone("IST"));
    return formatter.format(date);
  }

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

  public String get24HourTime(String time)
  {
    String convertedTime = "";
    try {
      final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
      final Date dateObj = sdf.parse(time);
      SimpleDateFormat parsedFormat = new SimpleDateFormat("HH:mm");
      convertedTime = parsedFormat.format(dateObj);
    } catch (final ParseException e) {
      e.printStackTrace();
    }
    return convertedTime;
  }

  public String toUTC(Date date)
  {
    String convertedTime="";
    final SimpleDateFormat sdfUTC = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    convertedTime = sdfUTC.format(date);
    return convertedTime;
  }

  public String toLocal(String strDate)
  {
    String convertedTime="";

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = null;
    try {
      date = dateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    final SimpleDateFormat sdfLocal = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    String timeZone = Calendar.getInstance().getTimeZone().getID();
    sdfLocal.setTimeZone(TimeZone.getTimeZone(timeZone));
    convertedTime = sdfLocal.format(date);
    return convertedTime;
  }

  public void showAccDeletedDialog(String msg, final String action)
  {
    final Dialog dialog = new Dialog(MyActionBar.this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_general);
    dialog.setCancelable(false);
    TextView tvTitle = dialog.findViewById(R.id.tvTitle);
    TextView tvDesc = dialog.findViewById(R.id.tvDesc);
    Button btnYes = dialog.findViewById(R.id.btnYes);
    Button btnNo = dialog.findViewById(R.id.btnNo);
    View viewLine = dialog.findViewById(R.id.viewLine);
    btnNo.setVisibility(View.GONE);
    tvTitle.setVisibility(View.GONE);
    viewLine.setVisibility(View.GONE);

    tvDesc.setText(msg);

    btnYes.setText(getResources().getString(R.string.ok));

    btnYes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (action.equalsIgnoreCase("MANAGER"))
        {
          appComponent.getSpUtils().saveManagerChanged(false);
          Bundle bundle = new Bundle();
          bundle.putString("type", "MANAGER");
          newIntentClear(HomeActivity.class, bundle, true);
        }
        else {
          appComponent.getSpUtils().clearUserData();
          appComponent.getSpUtils().saveIsRegistered(false);
          appComponent.getSpUtils().saveIsDeleted(false);
          newIntentClear(SelectAccountActivity.class, null, true);
        }
      }
    });

    dialog.show();
  }

  @Override
  protected void onStart() {
    super.onStart();

    IntentFilter fitler = new IntentFilter();
    fitler.addAction("LOGOUT_MSG");
    fitler.addAction("SESSION_EXPIRED");
    fitler.addAction("NO_INTERNET");
    fitler.addAction("DELETED");
    fitler.addAction("MANAGER");
    localBroadcastManager.registerReceiver(listener, fitler);
  }

  @Override
  protected void onStop() {

    localBroadcastManager.unregisterReceiver(listener);

    super.onStop();

  }

  @SuppressLint("HardwareIds")
  public String getDeviceId(){
    return Settings.Secure.getString(getContentResolver(),
            Settings.Secure.ANDROID_ID);
  }

  BroadcastReceiver listener = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent.getAction()!=null) {
        switch (intent.getAction()) {
          case "LOGOUT_MSG":
            showLogoutDialog();
            break;
          case "NO_INTERNET":
            showMsg(rootLayout, getResources().getString(R.string.internet_not_available));
            break;
          case "DELETED": {
            if (intent.getExtras() != null && intent.getExtras().getString("msg") != null) {
              showAccDeletedDialog(intent.getExtras().getString("msg"), "DELETED");
            }
            break;
          }
          case "MANAGER" : {
            if (intent.getExtras() != null && intent.getExtras().getString("msg") != null) {
              showAccDeletedDialog(intent.getExtras().getString("msg"), "MANAGER");
            }
            break;
          }
          case "SESSION_EXPIRED" : {
            showAlertExpireDialog();
          }
        }
      }
    }
  };

  ProgressDialog dialog;

  NetworkListener logoutListener = new NetworkListener() {
    @Override
    public void onSuccess(ResultModel<?> responseBody) {
      if (responseBody.getResultCode().equalsIgnoreCase(SPUtils.API_CODES.OK.toString())) {
        appComponent.getSpUtils().clearUserData();
        appComponent.getSpUtils().saveIsRegistered(false);
        appComponent.getSpUtils().saveIsDeleted(false);
        newIntentClear(SelectAccountActivity.class, null, true);
      }
      else
      {
        showMsg(getResources().getString(R.string.something_wrong));
      }
    }

    @Override
    public void onError(String msg) {
      showMsg(getResources().getString(R.string.something_wrong));
    }

    @Override
    public void onComplete() {
      dialog.dismiss();
    }

    @Override
    public void onStart() {
      dialog = ProgressDialog.show(MyActionBar.this, "",
              "Loging out...", true);
    }
  };
}
