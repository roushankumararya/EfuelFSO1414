// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TotalSaleFuelTypeVH_ViewBinding implements Unbinder {
  private TotalSaleFuelTypeVH target;

  @UiThread
  public TotalSaleFuelTypeVH_ViewBinding(TotalSaleFuelTypeVH target, View source) {
    this.target = target;

    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", CustomTextView.class);
    target.tvTotalSale = Utils.findRequiredViewAsType(source, R.id.tvTotalSale, "field 'tvTotalSale'", CustomTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TotalSaleFuelTypeVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFuelType = null;
    target.tvTotalSale = null;
  }
}
