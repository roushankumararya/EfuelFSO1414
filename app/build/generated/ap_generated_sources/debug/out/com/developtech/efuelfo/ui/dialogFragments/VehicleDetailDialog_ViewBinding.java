// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VehicleDetailDialog_ViewBinding implements Unbinder {
  private VehicleDetailDialog target;

  @UiThread
  public VehicleDetailDialog_ViewBinding(VehicleDetailDialog target, View source) {
    this.target = target;

    target.tvVehicleMake = Utils.findRequiredViewAsType(source, R.id.tvVehicleMake, "field 'tvVehicleMake'", TextView.class);
    target.tvVehicleModel = Utils.findRequiredViewAsType(source, R.id.tvVehicleModel, "field 'tvVehicleModel'", TextView.class);
    target.tvVehicleNumber = Utils.findRequiredViewAsType(source, R.id.tvVehicleNumber, "field 'tvVehicleNumber'", TextView.class);
    target.tvVehicleColor = Utils.findRequiredViewAsType(source, R.id.tvVehicleColor, "field 'tvVehicleColor'", TextView.class);
    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", TextView.class);
    target.tvfuelCapacity = Utils.findRequiredViewAsType(source, R.id.tvfuelCapacity, "field 'tvfuelCapacity'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VehicleDetailDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvVehicleMake = null;
    target.tvVehicleModel = null;
    target.tvVehicleNumber = null;
    target.tvVehicleColor = null;
    target.tvFuelType = null;
    target.tvfuelCapacity = null;
    target.tvName = null;
  }
}
