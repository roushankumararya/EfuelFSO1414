// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddFuelStationDialog_ViewBinding implements Unbinder {
  private AddFuelStationDialog target;

  private View view2131362045;

  private View view2131362015;

  private View view2131361871;

  private View view2131361901;

  private View view2131362202;

  @UiThread
  public AddFuelStationDialog_ViewBinding(final AddFuelStationDialog target, View source) {
    this.target = target;

    View view;
    target.cbIsAvailable = Utils.findRequiredViewAsType(source, R.id.cbIsAvailable, "field 'cbIsAvailable'", CheckBox.class);
    target.etStationName = Utils.findRequiredViewAsType(source, R.id.etStationName, "field 'etStationName'", EditText.class);
    target.etDealerCode = Utils.findRequiredViewAsType(source, R.id.etDealerCode, "field 'etDealerCode'", EditText.class);
    target.etStationId = Utils.findRequiredViewAsType(source, R.id.etStationId, "field 'etStationId'", EditText.class);
    view = Utils.findRequiredView(source, R.id.etStartTime, "field 'etStartTime' and method 'onClick'");
    target.etStartTime = Utils.castView(view, R.id.etStartTime, "field 'etStartTime'", EditText.class);
    view2131362045 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.etEndTime, "field 'etEndTime' and method 'onClick'");
    target.etEndTime = Utils.castView(view, R.id.etEndTime, "field 'etEndTime'", EditText.class);
    view2131362015 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.spinnerCode = Utils.findRequiredViewAsType(source, R.id.spinnerCode, "field 'spinnerCode'", Spinner.class);
    target.etMobileNumber = Utils.findRequiredViewAsType(source, R.id.etMobileNumber, "field 'etMobileNumber'", EditText.class);
    target.spinnerFuelCompany = Utils.findRequiredViewAsType(source, R.id.spinnerFuelCompany, "field 'spinnerFuelCompany'", Spinner.class);
    target.etAccountName = Utils.findRequiredViewAsType(source, R.id.etAccountName, "field 'etAccountName'", EditText.class);
    target.etAccountNumber = Utils.findRequiredViewAsType(source, R.id.etAccountNumber, "field 'etAccountNumber'", EditText.class);
    target.etIfsc = Utils.findRequiredViewAsType(source, R.id.etIfsc, "field 'etIfsc'", EditText.class);
    target.etLandline = Utils.findRequiredViewAsType(source, R.id.etLandline, "field 'etLandline'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnAddFuelStation, "field 'btnAddFuelStation' and method 'onClick'");
    target.btnAddFuelStation = Utils.castView(view, R.id.btnAddFuelStation, "field 'btnAddFuelStation'", Button.class);
    view2131361871 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rootlayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootlayout'", LinearLayout.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tvAddress, "field 'tvAddress'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnSendQr, "field 'btnSendQr' and method 'onClick'");
    target.btnSendQr = Utils.castView(view, R.id.btnSendQr, "field 'btnSendQr'", Button.class);
    view2131361901 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lytFuelStationId = Utils.findRequiredViewAsType(source, R.id.lytFuelStatinId, "field 'lytFuelStationId'", LinearLayout.class);
    target.lnrQR = Utils.findRequiredViewAsType(source, R.id.lnrQR, "field 'lnrQR'", LinearLayout.class);
    target.imgQR = Utils.findRequiredViewAsType(source, R.id.imgQR, "field 'imgQR'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.lytAddress, "method 'onClick'");
    view2131362202 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddFuelStationDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cbIsAvailable = null;
    target.etStationName = null;
    target.etDealerCode = null;
    target.etStationId = null;
    target.etStartTime = null;
    target.etEndTime = null;
    target.spinnerCode = null;
    target.etMobileNumber = null;
    target.spinnerFuelCompany = null;
    target.etAccountName = null;
    target.etAccountNumber = null;
    target.etIfsc = null;
    target.etLandline = null;
    target.btnAddFuelStation = null;
    target.rootlayout = null;
    target.tvAddress = null;
    target.btnSendQr = null;
    target.lytFuelStationId = null;
    target.lnrQR = null;
    target.imgQR = null;

    view2131362045.setOnClickListener(null);
    view2131362045 = null;
    view2131362015.setOnClickListener(null);
    view2131362015 = null;
    view2131361871.setOnClickListener(null);
    view2131361871 = null;
    view2131361901.setOnClickListener(null);
    view2131361901 = null;
    view2131362202.setOnClickListener(null);
    view2131362202 = null;
  }
}
