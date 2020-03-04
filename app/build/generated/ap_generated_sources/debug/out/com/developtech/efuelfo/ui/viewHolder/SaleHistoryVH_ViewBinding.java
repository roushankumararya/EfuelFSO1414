// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SaleHistoryVH_ViewBinding implements Unbinder {
  private SaleHistoryVH target;

  @UiThread
  public SaleHistoryVH_ViewBinding(SaleHistoryVH target, View source) {
    this.target = target;

    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvVehicleNumber = Utils.findRequiredViewAsType(source, R.id.tvVehicleNumber, "field 'tvVehicleNumber'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tvStatus, "field 'tvStatus'", TextView.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmt, "field 'tvAmount'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tvDate, "field 'tvDate'", TextView.class);
    target.cbCheck = Utils.findRequiredViewAsType(source, R.id.cbCheck, "field 'cbCheck'", CheckBox.class);
    target.ellipeseStatus = Utils.findRequiredView(source, R.id.ellipseStatus, "field 'ellipeseStatus'");
  }

  @Override
  @CallSuper
  public void unbind() {
    SaleHistoryVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvName = null;
    target.tvVehicleNumber = null;
    target.tvStatus = null;
    target.tvAmount = null;
    target.tvDate = null;
    target.cbCheck = null;
    target.ellipeseStatus = null;
  }
}
