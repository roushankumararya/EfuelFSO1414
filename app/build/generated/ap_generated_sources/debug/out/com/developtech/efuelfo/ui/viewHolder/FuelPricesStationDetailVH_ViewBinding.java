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

public class FuelPricesStationDetailVH_ViewBinding implements Unbinder {
  private FuelPricesStationDetailVH target;

  @UiThread
  public FuelPricesStationDetailVH_ViewBinding(FuelPricesStationDetailVH target, View source) {
    this.target = target;

    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", TextView.class);
    target.tvFuelPrice = Utils.findRequiredViewAsType(source, R.id.tvFuelPrice, "field 'tvFuelPrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelPricesStationDetailVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFuelType = null;
    target.tvFuelPrice = null;
  }
}
