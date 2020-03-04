// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleDriver;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DriverHomeFragment_ViewBinding implements Unbinder {
  private DriverHomeFragment target;

  @UiThread
  public DriverHomeFragment_ViewBinding(DriverHomeFragment target, View source) {
    this.target = target;

    target.recycleItems = Utils.findRequiredViewAsType(source, R.id.recycleItems, "field 'recycleItems'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DriverHomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleItems = null;
  }
}
