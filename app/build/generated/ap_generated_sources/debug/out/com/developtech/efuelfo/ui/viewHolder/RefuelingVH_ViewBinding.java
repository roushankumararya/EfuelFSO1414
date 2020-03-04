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

public class RefuelingVH_ViewBinding implements Unbinder {
  private RefuelingVH target;

  @UiThread
  public RefuelingVH_ViewBinding(RefuelingVH target, View source) {
    this.target = target;

    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", TextView.class);
    target.tvCost = Utils.findRequiredViewAsType(source, R.id.tvCost, "field 'tvCost'", TextView.class);
    target.tvVolume = Utils.findRequiredViewAsType(source, R.id.tvVolume, "field 'tvVolume'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RefuelingVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFuelType = null;
    target.tvCost = null;
    target.tvVolume = null;
  }
}
