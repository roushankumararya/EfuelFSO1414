// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DriverProfileDialog_ViewBinding implements Unbinder {
  private DriverProfileDialog target;

  @UiThread
  public DriverProfileDialog_ViewBinding(DriverProfileDialog target, View source) {
    this.target = target;

    target.tvMobile = Utils.findRequiredViewAsType(source, R.id.tvMobile, "field 'tvMobile'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.ivImg, "field 'ivImg'", ImageView.class);
    target.tvEmail = Utils.findRequiredViewAsType(source, R.id.tvEmail, "field 'tvEmail'", TextView.class);
    target.tvVehicleNumber = Utils.findRequiredViewAsType(source, R.id.tvVehicleNumber, "field 'tvVehicleNumber'", TextView.class);
    target.tvApprovedLimit = Utils.findRequiredViewAsType(source, R.id.tvLimit, "field 'tvApprovedLimit'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DriverProfileDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvMobile = null;
    target.ivImg = null;
    target.tvEmail = null;
    target.tvVehicleNumber = null;
    target.tvApprovedLimit = null;
    target.tvName = null;
  }
}
