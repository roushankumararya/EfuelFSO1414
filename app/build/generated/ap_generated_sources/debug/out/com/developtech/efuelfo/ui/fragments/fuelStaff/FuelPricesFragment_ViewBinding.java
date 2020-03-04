// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelStaff;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelPricesFragment_ViewBinding implements Unbinder {
  private FuelPricesFragment target;

  @UiThread
  public FuelPricesFragment_ViewBinding(FuelPricesFragment target, View source) {
    this.target = target;

    target.rvFuelPrices = Utils.findRequiredViewAsType(source, R.id.rvFuelPrices, "field 'rvFuelPrices'", RecyclerView.class);
    target.tvStationName = Utils.findRequiredViewAsType(source, R.id.tvStationName, "field 'tvStationName'", TextView.class);
    target.txtErrorMsg = Utils.findRequiredViewAsType(source, R.id.txtErrorMsg, "field 'txtErrorMsg'", TextView.class);
    target.tvStationAddress = Utils.findRequiredViewAsType(source, R.id.tvStationAddress, "field 'tvStationAddress'", TextView.class);
    target.ivStation = Utils.findRequiredViewAsType(source, R.id.ivStation, "field 'ivStation'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelPricesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvFuelPrices = null;
    target.tvStationName = null;
    target.txtErrorMsg = null;
    target.tvStationAddress = null;
    target.ivStation = null;
  }
}
