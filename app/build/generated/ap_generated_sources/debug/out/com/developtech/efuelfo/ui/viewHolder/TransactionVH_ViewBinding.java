// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TransactionVH_ViewBinding implements Unbinder {
  private TransactionVH target;

  @UiThread
  public TransactionVH_ViewBinding(TransactionVH target, View source) {
    this.target = target;

    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvVehicleNumber = Utils.findRequiredViewAsType(source, R.id.tvVehicleNumber, "field 'tvVehicleNumber'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tvStatus, "field 'tvStatus'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tvDate, "field 'tvDate'", TextView.class);
    target.tvAmt = Utils.findRequiredViewAsType(source, R.id.tvAmt, "field 'tvAmt'", TextView.class);
    target.cbCheck = Utils.findRequiredViewAsType(source, R.id.cbCheck, "field 'cbCheck'", CheckBox.class);
    target.imgReceived = Utils.findRequiredViewAsType(source, R.id.imgReceived, "field 'imgReceived'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TransactionVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvName = null;
    target.tvVehicleNumber = null;
    target.tvStatus = null;
    target.tvDate = null;
    target.tvAmt = null;
    target.cbCheck = null;
    target.imgReceived = null;
  }
}
