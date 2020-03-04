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

public class VehicleModelsVH_ViewBinding implements Unbinder {
  private VehicleModelsVH target;

  @UiThread
  public VehicleModelsVH_ViewBinding(VehicleModelsVH target, View source) {
    this.target = target;

    target.tvModel = Utils.findRequiredViewAsType(source, R.id.tvModel, "field 'tvModel'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VehicleModelsVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvModel = null;
  }
}
