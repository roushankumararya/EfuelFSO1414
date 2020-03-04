// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CashTransactionFragment_ViewBinding implements Unbinder {
  private CashTransactionFragment target;

  private View view2131361898;

  @UiThread
  public CashTransactionFragment_ViewBinding(final CashTransactionFragment target, View source) {
    this.target = target;

    View view;
    target.etVehicleNumber = Utils.findRequiredViewAsType(source, R.id.etVehicleNumber, "field 'etVehicleNumber'", EditText.class);
    target.etMobileNumber = Utils.findRequiredViewAsType(source, R.id.etMobileNumber, "field 'etMobileNumber'", CustomEditText.class);
    target.spinnerCode = Utils.findRequiredViewAsType(source, R.id.spinnerCode, "field 'spinnerCode'", Spinner.class);
    target.spinnerFuelType = Utils.findRequiredViewAsType(source, R.id.spinnerFuelType, "field 'spinnerFuelType'", Spinner.class);
    target.etQuantity = Utils.findRequiredViewAsType(source, R.id.etQuantity, "field 'etQuantity'", EditText.class);
    target.etAmount = Utils.findRequiredViewAsType(source, R.id.etAmount, "field 'etAmount'", EditText.class);
    target.tvTotalAmount = Utils.findRequiredViewAsType(source, R.id.tvTotalAmount, "field 'tvTotalAmount'", TextView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.btnSave, "field 'btnSave' and method 'onClick'");
    target.btnSave = Utils.castView(view, R.id.btnSave, "field 'btnSave'", Button.class);
    view2131361898 = view;
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
    CashTransactionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etVehicleNumber = null;
    target.etMobileNumber = null;
    target.spinnerCode = null;
    target.spinnerFuelType = null;
    target.etQuantity = null;
    target.etAmount = null;
    target.tvTotalAmount = null;
    target.rootLayout = null;
    target.btnSave = null;

    view2131361898.setOnClickListener(null);
    view2131361898 = null;
  }
}
