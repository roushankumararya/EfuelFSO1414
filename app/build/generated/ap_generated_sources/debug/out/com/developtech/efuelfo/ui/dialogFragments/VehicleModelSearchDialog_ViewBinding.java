// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VehicleModelSearchDialog_ViewBinding implements Unbinder {
  private VehicleModelSearchDialog target;

  @UiThread
  public VehicleModelSearchDialog_ViewBinding(VehicleModelSearchDialog target, View source) {
    this.target = target;

    target.etModelSearch = Utils.findRequiredViewAsType(source, R.id.etModelSearch, "field 'etModelSearch'", EditText.class);
    target.rvVehicleModels = Utils.findRequiredViewAsType(source, R.id.rvVehicleModels, "field 'rvVehicleModels'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VehicleModelSearchDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etModelSearch = null;
    target.rvVehicleModels = null;
  }
}
