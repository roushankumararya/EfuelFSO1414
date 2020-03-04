// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RefuelFragment_ViewBinding implements Unbinder {
  private RefuelFragment target;

  private View view2131361895;

  @UiThread
  public RefuelFragment_ViewBinding(final RefuelFragment target, View source) {
    this.target = target;

    View view;
    target.spinnerVehicle = Utils.findRequiredViewAsType(source, R.id.spinnerVehicle, "field 'spinnerVehicle'", Spinner.class);
    target.spinnerStationId = Utils.findRequiredViewAsType(source, R.id.spinnerStationId, "field 'spinnerStationId'", Spinner.class);
    target.spinnerFuelType = Utils.findRequiredViewAsType(source, R.id.spinnerFuelType, "field 'spinnerFuelType'", Spinner.class);
    target.etQuantity = Utils.findRequiredViewAsType(source, R.id.etQuantity, "field 'etQuantity'", EditText.class);
    target.tvUnits = Utils.findRequiredViewAsType(source, R.id.tvUnits, "field 'tvUnits'", TextView.class);
    target.etTotalAmount = Utils.findRequiredViewAsType(source, R.id.etTotalAmount, "field 'etTotalAmount'", EditText.class);
    target.tvAmountCurrency = Utils.findRequiredViewAsType(source, R.id.tvAmountCurrency, "field 'tvAmountCurrency'", TextView.class);
    target.etMileage = Utils.findRequiredViewAsType(source, R.id.etMileage, "field 'etMileage'", EditText.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnPay, "field 'btnPay' and method 'onClick'");
    target.btnPay = Utils.castView(view, R.id.btnPay, "field 'btnPay'", Button.class);
    view2131361895 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.spinnerImg = Utils.findRequiredViewAsType(source, R.id.spinnerImg, "field 'spinnerImg'", Spinner.class);
    target.lytStatoinSpinner = Utils.findRequiredViewAsType(source, R.id.lytStationSpinner, "field 'lytStatoinSpinner'", LinearLayout.class);
    target.switchSelfDriven = Utils.findRequiredViewAsType(source, R.id.switchSelfDriven, "field 'switchSelfDriven'", Switch.class);
    target.etStationId = Utils.findRequiredViewAsType(source, R.id.etStationId, "field 'etStationId'", CustomEditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RefuelFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spinnerVehicle = null;
    target.spinnerStationId = null;
    target.spinnerFuelType = null;
    target.etQuantity = null;
    target.tvUnits = null;
    target.etTotalAmount = null;
    target.tvAmountCurrency = null;
    target.etMileage = null;
    target.tvAmount = null;
    target.btnPay = null;
    target.spinnerImg = null;
    target.lytStatoinSpinner = null;
    target.switchSelfDriven = null;
    target.etStationId = null;

    view2131361895.setOnClickListener(null);
    view2131361895 = null;
  }
}
