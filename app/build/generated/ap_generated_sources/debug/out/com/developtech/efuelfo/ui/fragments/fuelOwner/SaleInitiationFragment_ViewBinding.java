// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SaleInitiationFragment_ViewBinding implements Unbinder {
  private SaleInitiationFragment target;

  private View view2131361900;

  private View view2131361894;

  private View view2131362158;

  @UiThread
  public SaleInitiationFragment_ViewBinding(final SaleInitiationFragment target, View source) {
    this.target = target;

    View view;
    target.etVehicleNumber = Utils.findRequiredViewAsType(source, R.id.etVehicleNumber, "field 'etVehicleNumber'", CustomEditText.class);
    target.spinnerFuelType = Utils.findRequiredViewAsType(source, R.id.spinnerFuelType, "field 'spinnerFuelType'", Spinner.class);
    target.etQuantity = Utils.findRequiredViewAsType(source, R.id.etQuantity, "field 'etQuantity'", CustomEditText.class);
    target.etAmount = Utils.findRequiredViewAsType(source, R.id.etAmount, "field 'etAmount'", CustomEditText.class);
    target.etMileage = Utils.findRequiredViewAsType(source, R.id.etMileage, "field 'etMileage'", CustomEditText.class);
    target.tvTotalAmount = Utils.findRequiredViewAsType(source, R.id.tvTotalAmount, "field 'tvTotalAmount'", CustomTextView.class);
    target.tvDriverNameMobile = Utils.findRequiredViewAsType(source, R.id.tvDriverNameMobile, "field 'tvDriverNameMobile'", CustomTextView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.btnSend, "field 'btnSend' and method 'onClick'");
    target.btnSend = Utils.castView(view, R.id.btnSend, "field 'btnSend'", Button.class);
    view2131361900 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnPark, "field 'btnPark' and method 'onClick'");
    target.btnPark = Utils.castView(view, R.id.btnPark, "field 'btnPark'", Button.class);
    view2131361894 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivSearch, "method 'onClick'");
    view2131362158 = view;
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
    SaleInitiationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etVehicleNumber = null;
    target.spinnerFuelType = null;
    target.etQuantity = null;
    target.etAmount = null;
    target.etMileage = null;
    target.tvTotalAmount = null;
    target.tvDriverNameMobile = null;
    target.rootLayout = null;
    target.btnSend = null;
    target.btnPark = null;

    view2131361900.setOnClickListener(null);
    view2131361900 = null;
    view2131361894.setOnClickListener(null);
    view2131361894 = null;
    view2131362158.setOnClickListener(null);
    view2131362158 = null;
  }
}
