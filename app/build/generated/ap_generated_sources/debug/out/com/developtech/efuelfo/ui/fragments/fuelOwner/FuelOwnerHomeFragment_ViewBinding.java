// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelOwnerHomeFragment_ViewBinding implements Unbinder {
  private FuelOwnerHomeFragment target;

  @UiThread
  public FuelOwnerHomeFragment_ViewBinding(FuelOwnerHomeFragment target, View source) {
    this.target = target;

    target.recycleItems = Utils.findRequiredViewAsType(source, R.id.recycleItems, "field 'recycleItems'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelOwnerHomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleItems = null;
  }
}
