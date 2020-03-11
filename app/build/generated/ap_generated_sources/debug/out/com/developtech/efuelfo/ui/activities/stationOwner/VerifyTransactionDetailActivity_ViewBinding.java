// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.stationOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VerifyTransactionDetailActivity_ViewBinding implements Unbinder {
  private VerifyTransactionDetailActivity target;

  private View view2131361886;

  private View view2131362202;

  @UiThread
  public VerifyTransactionDetailActivity_ViewBinding(VerifyTransactionDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VerifyTransactionDetailActivity_ViewBinding(final VerifyTransactionDetailActivity target,
      View source) {
    this.target = target;

    View view;
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootlayout, "field 'rootLayout'", CoordinatorLayout.class);
    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", TextView.class);
    target.tvQty = Utils.findRequiredViewAsType(source, R.id.tvQty, "field 'tvQty'", TextView.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    target.tvTransId = Utils.findRequiredViewAsType(source, R.id.tvTransId, "field 'tvTransId'", TextView.class);
    target.tvOwnerName = Utils.findRequiredViewAsType(source, R.id.tvOwnerName, "field 'tvOwnerName'", TextView.class);
    target.tvDriverName = Utils.findRequiredViewAsType(source, R.id.tvDriverName, "field 'tvDriverName'", TextView.class);
    target.tvDateTime = Utils.findRequiredViewAsType(source, R.id.tvDateTime, "field 'tvDateTime'", TextView.class);
    target.tvPaymentStatus = Utils.findRequiredViewAsType(source, R.id.tvPaymentStatus, "field 'tvPaymentStatus'", TextView.class);
    target.etDispenseCode = Utils.findRequiredViewAsType(source, R.id.etDispenseCode, "field 'etDispenseCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnDispense, "field 'btnDispense' and method 'onClick'");
    target.btnDispense = Utils.castView(view, R.id.btnDispense, "field 'btnDispense'", Button.class);
    view2131361886 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lytCode, "field 'lytCode' and method 'onClick'");
    target.lytCode = Utils.castView(view, R.id.lytCode, "field 'lytCode'", LinearLayout.class);
    view2131362202 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lytDriver = Utils.findRequiredViewAsType(source, R.id.lytDriver, "field 'lytDriver'", LinearLayout.class);
    target.tvVehicleNumber = Utils.findRequiredViewAsType(source, R.id.tvVehicleNumber, "field 'tvVehicleNumber'", TextView.class);
    target.tv3 = Utils.findRequiredViewAsType(source, R.id.tv3, "field 'tv3'", TextView.class);
    target.tv2 = Utils.findRequiredViewAsType(source, R.id.tv2, "field 'tv2'", TextView.class);
    target.tv1 = Utils.findRequiredViewAsType(source, R.id.tv1, "field 'tv1'", TextView.class);
    target.tv = Utils.findRequiredViewAsType(source, R.id.tv, "field 'tv'", TextView.class);
    target.view = Utils.findRequiredView(source, R.id.view, "field 'view'");
    target.view1 = Utils.findRequiredView(source, R.id.view1, "field 'view1'");
    target.view2 = Utils.findRequiredView(source, R.id.view2, "field 'view2'");
    target.view3 = Utils.findRequiredView(source, R.id.view3, "field 'view3'");
  }

  @Override
  @CallSuper
  public void unbind() {
    VerifyTransactionDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rootLayout = null;
    target.tvFuelType = null;
    target.tvQty = null;
    target.tvAmount = null;
    target.tvTransId = null;
    target.tvOwnerName = null;
    target.tvDriverName = null;
    target.tvDateTime = null;
    target.tvPaymentStatus = null;
    target.etDispenseCode = null;
    target.btnDispense = null;
    target.lytCode = null;
    target.lytDriver = null;
    target.tvVehicleNumber = null;
    target.tv3 = null;
    target.tv2 = null;
    target.tv1 = null;
    target.tv = null;
    target.view = null;
    target.view1 = null;
    target.view2 = null;
    target.view3 = null;

    view2131361886.setOnClickListener(null);
    view2131361886 = null;
    view2131362202.setOnClickListener(null);
    view2131362202 = null;
  }
}
