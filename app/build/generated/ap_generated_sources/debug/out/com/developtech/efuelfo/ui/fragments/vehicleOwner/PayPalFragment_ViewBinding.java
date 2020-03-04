// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayPalFragment_ViewBinding implements Unbinder {
  private PayPalFragment target;

  private View view2131361895;

  @UiThread
  public PayPalFragment_ViewBinding(final PayPalFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnPay, "field 'btnPay' and method 'onClick'");
    target.btnPay = Utils.castView(view, R.id.btnPay, "field 'btnPay'", Button.class);
    view2131361895 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etVehicleNumber = Utils.findRequiredViewAsType(source, R.id.etVehicleNumber, "field 'etVehicleNumber'", EditText.class);
    target.etMobileNumber = Utils.findRequiredViewAsType(source, R.id.etMobileNumber, "field 'etMobileNumber'", EditText.class);
    target.spinnerStationId = Utils.findRequiredViewAsType(source, R.id.spinnerStationId, "field 'spinnerStationId'", Spinner.class);
    target.spinnerFuelType = Utils.findRequiredViewAsType(source, R.id.spinnerFuelType, "field 'spinnerFuelType'", Spinner.class);
    target.etQuantity = Utils.findRequiredViewAsType(source, R.id.etQuantity, "field 'etQuantity'", EditText.class);
    target.tvUnits = Utils.findRequiredViewAsType(source, R.id.tvUnits, "field 'tvUnits'", TextView.class);
    target.etTotalAmount = Utils.findRequiredViewAsType(source, R.id.etTotalAmount, "field 'etTotalAmount'", EditText.class);
    target.tvAmountCurrency = Utils.findRequiredViewAsType(source, R.id.tvAmountCurrency, "field 'tvAmountCurrency'", TextView.class);
    target.etMileage = Utils.findRequiredViewAsType(source, R.id.etMileage, "field 'etMileage'", EditText.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    target.spinnerCode = Utils.findRequiredViewAsType(source, R.id.spinnerCode, "field 'spinnerCode'", Spinner.class);
    target.spinnerImg = Utils.findRequiredViewAsType(source, R.id.spinnerImg, "field 'spinnerImg'", Spinner.class);
    target.lytStatoinSpinner = Utils.findRequiredViewAsType(source, R.id.lytStationSpinner, "field 'lytStatoinSpinner'", LinearLayout.class);
    target.etStationId = Utils.findRequiredViewAsType(source, R.id.etStationId, "field 'etStationId'", CustomEditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PayPalFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnPay = null;
    target.etVehicleNumber = null;
    target.etMobileNumber = null;
    target.spinnerStationId = null;
    target.spinnerFuelType = null;
    target.etQuantity = null;
    target.tvUnits = null;
    target.etTotalAmount = null;
    target.tvAmountCurrency = null;
    target.etMileage = null;
    target.tvAmount = null;
    target.spinnerCode = null;
    target.spinnerImg = null;
    target.lytStatoinSpinner = null;
    target.etStationId = null;

    view2131361895.setOnClickListener(null);
    view2131361895 = null;
  }
}
