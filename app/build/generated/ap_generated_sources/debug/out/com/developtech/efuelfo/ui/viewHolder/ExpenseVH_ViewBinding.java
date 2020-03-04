// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpenseVH_ViewBinding implements Unbinder {
  private ExpenseVH target;

  @UiThread
  public ExpenseVH_ViewBinding(ExpenseVH target, View source) {
    this.target = target;

    target.tvVehicleName = Utils.findRequiredViewAsType(source, R.id.tvVehicleName, "field 'tvVehicleName'", TextView.class);
    target.tvOdometer = Utils.findRequiredViewAsType(source, R.id.tvOdometer, "field 'tvOdometer'", TextView.class);
    target.tvTotal = Utils.findRequiredViewAsType(source, R.id.tvTotal, "field 'tvTotal'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tvDate, "field 'tvDate'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tvTime, "field 'tvTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpenseVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvVehicleName = null;
    target.tvOdometer = null;
    target.tvTotal = null;
    target.tvDate = null;
    target.tvTime = null;
  }
}
